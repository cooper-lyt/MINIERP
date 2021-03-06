package com.dgsoft.common.jbpm;

import org.jboss.seam.annotations.*;
import org.jboss.seam.bpm.ManagedJbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

import java.util.List;

import static org.jboss.seam.ScopeType.APPLICATION;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 5/28/13
 * Time: 12:16 PM
 */
@Name("processDefinitionList")
@Scope(APPLICATION)
@Install(precedence=Install.APPLICATION, dependencies="org.jboss.seam.bpm.jbpm")
public class ProcessDefinitionList {

    @Unwrap
    @Transactional
    public List<ProcessDefinition> findAllProcessDefinitions(){
        return ManagedJbpmContext.instance().getGraphSession().findAllProcessDefinitions();
    }
}
