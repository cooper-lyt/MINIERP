package com.dgsoft.erp.action;

import com.dgsoft.common.system.DictionaryWord;
import com.dgsoft.erp.model.Format;
import com.dgsoft.erp.model.FormatDefine;
import com.dgsoft.erp.model.StoreRes;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cooper
 * Date: 10/13/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Name("resHelper")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class ResHelper {


    @In
    private DictionaryWord dictionary;

    public String generateStoreResTitle(StoreRes storeRes){

        String result = storeRes.getRes().getName() + "(" + storeRes.getRes().getCode() + ")  ";

        for (Format format: storeRes.getFormatList()){
            result += " " + format.getFormatDefine().getName() + " : ";
            if (format.getFormatDefine().getDataType().equals(FormatDefine.FormatType.WORD)){
                result += dictionary.getWordValue(format.getFormatValue());
            }else{
                result += format.getFormatValue();
            }
        }
        return result;
    }


    public static boolean sameFormat(Collection<Format> formatList1, Collection<Format> formatList2) {
        if (formatList1.size() != formatList2.size()) {
            return false;
        }

        Map<String, String> format1Values = new HashMap<String, String>();
        for (Format format : formatList1) {
            format1Values.put(format.getFormatDefine().getId(), format.getFormatValue());
        }
        for (Format format : formatList2) {
            if (!format1Values.get(format.getFormatDefine().getId()).equals(format.getFormatValue())) {
                return false;
            }
        }
        return true;
    }

}
