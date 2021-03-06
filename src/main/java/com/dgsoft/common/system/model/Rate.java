package com.dgsoft.common.system.model;
// Generated Apr 28, 2013 11:02:59 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Rate generated by hbm2java
 */
@Entity
@Table(name = "RATE", catalog = "DG_SYSTEM")
public class Rate implements java.io.Serializable {

	private String id;
	private String name;
	private String rateRule;
	private String detailsRule;
	private int priority;
	private String memo;
	public Rate() {
	}

	public Rate(String id, String name, String rateRule, int priority) {
		this.id = id;
		this.name = name;
		this.rateRule = rateRule;
		this.priority = priority;
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

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RATE_RULE", nullable = false, length = 500)
	@NotNull
	@Size(max = 500)
	public String getRateRule() {
		return this.rateRule;
	}

	public void setRateRule(String rateRule) {
		this.rateRule = rateRule;
	}

	@Column(name = "DETAILS_RULE", length = 500)
	@Size(max = 500)
	public String getDetailsRule() {
		return this.detailsRule;
	}

	public void setDetailsRule(String detailsRule) {
		this.detailsRule = detailsRule;
	}

	@Column(name = "PRIORITY", nullable = false)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
