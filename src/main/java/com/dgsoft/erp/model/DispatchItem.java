package com.dgsoft.erp.model;
// Generated Oct 30, 2013 1:46:18 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.ResCount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DispatchItem generated by hbm2java
 */
@Entity
@Table(name = "DISPATCH_ITEM", catalog = "MINI_ERP")
public class DispatchItem implements java.io.Serializable {

    private String id;
    private BigDecimal count;
    private Dispatch dispatch;
    private StoreRes storeRes;
    private ResUnit resUnit;
    private Res res;
    private boolean storeResItem;

    public DispatchItem() {
    }


    public DispatchItem(ResUnit resUnit, BigDecimal count, Dispatch dispatch,
                        StoreRes storeRes) {
        this.count = count;
        this.dispatch = dispatch;
        this.storeRes = storeRes;
        this.resUnit = resUnit;
        storeResItem = true;
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

    @Column(name = "COUNT", scale = 4, nullable = false)
    @NotNull
    public BigDecimal getCount() {
        return this.count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISPATCH", nullable = false)
    @NotNull
    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_RES")
    @NotNull
    public StoreRes getStoreRes() {
        return storeRes;
    }

    public void setStoreRes(StoreRes storeRes) {
        this.storeRes = storeRes;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNT_UNIT", nullable = false)
    @NotNull
    public ResUnit getResUnit() {
        return resUnit;
    }

    public void setResUnit(ResUnit resUnit) {
        this.resUnit = resUnit;
    }


    @Column(name = "STORE_RES_ITEM", nullable = false)
    public boolean isStoreResItem() {
        return storeResItem;
    }

    public void setStoreResItem(boolean storeResItem) {
        this.storeResItem = storeResItem;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "RES", nullable = true)
    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    @Transient
    public ResCount getResCount() {
        if (getStoreRes().getRes().getUnitGroup().getType().equals(UnitGroup.UnitGroupType.FLOAT_CONVERT)) {
            return new ResCount(getCount(), getResUnit(), getStoreRes().getFloatConversionRate());
        } else {
            return new ResCount(getCount(), getResUnit());
        }
    }

    @Transient
    public void addResCount(ResCount addCount) {
        if (!addCount.canMerger(getResCount()))
            throw new IllegalArgumentException("Res count cant add");
        setCount(getCount().add(addCount.getCountByResUnit(getResUnit())));
    }
}
