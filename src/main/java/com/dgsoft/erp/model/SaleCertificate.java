package com.dgsoft.erp.model;

import com.dgsoft.common.utils.finance.Certificate;
import com.dgsoft.common.utils.finance.CertificateItem;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 14-5-14
 * Time: 上午11:16
 */
@Entity
@Table(name = "SALE_PREPARED", catalog = "MINI_ERP")
public class SaleCertificate implements Serializable, Certificate {

    private String id;
    private String word;
    private int code;
    private Date date;
    private String preparedEmp;
    private String approvedEmp;
    private String checkedEmp;
    private String cashier;
    private String memo;
    private Integer vouchersCount;

    private BigDecimal money;

    private Set<AccountOper> accountOpers = new HashSet<AccountOper>(0);
    private Set<MoneySave> moneySaves = new HashSet<MoneySave>(0);
    private Set<SaleCertificateItem> saleCertificateItems = new HashSet<SaleCertificateItem>(0);

    public SaleCertificate() {
    }

    public SaleCertificate(String word, int code, Date date, String preparedEmp) {
        this.word = word;
        this.code = code;
        this.date = date;
        this.preparedEmp = preparedEmp;
    }

    public SaleCertificate(String word, int code, Date date, String preparedEmp,
                           String approvedEmp, String checkedEmp, String cashier, String memo, Integer vouchersCount) {
        this.word = word;
        this.code = code;
        this.date = date;
        this.preparedEmp = preparedEmp;
        this.approvedEmp = approvedEmp;
        this.checkedEmp = checkedEmp;
        this.cashier = cashier;
        this.memo = memo;
        this.vouchersCount = vouchersCount;
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

    @Override
    @Column(name = "_WORD", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    @Column(name = "_CODE", nullable = false)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CERTIFICATE_DATE", nullable = false, length = 19)
    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    @Column(name = "PREPARED_EMP", nullable = true, length = 32)
    @Size(max = 32)
    public String getPreparedEmp() {
        return preparedEmp;
    }

    public void setPreparedEmp(String preparedEmp) {
        this.preparedEmp = preparedEmp;
    }

    @Override
    @Column(name = "APPROVED_EMP", nullable = true, length = 32)
    @Size(max = 32)
    public String getApprovedEmp() {
        return approvedEmp;
    }

    public void setApprovedEmp(String approvedEmp) {
        this.approvedEmp = approvedEmp;
    }

    @Override
    @Column(name = "CHECKED_EMP", nullable = true, length = 32)
    @Size(max = 32)
    public String getCheckedEmp() {
        return checkedEmp;
    }

    public void setCheckedEmp(String checkedEmp) {
        this.checkedEmp = checkedEmp;
    }

    @Override
    @Column(name = "CASHIER", nullable = true, length = 32)
    @Size(max = 32)
    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    @Override
    @Column(name = "MEMO", nullable = true, length = 200)
    @Size(max = 200)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "VOUCHERS_COUNT", nullable = true)
    public Integer getVouchersCount() {
        return vouchersCount;
    }

    public void setVouchersCount(Integer vouchersCount) {
        this.vouchersCount = vouchersCount;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleCertificate")
    public Set<AccountOper> getAccountOpers() {
        return accountOpers;
    }

    public void setAccountOpers(Set<AccountOper> accountOpers) {
        this.accountOpers = accountOpers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleCertificate")
    public Set<MoneySave> getMoneySaves() {
        return moneySaves;
    }

    public void setMoneySaves(Set<MoneySave> moneySaves) {
        this.moneySaves = moneySaves;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleCertificate",orphanRemoval = true,cascade = {CascadeType.ALL})
    public Set<SaleCertificateItem> getSaleCertificateItems() {
        return saleCertificateItems;
    }

    public void setSaleCertificateItems(Set<SaleCertificateItem> saleCertificateItems) {
        this.saleCertificateItems = saleCertificateItems;
    }

    @Override
    @Column(name = "MONEY", nullable = false, scale = 4)
    @NotNull
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    @Transient
    public List<CertificateItem> getCertificateItems() {
       return new ArrayList<CertificateItem>(getSaleCertificateItems());
    }

    @Transient
    public void writeItem(){
        getSaleCertificateItems().clear();
        for (MoneySave moneySave : getMoneySaves()) {
            getSaleCertificateItems().addAll(moneySave.getCertificateItems());
        }
        for (AccountOper accountOper : getAccountOpers()) {
            getSaleCertificateItems().addAll(accountOper.getCertificateItems());
        }
    }

    @Transient
    @Override
    public List<CertificateItem> getItemByCodes(List<String> codes){
        List<CertificateItem> result = new ArrayList<CertificateItem>();
        for (CertificateItem item: getCertificateItems()){
            if (codes.contains(item.getAccountCode())){
                result.add(item);
            }
        }
        return result;
    }
}
