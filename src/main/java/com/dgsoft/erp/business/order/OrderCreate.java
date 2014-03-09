package com.dgsoft.erp.business.order;

import com.dgsoft.common.exception.ProcessCreatePrepareException;
import com.dgsoft.common.DataFormat;
import com.dgsoft.common.system.DictionaryWord;
import com.dgsoft.common.system.RunParam;
import com.dgsoft.common.system.action.BusinessDefineHome;
import com.dgsoft.common.system.business.StartData;
import com.dgsoft.common.system.model.BusinessDefine;
import com.dgsoft.erp.ErpEntityHome;
import com.dgsoft.erp.action.*;
import com.dgsoft.erp.model.*;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.security.Credentials;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 10/24/13
 * Time: 11:05 AM
 */
@Name("orderCreate")
public class OrderCreate extends ErpEntityHome<CustomerOrder> {

    private static final String ORDER_SEND_REASON_WORD_KEY = "erp.needResReason.order";

    @In(create = true)
    private CustomerHome customerHome;

    @In(create = true)
    private MiddleManHome middleManHome;

    @In(required = false)
    private CustomerAreaHome customerAreaHome;

    @In(create = true)
    private StartData startData;

    @In(create = true)
    private BusinessDefineHome businessDefineHome;

    @In(create = true)
    private StoreResHome storeResHome;

    // @In(create = true)
//    protected StoreResFormatFilter storeResFormatFilter;

    @In(create = true)
    protected ResHome resHome;

    @In(create = true)
    private OrderDispatch orderDispatch;

    @In(create = true)
    private NeedResHome needResHome;

    @In(create = true)
    private Map<String, String> messages;

    @In
    private RunParam runParam;

    @In
    private ResHelper resHelper;

    @In
    private DictionaryWord dictionary;

    @In
    private FacesMessages facesMessages;

    @In
    private Credentials credentials;

    @In(create = true)
    protected OrderHome orderHome;

    private NeedRes needRes;

    @DataModel("orderNeedItems")
    private List<OrderItem> orderNeedItems;

    @DataModelSelection
    private OrderItem orderNeedItem;

    private OrderItem editingItem;

    public List<OrderItem> getOrderNeedItems() {
        return orderNeedItems;
    }

    public OrderItem getEditingItem() {
        return editingItem;
    }

    public void setEditingItem(OrderItem editingItem) {
        this.editingItem = editingItem;
    }

    public NeedRes getNeedRes() {
        return needRes;
    }

    public void setNeedRes(NeedRes needRes) {
        this.needRes = needRes;
    }

    public void clearCustomerAndMiddleMan() {
        customerHome.clearInstance();
        customerHome.setHaveMiddleMan(false);
        middleManHome.clearInstance();
    }

    private BigDecimal earnestScale = BigDecimal.ZERO;

    public BigDecimal getEarnestScale() {
        return earnestScale;
    }

    public void setEarnestScale(BigDecimal earnestScale) {
        this.earnestScale = earnestScale;
    }

