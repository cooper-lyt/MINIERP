package com.dgsoft.erp.model;
// Generated Oct 20, 2013 6:30:11 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * StoreChange generated by hbm2java
 */
@Entity
@Table(name = "STORE_CHANGE", catalog = "MINI_ERP")
public class StoreChange implements java.io.Serializable {

    public enum StoreChangeType{

        BORROW_OUT_OUT(true),BORROW_OUT_IN(false),
        BORROW_IN_IN(false),BORROW_IN_OUT(true),
        OTHER_IN(false),OTHER_OUT(true);

        private boolean out;

        public boolean isOut() {
            return out;
        }

        public void setOut(boolean out) {
            this.out = out;
        }

        private StoreChangeType(boolean out){
           this.out = out;
        }
    }

	private String id;
	private StockChange stockChange;
	private StoreChangeType type;
	private String memo;

	public StoreChange() {
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


    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_IN", nullable = false)
    @NotNull
    public StockChange getStockChange() {
        return stockChange;
    }

    public void setStockChange(StockChange stockChange) {
        this.stockChange = stockChange;
    }

    @Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false, length = 32)
	@NotNull
	public StoreChangeType getType() {
		return this.type;
	}

	public void setType(StoreChangeType type) {
		this.type = type;
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
