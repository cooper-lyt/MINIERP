package com.dgsoft.erp.model;

import com.dgsoft.erp.model.api.ResCount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by cooper on 1/5/14.
 */
@Entity
@Table(name = "OVERLY_OUT", catalog = "MINI_ERP")
public class OverlyOut {

    private String id;
    private StoreRes storeRes;
    private BigDecimal count;
    private String description;
    private Dispatch dispatch;
    private boolean addTo;
    private OrderItem orderItem;

    public OverlyOut() {
    }

    public OverlyOut(Dispatch dispatch, StoreRes storeRes, BigDecimal count) {
        this.storeRes = storeRes;
        this.count = count;
        this.dispatch = dispatch;
        this.addTo = false;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @NotNull
    @Size(max = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "COUNT", nullable = false, scale = 4)
    @NotNull
    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @Column(name = "ADD_TO", nullable = false)
    public boolean isAddTo() {
        return addTo;
    }

    public void setAddTo(boolean addTo) {
        this.addTo = addTo;
    }

    @Column(name = "DESCRIPTION", length = 200)
    @Size(max = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, mappedBy = "overlyOut", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_RES", nullable = false)
    public StoreRes getStoreRes() {
        return storeRes;
    }

    public void setStoreRes(StoreRes storeRes) {
        this.storeRes = storeRes;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISPATCH", nullable = false)
    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    @Transient
    public ResCount getResCount() {
        return storeRes.getResCount(getCount());
    }

}
