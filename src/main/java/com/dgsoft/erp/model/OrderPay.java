package com.dgsoft.erp.model;
// Generated Oct 1, 2013 5:41:32 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * OrderPay generated by hbm2java
 */
@Entity
@Table(name = "ORDER_PAY", catalog = "MINI_ERP")
public class OrderPay implements java.io.Serializable {

	private String id;
	private CustomerOrder customerOrder;
	private AccountOper accountOper;

	public OrderPay() {
	}

	public OrderPay(String id, CustomerOrder customerOrder,
			AccountOper accountOper) {
		this.id = id;
		this.customerOrder = customerOrder;
		this.accountOper = accountOper;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ORDER", nullable = false)
	@NotNull
	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_OPER", nullable = false)
	@NotNull
	public AccountOper getAccountOper() {
		return this.accountOper;
	}

	public void setAccountOper(AccountOper accountOper) {
		this.accountOper = accountOper;
	}

}
