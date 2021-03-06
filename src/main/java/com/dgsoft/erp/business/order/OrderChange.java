package com.dgsoft.erp.business.order;

import com.dgsoft.erp.action.NeedResHome;
import com.dgsoft.erp.business.finance.AccountDateHelper;
import com.dgsoft.erp.model.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.log.Logging;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 2/4/14
 * Time: 2:36 PM
 */
@Name("orderChange")
public class OrderChange extends OrderShipTaskHandle {

    private static final String SUPPLEMENT_REASON = "erp.needResReason.supplement";

    @In(create = true)
    private OrderDispatch orderDispatch;

    @In(create = true)
    private NeedResHome needResHome;


    private Date moneyOperDate;

    public Date getMoneyOperDate() {
        return moneyOperDate;
    }

    public void setMoneyOperDate(Date moneyOperDate) {
        this.moneyOperDate = moneyOperDate;
    }

//    @In(create = true)
//    private OrderReSenderCreate orderReSenderCreate;

//    @DataModel(value = "newOrderItems")
//    private List<OrderItem> newOrderItems;
//
//    @DataModelSelection
//    private OrderItem selectOrderItem;

    private List<OrderItem> overlyItems;

    private List<OrderItem> oweItems;

    public String beginDispatch() {
        orderDispatch.init(needResHome.getInstance());
        dispatched = true;
        return "/business/taskOperator/erp/sale/OrderChangeDispatch.xhtml";
    }

    public String dispatchBack() {
        dispatched = false;
        return "/business/taskOperator/erp/sale/OrderChange.xhtml";
    }

    private boolean dispatched = false;

    private boolean reSend;

    public boolean isReSend() {
        return reSend;
    }

    public void setReSend(boolean reSend) {
        this.reSend = reSend;
    }

    public BigDecimal getOverlysTotalMoney() {
        BigDecimal result = BigDecimal.ZERO;
        for (OrderItem item : overlyItems) {
            if (item.getTotalMoney() != null)
                result = result.add(item.getTotalMoney());
        }
        return result;
    }

    public BigDecimal getOverlyTotalNeedMoney() {
        BigDecimal result = BigDecimal.ZERO;
        for (OrderItem item : overlyItems) {
            if (item.getNeedMoney() != null)
                result = result.add(item.getNeedMoney());
        }
        return result;
    }

    public List<OrderItem> getCompleteOrderItems() {
        return orderHome.getOrderItemByStatus(EnumSet.of(OrderItem.OrderItemStatus.COMPLETED));
    }

    public List<OrderItem> getOverlyItems() {
        return overlyItems;
    }

    private void initReSend() {

        reSend = !oweItems.isEmpty();

        if (reSend) {
            needResHome.clearInstance();
            needResHome.getInstance().setStatus(NeedRes.NeedResStatus.CREATED);
            needResHome.getInstance().setCustomerOrder(orderHome.getInstance());
            needResHome.getInstance().setType(NeedRes.NeedResType.SUPPLEMENT_SEND);
            needResHome.getInstance().setReason(SUPPLEMENT_REASON);
            needResHome.getInstance().setFareByCustomer(orderHome.getLastNeedRes().isFareByCustomer());
            needResHome.getInstance().setPostCode(orderHome.getLastNeedRes().getPostCode());
            needResHome.getInstance().setAddress(orderHome.getLastNeedRes().getAddress());
            needResHome.getInstance().setReceivePerson(orderHome.getLastNeedRes().getReceivePerson());
            needResHome.getInstance().setReceiveTel(orderHome.getLastNeedRes().getReceiveTel());
            needResHome.getInstance().setCreateDate(new Date());

            needResHome.getOrderNeedItems().addAll(oweItems);

            for (OrderItem orderItem : oweItems) {
                orderItem.setNeedRes(needResHome.getInstance());
            }
            dispatched = false;
        }

        reSend = false;

        reSendChangeListener();


    }


    public void reSendChangeListener() {
        if (reSend) {
            orderHome.getInstance().getNeedReses().add(needResHome.getInstance());
        } else {
            orderHome.getInstance().getNeedReses().remove(needResHome.getInstance());
        }
        orderHome.calcMoneys();
    }

    private void reCreateOrderRebate() {
        //for ()
    }

