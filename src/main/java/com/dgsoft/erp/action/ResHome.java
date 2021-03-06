package com.dgsoft.erp.action;

import com.dgsoft.common.OrderBeanComparator;
import com.dgsoft.common.helper.ActionExecuteState;
import com.dgsoft.common.system.NumberBuilder;
import com.dgsoft.common.system.RunParam;
import com.dgsoft.erp.ErpEntityHome;
import com.dgsoft.erp.model.*;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage;

import javax.faces.event.ValueChangeEvent;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cooperlee
 * Date: 9/24/13
 * Time: 2:45 PM
 */
@Name("resHome")
public class ResHome extends ErpEntityHome<Res> {

    public static final String RES_CODE_RULE_PARAM_NAME = "erp.resRegRule";

    @In(create = true)
    private ResCategoryHome resCategoryHome;

    @In
    private FacesMessages facesMessages;

    @In
    private ActionExecuteState actionExecuteState;

    @In
    private RunParam runParam;

    @In
    private NumberBuilder numberBuilder;

    @DataModel
    private List<FormatDefine> formatDefineList = new ArrayList<FormatDefine>();

    @DataModelSelection
    private FormatDefine formatDefine;

    private FormatDefine editFormatDefine;

    private FormatDefine newFormatDefine = new FormatDefine();

    public FormatDefine getNewFormatDefine() {
        return newFormatDefine;
    }

    public void setNewFormatDefine(FormatDefine newFormatDefine) {
        this.newFormatDefine = newFormatDefine;
    }

    public void addFormatDefine() {
        newFormatDefine.setRes(getInstance());
        if (!newFormatDefine.getDataType().equals(FormatDefine.FormatType.WORD)) {
            newFormatDefine.setWordCategory(null);
        }

        OrderBeanComparator.addToLast(newFormatDefine, formatDefineList);
        getInstance().getFormatDefines().add(newFormatDefine);
        newFormatDefine = new FormatDefine();
        actionExecuteState.setLastState("success");
    }

    public FormatDefine getEditFormatDefine() {
        return editFormatDefine;
    }

    public void setEditFormatDefine(FormatDefine editFormatDefine) {
        this.editFormatDefine = editFormatDefine;
    }

    public void beginEditFormatDefine(){
        editFormatDefine = formatDefine;
        actionExecuteState.actionExecute();
    }

    @Factory(value = "formatTypes", scope = ScopeType.SESSION, autoCreate = true)
    public FormatDefine.FormatType[] getFormatTypes() {
        return FormatDefine.FormatType.values();
    }

    @Override
    protected void initInstance() {
        super.initInstance();
        formatDefineList = getInstance().getFormatDefineList();
    }

    @Override
    protected boolean wire() {
        if (!isManaged()) {
            getInstance().setResCategory(resCategoryHome.getInstance());
        }
        return true;
    }

    public void deleteFormatDefine() {
        formatDefineList.remove(formatDefine);
        getInstance().getFormatDefines().remove(formatDefine);
    }

    public void upFormatDefine() {
        OrderBeanComparator.up(formatDefine, formatDefineList);
    }

    public void downFormatDefine() {
        OrderBeanComparator.down(formatDefine, formatDefineList);
    }

    public void unitGroupSelectListener() {
        if (getInstance().getUnitGroup() != null) {
            for (ResUnit resUnit : getInstance().getUnitGroup().getResUnits()) {
                if (resUnit.getConversionRate().compareTo(new BigDecimal("1")) == 0) {
                    getInstance().setResUnitByMasterUnit(resUnit);
                    getInstance().setResUnitByInDefault(resUnit);
                    getInstance().setResUnitByOutDefault(resUnit);
                    return;
                }
            }
        }
    }

    public void masterUnitSelectListener() {
        if ((getInstance().getUnitGroup() != null) &&
                (getInstance().getResUnitByMasterUnit() != null) &&
                (getInstance().getUnitGroup().getType().equals(UnitGroup.UnitGroupType.NO_CONVERT))) {
            getInstance().setResUnitByInDefault(getInstance().getResUnitByMasterUnit());
            getInstance().setResUnitByOutDefault(getInstance().getResUnitByMasterUnit());
        }
    }

    @Override
    protected Res createInstance() {
        return new Res(String.valueOf(numberBuilder.getNumber("erp.res.code")), true);
    }


    public void verifyCodeAvailable(ValueChangeEvent e) {
        String newCode = (String) e.getNewValue();

        if (!newCode.matches(runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME))) {
            facesMessages.addToControlFromResourceBundle(e.getComponent().getId(), StatusMessage.Severity.ERROR, "resCodeNotRule",
                    newCode, runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME));

        } else if (!isCodeAvailable(newCode)) {
            facesMessages.addToControlFromResourceBundle(e.getComponent().getId(),
                    StatusMessage.Severity.ERROR, "resCodeConflict", newCode);
        }
    }

    @Override
    protected boolean verifyPersistAvailable() {
        if (!getInstance().getCode().matches(runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME))) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "resCodeNotRule",
                    getInstance().getCode(), runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME));
            return false;
        } else if (!isCodeAvailable(getInstance().getCode())) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "resCodeConflict", getInstance().getCode());
            return false;
        } else {
            return true;
        }

    }

    @Override
    protected boolean verifyUpdateAvailable() {
        if (!getInstance().getCode().matches(runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME))) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "resCodeNotRule",
                    getInstance().getCode(), runParam.getStringParamValue(RES_CODE_RULE_PARAM_NAME));
            return false;
        } else {
            List<Res> codeResult = getEntityManager().createQuery("select res from Res res where res.code = ?1").setParameter(1, getInstance().getCode()).getResultList();
            if (!codeResult.isEmpty() && !codeResult.get(0).getId().equals(getInstance().getId())) {
                facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, "resCodeConflict", getInstance().getCode());
                return false;
            }
        }
        return true;

    }

    @Override
    protected boolean verifyRemoveAvailable() {

        if (!getInstance().getProductGroups().isEmpty() || !getInstance().getStoreReses().isEmpty()) {
            facesMessages.addFromResourceBundle(StatusMessage.Severity.ERROR,"res_cannotDelete");

            return false;
        }

        return true;
    }

    public boolean isCodeAvailable(String code) {
        return getEntityManager().createQuery("select res from Res res where res.code = ?1").setParameter(1, code).getResultList().isEmpty();
    }


    public String getResTitle() {
        if (isIdDefined()) {

            ResCategory category = getInstance().getResCategory();
            String result = "";
            if (category != null) {
                String categoryTitle = category.getName();

                while (category.getResCategory() != null) {
                    category = category.getResCategory();

                    categoryTitle = category.getName() + " > " + categoryTitle;
                }
                result = categoryTitle;
            }
            result = result + " : " + getInstance().getName() + "(" + getInstance().getCode() + ")";
            return result;

        } else
            return "";
    }

}
