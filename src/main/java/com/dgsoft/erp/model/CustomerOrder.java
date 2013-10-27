package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CustomerOrder generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER_ORDER", catalog = "MINI_ERP")
public class CustomerOrder implements java.io.Serializable {

    private String id;
    private Integer version;
    private Customer customer;
    private Store store;
    private String state;
    private String payType;
    private Date createDate;
    private BigDecimal money;
    private Date completeDate;
    private BigDecimal profit;
    private String memo;
    private boolean middleManPay;
    private Set<MiddleMoney> middleMoneys = new HashSet<MiddleMoney>(0);
    private Set<AccountOper> accountOper = new HashSet<AccountOper>(0);
    private Set<OrderList> orderLists = new HashSet<OrderList>(0);
    private Set<OrderDelivery> orderDeliveries = new HashSet<OrderDelivery>(0);
    private Set<OrderStoreOut> orderStoreOuts = new HashSet<OrderStoreOut>(0);
    private Set<OrderBack> orderBacks = new HashSet<OrderBack>(0);

    public CustomerOrder() {
    }

    public CustomerOrder(String id, Customer customer, Store store,
                         String state, String payType, Date createDate, BigDecimal money,
                         Date completeDate) {
        this.id = id;
        this.customer = customer;
        this.store = store;
        this.state = state;
        this.payType = payType;
        this.createDate = createDate;
        this.money = money;
        this.completeDate = completeDate;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID", nullable = false)
    @NotNull
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Column(name = "STATE", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "PAY_TYPE", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19, columnDefinition = "DATETIME")
    @NotNull
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETE_DATE", nullable = false, length = 19, columnDefinition = "DATETIME")
    @NotNull
    public Date getCompleteDate() {
        return this.completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    @Column(name = "PROFIT", scale = 3)
    public BigDecimal getProfit() {
        return this.profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Column(name = "MEMO", length = 200)
    @Size(max = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "MIDDLE_MAN_PAY", nullable = false)
    public boolean isMiddleManPay() {
        return this.middleManPay;
    }

    public void setMiddleManPay(boolean middleManPay) {
        this.middleManPay = middleManPay;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<MiddleMoney> getMiddleMoneys() {
        return this.middleMoneys;
    }

    public void setMiddleMoneys(Set<MiddleMoney> middleMoneys) {
        this.middleMoneys = middleMoneys;
    }


    @ManyToMany(fetch = FetchType.LAZY, targetEntity = AccountOper.class)
    @JoinTable(name = "ORDER_PAY", joinColumns = @JoinColumn(name = "CUSTOMER_ORDER"), inverseJoinColumns = @JoinColumn(name = "ACCOUNT_OPER"))
    public Set<AccountOper> getAccountOper() {
        return this.accountOper;
    }

    public void setAccountOper(Set<AccountOper> orderPays) {
        this.accountOper = orderPays;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<OrderList> getOrderLists() {
        return this.orderLists;
    }

    public void setOrderLists(Set<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<OrderDelivery> getOrderDeliveries() {
        return this.orderDeliveries;
    }

    public void setOrderDeliveries(Set<OrderDelivery> orderDeliveries) {
        this.orderDeliveries = orderDeliveries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<OrderStoreOut> getOrderStoreOuts() {
        return this.orderStoreOuts;
    }

    public void setOrderStoreOuts(Set<OrderStoreOut> orderStoreOuts) {
        this.orderStoreOuts = orderStoreOuts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<OrderBack> getOrderBacks() {
        return this.orderBacks;
    }

    public void setOrderBacks(Set<OrderBack> orderBacks) {
        this.orderBacks = orderBacks;
    }

}
