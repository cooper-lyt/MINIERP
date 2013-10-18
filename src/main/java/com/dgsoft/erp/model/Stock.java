package com.dgsoft.erp.model;
// Generated Oct 1, 2013 5:41:32 PM by Hibernate Tools 4.0.0

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Stock generated by hbm2java
 */
@Entity
@Table(name = "STOCK", catalog = "MINI_ERP")
public class Stock implements java.io.Serializable {

    private String id;
    private Integer version;
    private StoreRes storeRes;
    private Store store;
    private BigDecimal count;
    private BigDecimal floatConversionRate;
    private Set<BatchStoreCount> batchStoreCounts = new HashSet<BatchStoreCount>(0);
    private Set<Depositary> depositaries = new HashSet<Depositary>(0);
    private Set<StockChangeItem> stockChangeItems = new HashSet<StockChangeItem>(0);

    public Stock() {
    }

    public Stock(StoreRes storeRes, BigDecimal count) {
        this.storeRes = storeRes;
        this.count = count;
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

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "RES", nullable = false)
    @NotNull
    public StoreRes getStoreRes() {
        return this.storeRes;
    }

    public void setStoreRes(StoreRes storeRes) {
        this.storeRes = storeRes;
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

    @Column(name = "COUNT", nullable = false, scale = 4)
    @NotNull
    public BigDecimal getCount() {
        return this.count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @Column(name = "FLOAT_CONVERSION_RATE", scale = 10)
    public BigDecimal getFloatConversionRate() {
        return this.floatConversionRate;
    }

    public void setFloatConversionRate(BigDecimal floatConversionRate) {
        this.floatConversionRate = floatConversionRate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<BatchStoreCount> getBatchStoreCounts() {
        return this.batchStoreCounts;
    }

    public void setBatchStoreCounts(Set<BatchStoreCount> batchStoreCounts) {
        this.batchStoreCounts = batchStoreCounts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<Depositary> getDepositaries() {
        return this.depositaries;
    }

    public void setDepositaries(Set<Depositary> depositaries) {
        this.depositaries = depositaries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<StockChangeItem> getStockChangeItems() {
        return this.stockChangeItems;
    }

    public void setStockChangeItems(Set<StockChangeItem> stockChangeItems) {
        this.stockChangeItems = stockChangeItems;
    }


    @Transient
    public List<StockChangeItem> getStoreChangeItemList() {
        List<StockChangeItem> result = new ArrayList<StockChangeItem>(getStockChangeItems());
        Collections.sort(result, new Comparator<StockChangeItem>() {
            @Override
            public int compare(StockChangeItem stockChangeItem, StockChangeItem stockChangeItem2) {
                return stockChangeItem.getStockChange().getOperDate().compareTo(stockChangeItem2.getStockChange().getOperDate());
            }
        });
        return result;
    }

}
