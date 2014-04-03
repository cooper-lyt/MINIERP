package com.dgsoft.common.jbpm;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.bpm.ManagedJbpmContext;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Logging;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 03/04/14
 * Time: 10:37
 */

@Name("processInstanceHome")
@Scope(ScopeType.CONVERSATION)
public class ProcessInstanceHome {

    //private List<ProcessInstance>

    private ProcessInstance instance;

    private String processDefineName;

    private String processKey;

    //private Long processId;


    public ProcessInstance getInstance() {
        initInstance();
        return instance;
    }


    public String getProcessDefineName() {
        return processDefineName;
    }

    public void setProcessDefineName(String processDefineName) {
        if ((processDefineName == null) || !processDefineName.equals(this.processDefineName)) {
            instance = null;
        }
        this.processDefineName = processDefineName;

    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        if ((processKey == null) || (!processKey.equals(this.processKey))) {
            instance = null;
        }
        this.processKey = processKey;
    }

    public void initInstance() {
        if (instance == null) {
            if ((processDefineName != null) && (processKey != null)) {
                Logging.getLog(this.getClass()).debug("Locate processInstance - processDefineName:" + processDefineName + ";processKey:" + processKey);

                ProcessDefinition definition = ManagedJbpmContext.instance().getGraphSession().findLatestProcessDefinition(processDefineName);
                instance = definition == null ?
                        null : ManagedJbpmContext.instance().getProcessInstanceForUpdate(definition, processKey);
            }
        }
    }

    public void suspend() {
        getInstance().suspend();
        Events.instance().raiseTransactionSuccessEvent("org.jboss.seam.processSuspended");
    }

    public void resume() {

        getInstance().resume();
        Events.instance().raiseTransactionSuccessEvent("org.jboss.seam.processResumed");
    }


    public List<TaskInstance> getTaskInstanceList() {

        List<TaskInstance> result = new ArrayList<TaskInstance>(getInstance().getTaskMgmtInstance().getTaskInstances());
        Collections.sort(result,new Comparator<TaskInstance>() {
            @Override
            public int compare(TaskInstance o1, TaskInstance o2) {
                return o1.getCreate().compareTo(o2.getCreate());
            }
        });
        return result;
    }


}
