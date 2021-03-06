package com.dgsoft.erp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 14-5-13
 * Time: 上午10:13
 */
@Entity
@Table(name = "ACCOUNT_CHECK_OUT", catalog = "MINI_ERP",uniqueConstraints = @UniqueConstraint(columnNames = {"ACCOUNT_CODE", "CHECKOUT"}))
public class AccountCheckout implements Serializable {

    private String id;

    private BigDecimal beginningBalance;
    private BigDecimal beginningCount;
    private BigDecimal closingBalance;
    private BigDecimal closingCount;

    private BigDecimal debitMoney;
    private BigDecimal debitCount;

    private BigDecimal creditMoney;
    private BigDecimal creditCount;

    private Integer version;

    private Checkout checkout;

    private String accountCode;

    public AccountCheckout() {
    }

    public AccountCheckout(String accountCode, Checkout checkout, BigDecimal beginningBalance, BigDecimal beginningCount) {
        this.accountCode = accountCode;
        this.checkout = checkout;
        this.beginningBalance = beginningBalance;
        this.beginningCount = beginningCount;
        this.debitMoney = BigDecimal.ZERO;
        this.debitCount = BigDecimal.ZERO;
        this.creditCount = BigDecimal.ZERO;
        this.creditMoney = BigDecimal.ZERO;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @NotNull
    @Size(max = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="ACCOUNT_CODE",nullable = false,length = 50)
    @Size(max = 50)
    @NotNull
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CHECKOUT",nullable = false)
    @NotNull
    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "BEGINNING_BALANCE",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(BigDecimal beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    @Column(name = "BEGINNING_COUNT",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getBeginningCount() {
        return beginningCount;
    }

    public void setBeginningCount(BigDecimal beginningCount) {
        this.beginningCount = beginningCount;
    }

    @Column(name = "CLOSING_BALANCE",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }

    @Column(name = "CLOSING_COUNT",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getClosingCount() {
        return closingCount;
    }

    public void setClosingCount(BigDecimal closingCount) {
        this.closingCount = closingCount;
    }

    @Column(name = "DEBIT_MONEY",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getDebitMoney() {
        return debitMoney;
    }

    public void setDebitMoney(BigDecimal debitMoney) {
        this.debitMoney = debitMoney;
    }

    @Column(name = "DEBIT_COUNT",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getDebitCount() {
        return debitCount;
    }

    public void setDebitCount(BigDecimal debitCount) {
        this.debitCount = debitCount;
    }

    @Column(name = "CREDIT_MONEY",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    @Column(name = "CREDIT_COUNT",scale = 4,nullable = false)
    @NotNull
    public BigDecimal getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(BigDecimal creditCount) {
        this.creditCount = creditCount;
    }


}
