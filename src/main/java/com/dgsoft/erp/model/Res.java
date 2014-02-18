package com.dgsoft.erp.model;
// Generated Sep 25, 2013 4:34:50 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.OrderBeanComparator;
import com.dgsoft.erp.model.api.ResTreeNode;
import com.dgsoft.erp.tools.ResTreeFilter;
import com.dgsoft.erp.tools.StoreResPropertyTreeNode;
import com.google.common.collect.Iterators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.swing.tree.TreeNode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Res generated by hbm2java
 */
@Entity
@Table(name = "RES", catalog = "MINI_ERP", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Res implements java.io.Serializable, ResTreeNode {

    private String id;
    private ResCategory resCategory;
    private String name;
    private String description;
    private String code;
    private boolean enable;
    private boolean batchMgr;
    private Set<StoreRes> storeReses = new HashSet<StoreRes>(0);
    private Set<FormatDefine> formatDefines = new HashSet<FormatDefine>(0);
    private Accounting accounting;

    private ResUnit resUnitByInDefault;
    private ResUnit resUnitByMasterUnit;
    private ResUnit resUnitByOutDefault;

    private UnitGroup unitGroup;

    private Set<Supplier> suppliers = new HashSet<Supplier>(0);

    private Set<ProductGroup> productGroups = new HashSet<ProductGroup>(0);

    public Res() {
    }

    public Res(boolean enable) {
        this.enable = enable;
    }

    public Res(String code, boolean enable) {
        this.code = code;
        this.enable = enable;
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
    @JoinColumn(name = "IN_DEFAULT", nullable = false)
    @NotNull
    public ResUnit getResUnitByInDefault() {
        return this.resUnitByInDefault;
    }

    public void setResUnitByInDefault(ResUnit resUnitByInDefault) {
        this.resUnitByInDefault = resUnitByInDefault;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTER_UNIT", nullable = false)
    @NotNull
    public ResUnit getResUnitByMasterUnit() {
        return this.resUnitByMasterUnit;
    }

    public void setResUnitByMasterUnit(ResUnit resUnitByMasterUnit) {
        this.resUnitByMasterUnit = resUnitByMasterUnit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUT_DEFAULT", nullable = false)
    @NotNull
    public ResUnit getResUnitByOutDefault() {
        return this.resUnitByOutDefault;
    }

    public void setResUnitByOutDefault(ResUnit resUnitByOutDefault) {
        this.resUnitByOutDefault = resUnitByOutDefault;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY", nullable = false)
    @NotNull
    public ResCategory getResCategory() {
        return this.resCategory;
    }

    public void setResCategory(ResCategory resCategory) {
        this.resCategory = resCategory;
    }

    @Column(name = "CODE", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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


    @Column(name = "DESCRIPTION", length = 200)
    @Size(max = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ENABLE", nullable = false)
    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Column(name = "BATCH_MGR", nullable = false)
    public boolean isBatchMgr() {
        return batchMgr;
    }

    public void setBatchMgr(boolean batchMgr) {
        this.batchMgr = batchMgr;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "res")
    public Set<StoreRes> getStoreReses() {
        return this.storeReses;
    }

    public void setStoreReses(Set<StoreRes> storeReses) {
        this.storeReses = storeReses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "res", orphanRemoval = true, cascade = {CascadeType.ALL})
    public Set<FormatDefine> getFormatDefines() {
        return this.formatDefines;
    }

    public void setFormatDefines(Set<FormatDefine> formatDefines) {
        this.formatDefines = formatDefines;
    }

    @Transient
    public List<StoreRes> getStoreResList() {
        List<StoreRes> result = new ArrayList<StoreRes>(getStoreReses());
        Collections.sort(result, new Comparator<StoreRes>() {
            @Override
            public int compare(StoreRes o1, StoreRes o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
        return result;
    }

    @Transient
    public List<FormatDefine> getFormatDefineList() {
        List<FormatDefine> result = new ArrayList<FormatDefine>(getFormatDefines());
        Collections.sort(result, OrderBeanComparator.getInstance());
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Supplier.class)
    @JoinTable(name = "SUPPLIER_RES", joinColumns = @JoinColumn(name = "RES"), inverseJoinColumns = @JoinColumn(name = "SUPPLIER"))
    public Set<Supplier> getSuppliers() {
        return this.suppliers;
    }

    public void setSuppliers(Set<Supplier> supplierReses) {
        this.suppliers = supplierReses;
    }

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ACCOUNTING", unique = true, nullable = true, updatable = false)
    public Accounting getAccounting() {
        return accounting;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "reses")
    public Set<ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(Set<ProductGroup> productGroups) {
        this.productGroups = productGroups;
    }

    @Override
    @Transient
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }

        if (!(other instanceof Res)) {
            return false;
        }

        Res otherRes = (Res) other;

        if ((otherRes.getId() != null) && (!"".equals(otherRes.getId().trim()))) {
            return otherRes.getId().equals(getId());
        }

        if (otherRes.getCode() != null && (!"".equals(otherRes.getCode().trim()))) {
            return otherRes.getCode().equals(getCode());
        }

        return false;
    }

    @Override
    @Transient
    public int hashCode() {
        return (getId() + getCode()).hashCode();
    }

    @Transient
    @Override
    public Object getData() {
        return this;
    }

    @Transient
    @Override
    public String getNodeType() {
        return "res";
    }

    @Transient
    private List<ResTreeNode> getChildList() {

        if (!getTreeFilter().getCategoryTypes().equals(ResTreeFilter.StoreResAddType.NOT_ADD)) {
            return StoreResPropertyTreeNode.genStoreResNodes(this, getTreeFilter());
        } else
            return new ArrayList<ResTreeNode>(0);
    }

    @Transient
    @Override
    public ResTreeFilter getTreeFilter() {
       return getResCategory().getTreeFilter();
    }

    @Transient
    private Boolean expanded = null;

    @Transient
    @Override
    public boolean isExpanded() {
        if (expanded == null){
            expanded = getTreeFilter().expandedDefault();
        }
        return expanded;
    }

    @Transient
    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Transient
    @Override
    public TreeNode getChildAt(int childIndex) {
        return getChildList().get(childIndex);
    }

    @Transient
    @Override
    public int getChildCount() {
        return getChildList().size();
    }

    @Transient
    @Override
    public TreeNode getParent() {
        return getResCategory();
    }

    @Transient
    @Override
    public int getIndex(TreeNode node) {
        return getChildList().indexOf(node);
    }

    @Transient
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Transient
    @Override
    public boolean isLeaf() {
        return false;
    }

    @Transient
    @Override
    public Enumeration children() {
        return Iterators.asEnumeration(getChildList().iterator());
    }
}
