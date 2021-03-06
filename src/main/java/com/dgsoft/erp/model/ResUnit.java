package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.OrderModel;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * ResUnit generated by hbm2java
 */
@Entity
@Cacheable
@Table(name = "RES_UNIT", catalog = "MINI_ERP")
public class ResUnit implements java.io.Serializable,OrderModel {

	private String id;
	private UnitGroup unitGroup;
	private String name;
    private String countFormate;
	private BigDecimal conversionRate;
	private int priority;
	private Set<Res> resesForOutDefault = new HashSet<Res>(0);
	private Set<Res> resesForMasterUnit = new HashSet<Res>(0);
	private Set<Res> resesForInDefault = new HashSet<Res>(0);
    private Set<NoConvertCount> noConvertCounts = new HashSet<NoConvertCount>(0);
    private Set<OrderItem> orderItemsByMiddleUnit = new HashSet<OrderItem>(0);
    private Set<OrderItem> orderItemsByMoneyUnit = new HashSet<OrderItem>(0);
    private Set<BackItem> backItems = new HashSet<BackItem>(0);
    private Set<OrderItemRebate> orderItemRebates = new HashSet<OrderItemRebate>(0);
    private Set<StoreResRebate> storeResRebates = new HashSet<StoreResRebate>(0);
    private Set<ResSaleRebate> resSaleRebates = new HashSet<ResSaleRebate>(0);
    private Set<SalerPrice> salerPrices = new HashSet<SalerPrice>(0);
    private Set<SalerStoreResPrice> salerStoreResPrices = new HashSet<SalerStoreResPrice>(0);

	public ResUnit() {
        this.conversionRate = new BigDecimal(0);
	}

    public ResUnit(UnitGroup unitGroup){
        this.unitGroup = unitGroup;
        this.conversionRate = new BigDecimal(0);
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNIT_GROUP", nullable = false)
	@NotNull
	public UnitGroup getUnitGroup() {
		return this.unitGroup;
	}

	public void setUnitGroup(UnitGroup unitGroup) {
		this.unitGroup = unitGroup;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name="COUNT_FORMAT",nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    public String getCountFormate() {
        return countFormate;
    }

    public void setCountFormate(String countFormate) {
        this.countFormate = countFormate;
    }

    @Column(name = "CONVERSION_RATE", scale = 10)
	public BigDecimal getConversionRate() {
		return this.conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	@Column(name = "PRIORITY", nullable = false)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnitByOutDefault")
	public Set<Res> getResesForOutDefault() {
		return this.resesForOutDefault;
	}

	public void setResesForOutDefault(Set<Res> resesForOutDefault) {
		this.resesForOutDefault = resesForOutDefault;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnitByMasterUnit")
	public Set<Res> getResesForMasterUnit() {
		return this.resesForMasterUnit;
	}

	public void setResesForMasterUnit(Set<Res> resesForMasterUnit) {
		this.resesForMasterUnit = resesForMasterUnit;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnitByInDefault")
	public Set<Res> getResesForInDefault() {
		return this.resesForInDefault;
	}

	public void setResesForInDefault(Set<Res> resesForInDefault) {
		this.resesForInDefault = resesForInDefault;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnit")
    public Set<NoConvertCount> getNoConvertCounts() {
        return this.noConvertCounts;
    }

    public void setNoConvertCounts(Set<NoConvertCount> noConvertCounts) {
        this.noConvertCounts = noConvertCounts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "middleUnit")
    public Set<OrderItem> getOrderItemsByMiddleUnit() {
        return orderItemsByMiddleUnit;
    }

    public void setOrderItemsByMiddleUnit(Set<OrderItem> orderItems) {
        this.orderItemsByMiddleUnit = orderItems;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnit")
    public Set<OrderItem> getOrderItemsByMoneyUnit() {
        return orderItemsByMoneyUnit;
    }

    public void setOrderItemsByMoneyUnit(Set<OrderItem> orderItemsByMoneyUnit) {
        this.orderItemsByMoneyUnit = orderItemsByMoneyUnit;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resUnit")
    public Set<BackItem> getBackItems() {
        return backItems;
    }

    public void setBackItems(Set<BackItem> backItems) {
        this.backItems = backItems;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcUnit")
    public Set<OrderItemRebate> getOrderItemRebates() {
        return orderItemRebates;
    }

    public void setOrderItemRebates(Set<OrderItemRebate> orderItemRebates) {
        this.orderItemRebates = orderItemRebates;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcUnit")
    public Set<StoreResRebate> getStoreResRebates() {
        return storeResRebates;
    }

    public void setStoreResRebates(Set<StoreResRebate> storeResRebates) {
        this.storeResRebates = storeResRebates;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resUnit")
    public Set<ResSaleRebate> getResSaleRebates() {
        return resSaleRebates;
    }

    public void setResSaleRebates(Set<ResSaleRebate> resSaleRebates) {
        this.resSaleRebates = resSaleRebates;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resUnit")
    public Set<SalerPrice> getSalerPrices() {
        return salerPrices;
    }

    public void setSalerPrices(Set<SalerPrice> salerPrices) {
        this.salerPrices = salerPrices;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resUnit")
    public Set<SalerStoreResPrice> getSalerStoreResPrices() {
        return salerStoreResPrices;
    }

    public void setSalerStoreResPrices(Set<SalerStoreResPrice> salerStoreResPrices) {
        this.salerStoreResPrices = salerStoreResPrices;
    }

    @Transient
    public boolean isMasterUnit(){
        return getConversionRate().compareTo(new BigDecimal("1")) == 0;
    }

    @Transient
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ResUnit))
            return false;

        ResUnit castOther = (ResUnit) other;
        if ((this.getId() == null) || (this.getId().equals("")) || (castOther.getId() == null) || (castOther.getId().equals("")) ){
            return false;
        }
        return getId().equals(castOther.getId());
    }

    @Transient
    public int hashCode() {
        return 37 * 17 + (getId() == null ? super.hashCode() : getId().hashCode());
    }
}
