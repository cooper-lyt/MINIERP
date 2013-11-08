package com.dgsoft.erp.model;
// Generated Oct 28, 2013 12:46:39 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CustomerOrder generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER_ORDER", catalog = "MINI_ERP")
public class CustomerOrder implements java.io.Serializable {

    public enum OrderState{
        WAITING_PAY_SEND(false), WAITING_PAY_FIRST(false),WAITING_SEND(false),SENDING(false),
        ORDER_ACCOUNT(true),ORDER_ALL_COMPLETE(true),ORDER_OVERDRAFT_COMPLETE(true);

        private boolean completed;

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        private OrderState(boolean completed){
           this.completed = completed;
        }
    }

    public enum OrderPayType{
        COMPLETE_PAY,PAY_FIRST,EARNEST_FIRST,OVERDRAFT;
    }

    public enum MiddleMoneyCalcType{
       CONSULT_FIX,MIDDLE_RATE;
    }

	private String id;
	private Integer version;
	private Customer customer;
	private OrderState state;
    private String orderEmp;
	private OrderPayType payType;
	private Date createDate;
	private BigDecimal money;
	private Date completeDate;
	private BigDecimal profit;
	private String memo;
    private String contact;
    private String tel;
    private String address;
    private String postCode;
    private boolean payComplete;

    private BigDecimal totalMoney;
    private BigDecimal earnest;
    private BigDecimal totalRebate;
    private BigDecimal middleMoney;
    private BigDecimal totalCost;
    private BigDecimal middleRate;
    private BigDecimal middleTotal;

	private boolean includeMiddleMan;
    private MiddleMoneyCalcType middleMoneyCalcType;
	private Set<OrderBack> orderBacks = new HashSet<OrderBack>(0);
	private Set<MiddleMoneyPay> middleMoneyPays = new HashSet<MiddleMoneyPay>(0);

    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);
    private Set<NeedRes> needReses = new HashSet<NeedRes>(0);

	public CustomerOrder() {
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	@NotNull
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "STATE", nullable = false, length = 20)
	@NotNull
	public OrderState getState() {
		return this.state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "PAY_TYPE", nullable = false, length = 32)
	@NotNull
	public OrderPayType getPayType() {
		return this.payType;
	}

	public void setPayType(OrderPayType payType) {
		this.payType = payType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, length = 19)
	@NotNull
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    @Column(name = "ORDER_EMPLOYEE", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getOrderEmp() {
        return orderEmp;
    }

    public void setOrderEmp(String orderEmp) {
        this.orderEmp = orderEmp;
    }

    @Column(name = "MONEY", nullable = false, scale = 3)
	@NotNull
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

    @Column(name="TOTAL_MONEY", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Column(name = "EARNEST", scale = 3)
    public BigDecimal getEarnest() {
        return this.earnest;
    }

    public void setEarnest(BigDecimal realMoney) {
        this.earnest = realMoney;
    }

    @Column(name = "TOTAL_REBATE", nullable = false, scale = 4)
    @NotNull
    public BigDecimal getTotalRebate() {
        return this.totalRebate;
    }

    public void setTotalRebate(BigDecimal totalRebate) {
        this.totalRebate = totalRebate;
    }

    @Column(name = "MIDDLE_MONEY", scale = 3)
    public BigDecimal getMiddleMoney() {
        return this.middleMoney;
    }

    public void setMiddleMoney(BigDecimal middleMoney) {
        this.middleMoney = middleMoney;
    }

    @Column(name = "MIDDLE_TOTAL", scale = 3)
    public BigDecimal getMiddleTotal() {
        return middleTotal;
    }

    public void setMiddleTotal(BigDecimal middleTotal) {
        this.middleTotal = middleTotal;
    }

    @Column(name = "TOTAL_COST", nullable = false, scale = 3)
    @NotNull
    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Column(name = "MIDDLE_RATE", nullable = true, scale = 4)
    public BigDecimal getMiddleRate() {
        return this.middleRate;
    }

    public void setMiddleRate(BigDecimal middleRate) {
        this.middleRate = middleRate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "MIDDLE_CALC_TYPE",nullable = true)
    public MiddleMoneyCalcType getMiddleMoneyCalcType() {
        return middleMoneyCalcType;
    }

    public void setMiddleMoneyCalcType(MiddleMoneyCalcType middleMoneyCalcType) {
        this.middleMoneyCalcType = middleMoneyCalcType;
    }

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPLETE_DATE", length = 19)
	public Date getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "PROFIT", scale = 3)
	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<NeedRes> getNeedReses() {
        return needReses;
    }

    public void setNeedReses(Set<NeedRes> needReses) {
        this.needReses = needReses;
    }

    @Column(name="CONTACT",length = 50,nullable = false)
    @NotNull
    @Size(max = 50)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name="TEL",length = 50,nullable = false)
    @NotNull
    @Size(max = 50)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name="ADDRESS",length = 200,nullable = false)
    @NotNull
    @Size(max = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "POST_CODE",length = 10, nullable = false)
    @Size(max = 10)
    @NotNull
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name = "PAY_COMPLETE", nullable = false)
    public boolean isPayComplete() {
        return this.payComplete;
    }

    public void setPayComplete(boolean payComplete) {
        this.payComplete = payComplete;
    }

    @Column(name = "INCLUDE_MIDDLE_MAN", nullable = false)
	public boolean isIncludeMiddleMan() {
		return this.includeMiddleMan;
	}

	public void setIncludeMiddleMan(boolean middleManPay) {
		this.includeMiddleMan = middleManPay;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
	public Set<OrderBack> getOrderBacks() {
		return this.orderBacks;
	}

	public void setOrderBacks(Set<OrderBack> orderBacks) {
		this.orderBacks = orderBacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
	public Set<MiddleMoneyPay> getMiddleMoneyPays() {
		return this.middleMoneyPays;
	}

	public void setMiddleMoneyPays(Set<MiddleMoneyPay> middleMoneys) {
		this.middleMoneyPays = middleMoneys;
	}


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
    public Set<AccountOper> getAccountOpers() {
        return accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
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

}
