package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * BackDispatch generated by hbm2java
 */
@Entity
@Table(name = "BACK_DISPATCH", catalog = "MINI_ERP")
public class BackDispatch implements java.io.Serializable {

    private String id;
    private OrderBack orderBack;
    private StockChange stockChange;
    private Store store;

    private boolean storeOut;

    private Set<BackItem> backItems = new HashSet<BackItem>(0);

    public BackDispatch() {
    }

    public BackDispatch(OrderBack orderBack, Store store) {
        this.orderBack = orderBack;
        this.store = store;
        storeOut = false;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "CUSTOMER_ORDER", nullable = false)
    @NotNull
    public OrderBack getOrderBack() {
        return this.orderBack;
    }

    public void setOrderBack(OrderBack orderBack) {
        this.orderBack = orderBack;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "STOCK_CNAHGE", nullable = true)
    public StockChange getStockChange() {
        return this.stockChange;
    }

    public void setStockChange(StockChange stockChange) {
        this.stockChange = stockChange;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE", nullable = false)
    @NotNull
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Column(name = "STORE_OUT", nullable = false)
    public boolean isStoreOut() {
        return storeOut;
    }

    public void setStoreOut(boolean storeOut) {
        this.storeOut = storeOut;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dispatch", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    public Set<BackItem> getBackItems() {
        return backItems;
    }

    public void setBackItems(Set<BackItem> backItems) {
        this.backItems = backItems;
    }

    @Transient
    public List<BackItem> getBackItemList() {
        List<BackItem> result = new ArrayList<BackItem>(getBackItems());
        Collections.sort(result, new Comparator<BackItem>() {
            @Override
            public int compare(BackItem o1, BackItem o2) {
                return o1.getStoreRes().compareTo(o2.getStoreRes());
            }
        });
        return result;
    }
}
