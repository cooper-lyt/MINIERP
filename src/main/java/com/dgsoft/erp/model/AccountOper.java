package com.dgsoft.erp.model;
// Generated Nov 5, 2013 1:32:07 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.system.RunParam;
import com.dgsoft.common.utils.finance.CertificateItem;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * AccountOper generated by hbm2java
 */
@Entity
@Table(name = "ACCOUNT_OPER", catalog = "MINI_ERP")
public class AccountOper implements java.io.Serializable {


    public enum AccountOperType {
        DEPOSIT_BACK(Accounting.Direction.DBEDIT, Accounting.Direction.DBEDIT),   //false

        PROXY_SAVINGS(Accounting.Direction.CREDIT, Accounting.Direction.CREDIT),   //true
        CUSTOMER_SAVINGS(Accounting.Direction.CREDIT, Accounting.Direction.CREDIT),  //true
        DEPOSIT_PAY(Accounting.Direction.DBEDIT, Accounting.Direction.CREDIT),
        MONEY_FREE(Accounting.Direction.CREDIT, Accounting.Direction.CREDIT),
        ORDER_PAY(Accounting.Direction.DBEDIT, Accounting.Direction.DBEDIT),

        ORDER_BACK(Accounting.Direction.CREDIT, null);       //不包含冲帐

        public static EnumSet<AccountOperType> getCustomerMoneyAdd() {
            return EnumSet.of(PROXY_SAVINGS, CUSTOMER_SAVINGS);
        }

        public static EnumSet<AccountOperType> getCustomerMoneySub() {
            return EnumSet.of(ORDER_BACK, DEPOSIT_BACK);
        }

        public static EnumSet<AccountOperType> getCustomerOpers() {
            return EnumSet.of(DEPOSIT_BACK, PROXY_SAVINGS, CUSTOMER_SAVINGS, ORDER_BACK);
        }

        public boolean isCustomerOper() {
            return getCustomerOpers().contains(this);
        }

        public Boolean getAdd(){
            if (getCustomerMoneyAdd().contains(this)){
                return true;
            }
            if (getCustomerMoneySub().contains(this)){
                return false;
            }
            return null;
        }

        private Accounting.Direction adDirection; //贷方科目

        private Accounting.Direction acDirection; //借方科目



        public Accounting.Direction getAdDirection() {
            return adDirection;
        }

        public Accounting.Direction getAcDirection() {
            return acDirection;
        }

        private AccountOperType(Accounting.Direction adDirection,
                                Accounting.Direction acDirection) {
            this.adDirection = adDirection;
            this.acDirection = acDirection;
        }
    }

    private String id;
    private Customer customer;
    private String operEmp;
    private AccountOperType operType;
    private Date operDate;
    private String description;

    private BigDecimal advanceReceivable;
    private BigDecimal accountsReceivable;
    private BigDecimal proxcAccountsReceiveable;

    private MoneySave moneySave;
    private SaleCertificate saleCertificate;

    private CustomerOrder customerOrder;
    private OrderBack orderBack;

    private Integer version;


    public AccountOper() {
    }


    public AccountOper(AccountOperType operType, String operEmp) {
        this.operEmp = operEmp;
        this.operType = operType;
        this.advanceReceivable = BigDecimal.ZERO;
        this.accountsReceivable = BigDecimal.ZERO;
        this.proxcAccountsReceiveable = BigDecimal.ZERO;
    }

