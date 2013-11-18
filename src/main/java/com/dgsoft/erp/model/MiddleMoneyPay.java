package com.dgsoft.erp.model;
// Generated Nov 5, 2013 1:32:07 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.PayType;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * MiddleMoneyPay generated by hbm2java
 */
@Entity
@Table(name = "MIDDLE_MONEY_PAY", catalog = "MINI_ERP")
public class MiddleMoneyPay implements java.io.Serializable {

	private String id;
	private CustomerOrder customerOrder;
	private MiddleMan middleMan;
	private BigDecimal money;
	private Date operDate;
	private String operEmp;
	private String description;
	private PayType payType;
	private String checkNumber;

	public MiddleMoneyPay() {
	}

    public MiddleMoneyPay(CustomerOrder customerOrder, MiddleMan middleMan,
                          BigDecimal money, Date operDate, String operEmp,
                          String description, PayType payType, String checkNumber) {

        this.customerOrder = customerOrder;
        this.middleMan = middleMan;
        this.money = money;
        this.operDate = operDate;
        this.operEmp = operEmp;
        this.description = description;
        this.payType = payType;
        this.checkNumber = checkNumber;
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

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ORDER", nullable = false)
	@NotNull
	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MIDDLE_MAN", nullable = false)
	@NotNull
	public MiddleMan getMiddleMan() {
		return this.middleMan;
	}

	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}

	@Column(name = "MONEY", nullable = false, scale = 3)
	@NotNull
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

	@Column(name = "OPER_EMP", nullable = false, length = 32)
	@NotNull
	@Size(max = 32)
	public String getOperEmp() {
		return this.operEmp;
	}

	public void setOperEmp(String operEmp) {
		this.operEmp = operEmp;
	}

	@Column(name = "DESCRIPTION", length = 200)
	@Size(max = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "PAY_TYPE", nullable = false, length = 32)
	@NotNull
	public PayType getPayType() {
		return this.payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	@Column(name = "CHECK_NUMBER", length = 50)
	@Size(max = 50)
	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

}
