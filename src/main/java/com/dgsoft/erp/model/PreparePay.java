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
 * PreparePay generated by hbm2java
 */
@Entity
@Table(name = "PREPARE_PAY", catalog = "MINI_ERP")
public class PreparePay implements java.io.Serializable {

	private String id;
	private AccountOper accountOper;
	private String memo;

	public PreparePay() {
	}

	public PreparePay(String id, AccountOper accountOper) {
		this.id = id;
		this.accountOper = accountOper;
	}
	public PreparePay(String id, AccountOper accountOper, String memo) {
		this.id = id;
		this.accountOper = accountOper;
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
	@JoinColumn(name = "ACCOUNT_OPER", nullable = false)
	@NotNull
	public AccountOper getAccountOper() {
		return this.accountOper;
	}

	public void setAccountOper(AccountOper accountOper) {
		this.accountOper = accountOper;
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
