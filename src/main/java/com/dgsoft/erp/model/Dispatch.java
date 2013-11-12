package com.dgsoft.erp.model;
// Generated Oct 30, 2013 3:06:10 PM by Hibernate Tools 4.0.0

import com.dgsoft.erp.model.api.DeliveryType;
import com.dgsoft.erp.model.api.FarePayType;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dispatch generated by hbm2java
 */
@Entity
@Table(name = "DISPATCH", catalog = "MINI_ERP", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NEED_RES", "STORE"}))
public class Dispatch implements java.io.Serializable {

    public enum DispatchState{
        DISPATCH_COMPLETE,DISPATCH_STORE_OUT,SEND_COMPLETE,ALL_COMPLETE;
    }

	private String id;
	private Store store;
	private StockChange stockChange;
	private ExpressInfo expressInfo;
	private ProductToDoor productToDoor;
	private ExpressCar expressCar;
	private NeedRes needRes;
	private DeliveryType deliveryType;
	private Date sendTime;
	private FarePayType farePayType;
	private BigDecimal fare;
	private String sendEmp;
	private DispatchState state;
	private String outCustomer;
	private String memo;

    private Set<DispatchItem> dispatchItems = new HashSet<DispatchItem>(0);

	public Dispatch() {
	}

    public Dispatch(NeedRes needRes, Store store,
                    DeliveryType deliveryType, FarePayType farePayType,  String memo, DispatchState state) {
        this.deliveryType = deliveryType;
        this.farePayType = farePayType;
        this.store = store;
        this.memo = memo;
        this.state = state;
        this.needRes = needRes;
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
	@JoinColumn(name = "STORE", nullable = false)
	@NotNull
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_CHANGE")
	public StockChange getStockChange() {
		return this.stockChange;
	}

	public void setStockChange(StockChange stockChange) {
		this.stockChange = stockChange;
	}

	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "EXPRESS_INFO")
	public ExpressInfo getExpressInfo() {
		return this.expressInfo;
	}

	public void setExpressInfo(ExpressInfo expressInfo) {
		this.expressInfo = expressInfo;
	}

	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "PRODUCT_TO_DOOR")
	public ProductToDoor getProductToDoor() {
		return this.productToDoor;
	}

	public void setProductToDoor(ProductToDoor productToDoor) {
		this.productToDoor = productToDoor;
	}

	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "EXPRESS_CAR")
	public ExpressCar getExpressCar() {
		return this.expressCar;
	}

	public void setExpressCar(ExpressCar expressCar) {
		this.expressCar = expressCar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEED_RES", nullable = false)
	@NotNull
	public NeedRes getNeedRes() {
		return this.needRes;
	}

	public void setNeedRes(NeedRes needRes) {
		this.needRes = needRes;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "DELIVERY_TYPE", nullable = false, length = 32)
	@NotNull
	public DeliveryType getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "FARE_PAY_TYPE", nullable = false, length = 32)
	@NotNull
	public FarePayType getFarePayType() {
		return this.farePayType;
	}

	public void setFarePayType(FarePayType farePayType) {
		this.farePayType = farePayType;
	}

	@Column(name = "FARE", scale = 3)
	public BigDecimal getFare() {
		return this.fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	@Column(name = "SEND_EMP", length = 32)
	@Size(max = 32)
	public String getSendEmp() {
		return this.sendEmp;
	}

	public void setSendEmp(String sendEmp) {
		this.sendEmp = sendEmp;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "STATE", nullable = false, length = 20)
	@NotNull
	public DispatchState getState() {
		return this.state;
	}

	public void setState(DispatchState state) {
		this.state = state;
	}

	@Column(name = "OUT_CUSTOMER", length = 50)
	@Size(max = 50)
	public String getOutCustomer() {
		return this.outCustomer;
	}

	public void setOutCustomer(String outCustomer) {
		this.outCustomer = outCustomer;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "dispatch",orphanRemoval = true,cascade = {CascadeType.ALL})
    public Set<DispatchItem> getDispatchItems() {
        return dispatchItems;
    }

    public void setDispatchItems(Set<DispatchItem> dispatchItems) {
        this.dispatchItems = dispatchItems;
    }

    @Transient
    public List<DispatchItem> getDispatchItemList(){
        List<DispatchItem> result = new ArrayList<DispatchItem>(getDispatchItems());
        Collections.sort(result,new Comparator<DispatchItem>() {
            @Override
            public int compare(DispatchItem o1, DispatchItem o2) {
                return o1.getStoreRes().getId().compareTo(o2.getStoreRes().getId());
            }
        });
        return result;
    }
}
