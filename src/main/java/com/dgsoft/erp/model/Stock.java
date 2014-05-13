package com.dgsoft.erp.model;
// Generated Oct 1, 2013 5:41:32 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.OrderBeanComparator;
import com.dgsoft.erp.model.api.StoreResCountEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * Stock generated by hbm2java
 */
@Entity
@Table(name = "STOCK", catalog = "MINI_ERP", uniqueConstraints = @UniqueConstraint(columnNames = {"RES", "STORE"}))
public class Stock extends StoreResCountEntity implements java.io.Serializable {

    private String id;
    private Integer version;
    private StoreRes storeRes;
    private Store store;
    private BigDecimal count;
    private Set<NoConvertCount> noConvertCounts = new HashSet<NoConvertCount>(0);
    private Set<StockChangeItem> stockChangeItems = new HashSet<StockChangeItem>(0);

    public Stock() {
    }

    public Stock(StoreRes storeRes, BigDecimal count) {
        this.storeRes = storeRes;
        this.count = count;
    }

    public Stock(Store store, StoreRes storeRes, BigDecimal count) {
        this.storeRes = storeRes;
        this.store = store;
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


    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<StockChangeItem> getStockChangeItems() {
        return this.stockChangeItems;
    }

    public void setStockChangeItems(Set<StockChangeItem> stockChangeItems) {
        this.stockChangeItems = stockChangeItems;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<NoConvertCount> getNoConvertCounts() {
        return this.noConvertCounts;
    }

    public void setNoConvertCounts(Set<NoConvertCount> noConvertCounts) {
        this.noConvertCounts = noConvertCounts;
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

    @Transient
    public List<NoConvertCount> getNoConvertCountList() {
        List<NoConvertCount> result = new ArrayList<NoConvertCount>(getNoConvertCounts());
        Collections.sort(result, OrderBeanComparator.getInstance());
        return result;
    }

}
