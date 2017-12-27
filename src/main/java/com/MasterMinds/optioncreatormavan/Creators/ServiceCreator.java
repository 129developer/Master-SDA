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
public class ServiceCreator {

    public ServiceCreator() {
    }

    public static String getServiceCode(String tableName, String AuthorName, String OptonName, String module, String subModule) throws Exception {
        String retStr = "package com.baya." + module + ".service." + subModule + ";\n"
                + "\n"
                + " /***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n"
                + "import com.baya.resources.persistence.BasicDTO;\n"
                + "import com.baya.resources.persistence.GenericServices;\n"
                //                + "import com.baya.resources." + module + ".secimpl." + stringUtils.getFirstCaps(tableName) + "Context;\n"
                + "import com.baya." + module + ".model." + subModule + "." + stringUtils.getFirstCaps(tableName) + "Model;\n"
                + "import org.codehaus.jettison.json.JSONObject;\n"
                + "import org.hibernate.Session;\n"
                + "\n"
                + "public interface " + stringUtils.getFirstCaps(tableName) + "Service extends GenericServices<" + stringUtils.getFirstCaps(tableName) + "Model> {\n"
                + "\n"
                + "\n"
                + "}";
        return retStr;
    }

    public static String getServiceImplCode(String tableName, String AuthorName, String OptonName, String module, String subModule) throws Exception {
        String retStr = "package com.baya." + module + ".service." + subModule + ";\n"
                + "\n"
                + " /***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n"
                + "import com.baya.resources.persistence.GenericDAO;\n"
                + "import com.baya.resources.persistence.GenericServicesImpl;\n"
                + "import com.baya." + module + ".dao." + subModule + "." + stringUtils.getFirstCaps(tableName) + "DAO;\n"
                //                + "import com.baya." + module + ".dto." + subModule + "." + stringUtils.getFirstCaps(tableName) + "DTO;\n"
                + "import com.baya." + module + ".model." + subModule + "." + stringUtils.getFirstCaps(tableName) + "Model;\n"
                + "import org.hibernate.Session;\n"
                + "import org.springframework.beans.factory.annotation.Autowired;\n"
                + "import org.springframework.stereotype.Service;\n"
                + "import org.springframework.transaction.annotation.Propagation;\n"
                + "import org.springframework.transaction.annotation.Transactional;\n"
                + "\n"
                + "@Service(\"" + stringUtils.getFirstCaps(tableName) + "Service\")\n"
                + "@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)\n"
                + "public class " + stringUtils.getFirstCaps(tableName) + "Service_Impl extends GenericServicesImpl<" + stringUtils.getFirstCaps(tableName) + "Model> implements " + stringUtils.getFirstCaps(tableName) + "Service {\n"
                + "\n"
                + "    @Autowired\n"
                + "    private " + stringUtils.getFirstCaps(tableName) + "DAO " + stringUtils.getFirstCaps(tableName) + "DAO;\n"
                + "\n"
                + "    public " + stringUtils.getFirstCaps(tableName) + "Service_Impl() {\n"
                + "        super(" + stringUtils.getFirstCaps(tableName) + "Model.class);\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    public GenericDAO<" + stringUtils.getFirstCaps(tableName) + "Model, Integer> getDao() {\n"
                + "        return (GenericDAO<" + stringUtils.getFirstCaps(tableName) + "Model, Integer>) " + stringUtils.getFirstCaps(tableName) + "DAO;\n"
                + "    }\n"
                + "\n"
                + "   \n"
                + "}";
        return retStr;
    }

}
