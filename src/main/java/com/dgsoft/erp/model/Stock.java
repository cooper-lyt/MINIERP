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
	private StoreArea storeArea;
	private BigDecimal count;
	private Set<StockChangeItem> stockChangeItems = new HashSet<StockChangeItem>(
			0);

	public Stock() {
	}

	public Stock(StoreRes storeRes, StoreArea storeArea,
                 BigDecimal count) {
		this.storeRes = storeRes;
		this.storeArea = storeArea;
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

	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "RES", nullable = false)
	@NotNull
	public StoreRes getStoreRes() {
		return this.storeRes;
	}

	public void setStoreRes(StoreRes storeRes) {
		this.storeRes = storeRes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_AREA", nullable = false)
	@NotNull
	public StoreArea getStoreArea() {
		return this.storeArea;
	}

	public void setStoreArea(StoreArea storeArea) {
		this.storeArea = storeArea;
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


    @Transient
    public List<StockChangeItem> getStoreChangeItemList(){
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
