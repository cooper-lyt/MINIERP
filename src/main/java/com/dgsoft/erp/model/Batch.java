package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Batch generated by hbm2java
 */
@Entity
@Table(name = "BATCH", catalog = "MINI_ERP")
public class Batch implements java.io.Serializable {

    private String id;
    private Supplier supplier;
    private boolean produce;
    private boolean storeIn;
    private Date proDate;
    private Date expDate;
    private Date lastInTime;
    private MaterialStoreOut materialStoreOut;
    private boolean defaultBatch;
    private StoreRes storeRes;

    public Batch() {
    }

    public Batch(String id, Date proDate, Date expDate) {
        this.id = id;
        this.proDate = proDate;
        this.expDate = expDate;

    }

    public Batch(String id, StoreRes storeRes, boolean defaultBatch, boolean produce, boolean storeIn, Date lastInTime) {
        this.id = id;
        this.defaultBatch = defaultBatch;
        this.produce = produce;
        this.storeIn = storeIn;
        this.lastInTime = lastInTime;
        this.storeRes = storeRes;
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
    @JoinColumn(name = "SUPPLIER")
    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRO_DATE", nullable = true, length = 19, columnDefinition = "DATETIME")
    public Date getProDate() {
        return this.proDate;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXP_DATE", nullable = true, length = 19, columnDefinition = "DATETIME")
    public Date getExpDate() {
        return this.expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_IN_TIME", nullable = true, length = 19)
    public Date getLastInTime() {
        return lastInTime;
    }

    public void setLastInTime(Date firstInTime) {
        this.lastInTime = firstInTime;
    }

    @Column(name = "DEFAULT_BATCH", nullable = false)
    public boolean isDefaultBatch() {
        return defaultBatch;
    }

    public void setDefaultBatch(boolean defaultBatch) {
        this.defaultBatch = defaultBatch;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_RES", nullable = false)
    @NotNull
    public StoreRes getStoreRes() {
        return storeRes;
    }

    public void setStoreRes(StoreRes storeRes) {
        this.storeRes = storeRes;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, mappedBy = "batch")
    public MaterialStoreOut getMaterialStoreOut() {
        return this.materialStoreOut;
    }

    public void setMaterialStoreOut(MaterialStoreOut materialStoreOut) {
        this.materialStoreOut = materialStoreOut;
    }

    @Column(name = "PRODUCE", nullable = false)
    public boolean isProduce() {
        return produce;
    }

    public void setProduce(boolean product) {
        this.produce = product;
    }

    @Column(name = "STORE_IN", nullable = false)
    public boolean isStoreIn() {
        return storeIn;
    }

    public void setStoreIn(boolean storeIn) {
        this.storeIn = storeIn;
    }

}