    public AccountOper(MoneySave moneySave, AccountOperType operType, Customer customer, String operEmp) {
        this.operEmp = operEmp;
        this.operType = operType;
        this.customer = customer;
        this.moneySave = moneySave;
        this.advanceReceivable = BigDecimal.ZERO;
        this.accountsReceivable = BigDecimal.ZERO;
        this.proxcAccountsReceiveable = BigDecimal.ZERO;
    }

//    public AccountOper(PreparePay preparePay, Customer customer, String operEmp) {
//        this.operType = AccountOperType.PRE_DEPOSIT;
//        this.preparePay = preparePay;
//        this.customer = customer;
//        this.operEmp = operEmp;
//    }
//
//    public AccountOper(BackPrepareMoney backPrepareMoney, Customer customer, String operEmp) {
//        this.operType = AccountOperType.DEPOSIT_BACK;
//        this.backPrepareMoney = backPrepareMoney;
//        this.customer = customer;
//        this.operEmp = operEmp;
//    }
//
//
//    public AccountOper(OrderBack orderBack, String operEmp, AccountOperType operType, Date operDate,
//                       BigDecimal remitFee, BigDecimal advanceReceivable, BigDecimal accountsReceivable) {
//        this.orderBack = orderBack;
//        this.customer = orderBack.getCustomer();
//        this.operEmp = operEmp;
//        this.operType = operType;
//        this.remitFee = remitFee;
//        this.advanceReceivable = advanceReceivable;
//        this.accountsReceivable = accountsReceivable;
//        this.operDate = operDate;
//        this.proxcAccountsReceiveable = BigDecimal.ZERO;
//    }
//
//    public AccountOper(CustomerOrder order, String operEmp, AccountOperType operType){
//        this.customerOrder = order;
//        this.customer = order.getCustomer();
//        this.operEmp = operEmp;
//        this.operType = operType;
//    }
//
//    public AccountOper(CustomerOrder order, String operEmp, AccountOperType operType, Date operDate,
//                       BigDecimal remitFee, BigDecimal advanceReceivable, BigDecimal accountsReceivable, BigDecimal proxcAccountsReceiveable) {
//        this.customerOrder = order;
//        this.customer = order.getCustomer();
//        this.operEmp = operEmp;
//        this.operType = operType;
//        this.remitFee = remitFee;
//        this.advanceReceivable = advanceReceivable;
//        this.accountsReceivable = accountsReceivable;
//        this.operDate = operDate;
//        this.proxcAccountsReceiveable = proxcAccountsReceiveable;
//    }

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


