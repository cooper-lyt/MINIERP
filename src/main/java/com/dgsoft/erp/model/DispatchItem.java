package com.dgsoft.erp.model;
// Generated Oct 30, 2013 1:46:18 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.ResCount;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public DispatchItem() {
    }


    public DispatchItem(ResUnit resUnit, BigDecimal count, Dispatch dispatch,
                        StoreRes storeRes) {
        this.count = count;
        this.dispatch = dispatch;
        this.storeRes = storeRes;
        this.resUnit = resUnit;
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


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_RES", nullable = false)
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

    @Transient
    public ResCount getStockCount() {
        for(Stock stock: getStoreRes().getStocks()){
            if (stock.getStore().getId().equals(getDispatch().getStore().getId())){
                return stock.getResCount();
            }
        }
        return getStoreRes().getResCount(BigDecimal.ZERO,getStoreRes().getRes().getUnitGroup().getMasterUnit());
    }

    @Transient
    public boolean isEnough() {
        return getStockCount().getMasterCount().compareTo(getResCount().getMasterCount()) >= 0;
    }

    @Transient
    public ResCount getDisparity() {

        ResCount result = getResCount();
        result.subtract(getStockCount());
        return getStoreRes().getResCount(result.getMasterCount(), getStoreRes().getRes().getUnitGroup().getMasterUnit());
    }


}
