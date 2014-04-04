package com.dgsoft.erp.business.order;

import com.dgsoft.common.DataFormat;
import com.dgsoft.common.exception.ProcessDefineException;
import com.dgsoft.common.helper.ActionExecuteState;
import com.dgsoft.common.system.NumberBuilder;
import com.dgsoft.common.system.RunParam;
import com.dgsoft.common.system.business.TaskDescription;
import com.dgsoft.erp.action.*;
import com.dgsoft.erp.model.*;
import com.dgsoft.erp.model.api.StoreResCount;
import com.dgsoft.erp.model.api.StoreResCountEntity;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.international.StatusMessage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 11/13/13
 * Time: 4:32 PM
 */
@Name("orderStoreOut")
public class OrderStoreOut extends OrderTaskHandle {

    public final static String TASK_STORE_ID_KEY = "storeId";

    @In
    private TaskDescription taskDescription;

    @In(create = true)
    private DispatchHome dispatchHome;

    @In
    protected NumberBuilder numberBuilder;

    @In
    private ActionExecuteState actionExecuteState;

    @In
    private org.jboss.seam.security.Credentials credentials;

    @DataModel("orderStoreOutItems")
    private List<OrderStoreOutItem> orderStoreOutItems;

    private List<DispatchItem> noAssignedItems;

    @DataModelSelection
    private OrderStoreOutItem selectOutItem;

    private OrderStoreOutItem operOutItem;

    //private String storeId;

    private String memo;

    private Date storeOutDate;

    public Date getStoreOutDate() {
        return storeOutDate;
    }

    public void setStoreOutDate(Date storeOutDate) {
        this.storeOutDate = storeOutDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<DispatchItem> getNoAssignedItems() {
        return noAssignedItems;
    }

    public void setNoAssignedItems(List<DispatchItem> noAssignedItems) {
        this.noAssignedItems = noAssignedItems;
    }

    public OrderStoreOutItem getOperOutItem() {
        return operOutItem;
    }

    public void editOverlay() {
        operOutItem = selectOutItem;
        actionExecuteState.clearState();
    }

    public void saveOverlay() {
        if (operOutItem.getMasterCount().compareTo(BigDecimal.ZERO) < 0) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "sale_store_out_must_less_need", operOutItem.getDispatchItem().getDisplayMasterCount());
            operOutItem.getOverlyOut().setMasterCount(operOutItem.getDispatchItem().getMasterCount());
            return;
        }
        actionExecuteState.actionExecute();
    }


    @Override
    protected String completeOrderTask() {

        if (!noAssignedItems.isEmpty()) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR,
                    "dispatch_res_item_not_assigned", noAssignedItems.get(0).getStoreRes().getRes().getName() + " ...");
            return "resItemNotAssigned";
        }
