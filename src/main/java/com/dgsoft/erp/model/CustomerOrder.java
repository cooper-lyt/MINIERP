package com.dgsoft.erp.model;
// Generated Oct 28, 2013 12:46:39 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.TotalDataGroup;
import com.dgsoft.common.TotalDataLazyList;
import com.dgsoft.erp.model.api.StoreResCount;
import com.dgsoft.erp.model.api.StoreResPriceGroup;
import com.dgsoft.erp.total.ResPriceGroupStrategy;
import com.dgsoft.erp.total.data.OrderItemTotal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * CustomerOrder generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER_ORDER", catalog = "MINI_ERP")
public class CustomerOrder implements java.io.Serializable {

    public enum OrderPayType {
        COMPLETE_PAY, PAY_FIRST, EXPRESS_PROXY, OVERDRAFT;

    }

    public enum ProxyReceiveType{
        ANY_PROXY_RECEIVE,PROXY_MONEY,PROXY_CHECK;
    }

    private String id;
    private Integer version;
    private Customer customer;
    private String orderEmp;
    private OrderPayType payType;
    private Date createDate;
    private Date allShipDate;

    private BigDecimal profit;
    private String memo;
    private String contact;
    private String tel;


    private boolean resReceived;
    private boolean canceled;
    private boolean allStoreOut;

    private boolean payTag;

    private boolean accountChange;

    private BigDecimal earnest;
    private BigDecimal totalRebateMoney;
    private BigDecimal middleMoney;
    private BigDecimal totalCost;
    private BigDecimal middleRate;
    private BigDecimal money;
    private BigDecimal resMoney;
    private BigDecimal middleTotal;
    private BigDecimal advanceMoney;


    private boolean middlePayed;
    //private boolean moneyComplete;
    private boolean earnestFirst;
    private ProxyReceiveType proxyReceiveType;

