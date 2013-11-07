package com.dgsoft.erp.model;
// Generated Oct 24, 2013 3:27:02 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.NamedEntity;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER", catalog = "MINI_ERP")
public class Customer implements java.io.Serializable, NamedEntity {

    private String id;
    private Integer version;
    private CustomerLevel customerLevel;
    private CustomerArea customerArea;
    private MiddleMan middleMan;
    private String name;
    private String type;
    private String contact;
    private BigDecimal balance;
    private String tel;
    private String fax;
    private String memo;
    private String mail;
    private String address;
    private boolean enable;
    private String postCode;
    private int provinceCode;
    private Set<CustomerOrder> customerOrders = new HashSet<CustomerOrder>(0);
    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);

    public Customer() {
        balance = BigDecimal.ZERO;
    }

    public Customer(boolean enable){
        balance = BigDecimal.ZERO;
        this.enable = enable;
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

    @Column(name = "CONTACT", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "BALANCE", scale = 3, nullable = false)
    @NotNull
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(name = "TEL", nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "FAX", length = 50)
    @Size(max = 50)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "POST_CODE", length = 10)
    @Size(max = 10)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<AccountOper> getAccountOpers() {
        return this.accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
    }

}