    @Column(name = "DESCRIPTION", length = 200)
    @Size(max = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "ADVANCE_RECEIVABLE", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getAdvanceReceivable() {
        return advanceReceivable;
    }

    public void setAdvanceReceivable(BigDecimal advanceReceivable) {
        this.advanceReceivable = advanceReceivable;
    }

    @Column(name = "ACCOUNTS_RECEIVABLE", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(BigDecimal accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }


    @Column(name = "PROXY_ACCOUNTS_RECEIVABLE", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getProxcAccountsReceiveable() {
        return proxcAccountsReceiveable;
    }

    public void setProxcAccountsReceiveable(BigDecimal proxcAccountsReceiveable) {
        this.proxcAccountsReceiveable = proxcAccountsReceiveable;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "SAVEING", nullable = true)
    public MoneySave getMoneySave() {
        return moneySave;
    }

    public void setMoneySave(MoneySave moneySave) {
        this.moneySave = moneySave;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, targetEntity = CustomerOrder.class)
    @JoinColumn(name = "CUSTOMER_ORDER", nullable = true)
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, targetEntity = OrderBack.class)
    @JoinColumn(name = "ORDER_BACK", nullable = true)
    public OrderBack getOrderBack() {
        return orderBack;
    }

    public void setOrderBack(OrderBack orderBack) {
        this.orderBack = orderBack;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "CERTIFICATE", nullable = true)
    public SaleCertificate getSaleCertificate() {
        return saleCertificate;
    }

    public void setSaleCertificate(SaleCertificate saleCertificate) {
        this.saleCertificate = saleCertificate;
    }

    @Version
    @Column(name = "VERSION", nullable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Transient
    public BigDecimal getCustomerOperMoney() {
        switch (getOperType()) {

            case DEPOSIT_BACK:
                return getAdvanceReceivable();

            case PROXY_SAVINGS:
                return getProxcAccountsReceiveable();
            case CUSTOMER_SAVINGS:
                return getAdvanceReceivable().add(getAccountsReceivable());
            case DEPOSIT_PAY:
                return getAccountsReceivable();
            case MONEY_FREE:
                return getAccountsReceivable();
            case ORDER_PAY:
                return getAdvanceReceivable().add(getAccountsReceivable()).add(getProxcAccountsReceiveable());
            case ORDER_BACK:
                return getAccountsReceivable();

        }

        throw new IllegalArgumentException("unkonw operType:" + getOperType());
    }

    @Transient
    public BigDecimal getCustomerAdvanceMoney(){
        switch (getOperType()) {

            case DEPOSIT_BACK:
            case DEPOSIT_PAY:
            case ORDER_PAY:
                return  getAdvanceReceivable().multiply(new BigDecimal("-1"));
            case CUSTOMER_SAVINGS:
            case ORDER_BACK:
                return getAdvanceReceivable();
        }
        return BigDecimal.ZERO;
    }

    @Transient
    public BigDecimal getCustomerProxyAccountMoney(){
        switch (getOperType()) {

            case PROXY_SAVINGS:
                return getProxcAccountsReceiveable().multiply(new BigDecimal("-1"));
            case ORDER_PAY:
                return getProxcAccountsReceiveable();
        }
        return BigDecimal.ZERO;
    }

    @Transient
    public BigDecimal getCustomerAccountMoney(BigDecimal beginMoney){
        switch (getOperType()) {
            case CUSTOMER_SAVINGS:
            case DEPOSIT_PAY:
            case MONEY_FREE:
                return getAccountsReceivable().multiply(new BigDecimal("-1"));
            case ORDER_PAY:
                return getAccountsReceivable();
        }
        return BigDecimal.ZERO;
    }

    @Transient
    public void calcCustomerMoney() {
        switch (getOperType()) {

            case DEPOSIT_BACK:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().subtract(getAdvanceReceivable()));
                break;
            case PROXY_SAVINGS:
                getCustomer().setProxyAccountMoney(getCustomer().getProxyAccountMoney().subtract(getProxcAccountsReceiveable()));
                break;
            case CUSTOMER_SAVINGS:
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().subtract(getAccountsReceivable()));
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().add(getAdvanceReceivable()));
                break;
            case DEPOSIT_PAY:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().subtract(getAdvanceReceivable()));
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().subtract(getAccountsReceivable()));
                break;
            case MONEY_FREE:
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().subtract(getAccountsReceivable()));
                break;
            case ORDER_PAY:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().subtract(getAdvanceReceivable()));
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().add(getAccountsReceivable()));
                getCustomer().setProxyAccountMoney(getCustomer().getProxyAccountMoney().add(getProxcAccountsReceiveable()));
                break;
            case ORDER_BACK:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().add(getAdvanceReceivable()));
                break;
        }
    }

    @Transient
    public void revertCustomerMoney() {
        switch (getOperType()) {

            case DEPOSIT_BACK:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().add(getAdvanceReceivable()));
                break;
            case PROXY_SAVINGS:
                getCustomer().setProxyAccountMoney(getCustomer().getProxyAccountMoney().add(getProxcAccountsReceiveable()));
                break;
            case CUSTOMER_SAVINGS:
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().add(getAccountsReceivable()));
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().subtract(getAdvanceReceivable()));
                break;
            case MONEY_FREE:
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().add(getAccountsReceivable()));
                break;
            case ORDER_PAY:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().add(getAdvanceReceivable()));
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().subtract(getAccountsReceivable()));
                getCustomer().setProxyAccountMoney(getCustomer().getProxyAccountMoney().subtract(getProxcAccountsReceiveable()));
                break;
            case ORDER_BACK:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().subtract(getAdvanceReceivable()));
                break;
            case DEPOSIT_PAY:
                getCustomer().setAdvanceMoney(getCustomer().getAdvanceMoney().add(getAdvanceReceivable()));
                getCustomer().setAccountMoney(getCustomer().getAccountMoney().add(getAccountsReceivable()));
                break;
        }
    }

    @Transient
    public boolean isPrepared() {
        return getSaleCertificate() != null;
    }

    @Transient
    public List<SaleCertificateItem> getCertificateItems() {
        List<SaleCertificateItem> result = new ArrayList<SaleCertificateItem>();
        switch (getOperType()) {

            case DEPOSIT_BACK:
                if (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0)
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ad.DEPOSIT_BACK"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.advance") + getCustomer().getId(), getAdvanceReceivable(), BigDecimal.ZERO));
                if ((getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) || (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0)) {
                    throw new IllegalArgumentException(" DEPOSIT_BACK unSupport type");
                }
                break;
            case PROXY_SAVINGS:
                if (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.pac.PROXY_SAVINGS"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.proxyAccount") + getCustomer().getId(), BigDecimal.ZERO, getProxcAccountsReceiveable()));
                }
                if ((getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) || (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0)) {
                    throw new IllegalArgumentException(" PROXY_SAVINGS unSupport type");
                }
                break;
            case CUSTOMER_SAVINGS:
                if (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ad.CUSTOMER_SAVINGS"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.advance") + getCustomer().getId(), BigDecimal.ZERO, getAdvanceReceivable()));
                }
                if (getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.CUSTOMER_SAVINGS"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), BigDecimal.ZERO, getAccountsReceivable()));
                }
                if (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0) {
                    throw new IllegalArgumentException(" CUSTOMER_SAVINGS unSupport type");
                }
                break;
            case DEPOSIT_PAY:
                if (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ad.DEPOSIT_PAY"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.advance") + getCustomer().getId(), getAdvanceReceivable(), BigDecimal.ZERO));
                }
                if (getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.DEPOSIT_PAY"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), BigDecimal.ZERO, getAccountsReceivable()));
                }
                if (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0) {
                    throw new IllegalArgumentException(" DEPOSIT_PAY unSupport type");
                }

                break;
            case MONEY_FREE:
                if (getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.MONEY_FREE"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), BigDecimal.ZERO, getAccountsReceivable()));
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.mf.MONEY_FREE"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.mgrFee"), getAccountsReceivable(), BigDecimal.ZERO));
                }
                if ((getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0) || (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0)) {
                    throw new IllegalArgumentException(" PROXY_SAVINGS unSupport type");
                }
                break;
            case ORDER_PAY:
                if (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ad.ORDER_PAY"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.advance") + getCustomer().getId(), getAdvanceReceivable(), BigDecimal.ZERO));
                }
                if (getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.ORDER_PAY"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), getAccountsReceivable(), BigDecimal.ZERO));
                }
                if (getProxcAccountsReceiveable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.pac.ORDER_PAY"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.proxyAccount") + getCustomer().getId(), getProxcAccountsReceiveable(), BigDecimal.ZERO));
                }
                result.add(new SaleCertificateItem(getSaleCertificate(),
                        String.format(RunParam.instance().getStringParamValue("erp.ADF.rm.ORDER_PAY"), getCustomer().getName()),
                        RunParam.instance().getStringParamValue("erp.finance.receive"), BigDecimal.ZERO,
                        getAdvanceReceivable().add(getAccountsReceivable()).add(getProxcAccountsReceiveable())));

                break;
            case ORDER_BACK:
                if (getAccountsReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.ORDER_BACK"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), getAccountsReceivable().multiply(new BigDecimal("-1")), BigDecimal.ZERO));
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.rm.ORDER_BACK"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.receive"), BigDecimal.ZERO, getAccountsReceivable().multiply(new BigDecimal("-1"))));
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ac.c.ORDER_BACK"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.customerAccount") + getCustomer().getId(), getAccountsReceivable(), BigDecimal.ZERO));
                }
                if (getAdvanceReceivable().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(new SaleCertificateItem(getSaleCertificate(),
                            String.format(RunParam.instance().getStringParamValue("erp.ADF.ad.ORDER_BACK"), getCustomer().getName()),
                            RunParam.instance().getStringParamValue("erp.finance.advance") + getCustomer().getId(), BigDecimal.ZERO, getAdvanceReceivable()));
                }
                break;
        }
        return result;

    }


}
