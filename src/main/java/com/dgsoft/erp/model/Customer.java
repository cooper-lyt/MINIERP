package com.dgsoft.erp.model;
// Generated Oct 24, 2013 3:27:02 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.NamedEntity;
import com.dgsoft.common.utils.persistence.UniqueVerify;
import com.dgsoft.erp.model.api.BatchOperEntity;
import org.hibernate.annotations.GenericGenerator;
import org.jboss.seam.international.StatusMessage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER", catalog = "MINI_ERP")
@UniqueVerify(name = "name", severity = StatusMessage.Severity.ERROR, field = {"name"})
public class Customer implements Comparable<Customer>, java.io.Serializable, NamedEntity {

    private String id;
    private Integer version;
    private CustomerLevel customerLevel;
    private CustomerArea customerArea;
    private MiddleMan middleMan;
    private String name;
    private String type;
    //private BigDecimal balance;
    private BigDecimal advanceMoney;// ADVANCE_MONEY;
    private BigDecimal accountMoney;// ACCOUNT_MONEY;
    private BigDecimal proxyAccountMoney; // PROXY_ACCOUNT_MONEY;

    private BigDecimal initAD;
    private BigDecimal initAC;
    private BigDecimal initPAC;

    private String fax;
    private String memo;
    private String mail;
    private String address;
    private boolean enable;
    private String postCode;
    private int provinceCode;
    private String city;
    private Date createDate;
    private Set<CustomerOrder> customerOrders = new HashSet<CustomerOrder>(0);
    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);
    private Set<CustomerContact> customerContacts = new HashSet<CustomerContact>(0);
    private Set<OrderBack> orderBacks = new HashSet<OrderBack>(0);
    private RebateProgram rebateProgram;


    public Customer() {
    }

    public Customer(boolean enable){
        this.enable = enable;
        advanceMoney = BigDecimal.ZERO;
        accountMoney = BigDecimal.ZERO;
        proxyAccountMoney = BigDecimal.ZERO;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEVEL", nullable = false)
    @NotNull
    public CustomerLevel getCustomerLevel() {
        return this.customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_AREA", nullable = false)
    @NotNull
    public CustomerArea getCustomerArea() {
        return this.customerArea;
    }

    public void setCustomerArea(CustomerArea customerArea) {
        this.customerArea = customerArea;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "MIDDLE_MAN", nullable = true)
    public MiddleMan getMiddleMan() {
        return this.middleMan;
    }

    public void setMiddleMan(MiddleMan middleMan) {
        this.middleMan = middleMan;
    }

    @Column(name = "NAME", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TYPE", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


//    @Column(name = "BALANCE", scale = 3, nullable = false)
//    @NotNull
//    public BigDecimal getBalance() {
//        return this.balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }

    @Column(name = "FAX", length = 50)
    @Size(max = 50)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "POST_CODE", length = 10, nullable = false)
    @Size(max = 10)
    @NotNull
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name="CITY",length = 100,nullable = false)
    @NotNull
    @Size(max = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "MEMO", length = 200)
    @Size(max = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "ADDRESS", length = 200)
    @Size(max = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "MAIL", length = 50)
    @Size(max = 50)
    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "ENABLE", nullable = false)
    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Column(name = "PROVINCE_CODE", nullable = false)
    public int getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<CustomerOrder> getCustomerOrders() {
        return this.customerOrders;
    }

    public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer",orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<AccountOper> getAccountOpers() {
        return this.accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<CustomerContact> getCustomerContacts() {
        return customerContacts;
    }

    public void setCustomerContacts(Set<CustomerContact> customerContacts) {
        this.customerContacts = customerContacts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<OrderBack> getOrderBacks() {
        return orderBacks;
    }

    public void setOrderBacks(Set<OrderBack> orderBacks) {
        this.orderBacks = orderBacks;
    }


    @Column(name = "ADVANCE_MONEY", scale = 3, nullable = false)
    @NotNull
    public BigDecimal getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(BigDecimal advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    @Column(name = "ACCOUNT_MONEY", scale = 3, nullable = false)
    @NotNull
    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    @Column(name = "PROXY_ACCOUNT_MONEY", scale = 3, nullable = false)
    @NotNull
    public BigDecimal getProxyAccountMoney() {
        return proxyAccountMoney;
    }

    public void setProxyAccountMoney(BigDecimal proxyAccountMoney) {
        this.proxyAccountMoney = proxyAccountMoney;
    }


    @Column(name="INIT_AD",nullable = true, scale = 4)
    public BigDecimal getInitAD() {
        return initAD;
    }

    public void setInitAD(BigDecimal initAD) {
        this.initAD = initAD;
    }

    @Column(name="INIT_AC", nullable = true, scale = 4)
    public BigDecimal getInitAC() {
        return initAC;
    }

    public void setInitAC(BigDecimal initAC) {
        this.initAC = initAC;
    }

    @Column(name="INIT_PAC",nullable = true,scale = 4)
    public BigDecimal getInitPAC() {
        return initPAC;
    }

    public void setInitPAC(BigDecimal initPAC) {
        this.initPAC = initPAC;
    }

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "REBATE_PROGRAM",nullable = true)
    public RebateProgram getRebateProgram() {
        return rebateProgram;
    }

    public void setRebateProgram(RebateProgram rebateProgram) {
        this.rebateProgram = rebateProgram;
    }

    @Transient
    public List<CustomerContact> getCustomerContactList(){
        List<CustomerContact> result = new ArrayList<CustomerContact>(getCustomerContacts());
        Collections.sort(result,new Comparator<CustomerContact>() {
            @Override
            public int compare(CustomerContact o1, CustomerContact o2) {
                if ((o1.getId()) == null || (o2.getId() == null)){
                    return 0;
                }
                int r = o1.getType().compareTo(o2.getType());
                if (r == 0){
                    return o1.getName().compareTo(o2.getName());
                }else
                    return r;

            }
        });
        return result;
    }

    @Transient
    public List<AccountOper> getAccountOperList(){
        List<AccountOper> result = new ArrayList<AccountOper>(getAccountOpers());
        Collections.sort(result,new Comparator<AccountOper>() {
            @Override
            public int compare(AccountOper o1, AccountOper o2) {
                return o1.getOperDate().compareTo(o2.getOperDate());
            }
        });
        return result;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19)
    @NotNull
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    @Transient
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;

        if ((getId() != null) && (!"".equals(getId().trim()))) {
            return getId().equals(other.getId());
        }

        return false;
    }

    @Override
    @Transient
    public int hashCode() {
        if ((getId() != null) && (!"".equals(getId().trim()))) {
            return getId().hashCode();
        }else{
            return super.hashCode();
        }

    }

    @Override
    public int compareTo(Customer o) {
        if ((getId() == null) || (o.getId() == null)){
            if ((getName() != null) && (o.getName() != null)){
                return getName().compareTo(o.getName());
            }else{
                return 0;
            }
        } else{
            return getId().compareTo(o.getId());
        }
    }

    @Transient
    public BigDecimal getBalance(){
        return getAdvanceMoney().subtract(getAccountMoney()).subtract(getProxyAccountMoney());
    }

    @Transient
    public BigDecimal getAccountBalance(){
        return getAdvanceMoney().subtract(getAccountMoney());
    }

}
