package com.dgsoft.erp.total;

import com.dgsoft.common.DataFormat;
import com.dgsoft.common.TotalDataGroup;
import com.dgsoft.common.TotalGroupStrategy;
import com.dgsoft.erp.model.BackItem;
import com.dgsoft.erp.model.Customer;
import com.dgsoft.erp.model.OrderItem;
import com.dgsoft.erp.model.api.StoreResPriceEntity;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 15/04/14
 * Time: 14:16
 */
@Name("customerResContactsTotal")
public class CustomerResContactsTotal {

    @In(create = true)
    private BackResContactsTotal backResContactsTotal;

    @In(create = true)
    private OrderResContactsTotal orderResContactsTotal;

    @In(create = true)
    private CustomerResCondition customerResCondition;

    private boolean onlyModel = false;

    private boolean onlyStoreOut = true;

    private boolean groupByDay = true;

    public boolean isGroupByDay() {
        return groupByDay;
    }

    public void setGroupByDay(boolean groupByDay) {
        this.groupByDay = groupByDay;
    }

    public boolean isOnlyStoreOut() {
        return onlyStoreOut;
    }

    public void setOnlyStoreOut(boolean onlyStoreOut) {
        this.onlyStoreOut = onlyStoreOut;
    }

    public boolean isOnlyModel() {
        return onlyModel;
    }

    public void setOnlyModel(boolean onlyModel) {
        this.onlyModel = onlyModel;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal result = BigDecimal.ZERO;
        for (StoreResPriceEntity item: getResultList()){
            result = result.add(item.getTotalMoney());
        }
        return result;
    }


    public List<StoreResPriceEntity> getResultList() {
        final List<StoreResPriceEntity> result = new ArrayList<StoreResPriceEntity>();
        if (!onlyModel) {

            if (customerResCondition.isContainStoreOut()) {
                result.addAll(orderResContactsTotal.getResultList());
            }
            if (customerResCondition.isContainResBack()) {
                result.addAll(backResContactsTotal.getResultList());
            }
        }else{
            if (onlyStoreOut){
                customerResCondition.setContainStoreOut(true);
                customerResCondition.setContainResBack(false);
                result.addAll(orderResContactsTotal.getResultList());
            }else{
                customerResCondition.setContainStoreOut(false);
                customerResCondition.setContainResBack(true);
                result.addAll(backResContactsTotal.getResultList());
            }
        }
        Collections.sort(result, new Comparator<StoreResPriceEntity>() {
            @Override
            public int compare(StoreResPriceEntity o1, StoreResPriceEntity o2) {
                Date o1Date;
                if (o1 instanceof OrderItem) {
                    o1Date = ((OrderItem) o1).getDispatch().getStockChange().getOperDate();
                } else {
                    o1Date = ((BackItem) o1).getDispatch().getStockChange().getOperDate();
                }

                Date o2Date;
                if (o2 instanceof OrderItem) {
                    o2Date = ((OrderItem) o2).getDispatch().getStockChange().getOperDate();
                } else {
                    o2Date = ((BackItem) o2).getDispatch().getStockChange().getOperDate();
                }
                int result = o1Date.compareTo(o2Date);
                if (result == 0) {

                    String o1CId;

                    if (o1 instanceof OrderItem) {
                        o1CId = ((OrderItem) o1).getNeedRes().getCustomerOrder().getCustomer().getId();
                    } else {
                        o1CId = ((BackItem) o1).getOrderBack().getCustomer().getId();
                    }

                    String o2CId;
                    if (o2 instanceof OrderItem) {
                        o2CId = ((OrderItem) o2).getNeedRes().getCustomerOrder().getCustomer().getId();
                    } else {
                        o2CId = ((BackItem) o2).getOrderBack().getCustomer().getId();
                    }

                    result = o1CId.compareTo(o2CId);

                    if (result == 0) {

                        result = o1.getRes().compareTo(o2.getRes());

                        if (result == 0) {
                            result = o1.getStoreRes().compareTo(o2.getStoreRes());
                        }

                    }

                }
                return result;
            }
        });

        return result;
    }

    public TotalDataGroup<?, StoreResPriceEntity> getCustomerResultGroup() {
        return TotalDataGroup.allGroupBy(getResultList(), new CustomerGroupStrategy(),
                new StoreResGroupStrategy<StoreResPriceEntity>(),
                new SameFormatResGroupStrategy<StoreResPriceEntity>());
    }

    public TotalDataGroup<?, StoreResPriceEntity> getDayResultGroup() {
        return TotalDataGroup.allGroupBy(getResultList(), new TotalGroupStrategy<Date, StoreResPriceEntity>() {
            @Override
            public Date getKey(StoreResPriceEntity storeResPriceEntity) {
                if (storeResPriceEntity instanceof OrderItem) {
                    return DataFormat.halfTime(((OrderItem) storeResPriceEntity).getDispatch().getStockChange().getOperDate());
                } else if (storeResPriceEntity instanceof BackItem) {
                    return DataFormat.halfTime(((BackItem) storeResPriceEntity).getDispatch().getStockChange().getOperDate());
                } else
                    return null;
            }

            @Override
            public Object totalGroupData(Collection<StoreResPriceEntity> datas) {
                BigDecimal result = BigDecimal.ZERO;
                for(StoreResPriceEntity item: datas){
                    result = result.add(item.getTotalMoney());
                }
                return result;
            }
        }, new CustomerGroupStrategy(), new StoreResGroupStrategy<StoreResPriceEntity>(), new SameFormatResGroupStrategy<StoreResPriceEntity>());
    }


    private static class CustomerGroupStrategy implements TotalGroupStrategy<Customer, StoreResPriceEntity> {
        @Override
        public Customer getKey(StoreResPriceEntity storeResPriceEntity) {
            if (storeResPriceEntity instanceof OrderItem) {
                return ((OrderItem) storeResPriceEntity).getNeedRes().getCustomerOrder().getCustomer();
            } else if (storeResPriceEntity instanceof BackItem) {
                return ((BackItem) storeResPriceEntity).getOrderBack().getCustomer();
            } else
                return null;
        }

        @Override
        public Object totalGroupData(Collection<StoreResPriceEntity> datas) {
            BigDecimal result = BigDecimal.ZERO;
            for(StoreResPriceEntity item: datas){
                result = result.add(item.getTotalMoney());
            }
            return result;
        }
    }

}
