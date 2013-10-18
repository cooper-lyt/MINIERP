package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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
 * OrderFee generated by hbm2java
 */
@Entity
@Table(name = "ORDER_FEE", catalog = "MINI_ERP")
public class OrderFee implements java.io.Serializable {

	private String id;
	private CustomerOrder customerOrder;
	private String item;
	private BigDecimal money;
	private String description;

	public OrderFee() {
	}

	public OrderFee(String id, CustomerOrder customerOrder, String item,
			BigDecimal money) {
		this.id = id;
		this.customerOrder = customerOrder;
		this.item = item;
		this.money = money;
	}
	public OrderFee(String id, CustomerOrder customerOrder, String item,
			BigDecimal money, String description) {
		this.id = id;
		this.customerOrder = customerOrder;
		this.item = item;
		this.money = money;
		this.description = description;
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

	@Column(name = "ITEM", nullable = false, length = 32)
	@NotNull
	@Size(max = 32)
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "MONEY", nullable = false, scale = 3)
	@NotNull
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "DESCRIPTION", length = 200)
	@Size(max = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
