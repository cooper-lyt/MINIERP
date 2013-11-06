package com.dgsoft.erp.action;

import com.dgsoft.erp.ErpEntityHome;
import com.dgsoft.erp.model.AccountOper;
import com.dgsoft.erp.model.api.PayType;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Role;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 11/5/13
 * Time: 10:45 AM
 */
@Name("accountOperHome")
public class AccountOperHome extends ErpEntityHome<AccountOper> {

    @Factory(value = "accountPayTypes",scope = ScopeType.CONVERSATION)
    public PayType[] getAccountPayTypes() {
        return PayType.values();
    }


}
