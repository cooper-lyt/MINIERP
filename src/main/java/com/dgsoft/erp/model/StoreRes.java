package com.dgsoft.erp.model;
// Generated Oct 1, 2013 5:41:32 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.action.ResHelper;
import com.dgsoft.erp.action.store.StoreChangeHelper;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * StoreRes generated by hbm2java
 */
@Entity
@Table(name = "STORE_RES", catalog = "MINI_ERP",uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class StoreRes implements java.io.Serializable {

    private String id;
    private Res res;
    private Set<StockChangeItem> stockChangeItems = new HashSet<StockChangeItem>(0);
    private Set<Format> formats = new HashSet<Format>(0);

    private String code;
    private BigDecimal storeWarn;
    private Integer expWarn;

    private BigDecimal floatConversionRate;

    private Set<AllocationRes> allocationReses = new HashSet<AllocationRes>(0);
    private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
    private Set<DispatchItem> dispatchItems = new HashSet<DispatchItem>(0);

    private Stock stock;
    private boolean enable;

    public StoreRes() {
        enable = true;
    }


    public StoreRes(Res res, Set<Format> formats) {
        this.res = res;
        this.formats = formats;
        enable = true;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RES", nullable = false)
    @NotNull
    public Res getRes() {
        return this.res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    @Column(name = "CODE", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "ENABLE", nullable = false)
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Column(name = "STORE_WARN", scale = 4)
    public BigDecimal getStoreWarn() {
        return this.storeWarn;
    }

    public void setStoreWarn(BigDecimal storeWarn) {
        this.storeWarn = storeWarn;
    }

    @Column(name = "EXP_WARN")
    public Integer getExpWarn() {
        return this.expWarn;
    }

    public void setExpWarn(Integer expWarn) {
        this.expWarn = expWarn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeRes")
    public Set<StockChangeItem> getStockChangeItems() {
        return this.stockChangeItems;
    }

    public void setStockChangeItems(Set<StockChangeItem> stockChangeItems) {
        this.stockChangeItems = stockChangeItems;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, mappedBy = "storeRes")
    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeRes", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<Format> getFormats() {
        return this.formats;
    }

    public void setFormats(Set<Format> formats) {
        this.formats = formats;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeRes")
    public Set<AllocationRes> getAllocationReses() {
        return this.allocationReses;
    }

    public void setAllocationReses(Set<AllocationRes> allocationReses) {
        this.allocationReses = allocationReses;
    }

    @Column(name = "FLOAT_CONVERSION_RATE", scale = 10)
    public BigDecimal getFloatConversionRate() {
        return this.floatConversionRate;
    }

    public void setFloatConversionRate(BigDecimal floatConversionRate) {
        this.floatConversionRate = floatConversionRate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeRes")
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "storeReses")
    public Set<DispatchItem> getDispatchItems() {
        return dispatchItems;
    }

    public void setDispatchItems(Set<DispatchItem> dispatchItems) {
        this.dispatchItems = dispatchItems;
    }

    @Transient
    public Format getFormat(String formatDefineId) {
        for (Format format : getFormats()) {
            if (format.getFormatDefine().getId().equals(formatDefineId)) {
                return format;
            }
        }
        return null;
    }

    @Transient
    public List<Format> getFormatList() {
        List<Format> result = new ArrayList<Format>(getFormats());
        Collections.sort(result, new Comparator<Format>() {
            @Override
            public int compare(Format o1, Format o2) {
                return new Integer(o1.getFormatDefine().getPriority()).compareTo(o2.getFormatDefine().getPriority());
            }
        });
        return result;
    }

    @Override
    @Transient
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoreRes)) {
            return false;
        }
        StoreRes other = (StoreRes) obj;
        if (!getRes().getId().equals(other.getRes().getId())) {
            return false;
        }

        return StoreChangeHelper.sameFormat(other.getFormats(), getFormats())
                && (!getRes().getUnitGroup().getType().equals(UnitGroup.UnitGroupType.FLOAT_CONVERT)
                || floatConversionRate.equals(other.floatConversionRate));
    }

    @Override
    @Transient
    public int hashCode() {
        String result = getRes().getId();
        for (Format format : getFormatList()) {
            result += "_" + format.getFormatDefine().getId() + ":" + format.getFormatValue();
        }
        return result.hashCode();
    }

}
