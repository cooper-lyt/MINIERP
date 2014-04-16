package com.dgsoft.erp.total;

import com.dgsoft.common.TotalDataGroup;
import com.dgsoft.common.TotalGroupStrategy;
import com.dgsoft.erp.ErpEntityQuery;
import com.dgsoft.erp.model.BackItem;
import com.dgsoft.erp.model.StockChange;
import com.dgsoft.erp.model.StockChangeItem;
import org.jboss.seam.annotations.Name;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 14/03/14
 * Time: 12:14
 */
@Name("backResContactsTotal")
public class BackResContactsTotal extends ErpEntityQuery<BackItem>{

    protected static final String EJBQL = "select backItem from BackItem backItem " +
            "left join fetch backItem.dispatch dispatch left join fetch dispatch.stockChange stockChange " +
            "left join fetch backItem.orderBack orderBack left join fetch orderBack.customer customer " +
            "left join fetch customer.customerArea left join fetch customer.customerLevel  " +
            "left join stockChange.assemblyForStoreOut left join fetch stockChange.assemblyForStoreIn " +
            "left join stockChange.assemblyForLoseOut left join fetch productStoreIn " +
            "left join stockChange.materialStoreOut left join stockChange.allocationForStoreOut " +
            "left join stockChange.materialStoreIn left join stockChange.allocationForStoreIn " +
            "left join stockChange.inventoryAdd left join stockChange.inventoryLoss " +
            "left join stockChange.scrapStoreOut left join stockChange.backDispatch " +
            "left join stockChange.materialBackStoreIn left join stockChange.storeChange " +
            "where backItem.backItemStatus = 'STORE_IN'";

    protected static final String[] RESTRICTIONS = {
            "backItem.dispatch.stockChange.operDate >= #{searchDateArea.dateFrom}",
            "backItem.dispatch.stockChange.operDate <= #{searchDateArea.searchDateTo}",
            "backItem.orderBack.customer.customerArea.id = #{customerSearchCondition.customerAreaId}",
            "backItem.orderBack.customer.customerLevel.priority >= #{customerSearchCondition.levelFrom}",
            "backItem.orderBack.customer.customerLevel.priority <= #{customerSearchCondition.levelTo}",
            "backItem.orderBack.customer.type = #{customerSearchCondition.type}",
            "backItem.orderBack.customer.provinceCode <= #{customerSearchCondition.provinceCode}",


    };

    public BackResContactsTotal() {
        setEjbql(EJBQL);
        setRestrictionLogicOperator("and");
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setOrderColumn("backItem.dispatch.stockChange.operDate");
    }
}