    private RebateProgram.OrderRebateMode middleMoneyCalcType;
    private Set<MiddleMoneyPay> middleMoneyPay = new HashSet<MiddleMoneyPay>();

    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);
    private Set<NeedRes> needReses = new HashSet<NeedRes>(0);
    private Set<OrderFee> orderFees = new HashSet<OrderFee>(0);
    private Set<ResSaleRebate> resSaleRebates = new HashSet<ResSaleRebate>(0);
    private Set<OrderReduce> orderReduces = new HashSet<OrderReduce>(0);

    public CustomerOrder() {
    }

    public CustomerOrder(String orderEmp, Date createDate) {
        this.orderEmp = orderEmp;
        this.createDate = createDate;
        this.resReceived = false;
        this.canceled = false;
        allStoreOut = false;
        earnest = BigDecimal.ZERO;
        totalRebateMoney = BigDecimal.ZERO;
        middlePayed = false;
        earnestFirst = false;
        advanceMoney = BigDecimal.ZERO;
        payTag = false;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "PAY_TYPE", nullable = false, length = 32)
    @NotNull
    public OrderPayType getPayType() {
        return this.payType;
    }

    public void setPayType(OrderPayType payType) {
        this.payType = payType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19)
    @NotNull
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ALL_SHIP_DATE", nullable = true, length = 19)
    public Date getAllShipDate() {
        return allShipDate;
    }

    public void setAllShipDate(Date allShipDate) {
        this.allShipDate = allShipDate;
    }

    @Column(name = "ORDER_EMPLOYEE", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getOrderEmp() {
        return orderEmp;
    }

    public void setOrderEmp(String orderEmp) {
        this.orderEmp = orderEmp;
    }

    @Column(name = "EARNEST", scale = 3)
    public BigDecimal getEarnest() {
        return this.earnest;
    }

    public void setEarnest(BigDecimal realMoney) {
        this.earnest = realMoney;
    }

    @Transient
    public BigDecimal getTotalRebate() {
        if ((getTotalRebateMoney() != null) && (getMoney() != null)) {
            if (getMoney().compareTo(BigDecimal.ZERO) == 0) {
                return new BigDecimal("100");
            }
            BigDecimal resMoney = getMoney().add(getTotalRebateMoney());
            return getMoney().divide(resMoney, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
        }
        return null;
    }

    @Column(name = "TOTAL_REBATE", nullable = false, scale = 4)
    @NotNull
    public BigDecimal getTotalRebateMoney() {
        return totalRebateMoney;
    }

    public void setTotalRebateMoney(BigDecimal totalRebateMoney) {
        this.totalRebateMoney = totalRebateMoney;
    }

    @Column(name = "MIDDLE_MONEY", scale = 3)
    public BigDecimal getMiddleMoney() {
        return this.middleMoney;
    }

    public void setMiddleMoney(BigDecimal middleMoney) {
        this.middleMoney = middleMoney;
    }

    @Column(name = "TOTAL_COST", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Column(name = "MIDDLE_RATE", nullable = true, scale = 6)
    public BigDecimal getMiddleRate() {
        return this.middleRate;
    }

    public void setMiddleRate(BigDecimal middleRate) {
        this.middleRate = middleRate;
    }

    @Column(name = "MIDDLE_TOTAL", nullable = true, scale = 3)
    public BigDecimal getMiddleTotal() {
        return middleTotal;
    }

    public void setMiddleTotal(BigDecimal middleTotal) {
        this.middleTotal = middleTotal;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "MIDDLE_CALC_TYPE", nullable = true)
    public RebateProgram.OrderRebateMode getMiddleMoneyCalcType() {
        return middleMoneyCalcType;
    }

    public void setMiddleMoneyCalcType(RebateProgram.OrderRebateMode middleMoneyCalcType) {
        this.middleMoneyCalcType = middleMoneyCalcType;
    }

    @Column(name = "PROFIT", scale = 3)
    public BigDecimal getProfit() {
        return this.profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Column(name = "RES_MONEY", nullable = false, scale = 3)
    public BigDecimal getResMoney() {
        return resMoney;
    }

    public void setResMoney(BigDecimal resMoney) {
        this.resMoney = resMoney;
    }

    @Column(name = "MEMO", length = 200)
    @Size(max = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<NeedRes> getNeedReses() {
        return needReses;
    }

    public void setNeedReses(Set<NeedRes> needReses) {
        this.needReses = needReses;
    }

    @Transient
    public List<NeedRes> getNeedResList() {
        List<NeedRes> result = new ArrayList<NeedRes>(getNeedReses());
        Collections.sort(result, new Comparator<NeedRes>() {
            @Override
            public int compare(NeedRes o1, NeedRes o2) {
                return o1.getCreateDate().compareTo(o2.getCreateDate());
            }
        });
        return result;
    }

    @Column(name = "CONTACT", length = 50, nullable = true)
    @Size(max = 50)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "TEL", length = 50, nullable = true)
    @Size(max = 50)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Deprecated
    @Transient
    public boolean isMoneyComplete() {
        return true;
    }

    @Transient
    public BigDecimal getShortageMoney() {
        BigDecimal result = getMoney();
        if (isEarnestFirst()){
            result = result.subtract(getEarnest());
        }
        return result;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "RECEIVE_TYPE",nullable = true, length = 20)
    public ProxyReceiveType getProxyReceiveType() {
        return proxyReceiveType;
    }

    public void setProxyReceiveType(ProxyReceiveType proxyReceiveType) {
        this.proxyReceiveType = proxyReceiveType;
    }

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "customerOrder", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    public Set<MiddleMoneyPay> getMiddleMoneyPay() {
        return this.middleMoneyPay;
    }

    public void setMiddleMoneyPay(Set<MiddleMoneyPay> middleMoneys) {
        this.middleMoneyPay = middleMoneys;
    }

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customerOrder", cascade = {CascadeType.ALL})
    public Set<ResSaleRebate> getResSaleRebates() {
        return resSaleRebates;
    }

    public void setResSaleRebates(Set<ResSaleRebate> resSaleRebates) {
        this.resSaleRebates = resSaleRebates;
    }

    @Transient
    public List<ResSaleRebate> getResSaleRebateList() {
        List<ResSaleRebate> result = new ArrayList<ResSaleRebate>(getResSaleRebates());
        Collections.sort(result, new Comparator<ResSaleRebate>() {
            @Override
            public int compare(ResSaleRebate o1, ResSaleRebate o2) {
                return o1.getRes().compareTo(o2.getRes());
            }
        });
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<OrderFee> getOrderFees() {
        return orderFees;
    }

    public void setOrderFees(Set<OrderFee> orderFees) {
        this.orderFees = orderFees;
    }

    @Transient
    public List<OrderFee> getOrderFeeList() {
        List<OrderFee> result = new ArrayList<OrderFee>(getOrderFees());
        Collections.sort(result, new Comparator<OrderFee>() {
            @Override
            public int compare(OrderFee o1, OrderFee o2) {
                return o1.getApplyDate().compareTo(o2.getApplyDate());
            }
        });
        return result;
    }

    @Column(name="PAY_TAG", nullable = false)
    public boolean isPayTag() {
        return payTag;
    }

    public void setPayTag(boolean payTag) {
        this.payTag = payTag;
    }

    @Column(name = "RES_RECEIVED", nullable = false)
    public boolean isResReceived() {
        return resReceived;
    }

    public void setResReceived(boolean resReceived) {
        this.resReceived = resReceived;
    }

    @Column(name = "CANCELED", nullable = false)
    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Column(name = "MIDDLE_PAYED", nullable = false)
    public boolean isMiddlePayed() {
        return middlePayed;
    }

    public void setMiddlePayed(boolean middlePayed) {
        this.middlePayed = middlePayed;
    }

    @Column(name = "ALL_STORE_OUT", nullable = false)
    public boolean isAllStoreOut() {
        return allStoreOut;
    }

    public void setAllStoreOut(boolean allStoreOut) {
        this.allStoreOut = allStoreOut;
    }

    @Column(name = "EARNEST_FIRST", nullable = false)
    public boolean isEarnestFirst() {
        return earnestFirst;
    }

    public void setEarnestFirst(boolean earnestFirst) {
        this.earnestFirst = earnestFirst;
    }

    @Column(name = "MONEY", scale = 3, nullable = false)
    @NotNull
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Column(name = "ADVANCE_MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(BigDecimal advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true, cascade = {CascadeType.ALL}, mappedBy = "customerOrder")
    public Set<AccountOper> getAccountOpers() {
        return accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
    }

    @Transient
    public AccountOper getAccountOper(){
        if (getAccountOpers().isEmpty()){
            return null;
        }
        return getAccountOpers().iterator().next();
    }

    @Column(name = "ACCOUNT_CHANGE",nullable = false)
    public boolean isAccountChange() {
        return accountChange;
    }

    public void setAccountChange(boolean accountChange) {
        this.accountChange = accountChange;
    }

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "customerOrder",cascade = {CascadeType.ALL})
    public Set<OrderReduce> getOrderReduces() {
        return orderReduces;
    }

    public void setOrderReduces(Set<OrderReduce> orderReduces) {
        this.orderReduces = orderReduces;
    }

    @Transient
    public StoreResPriceGroup getAllOrderItemList() {
        StoreResPriceGroup result = new StoreResPriceGroup();
        for (NeedRes needRes : getNeedResList()) {
            result.putAll(needRes.getOrderItemList());
        }
        return result;
    }

    @Transient
    public List<OrderItem> getOrderItemList(){
        List<OrderItem> result = new ArrayList<OrderItem>();
        for (NeedRes needRes : getNeedResList()) {
            result.addAll(needRes.getOrderItems());
        }
        Collections.sort(result,new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                return o1.getStoreRes().compareTo(o2.getStoreRes());
            }
        });
        return result;
    }

    @Transient
    public List<OrderItem> getNoZeroItemList(){
        List<OrderItem> result = new ArrayList<OrderItem>();
        for (NeedRes needRes : getNeedResList()) {
            result.addAll(needRes.getNoZeroItemList());
        }
        Collections.sort(result,new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                return o1.getStoreRes().compareTo(o2.getStoreRes());
            }
        });
        return result;
    }

//    @Transient
//    public void calcOrderMiddleMoney() {
//        switch (getMiddleMoneyCalcType()) {
//            case NOT_CALC:
//                setMiddleRate(null);
//                setMiddleMoney(BigDecimal.ZERO);
//                break;
//            case CONSULT_FIX:
//                setMiddleRate(null);
//                break;
//            case TOTAL_MONEY_RATE:
//                setMiddleMoney(getMoney().multiply(getMiddleRate().divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP)));
//                break;
//        }
//    }

    @Transient
    public Map<StoreRes, StoreResCount> getAllShipStoreReses() {
        Map<StoreRes, StoreResCount> result = new HashMap<StoreRes, StoreResCount>();

        for (NeedRes nr : getNeedReses()) {
            for (Dispatch dispatch : nr.getDispatches()) {
                if (dispatch.isStoreOut() && (dispatch.getStockChange() != null))
                    for (StockChangeItem sci : dispatch.getStockChange().getStockChangeItems()) {
                        StoreResCount count = result.get(sci.getStoreRes());
                        if (count == null) {
                            result.put(sci.getStoreRes(), new StoreResCount(sci.getStoreRes(), sci.getMasterCount()));
                        } else {
                            count.addCount(sci);
                        }
                    }
            }
        }
        return result;
    }


    @Override
    @Transient
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }

        if (!(other instanceof CustomerOrder)) {
            return false;
        }

        CustomerOrder otherOrder = (CustomerOrder) other;

        if ((this.getId() == null) || getId().trim().isEmpty() || (otherOrder.getId() == null) || otherOrder.getId().trim().equals("")){
            return false;
        }

        if ((otherOrder.getId() != null) && (!"".equals(otherOrder.getId().trim()))) {
            return otherOrder.getId().equals(getId());
        }

        return false;
    }

    @Override
    @Transient
    public int hashCode() {
        if ((getId() != null) && !getId().trim().equals("")) {
            return getId().hashCode();
        } else {
            return super.hashCode();
        }
    }


    @Transient
    public BigDecimal getTotalResRebateMoney() {
        BigDecimal result = BigDecimal.ZERO;
        for (ResSaleRebate rebate : getResSaleRebates()) {
            if (rebate.getRebateMoney() != null)
                result = result.add(rebate.getRebateMoney());
        }
        return result;
    }

    private boolean expanded = false;

    @Transient
    public boolean isExpanded() {
        return expanded;
    }

    @Transient
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    private List<TotalDataGroup<OrderItemTotal.OrderItemResKey,OrderItem,OrderItemTotal>> itemTotalGroup;

    @Transient
    public List<TotalDataGroup<OrderItemTotal.OrderItemResKey,OrderItem,OrderItemTotal>> getItemTotalGroup(){
        if (itemTotalGroup == null){
            itemTotalGroup = new TotalDataLazyList<OrderItemTotal.OrderItemResKey,OrderItem,OrderItemTotal>(getOrderItemList(),
                    new OrderItemTotal.ResOrderItemGroupStrategy(),new OrderItemTotal.FormatOrderItemGroupStrategy());
        }
        return itemTotalGroup;

    }


}
