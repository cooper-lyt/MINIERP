package com.dgsoft.erp.model;
// Generated Nov 5, 2013 1:32:07 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.PayType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumSet;

/**
 * AccountOper generated by hbm2java
 */
@Entity
@Table(name = "ACCOUNT_OPER", catalog = "MINI_ERP")
public class AccountOper implements java.io.Serializable {

    public enum AccountOperType {
        ORDER_SAVINGS(true), ORDER_PAY(false), ORDER_EARNEST(false),
        PRE_DEPOSIT(true),PRE_DEPOSIT_BY_ORDER(true), DEPOSIT_BACK(false), ORDER_FREE(true),
        ORDER_BACK_SAVINGS(true), ORDER_BACK(false),
        ORDER_CANCEL_SAVINGS(true), ORDER_CANCEL_BACK(false);

        public static EnumSet<AccountOperType> allCustomerOper(){
            return EnumSet.of(ORDER_SAVINGS,PRE_DEPOSIT,DEPOSIT_BACK,ORDER_FREE,ORDER_BACK,ORDER_CANCEL_BACK);
        }

        private boolean add;

        public boolean isAdd() {
            return add;
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
    private String description;
    private PayType payType;
    private String checkNumber;
    private BigDecimal remitFee;


    private BackPrepareMoney backPrepareMoney;
    private PreparePay preparePay;
    private OrderBack orderBack;
    private CustomerOrder customerOrder;
    private BankAccount bankAccount;


    public AccountOper() {
    }

    public AccountOper(PayType payType, String operEmp) {
        this.payType = payType;
        this.operEmp = operEmp;
    }


    public AccountOper(Customer customer, String operEmp,
                       BigDecimal operMoney, AccountOperType operType,
                       Date operDate,
                       String description, PayType payType, CustomerOrder customerOrder,
                       String checkNumber,BigDecimal remitFee) {
        this.customer = customer;
        this.operEmp = operEmp;
        this.operMoney = operMoney;
        this.operType = operType;
        this.operDate = operDate;
        this.description = description;
        this.payType = payType;
        this.customerOrder = customerOrder;
        this.checkNumber = checkNumber;
        this.remitFee = remitFee;
    }

    public AccountOper(PreparePay preparePay, Customer customer, String operEmp, BigDecimal remitFee) {
        this.operType = AccountOperType.PRE_DEPOSIT;
        this.preparePay = preparePay;
        this.customer = customer;
        this.operEmp = operEmp;
        this.remitFee = remitFee;
    }

    public AccountOper(BackPrepareMoney backPrepareMoney, Customer customer, String operEmp, BigDecimal remitFee) {
        this.operType = AccountOperType.DEPOSIT_BACK;
        this.backPrepareMoney = backPrepareMoney;
        this.customer = customer;
        this.operEmp = operEmp;
        this.remitFee = remitFee;
    }


    public AccountOper(OrderBack orderBack, String operEmp,BigDecimal remitFee) {
        this.orderBack = orderBack;
        this.remitFee = remitFee;
        this.customer = orderBack.getCustomer();
        this.operEmp = operEmp;
        this.operType = AccountOperType.ORDER_BACK_SAVINGS;
        this.operMoney = orderBack.getMoney();
    }

    public AccountOper(CustomerOrder order, String operEmp, BigDecimal remitFee){
        this.customerOrder = order;
        this.operEmp = operEmp;
        this.remitFee = remitFee;
        this.customer = order.getCustomer();
        this.operType = AccountOperType.ORDER_CANCEL_SAVINGS;
        this.operMoney = order.getReceiveMoney();
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
    @JoinColumn(name = "CREDIT_ACCOUNT")
    public Accounting getAccountingByCreditAccount() {
        return this.accountingByCreditAccount;
    }

    public void setAccountingByCreditAccount(
            Accounting accountingByCreditAccount) {
        this.accountingByCreditAccount = accountingByCreditAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEBIT_ACCOUNT")
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

    @Column(name = "REMIT_FEE", nullable = true, scale = 3)
    public BigDecimal getRemitFee() {
        return remitFee;
    }

    public void setRemitFee(BigDecimal remitFee) {
        this.remitFee = remitFee;
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
    @Column(name = "PAY_TYPE", nullable = true, length = 32)
    public PayType getPayType() {
        return this.payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Transient
    public PayType getDisplayPayType() {
        if (AccountOperType.ORDER_BACK_SAVINGS.equals(getOperType()) ||
                AccountOperType.ORDER_CANCEL_SAVINGS.equals(getOperType()) ||
                AccountOperType.ORDER_PAY.equals(getOperType()) ||
                AccountOperType.ORDER_EARNEST.equals(getOperType()) ||
                AccountOperType.ORDER_FREE.equals(getOperType())) {
            return null;
        } else
            return getPayType();
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accountOper")
    public BackPrepareMoney getBackPrepareMoney() {
        return this.backPrepareMoney;
    }

    public void setBackPrepareMoney(BackPrepareMoney backPrepareMoney) {
        this.backPrepareMoney = backPrepareMoney;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, mappedBy = "accountOper")
    public PreparePay getPreparePay() {
        return this.preparePay;
    }

    public void setPreparePay(PreparePay preparePay) {
        this.preparePay = preparePay;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_BACK", nullable = true)
    public OrderBack getOrderBack() {
        return this.orderBack;
    }

    public void setOrderBack(OrderBack orderBack) {
        this.orderBack = orderBack;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT", nullable = true)
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Transient
    public BigDecimal getRealMoney(){
        if (getRemitFee() != null){

           switch (getOperType()){
               //customer save money sub remiteFee
               case PRE_DEPOSIT:
               case ORDER_SAVINGS:
                   return getOperMoney().subtract(getRemitFee());

               // to customer add remitFee
               case DEPOSIT_BACK:
               case ORDER_BACK:
               case ORDER_CANCEL_BACK:
                   return getOperMoney().add(getRemitFee());
           }
        }
        return getOperMoney();
    }

    @Transient
    public BigDecimal getBankRemitFee(){
        if (getOperType().equals(AccountOperType.DEPOSIT_BACK) ||
                getOperType().equals(AccountOperType.ORDER_BACK) ||
                AccountOperType.ORDER_CANCEL_BACK.equals(getOperType())){
            return getRemitFee();
        }
        return BigDecimal.ZERO;
    }
}
