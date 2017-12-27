/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.util.Date;

/**
 *
 * @author bayasys
 */
public class ControllerCreator {

    public ControllerCreator() {
    }

    public static String getControllerCode(String tableName, String AuthorName, String OptonName, String module, String subModule) throws Exception {
        String retStr = "package com.baya." + module + ".dao." + subModule + ";\n"
                + "\n"
                + " /***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n"
                + "import com.baya.resources.persistence.GenericDAO;\n"
                + "import com.baya." + module + ".model." + subModule + "." + stringUtils.getFirstCaps(tableName) + "Model;\n"
                + "\n"
                + "public interface " + stringUtils.getFirstCaps(tableName) + "DAO extends GenericDAO<" + stringUtils.getFirstCaps(tableName) + "Model, Integer> {\n"
                + "    \n"
                + "}";
        return retStr;
    }

}
