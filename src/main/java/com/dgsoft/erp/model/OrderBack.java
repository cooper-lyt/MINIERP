package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * OrderBack generated by hbm2java
 */
@Entity
@Table(name = "ORDER_BACK", catalog = "MINI_ERP")
public class OrderBack implements java.io.Serializable {

	private String id;
	private Integer version;
	private CustomerOrder customerOrder;
	private String state;
	private String reason;
	private Date createDate;
	private Date completeDate;
	private String memo;
    private String bankNumber;
    private String bank;

	private Set<ProductBackStoreIn> productBackReses = new HashSet<ProductBackStoreIn>(0);

	private AccountOper accountOper;

	public OrderBack() {
	}

	public OrderBack(String id, CustomerOrder customerOrder, String state,
			String reason, Date createDate, Date completeDate) {
		this.id = id;
		this.customerOrder = customerOrder;
		this.state = state;
		this.reason = reason;
		this.createDate = createDate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ORDER", nullable = false)
	@NotNull
	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
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

	@Column(name = "REASON", nullable = false, length = 32)
	@NotNull
	@Size(max = 32)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, length = 19,columnDefinition = "DATETIME")
	@NotNull
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPLETE_DATE", nullable = false, length = 19,columnDefinition = "DATETIME")
	@NotNull
	public Date getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBack")
	public Set<ProductBackStoreIn> getProductBackReses() {
		return this.productBackReses;
	}

	public void setProductBackReses(Set<ProductBackStoreIn> productBackReses) {
		this.productBackReses = productBackReses;
	}

    @ManyToOne(optional = true, fetch = FetchType.LAZY, targetEntity = AccountOper.class)
    @JoinColumn(name = "BACK_MONEY", nullable = true)
    public AccountOper getAccountOper() {
		return this.accountOper;
	}

	public void setAccountOper(AccountOper backMoneys) {
		this.accountOper = backMoneys;
	}

    @Column(name = "BANK_NUMBER", length = 50)
    @Size(max = 50)
    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }


    @Column(name = "BANK", length = 32)
    @Size(max = 32)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
