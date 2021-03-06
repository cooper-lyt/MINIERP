package com.dgsoft.erp.total;

import com.dgsoft.common.DataFormat;
import com.dgsoft.common.SearchDateArea;
import com.dgsoft.common.TotalDataGroup;
import com.dgsoft.common.TotalGroupStrategy;
import com.dgsoft.erp.ErpEntityQuery;
import com.dgsoft.erp.model.AccountOper;
import com.dgsoft.erp.model.Customer;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 14/03/14
 * Time: 12:21
 */
public abstract class CustomerMoneyTotalBase extends ErpEntityQuery<AccountOper> {


    protected static final String EJBQL = "select accountOper from AccountOper accountOper left join fetch accountOper.moneySave " +
            " where (accountOper.operType = 'DEPOSIT_BACK' or accountOper.operType = 'MONEY_FREE' or accountOper.operType = 'PROXY_SAVINGS' " +
            " or accountOper.operType = 'CUSTOMER_SAVINGS' or ( accountOper.operType = 'ORDER_BACK' and accountOper.advanceReceivable = 0 and accountOper.moneySave is null))";

    public CustomerMoneyTotalBase() {
        setEjbql(EJBQL);
        setRestrictionLogicOperator("and");
        setOrderColumn("accountOper.operDate");
    }

    private SearchDateArea searchDateArea = new SearchDateArea(new Date(), new Date());

    public SearchDateArea getSearchDateArea() {
        return searchDateArea;
    }


    private List<TotalDataGroup<Customer, AccountOper,CustomerMoneyTotalData>> customerGroupResultList = null;

    private void initCustomerGroupResultList() {
        if (customerGroupResultList == null) {
            customerGroupResultList = TotalDataGroup.groupBy(getResultList(), new TotalGroupStrategy<Customer, AccountOper,CustomerMoneyTotalData>() {
                @Override
                public Customer getKey(AccountOper accountOper) {
                    return accountOper.getCustomer();
                }

                @Override
                public CustomerMoneyTotalData totalGroupData(Collection<AccountOper> datas) {
                    return totalData(datas);
                }
            });
        }
    }

    public List<TotalDataGroup<Customer, AccountOper,CustomerMoneyTotalData>> getCustomerGroupResultList() {
        if (isAnyParameterDirty()) {
            refresh();
        }
        initCustomerGroupResultList();
        return customerGroupResultList;
    }

    private List<TotalDataGroup<TotalDataGroup.DateKey, AccountOper,CustomerMoneyTotalData>> dayGroupResultList = null;

    private void initDayGroupResultList() {
        if (dayGroupResultList == null) {
            dayGroupResultList = TotalDataGroup.groupBy(getResultList(), new TotalGroupStrategy<TotalDataGroup.DateKey, AccountOper,CustomerMoneyTotalData>() {
                @Override
                public TotalDataGroup.DateKey getKey(AccountOper accountOper) {
                    return new TotalDataGroup.DateKey(DataFormat.halfTime(accountOper.getOperDate()));
                }

                @Override
                public CustomerMoneyTotalData totalGroupData(Collection<AccountOper> datas) {
                    return totalData(datas);
                }

            });
        }
    }

    private CustomerMoneyTotalData totalData(Collection<AccountOper> datas) {
        CustomerMoneyTotalData result = new CustomerMoneyTotalData();
        for (AccountOper oper : datas) {
            result.add(oper);
        }
        return result;
    }


    public List<TotalDataGroup<TotalDataGroup.DateKey, AccountOper,CustomerMoneyTotalData>> getDayGroupResultList() {

        if (isAnyParameterDirty()) {
            refresh();
        }
        initDayGroupResultList();
        return dayGroupResultList;
    }

    @Override
    public void refresh() {
        super.refresh();
        dayGroupResultList = null;
        customerGroupResultList = null;
    }

    public CustomerMoneyTotalData getResultTotalData() {
        return totalData(getResultList());
    }


    public class CustomerMoneyTotalData implements TotalDataGroup.GroupTotalData{

        Map<AccountOper.AccountOperType, BigDecimal> typeTotalData;

        BigDecimal outMoney;

        BigDecimal inMoney;

        public CustomerMoneyTotalData() {
            outMoney = BigDecimal.ZERO;
            inMoney = BigDecimal.ZERO;
            typeTotalData = new HashMap<AccountOper.AccountOperType, BigDecimal>();
            for (AccountOper.AccountOperType type : AccountOper.AccountOperType.getCustomerOpers()) {
                typeTotalData.put(type, BigDecimal.ZERO);
            }
        }

        public BigDecimal getTypeTotalByName(String name) {
            return typeTotalData.get(Enum.valueOf(AccountOper.AccountOperType.class, name));
        }

        public List<Map.Entry<AccountOper.AccountOperType, BigDecimal>> getTypeOperMoney() {
            List<Map.Entry<AccountOper.AccountOperType, BigDecimal>> result =
                    new ArrayList<Map.Entry<AccountOper.AccountOperType, BigDecimal>>(typeTotalData.entrySet());
            Collections.sort(result, new Comparator<Map.Entry<AccountOper.AccountOperType, BigDecimal>>() {
                @Override
                public int compare(Map.Entry<AccountOper.AccountOperType, BigDecimal> o1, Map.Entry<AccountOper.AccountOperType, BigDecimal> o2) {
                    return Integer.valueOf(o1.getKey().ordinal()).compareTo(o2.getKey().ordinal());
                }
            });
            return result;
        }

        public void add(AccountOper oper) {
            typeTotalData.put(oper.getOperType(), typeTotalData.get(oper.getOperType()).add(oper.getCustomerOperMoney()));
            if (oper.getOperType().isCustomerOper())
                if (oper.getOperType().getAdd() != null)
                    if (oper.getOperType().getAdd()) {
                        inMoney = inMoney.add(oper.getCustomerOperMoney());
                    } else {
                        outMoney = outMoney.add(oper.getAccountsReceivable());
                    }
        }

        public BigDecimal getOutMoney() {
            return outMoney;
        }

        public BigDecimal getInMoney() {
            return inMoney;
        }
    }
}
