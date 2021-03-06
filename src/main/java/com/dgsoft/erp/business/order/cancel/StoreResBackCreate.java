package com.dgsoft.erp.business.order.cancel;

import com.dgsoft.common.system.NumberBuilder;
import com.dgsoft.erp.action.*;
import com.dgsoft.erp.business.order.BackItemCreate;
import com.dgsoft.erp.model.*;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.security.Credentials;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by cooper on 2/23/14.
 */
@Name("storeResBackCreate")
@Scope(ScopeType.CONVERSATION)
public class StoreResBackCreate extends OrderBackHome {

    @In
    private Credentials credentials;

    @In(create = true)
    private OrderBackHome orderBackHome;

    @Override
    @DataModel("orderBackItems")
    public List<BackItem> getBackItems() {
        return backItems;
    }

    @In(required = false)
    private OrderHome orderHome;

    @In(create = true)
    private BackItemCreate backItemCreate;

    @In(create = true)
    private StoreResHome storeResHome;

    @In
    private FacesMessages facesMessages;

    @In(create = true)
    private CustomerHome customerHome;

    @In(required = false)
    private CustomerAreaHome customerAreaHome;

    @DataModelSelection
    private BackItem selectBackItem;

    @In(create = true)
    private ResBackDispatch resBackDispatch;

    @In
    private NumberBuilder numberBuilder;

    @Override
    protected OrderBack createInstance() {
        return new OrderBack(false,false,credentials.getUsername());
    }

    @Override
    public void create(){
        super.create();

        getInstance().setId("T" + numberBuilder.getSampleNumber("storeResBack"));
        if ((orderHome != null) && orderHome.isIdDefined()){
            getInstance();
            for (OrderItem orderItem: orderHome.getOrderItemByStatus(EnumSet.of(OrderItem.OrderItemStatus.COMPLETED, OrderItem.OrderItemStatus.WAIT_PRICE))){
                getBackItems().add(new BackItem(getInstance(),orderItem));
            }
        }
    }

    @Override
    public Class<OrderBack> getEntityClass() {
        return OrderBack.class;
    }

    public void deleteItem() {
        getBackItems().remove(selectBackItem);
    }

    public void addNewBackItem() {
        BackItem item =  backItemCreate.getEditingItem();

        storeResHome.setRes(item.getRes(), item.getFormats(), item.getFloatConvertRate());
        if (storeResHome.isIdDefined()) {
            item.setStoreRes(storeResHome.getInstance());
            item.setOrderBack(getInstance());
            item.setBackItemStatus(BackItem.BackItemStatus.CREATE);
            item.calcMoney();

            backItems.add(item);
            backItemCreate.createNext();
            calcBackMoney();
        } else {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "orderStoreResNotExists");
        }
    }

    public String dispatchBack() {
        //if (!isCanCreate()) return null;
        resBackDispatch.init(getInstance());
        return "/business/startPrepare/erp/sale/BackStoreResDispatch.xhtml";
    }

    public String createItems(){

        if (customerHome.isIdDefined()) {
            customerHome.refresh();
            getInstance().setCustomer(customerHome.getInstance());
        } else {
            getInstance().setCustomer(customerHome.getReadyInstance());
            getInstance().getCustomer().setCustomerArea(customerAreaHome.getInstance());
        }

        return "/business/startPrepare/erp/sale/ResBackItemCreate.xhtml";
    }

    public String toConfirm(){
        getInstance().getBackItems().clear();
        getInstance().getBackItems().addAll(backItems);
        return  "/business/startPrepare/erp/sale/ResBackConfirm.xhtml";
    }

    public String dispatchToConfirm(){
        getInstance().setDispatched(true);
        if (!resBackDispatch.isComplete()) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "dispatchNotComplete");
            return null;
        }


        getInstance().getBackDispatchs().clear();

        getInstance().getBackDispatchs().addAll(resBackDispatch.getResBackDispatcheds());
        for (BackItem item : getInstance().getBackItems()) {
            item.setBackItemStatus(BackItem.BackItemStatus.DISPATCH);
        }
        return toConfirm();
    }

    public String confirmtoPrevious(){
        getInstance().setDispatched(false);
        return "/business/startPrepare/erp/sale/ResBackItemCreate.xhtml";
    }

    @CreateProcess(definition = "orderCancel", processKey = "#{storeResBackCreate.instance.id}")
    @Transactional
    public String createBack() {

        getInstance().setResComplete(false);
        if (getInstance().getMoney().compareTo(BigDecimal.ZERO) == 0){
            getInstance().setMoneyComplete(true);
        }else{
            getInstance().setMoneyComplete(false);
        }

        orderBackHome.setInstance(getInstance());
        if (!"persisted".equals(persist())) {
            return null;
        }
        return "businessCreated";

    }

//    private boolean isCanCreate() {
//        if (backItems.isEmpty()){
//            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR,"noneBackItemAdd");
//            return false;
//        }
//        if (getInstance().getMoney().compareTo(BigDecimal.ZERO) < 0){
//            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR,"backMoneyCantLessZero");
//            return false;
//        }
//        return true;
//    }


}
