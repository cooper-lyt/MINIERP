package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * OrderBack generated by hbm2java
 */
@Entity
@Table(name = "ORDER_BACK", catalog = "MINI_ERP")
public class OrderBack implements java.io.Serializable {

    private String id;
    private Integer version;
    private String reason;
    private Date createDate;
    private BigDecimal money;
    private String memo;
    private boolean moneyComplete;
    private boolean resComplete;
    private String applyEmp;
    private BigDecimal saveMoney;
    private Set<BackItem> backItems = new HashSet<BackItem>(0);
    private Customer customer;
    private Set<BackDispatch> backDispatchs = new HashSet<BackDispatch>(0);
    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);
    private boolean dispatched;

    public OrderBack() {
    }

    public OrderBack(BigDecimal saveMoney, boolean dispatched) {
        this.saveMoney = saveMoney;
        this.dispatched = dispatched;
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

    @Column(name = "REASON", nullable = false, length = 32)
    @Size(max = 32)
    @NotNull
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    @Column(name = "MEMO", length = 200)
    @Size(max = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBack", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<BackDispatch> getBackDispatchs() {
        return this.backDispatchs;
    }

    public void setBackDispatchs(Set<BackDispatch> productBackReses) {
        this.backDispatchs = productBackReses;
    }

    @Transient
    public List<BackDispatch> getBackDispatchList() {
        List<BackDispatch> result = new ArrayList<BackDispatch>(getBackDispatchs());
        Collections.sort(result, new Comparator<BackDispatch>() {
            @Override
            public int compare(BackDispatch o1, BackDispatch o2) {
                if ((o1.getStore() != null) && (o2.getStore() != null)) {
                    return o1.getStore().getId().compareTo(o2.getStore().getId());
                }else{
                    return 0;
                }
            }
        });
        return result;
    }

    @OneToMany(orphanRemoval = false, mappedBy = "orderBack", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    public Set<AccountOper> getAccountOpers() {
        return accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
    }

    @Column(name = "MONEY_COMPLETE", nullable = false)
    public boolean isMoneyComplete() {
        return moneyComplete;
    }

    public void setMoneyComplete(boolean moneyComplete) {
        this.moneyComplete = moneyComplete;
    }

    @Column(name = "RES_COMPLETE", nullable = false)
    public boolean isResComplete() {
        return resComplete;
    }

    public void setResComplete(boolean resComplete) {
        this.resComplete = resComplete;
    }

    @Column(name = "APPLY_EMP", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getApplyEmp() {
        return applyEmp;
    }

    public void setApplyEmp(String applyEmp) {
        this.applyEmp = applyEmp;
    }

    @Column(name = "MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBack", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<BackItem> getBackItems() {
        return backItems;
    }

    public void setBackItems(Set<BackItem> backItems) {
        this.backItems = backItems;
    }

    @Transient
    public List<BackItem> getBackItemList() {
        List<BackItem> result = new ArrayList<BackItem>(getBackItems());
        Collections.sort(result, new Comparator<BackItem>() {
            @Override
            public int compare(BackItem o1, BackItem o2) {
                int cResult = o1.getStoreRes().compareTo(o2.getStoreRes());
                if (cResult == 0) {
                    if ((o1.getId() != null) && (o2.getId() != null)) {
                        return o1.getId().compareTo(o2.getId());
                    } else return 0;
                } else {
                    return cResult;
                }

            }
        });
        return result;
    }

    @Column(name = "SAVE_MONEY", nullable = false)
    public BigDecimal getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(BigDecimal saveMoney) {
        this.saveMoney = saveMoney;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CUSTOMER", nullable = false)
    @NotNull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "DISPATCHED", nullable = false)
    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }
}
