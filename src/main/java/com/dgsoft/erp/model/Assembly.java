package com.dgsoft.erp.model;
// Generated Oct 17, 2013 5:33:51 PM by Hibernate Tools 4.0.0

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Assembly generated by hbm2java
 */
@Entity
@Table(name = "ASSEMBLY", catalog = "MINI_ERP")
public class Assembly implements java.io.Serializable {

	private String id;

	private String reason;
	private String memo;
	private String assemblyEmp;
    private List<StockChange> stockChanges;

	public Assembly() {
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "assembly")
    public List<StockChange> getStockChanges() {
        return stockChanges;
    }

    public void setStockChanges(List<StockChange> stockChanges) {
        this.stockChanges = stockChanges;
    }

    @Transient
	public StockChange getStockChangeByStoreIn() {
		for(StockChange change: getStockChanges()){
            if(StockChange.StoreChangeType.ASSEMBLY_IN.equals(change.getOperType())){
                return change;
            }
        }
        return null;
	}

    @Transient
	public StockChange getStockChangeByStoreOut() {
        for(StockChange change: getStockChanges()){
            if(StockChange.StoreChangeType.ASSEMBLY_OUT.equals(change.getOperType())){
                return change;
            }
        }
        return null;
	}



    @Transient
    public StockChange getStockChangeByLoseOut() {
        for(StockChange change: getStockChanges()){
            if(StockChange.StoreChangeType.SCRAP_OUT.equals(change.getOperType())){
                return change;
            }
        }
        return null;
    }



    @Column(name = "REASON", nullable = false, length = 32)
	@NotNull
	@Size(max = 32)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "MEMO", length = 200)
	@Size(max = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "ASSEMBLY_EMP", length = 32)
	@Size(max = 32)
	public String getAssemblyEmp() {
		return this.assemblyEmp;
	}

	public void setAssemblyEmp(String assemblyEmp) {
		this.assemblyEmp = assemblyEmp;
	}

}
