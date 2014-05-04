package com.dgsoft.erp.business.order;


import com.dgsoft.erp.action.CustomerHome;
import com.dgsoft.erp.model.AccountOper;
import com.dgsoft.erp.model.Customer;
import com.dgsoft.erp.model.CustomerOrder;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.core.Events;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.security.Credentials;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 11/5/13
 * Time: 9:55 AM
 */

public abstract class FinanceReceivables extends OrderTaskHandle {

    @In(create = true)
    protected CustomerHome customerHome;

    protected AccountOper accountOper;

    @In
    protected Credentials credentials;

    private BigDecimal operMoney;

    public BigDecimal getOrderShortageMoney() {
        return orderHome.getInstance().getShortageMoney();
    }

    public BigDecimal getShortageMoney() {
        return getOrderShortageMoney();
    }

//    private Customer getCustomer() {
//        return orderHome.getInstance().getCustomer();
//    }

    public AccountOper getAccountOper() {
        return accountOper;
    }

    public void setAccountOper(AccountOper accountOper) {
        this.accountOper = accountOper;
    }

    public BigDecimal getOperMoney() {
        return operMoney;
    }

    public void setOperMoney(BigDecimal operMoney) {
        this.operMoney = operMoney;
    }

    public void allMoney() {
        setOperMoney(getShortageMoney());
    }

    protected void receiveAdvance() {
        accountOper.setOperType(AccountOper.AccountOperType.PRE_DEPOSIT);
        accountOper.setAdvanceReceivable(getOperMoney());
        accountOper.setAccountsReceivable(BigDecimal.ZERO);
        accountOper.setProxcAccountsReceiveable(BigDecimal.ZERO);
    }

    protected abstract boolean receiveAccountOper();

    @Transactional
    public void receiveMoney() {

        if (getOrderShortageMoney().compareTo(BigDecimal.ZERO) <= 0) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "orderMoneyIsComplete");
            return;
        }

        if (receiveAccountOper()) {
            accountOper.calcCustomerMoney();
            orderHome.getInstance().getAccountOpers().add(accountOper);

            orderHome.calcMoneys();
            if ("updated".equals(orderHome.update())) {
                reset();
                Events.instance().raiseTransactionSuccessEvent("org.jboss.seam.afterTransactionSuccess.AccountOper");
            }
        }
    }

    public void reset() {
        accountOper = new AccountOper(orderHome.getInstance(), credentials.getUsername(),
                AccountOper.AccountOperType.ORDER_SAVINGS);
    }

    @Override
    protected void initOrderTask() {
        reset();
        customerHome.setId(orderHome.getInstance().getCustomer().getId());
        super.initOrderTask();
    }

}
