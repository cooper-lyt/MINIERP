package com.dgsoft.common.system;

import com.dgsoft.common.EntityQueryAdapter;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 5/9/13
 * Time: 11:33 AM
 */
public class SystemEntityQuery<E> extends EntityQueryAdapter<E> {

    @Override
    protected String getPersistenceContextName() {

        return "systemEntityManager";
    }
}
