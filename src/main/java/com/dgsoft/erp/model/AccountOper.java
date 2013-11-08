package com.dgsoft.erp.model;
// Generated Nov 5, 2013 1:32:07 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.PayType;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * AccountOper generated by hbm2java
 */
@Entity
@Table(name = "ACCOUNT_OPER", catalog = "MINI_ERP")
public class AccountOper implements java.io.Serializable {

    public enum AccountOperType {
        ORDER_PAY(false), PRE_DEPOSIT(true), DEPOSIT_BACK(false),
        ORDER_BACK(true), ORDER_ARREARS(false), ORDER_EARNEST(false);

        private boolean add;

        public boolean isAdd() {
            return add;
        }

        public void setAdd(boolean add) {
            this.add = add;
        }

        private AccountOperType(boolean add) {
            this.add = add;
        }
    }

    private String id;
    private Accounting accountingByCreditAccount;
    private Accounting accountingByDebitAccount;
    private Customer customer;
    private String operEmp;
    private BigDecimal operMoney;
    private AccountOperType operType;
    private Date operDate;
    private BigDecimal beforMoney;
    private BigDecimal afterMoney;
    private String description;
    private PayType payType;
    private String checkNumber;
    private Set<BackPrepareMoney> backPrepareMoneys = new HashSet<BackPrepareMoney>(
            0);
    private Set<PreparePay> preparePays = new HashSet<PreparePay>(0);
    private Set<OrderBack> orderBacks = new HashSet<OrderBack>(0);
    private CustomerOrder customerOrder;

    public AccountOper() {
    }

    public AccountOper(PayType payType) {
        this.payType = payType;
    }

    public AccountOper(Customer customer, String operEmp,
                       BigDecimal operMoney, AccountOperType operType,
                       Date operDate, BigDecimal beforMoney, BigDecimal afterMoney,
                       String description, PayType payType, CustomerOrder customerOrder) {
        this.customer = customer;
        this.operEmp = operEmp;
        this.operMoney = operMoney;
        this.operType = operType;
        this.operDate = operDate;
        this.beforMoney = beforMoney;
        this.afterMoney = afterMoney;
        this.description = description;
        this.payType = payType;
        this.customerOrder = customerOrder;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @NotNull
    @Size(max = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_ACCOUNT", nullable = false)
    @NotNull
    public Accounting getAccountingByCreditAccount() {
        return this.accountingByCreditAccount;
    }

    public void setAccountingByCreditAccount(
            Accounting accountingByCreditAccount) {
        this.accountingByCreditAccount = accountingByCreditAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEBIT_ACCOUNT", nullable = false)
    @NotNull
    public Accounting getAccountingByDebitAccount() {
        return this.accountingByDebitAccount;
    }

    public void setAccountingByDebitAccount(Accounting accountingByDebitAccount) {
        this.accountingByDebitAccount = accountingByDebitAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CUSTOM", nullable = false)
    @NotNull
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "OPER_EMP", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getOperEmp() {
        return this.operEmp;
    }

    public void setOperEmp(String operEmp) {
        this.operEmp = operEmp;
    }

    @Column(name = "OPER_MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getOperMoney() {
        return this.operMoney;
    }

    public void setOperMoney(BigDecimal operMoney) {
        this.operMoney = operMoney;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "OPER_TYPE", nullable = false, length = 32)
    @NotNull
    public AccountOperType getOperType() {
        return this.operType;
    }

    public void setOperType(AccountOperType operType) {
        this.operType = operType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPER_DATE", nullable = false, length = 19)
    @NotNull
    public Date getOperDate() {
        return this.operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    @Column(name = "BEFOR_MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getBeforMoney() {
        return this.beforMoney;
    }

    public void setBeforMoney(BigDecimal beforMoney) {
        this.beforMoney = beforMoney;
    }

    @Column(name = "AFTER_MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getAfterMoney() {
        return this.afterMoney;
    }

    public void setAfterMoney(BigDecimal afterMoney) {
        this.afterMoney = afterMoney;
    }

    @Column(name = "DESCRIPTION", length = 200)
    @Size(max = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "PAY_TYPE", nullable = false, length = 32)
    @NotNull
    public PayType getPayType() {
        return this.payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Column(name = "CHECK_NUMBER", length = 50)
    @Size(max = 50)
    public String getCheckNumber() {
        return this.checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ORDER", nullable = true)
    public CustomerOrder getCustomerOrder() {
        return this.customerOrder;
    }

    public void setCustomerOrder(CustomerOrder orderPays) {
        this.customerOrder = orderPays;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOper")
    public Set<BackPrepareMoney> getBackPrepareMoneys() {
        return this.backPrepareMoneys;
    }

    public void setBackPrepareMoneys(Set<BackPrepareMoney> backPrepareMoneys) {
        this.backPrepareMoneys = backPrepareMoneys;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOper")
    public Set<PreparePay> getPreparePays() {
        return this.preparePays;
    }

    public void setPreparePays(Set<PreparePay> preparePays) {
        this.preparePays = preparePays;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOper")
    public Set<OrderBack> getOrderBacks() {
        return this.orderBacks;
    }

    public void setOrderBacks(Set<OrderBack> orderBacks) {
        this.orderBacks = orderBacks;
    }

}