    @Override
    protected void initOrderTask() {
        Logging.getLog(getClass()).debug("orderChange init is execute");

        overlyItems = orderHome.getOrderItemByStatus(EnumSet.of(OrderItem.OrderItemStatus.WAIT_PRICE));


        oweItems = orderHome.getOrderItemByStatus(EnumSet.of(OrderItem.OrderItemStatus.CREATED));

        if (!oweItems.isEmpty())
            for (NeedRes needRes : orderHome.getInstance().getNeedReses()) {
                needRes.getOrderItems().removeAll(oweItems);
            }

        for (OweOut oweOut : orderHome.getNoAddOweItems()) {
            OrderItem matchItem = null;
            for (OrderItem orderItem : orderHome.getOrderItemByStatus(
                    EnumSet.of(OrderItem.OrderItemStatus.CREATED,
                            OrderItem.OrderItemStatus.COMPLETED, OrderItem.OrderItemStatus.DISPATCHED)
            )) {
                if (orderItem.getStoreRes().equals(oweOut.getStoreRes())) {
                    matchItem = orderItem;
                    break;
                }
            }

            OrderItem newItem = new OrderItem(oweOut.getStoreRes(), oweOut.getCount(), false,
                    OrderItem.OrderItemStatus.CREATED, false, oweOut.getDescription(), (oweOut.getNeedConvertRate() == null) ? oweOut.getStoreRes().getFloatConversionRate() : oweOut.getNeedConvertRate());

            if (matchItem != null) {
                newItem.setUseUnit(matchItem.getResUnit());
                newItem.setMoney(matchItem.getMoney());
                newItem.setRebate(matchItem.getRebate());

                newItem.calcMoney();
            }
            if (newItem.getUseUnit() == null) {
                newItem.setUseUnit(newItem.getStoreRes().getRes().getResUnitByOutDefault());
            }

            oweItems.add(newItem);
        }

        initReSend();

        moneyOperDate = orderHome.getInstance().getCreateDate();

    }

    private void addOrderItemsToRebate(Collection<OrderItem> orderItems){
        for (OrderItem item : orderItems) {
            boolean find = false;
            for (ResSaleRebate resSaleRebate : orderHome.getResSaleRebates()) {
                if (resSaleRebate.isSameItem(item)){
                    find = true;
                    resSaleRebate.setCount(resSaleRebate.getCount().add(item.getUseUnitCount()));
                    break;
                }
            }
            if (!find){
                orderHome.getResSaleRebates().add(new ResSaleRebate(orderHome.getInstance(),
                        item.getRes(),item.getUseUnit(),item.getUseUnitCount(),item.getMoney(),item.getRebate()));
            }
        }
    }

    public String toChangeOrderMoney() {

        orderHome.refreshSaleRebate();

        addOrderItemsToRebate(orderHome.getOrderItemByStatus(EnumSet.of(OrderItem.OrderItemStatus.COMPLETED)));


        addOrderItemsToRebate(overlyItems);

        if (reSend){
            addOrderItemsToRebate(needResHome.getInstance().getOrderItems());
        }

        List<ResSaleRebate> removeItems = new ArrayList<ResSaleRebate>();
        for (ResSaleRebate resSaleRebate : orderHome.getResSaleRebates()) {
            if (resSaleRebate.getCount().compareTo(BigDecimal.ZERO) == 0){
                removeItems.add(resSaleRebate);
            }else{
                resSaleRebate.calcMoney();
            }
        }

        orderHome.getResSaleRebates().removeAll(removeItems);
        orderHome.calcMoneys();

        return "/business/taskOperator/erp/sale/OrderMoneyChange.xhtml";
    }

    @Override
    protected String completeOrderTask() {
        Logging.getLog(getClass()).debug("orderChange complete is execute");

        if (!orderHome.isMoneyCanChange()){
            return null;
        }

        List<ResSaleRebate> removeItems = new ArrayList<ResSaleRebate>();
        for (ResSaleRebate resSaleRebate : orderHome.getResSaleRebates()) {
            if (resSaleRebate.getRebateMoney().compareTo(BigDecimal.ZERO) == 0){
                removeItems.add(resSaleRebate);
            }
        }
        orderHome.getResSaleRebates().removeAll(removeItems);


        if (!reSend || oweItems.isEmpty()) {
            orderHome.getInstance().setAllStoreOut(true);
            orderHome.getInstance().getNeedReses().remove(needResHome.getInstance());
            for (OrderItem item : oweItems) {
                item.setStatus(OrderItem.OrderItemStatus.CREATED);
                item.setDispatch(null);
            }
            shipComplete(orderHome.getLastShipDate());
        } else {
            if (dispatched) {
                if (!orderDispatch.isDispatchComplete()) {
                    facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "dispatchNotComplete");
                    return null;
                }
                orderDispatch.wire();
                needResHome.getInstance().setStatus(NeedRes.NeedResStatus.DISPATCHED);
            }

            orderHome.getInstance().setAllStoreOut(false);
        }


        orderHome.calcMoneys();

        for (OrderItem item : overlyItems) {
            item.setStatus(OrderItem.OrderItemStatus.COMPLETED);
        }

        for (OweOut oweOut : orderHome.getNoAddOweItems()) {
            oweOut.setAdd(true);
        }

        if ( orderHome.changeMoney(moneyOperDate) && "updated".equals(orderHome.update())) {
            return "taskComplete";
        } else {
            return null;
        }

    }

}
