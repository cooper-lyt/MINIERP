package com.dgsoft.erp.model;
// Generated Oct 1, 2013 5:41:32 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Inventory generated by hbm2java
 */
@Entity
@Table(name = "INVENTORY", catalog = "MINI_ERP")
public class Inventory implements java.io.Serializable {

	private String id;
	private Store store;
	private StockChange stockChangeLoss;
	private StockChange stockChangeAdd;
	private Date checkDate;
	private String memo;

	public Inventory() {
	}

	public Inventory(String id, Store store, Date checkDate) {
		this.id = id;
		this.store = store;
		this.checkDate = checkDate;
	}
	public Inventory(String id, Store store, StockChange stockChangeLoss,
                     StockChange stockChangeAdd, Date checkDate, String memo) {
		this.id = id;
		this.store = store;
		this.stockChangeLoss = stockChangeLoss;
		this.stockChangeAdd = stockChangeAdd;
		this.checkDate = checkDate;
		this.memo = memo;
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
	@JoinColumn(name = "STORE", nullable = false)
	@NotNull
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

    @OneToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "LOSS_RES", unique = true, nullable = true, updatable = false)
	public StockChange getStockChangeLoss() {
		return this.stockChangeLoss;
	}

	public void setStockChangeLoss(StockChange stockChangeLoss) {
		this.stockChangeLoss = stockChangeLoss;
	}

	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADD_RES", unique = true, nullable = true, updatable = false)
	public StockChange getStockChangeAdd() {
		return this.stockChangeAdd;
	}

	public void setStockChangeAdd(StockChange stockChangeAdd) {
		this.stockChangeAdd = stockChangeAdd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHECK_DATE", nullable = false, length = 19)
	@NotNull
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