//
//        for (DispatchItem item : dispatchHome.getInstance().getDispatchItems()) {
//            if (!item.isEnough()) {
//                facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR,
//                        "orderStockNotEnoughCantStoreOut",
//                        resHelper.generateStoreResTitle(item.getStoreRes()),
//                        item.getResCount().getMasterDisplayCount() + "(" +
//                                item.getResCount().getDisplayAuxCount() + ")",
//                        item.getStockCount().getMasterDisplayCount() + "(" +
//                                item.getStockCount().getDisplayAuxCount() + ")",
//                        item.getDisparity().getMasterDisplayCount() + "(" +
//                                item.getDisparity().getDisplayAuxCount() + ")");
//                return "storeNotEnough";
//            }
//        }

        dispatchHome.getInstance().setStockChange(
                new StockChange(orderHome.getInstance().getId() + "-" + numberBuilder.getNumber("storeInCode"), dispatchHome.getInstance().getStore(), storeOutDate,
                        credentials.getUsername(), StockChange.StoreChangeType.SELL_OUT, memo, true)
        );

        for (OrderStoreOutItem item : orderStoreOutItems) {
            //TODO noConvertRate Unit


            if (item.getCount().compareTo(BigDecimal.ZERO) > 0) {
                Stock stock = item.getStock();
                if (stock == null) {
                    stock = new Stock(dispatchHome.getInstance().getStore(), item.getStoreRes(), BigDecimal.ZERO);
                }

                StockChangeItem stockChangeItem = new StockChangeItem(dispatchHome.getInstance().getStockChange(),
                        stock, item.getMasterCount());
                dispatchHome.getInstance().getStockChange().getStockChangeItems()
                        .add(stockChangeItem);
                stock.setCount(stock.getCount().subtract(item.getMasterCount()));
            }

            if ((item.getOverlyOut() != null) && (item.getOverlyOut().getCount().compareTo(BigDecimal.ZERO) > 0)) {
                dispatchHome.getInstance().getOverlyOuts().add(item.getOverlyOut());
            }

            if (item.getStoreRes().getRes().isBatchMgr()) {
                //TODO UseSelectBatch
                //TODO StoreArea Count subtract
            }
        }


        dispatchHome.getInstance().setStoreOut(true);

        if (dispatchHome.getInstance().getDeliveryType().equals(Dispatch.DeliveryType.CUSTOMER_SELF)) {
            dispatchHome.getInstance().setSendTime(storeOutDate);
            dispatchHome.getInstance().setFare(BigDecimal.ZERO);
            dispatchHome.getInstance().getNeedRes().getCustomerOrder().setResReceived(true);
        }


        boolean allStoreOut = true;
        for (Dispatch dispatch : dispatchHome.getInstance().getNeedRes().getDispatches()) {
            if (!dispatch.isStoreOut() || dispatch.haveSubOut()) {
                allStoreOut = false;
                break;
            }
        }

        //if (allStoreOut) {
        orderHome.getInstance().setAllStoreOut(allStoreOut);
        //}

        boolean needResComplete = true;
        for (Dispatch dispatch : dispatchHome.getInstance().getNeedRes().getDispatches()) {
            if (!dispatch.isStoreOut()) {
                needResComplete = false;
                break;
            }
        }

        if (needResComplete) {
            dispatchHome.getInstance().getNeedRes().setStatus(NeedRes.NeedResStatus.OUTED);
        }


        if (dispatchHome.update().equals("updated")) {
            return "taskComplete";
        } else {
            return "updateFail";
        }
    }

    @Override
    protected void initOrderTask() {

        String storeId = taskDescription.getValue(TASK_STORE_ID_KEY);
        if (storeId == null) {
            throw new ProcessDefineException("Order Store out store ID not Define");
        }

        for (NeedRes needRes : orderHome.getInstance().getNeedReses()) {
            if (needRes.getStatus().equals(NeedRes.NeedResStatus.DISPATCHED)) {
                for (Dispatch dispatch : needRes.getDispatches()) {
                    if (dispatch.getStore().getId().equals(storeId) &&
                            !dispatch.isStoreOut()) {
                        dispatchHome.setId(dispatch.getId());
                        dispatchHome.getInstance().getOverlyOuts().clear();
                        orderStoreOutItems = new ArrayList<OrderStoreOutItem>();
                        noAssignedItems = new ArrayList<DispatchItem>();
                        for (DispatchItem dispatchItem : dispatchHome.getInstance().getDispatchItems()) {
                            //addDispatchItem(dispatchItem);
                            orderStoreOutItems.add(new OrderStoreOutItem(dispatchItem));
                        }

                    }
                }
            }
        }
    }


    @In(create = true)
    private ResHome resHome;

    @In(create = true)
    private StoreResHome storeResHome;

    @In(required = false)
    private ResCategoryHome resCategoryHome;

    @In
    private ResHelper resHelper;

    private OverlyOut newOverlyOut;

    public OverlyOut getNewOverlyOut() {
        return newOverlyOut;
    }

    public void setNewOverlyOut(OverlyOut newOverlyOut) {
        this.newOverlyOut = newOverlyOut;
    }

    @In(create = true)
    private ResLocate resLocate;

    public void locateByCode() {

        switch (resLocate.locateByCode(StockChange.StoreChangeType.SELL_OUT)) {

            case NOT_FOUND:
                break;
            case FOUND_STORERES:
                storeResSelected();
                break;
            case FOUND_RES:
                resSelected();
                break;
        }
    }

    public void resCategorySelected() {
        newOverlyOut = null;
        resHome.clearInstance();
        storeResHome.clearInstance();
    }

    public void resSelected() {
        newOverlyOut = new OverlyOut(resHome.getInstance(),
                resHelper.getFormatHistory(resHome.getInstance()),
                resHelper.getFloatConvertRateHistory(resHome.getInstance()),
                resHome.getInstance().getResUnitByInDefault(), dispatchHome.getInstance());

        resCategoryHome.setId(resHome.getInstance().getResCategory().getId());
    }

    public void storeResSelected() {
        resHome.setId(storeResHome.getInstance().getRes().getId());
        newOverlyOut = new OverlyOut(storeResHome.getInstance(),
                resHelper.getFormatHistory(resHome.getInstance()),
                resHelper.getFloatConvertRateHistory(resHome.getInstance()),
                resHome.getInstance().getResUnitByInDefault(), dispatchHome.getInstance());

        resCategoryHome.setId(storeResHome.getInstance().getRes().getResCategory().getId());
    }

    public void resChange() {
        newOverlyOut = new OverlyOut(resHome.getInstance(),
                resHelper.getFormatHistory(resHome.getInstance()),
                resHelper.getFloatConvertRateHistory(resHome.getInstance()),
                resHome.getInstance().getResUnitByInDefault(),
                dispatchHome.getInstance());
    }


    public void addNewOverlyOut() {


        if (newOverlyOut == null) {
            throw new IllegalArgumentException("editingItem state error");
        }


        storeResHome.setRes(newOverlyOut.getRes(), newOverlyOut.getFormats(), newOverlyOut.getFloatConvertRate());


        if (!storeResHome.isIdDefined()) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.INFO,
                    "storeResNotDefine");
            return;
        }

        for (OrderStoreOutItem item : orderStoreOutItems) {
            if (item.getStoreRes().equals(storeResHome.getInstance())) {
                facesMessages.addFromResourceBundle(StatusMessage.Severity.INFO,
                        " sale_store_out_item_exists");
                return;
            }
        }

        newOverlyOut.setStoreRes(storeResHome.getInstance());


        orderStoreOutItems.add(new OrderStoreOutItem(newOverlyOut));
        newOverlyOut = null;
        resCategoryHome.clearInstance();
        resHome.clearInstance();
        storeResHome.clearInstance();
    }

    public class OrderStoreOutItem extends StoreResCountEntity implements Serializable {

        private DispatchItem dispatchItem;

        private OverlyOut overlyOut;

        public boolean isHaveOverlyOut() {
            return overlyOut.getMasterCount().compareTo(BigDecimal.ZERO) > 0;
        }

        public OverlyOut getOverlyOut() {
            return overlyOut;
        }

        public DispatchItem getDispatchItem() {
            return dispatchItem;
        }

        public OrderStoreOutItem(DispatchItem dispatchItem) {
            this.dispatchItem = dispatchItem;
            overlyOut = new OverlyOut(dispatchItem.getDispatch(), dispatchItem.getStoreRes(), BigDecimal.ZERO, false);
        }

        public OrderStoreOutItem(OverlyOut overlyOut) {
            this.overlyOut = overlyOut;
        }

        public Stock getStock() {
            for (Stock stock : getStoreRes().getStocks()) {
                if (stock.getStore().getId().equals(dispatchItem.getDispatch().getStore().getId())) {
                    return stock;
                }
            }
            return null;
        }

        public boolean isEnough() {
            Stock stock = getStock();
            if (stock == null) {
                return false;
            } else
                return getStock().getCount().compareTo(getMasterCount()) >= 0;
        }


        public StoreResCount getDisparity() {
            Stock stock = getStock();
            if (stock == null) {
                return new StoreResCount(getStoreRes(), getMasterCount());
            } else
                return new StoreResCount(getStoreRes(), getMasterCount().subtract(getStock().getCount()));
        }

        @Override
        public BigDecimal getCount() {
            BigDecimal masterCount;
            if (dispatchItem != null) {
                masterCount = dispatchItem.getCount();
            } else {
                masterCount = BigDecimal.ZERO;
            }
            if (overlyOut != null) {
                if (overlyOut.isAdd()) {
                    masterCount = masterCount.add(overlyOut.getCount());
                } else {
                    masterCount = masterCount.subtract(overlyOut.getCount());
                }
            }

            return masterCount;
        }

        @Override
        public void setCount(BigDecimal count) {
            throw new IllegalArgumentException("this is readonly");
        }

        @Override
        public StoreRes getStoreRes() {
            if (dispatchItem == null) {
                return overlyOut.getStoreRes();
            } else
                return dispatchItem.getStoreRes();
        }

        @Override
        public void setStoreRes(StoreRes storeRes) {
            throw new IllegalArgumentException("this is readonly");
        }
    }


}
