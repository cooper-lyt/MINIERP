package com.dgsoft.erp.model;
// Generated Nov 9, 2014 12:57:57 PM by Hibernate Tools 4.0.0

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * InventoryItem generated by hbm2java
 */
@Entity
@Table(name = "INVENTORY_ITEM", catalog = "MINI_ERP",uniqueConstraints = @UniqueConstraint(columnNames = {"STOCK","INVENTORY"}) )

public class InventoryItem implements java.io.Serializable {

    public enum InventoryItemChangeType{
        NO_CHANGE,INVENTORY_ADD,INVENTORY_LOSS;
    }

	private String id;
	private Stock stock;
	private StockChangeItem stockChangeItem;
	private Inventory inventory;
	private BigDecimal beforCount;
	private BigDecimal lastCount;
	private InventoryItemChangeType changeType;
	private BigDecimal changeCount;
	private String memo;

	public InventoryItem() {
	}

    public InventoryItem(BigDecimal beforCount,BigDecimal lastCount,Inventory inventory, Stock stock) {
        this.lastCount = lastCount;
        this.beforCount = beforCount;
        this.inventory = inventory;
        this.stock = stock;
        this.changeType = InventoryItemChangeType.NO_CHANGE;
        changeCount = BigDecimal.ZERO;
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STOCK", nullable = false)
    @NotNull
    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTORY", nullable = false)
    @NotNull
    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CHANGE_ITEM")
	public StockChangeItem getStockChangeItem() {
		return this.stockChangeItem;
	}

	public void setStockChangeItem(StockChangeItem stockChangeItem) {
		this.stockChangeItem = stockChangeItem;
	}

	@Column(name = "BEFOR_COUNT", nullable = false, scale = 4)
	@NotNull
	public BigDecimal getBeforCount() {
		return this.beforCount;
	}

	public void setBeforCount(BigDecimal beforCount) {
		this.beforCount = beforCount;
	}

	@Column(name = "LAST_COUNT", nullable = false, scale = 4)
	@NotNull
	public BigDecimal getLastCount() {
		return this.lastCount;
	}

	public void setLastCount(BigDecimal lastCount) {
		this.lastCount = lastCount;
	}

    @Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_TYPE", nullable = false, length = 20)
	@NotNull
	public InventoryItemChangeType getChangeType() {
		return this.changeType;
	}

	public void setChangeType(InventoryItemChangeType changeType) {
		this.changeType = changeType;
	}

	@Column(name = "CHANGE_COUNT", nullable = true, scale = 4)
	public BigDecimal getChangeCount() {
		return this.changeCount;
	}

	public void setChangeCount(BigDecimal changeCount) {
		this.changeCount = changeCount;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


}
