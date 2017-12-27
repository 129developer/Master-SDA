/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author bayasys
 */
public class DAOCreator {

    public DAOCreator() {
    }

    public static String getDaoCode(String tableName, String AuthorName, String OptonName, String module, String subModule) throws Exception {
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

    public static String getDaoImplCode(String tableName, String AuthorName, String OptonName, String module, String subModule) throws Exception {
        String retStr = "package com.baya." + module + ".dao." + subModule + ";\n"
                + "\n"
                + "import com.baya.resources.persistence.GenericDAO_Impl;\n"
                + "import com.baya." + module + ".model." + subModule + "." + stringUtils.getFirstCaps(tableName) + "Model;\n"
                + "import org.springframework.stereotype.Repository;\n"
                + "\n"
                + " /***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n"
                + "@Repository(\"" + stringUtils.getFirstCaps(tableName) + "DAO\")\n"
                + "public class " + stringUtils.getFirstCaps(tableName) + "DAO_Impl extends GenericDAO_Impl<" + stringUtils.getFirstCaps(tableName) + "Model, Integer> implements " + stringUtils.getFirstCaps(tableName) + "DAO {\n"
                + "\n"
                + "    private static final long serialVersionUID = 7440297955003302414L;\n"
                + "\n"
                + "    public " + stringUtils.getFirstCaps(tableName) + "DAO_Impl() {\n"
                + "        super(" + stringUtils.getFirstCaps(tableName) + "Model.class);\n"
                + "    }\n"
                + "}";
        return retStr;
    }

}