    public void calcEarnest() {
        getInstance().setEarnest(DataFormat.halfUpCurrency(getInstance().getMoney()
                .multiply(earnestScale.divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP))));
    }

    public void calcEarnestScale() {
        if (getInstance().getMoney().compareTo(BigDecimal.ZERO) == 1) {
            earnestScale = getInstance().getEarnest()
                    .divide(getInstance().getMoney(), 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));
        } else {
            earnestScale = BigDecimal.ZERO;
        }
    }


    public void calcOrderPrice() {

        getInstance().setMoney(DataFormat.halfUpCurrency(getOrderTotalPrice().multiply(getInstance().getTotalRebate().
                divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP))));
        calcEarnest();

    }

    public void calcOrderTotalRebate() {
        if (getOrderTotalPrice().compareTo(BigDecimal.ZERO) == 1) {
            getInstance().setTotalRebate(getInstance().getMoney()
                    .divide(getOrderTotalPrice(), 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100")));
        } else {
            getInstance().setTotalRebate(BigDecimal.ZERO);
        }
    }

    public void orderTelChanged() {
        if ((!DataFormat.isEmpty(getInstance().getTel())) && (DataFormat.isEmpty(getInstance().getContact()))) {
            for (CustomerContact contact : customerHome.getInstance().getCustomerContacts()) {
                if ((contact.getTel() != null) && contact.getTel().equals(getInstance().getTel())) {
                    getInstance().setContact(contact.getName());
                    break;
                }
            }
        }
        orderContactInfoChanged();
    }

    public void orderContactChanged() {
        if ((DataFormat.isEmpty(getInstance().getTel())) && (!DataFormat.isEmpty(getInstance().getContact()))) {
            for (CustomerContact contact : customerHome.getInstance().getCustomerContacts()) {
                if ((contact.getName() != null) && contact.getName().equals(getInstance().getContact())) {
                    getInstance().setTel(contact.getTel());
                    break;
                }
            }
        }
        orderContactInfoChanged();
    }

    private void orderContactInfoChanged() {
        if ((!DataFormat.isEmpty(getInstance().getTel())) && (!DataFormat.isEmpty(getInstance().getContact())))
            if ((DataFormat.isEmpty(getNeedRes().getReceiveTel())) && (DataFormat.isEmpty(getNeedRes().getReceivePerson()))) {
                getNeedRes().setReceiveTel(getInstance().getTel());
                getNeedRes().setReceivePerson(getInstance().getContact());
            }
    }

    public void orderReceivceTelChanged() {
        if ((!DataFormat.isEmpty(getNeedRes().getReceiveTel())) && (DataFormat.isEmpty(getNeedRes().getReceivePerson()))) {
            for (CustomerContact contact : customerHome.getInstance().getCustomerContacts()) {
                if ((contact.getTel() != null) && contact.getTel().equals(getNeedRes().getReceiveTel())) {
                    getNeedRes().setReceivePerson(contact.getName());
                    break;
                }
            }
        }
        orderReceiveInfoChanged();
    }

    private void orderReceiveInfoChanged() {
        if ((!DataFormat.isEmpty(getNeedRes().getReceiveTel())) && (!DataFormat.isEmpty(getNeedRes().getReceivePerson())))
            if ((DataFormat.isEmpty(getInstance().getTel())) && (DataFormat.isEmpty(getInstance().getContact()))) {
                getInstance().setTel(getNeedRes().getReceiveTel());
                getInstance().setContact(getNeedRes().getReceivePerson());
            }
    }

    public void orderReceivceContactChanged() {
        if ((!DataFormat.isEmpty(getNeedRes().getReceivePerson())) && (DataFormat.isEmpty(getNeedRes().getReceiveTel()))) {
            for (CustomerContact contact : customerHome.getInstance().getCustomerContacts()) {
                if ((contact.getName() != null) && contact.getName().equals(getNeedRes().getReceivePerson())) {
                    getNeedRes().setReceiveTel(contact.getTel());
                    break;
                }
            }
        }
        orderReceiveInfoChanged();
    }

    public String getToastMessages() {
        StringBuffer result = new StringBuffer();
        result.append(messages.get("OrderCode") + ":" + startData.getBusinessKey() + "\n");

        result.append(messages.get("Customer") + ":" + customerHome.getReadyInstance().getName());
        result.append("\n");
        result.append(messages.get("order_field_reveiveContact") + ":" + needRes.getReceivePerson());
        result.append(" ");
        result.append(messages.get("order_field_reveiveTel") + " " + needRes.getReceiveTel());
        result.append("\n");
        result.append(messages.get("address") + ":" + needRes.getAddress());
        result.append("\n");

        for (OrderItem item : orderNeedItems) {

            result.append("\t" + resHelper.generateStoreResTitle(item.getStoreRes()) + ": ");
            result.append(item.getDisplayMasterCount());
            result.append("(" + item.getDisplayAuxCount() + ")\n");

        }

        return result.toString();
    }

    @Observer(value = "erp.resLocateSelected", create = false)
    public void generateSaleItemByRes(Res res) {
        editingItem = new OrderItem(res, resHelper.getFormatHistory(res),
                resHelper.getFloatConvertRateHistory(res), res.getResUnitByOutDefault());
        editingItem.setNeedRes(needRes);

    }

    @Observer(value = "erp.storeResLocateSelected", create = false)
    public void generateSaleItemByStoreRes(StoreRes storeRes) {
        editingItem = new OrderItem(storeRes, resHelper.getFormatHistory(storeRes.getRes()),
                resHelper.getFloatConvertRateHistory(storeRes.getRes()), storeRes.getRes().getResUnitByOutDefault());
        editingItem.setNeedRes(needRes);
    }

    public int getItemsCount() {
        return orderNeedItems.size();
    }

    public BigDecimal getOrderTotalPrice() {
        BigDecimal result = new BigDecimal("0");
        for (OrderItem item : orderNeedItems) {
            result = result.add(item.getTotalPrice());
        }
        return result;
    }

    public void removeItem() {
        orderNeedItems.remove(orderNeedItem);
    }

    public void addOrderItem() {
        if (editingItem == null) {
            throw new IllegalArgumentException("editingItem state error");
        }

        if (editingItem.getMasterCount().equals(BigDecimal.ZERO)) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "orderItemZeroError");
            return;
        }


        storeResHome.setRes(editingItem.getRes(), editingItem.getFormats(), editingItem.getFloatConvertRate());
        if (storeResHome.isIdDefined()) {
            editingItem.setStoreRes(storeResHome.getInstance());
        } else {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "orderStoreResNotExists");
            return;
        }


        for (OrderItem orderNeedItem : orderNeedItems) {

            if (orderNeedItem.isSameItem(editingItem)) {
                orderNeedItem.add(editingItem);
                editingItem = null;
                facesMessages.addFromResourceBundle(StatusMessage.Severity.INFO, "storeResInOrderItemMerger");
                break;
            }
        }
        if (editingItem != null) {
            orderNeedItems.add(editingItem);
        }
        editingItem = null;
        resHome.clearInstance();
        storeResHome.clearInstance();
        calcOrderPrice();
    }

    public void clearCustomer() {
        customerHome.clearInstance();
        middleManHome.clearInstance();
    }

    public String saveOrderCustomer() {
        return "/business/startPrepare/erp/sale/CreateSaleOrderItem.xhtml";
    }

    private boolean dispatched = false;

    public String saveOrderItem() {

        if (verifyItem()) {

            orderDispatch.init(orderNeedItems);
            dispatched = true;
            return "/business/startPrepare/erp/sale/CreateSaleOrderDispatch.xhtml";

        }

        return "fail";
    }


    public String beginCreateOrder() {
        businessDefineHome.setId("erp.business.order");
        startData.generateKey();
        needRes = new NeedRes(getInstance(),
                NeedRes.NeedResType.ORDER_SEND, ORDER_SEND_REASON_WORD_KEY, new Date(), NeedRes.NeedResStatus.CREATED);
        orderNeedItems = new ArrayList<OrderItem>();
        //toDO read from customer level

        getInstance().setTotalRebate(new BigDecimal("100"));
        getInstance().setMoney(BigDecimal.ZERO);
        //getInstance().setTotalMoney(BigDecimal.ZERO);
        getInstance().setEarnest(BigDecimal.ZERO);
        return "beginning";
    }

    public String createCloneOrder() {
        if (orderHome.isIdDefined()) {

            businessDefineHome.setId("erp.business.order");
            startData.generateKey();
            needRes = new NeedRes(getInstance(),
                    NeedRes.NeedResType.ORDER_SEND, ORDER_SEND_REASON_WORD_KEY, new Date(), NeedRes.NeedResStatus.CREATED);


            orderNeedItems = new ArrayList<OrderItem>();

            for (OrderItem orderItem : orderHome.getMasterNeedRes().getOrderItems()) {

                orderNeedItems.add(orderItem);
            }

            getInstance().setPayType(orderHome.getInstance().getPayType());
            //customerAreaHome.setId(orderHome.getInstance().getCustomer().getCustomerArea().getId());
            customerHome.setId(orderHome.getInstance().getCustomer().getId());
            getInstance().setContact(orderHome.getInstance().getContact());
            getInstance().setTel(orderHome.getInstance().getTel());
            needRes.setFareByCustomer(orderHome.getMasterNeedRes().isFareByCustomer());
            needRes.setPostCode(orderHome.getMasterNeedRes().getPostCode());
            needRes.setAddress(orderHome.getMasterNeedRes().getAddress());
            getInstance().setIncludeMiddleMan(orderHome.getInstance().isIncludeMiddleMan());
            if (getInstance().isIncludeMiddleMan()) {
                middleManHome.setId(orderHome.getInstance().getCustomer().getMiddleMan().getId());
            }

            getInstance().setTotalRebate(orderHome.getInstance().getTotalRebate());
            getInstance().setEarnestFirst(orderHome.getInstance().isEarnestFirst());
            getInstance().setEarnest(orderHome.getInstance().getEarnest());
            getInstance().setMoney(orderHome.getInstance().getMoney());
            //getInstance().setTotalMoney(orderHome.getInstance().getTotalMoney());
            calcEarnestScale();


        } else {
            return beginCreateOrder();
        }

        return "beginning";
    }

    @Factory("orderPayTypes")
    public CustomerOrder.OrderPayType[] getOrderPayTypes() {
        return CustomerOrder.OrderPayType.values();
    }


    //private boolean orderWired = false;

    protected boolean wireOrder() {

        //TODO cost calc for  BOM table
        getInstance().setTotalCost(new BigDecimal(0));
        getInstance().setMoneyComplete(false);

        //getInstance().setCreateDate(new Date());
        getInstance().setOrderEmp(credentials.getUsername());
        getInstance().setId(startData.getBusinessKey());
        if (getInstance().getPayType().equals(CustomerOrder.OrderPayType.PAY_FIRST)) {
            getInstance().setEarnestFirst(false);
        }
        if (customerHome.isIdDefined()) {
            customerHome.refresh();
            getInstance().setCustomer(customerHome.getInstance());
        } else {

            getInstance().setCustomer(customerHome.getReadyInstance());
            getInstance().getCustomer().setCustomerArea(customerAreaHome.getInstance());

        }

        if (getInstance().isIncludeMiddleMan()) {
            if (getInstance().getCustomer().getMiddleMan() == null) {
                facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "orderIncludeMiddleManError");
                return false;
            }
        }

        needRes.getOrderItems().clear();
        needRes.getOrderItems().addAll(orderNeedItems);

        getInstance().getNeedReses().clear();
        getInstance().getNeedReses().add(needRes);

        return true;
    }

    public void customerChangeListener() {
        if (customerHome.isIdDefined()) {
            if (runParam.getStringParamValue("erp.sale.receiveAddress").trim().equals("CITY")) {
                needRes.setAddress(dictionary.getCityName(customerHome.getInstance().getProvinceCode()));
            } else
                needRes.setAddress(customerHome.getInstance().getAddress());
            needRes.setPostCode(customerHome.getInstance().getPostCode());
        }
    }

    private boolean verifyItem() {

        if (orderNeedItems.isEmpty()) {
            getStatusMessages().addFromResourceBundle(StatusMessage.Severity.ERROR, "createOrderItemIsEmptyError");
            editingItem = null;
            resHome.clearInstance();
            storeResHome.clearInstance();
            return false;
        }

        if (getInstance().isEarnestFirst() && (getInstance().getEarnest().compareTo(BigDecimal.ZERO) <= 0)) {
            getStatusMessages().addFromResourceBundle(StatusMessage.Severity.ERROR, "createOrderEarnestIsZero");
            editingItem = null;
            resHome.clearInstance();
            storeResHome.clearInstance();
            return false;
        }
        return true;
    }


    @Observer("com.dgsoft.BusinessCreatePrepare.order")
    @Transactional
    public void createOrder(BusinessDefine businessDefine) {


        if (!verifyItem()) {
            throw new ProcessCreatePrepareException("order item verify fail");
        }


        if (!wireOrder()) {
            throw new ProcessCreatePrepareException("create order wire fail");
        }

        if (dispatched) {

            if (!orderDispatch.isDispatchComplete()) {
                facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "dispatch_not_complete");
                throw new ProcessCreatePrepareException("dispatch not complete");
            }

            if (getInstance().getPayType().equals(CustomerOrder.OrderPayType.EXPRESS_PROXY)) {
                boolean allCustomerSelf = true;
                for (Dispatch dispatch : orderDispatch.getDispatchList(needRes)) {
                    if (!dispatch.getDeliveryType().equals(Dispatch.DeliveryType.CUSTOMER_SELF)) {
                        allCustomerSelf = false;
                        break;
                    }
                }
                if (allCustomerSelf) {
                    facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "canotAllCustomerSelf");
                    throw new ProcessCreatePrepareException("dispatch error");
                }
            }
            needRes.getDispatches().addAll(orderDispatch.getDispatchList(needRes));

            needRes.setStatus(NeedRes.NeedResStatus.DISPATCHED);
        }

        if (!"persisted".equals(persist())) {
            editingItem = null;
            resHome.clearInstance();
            storeResHome.clearInstance();


            throw new ProcessCreatePrepareException("create order persist fail");
        }

        startData.setDescription(getInstance().getCustomer().getName());
        orderHome.setId(getInstance().getId());
        needResHome.setId(getInstance().getNeedResList().get(0).getId());
    }

}
