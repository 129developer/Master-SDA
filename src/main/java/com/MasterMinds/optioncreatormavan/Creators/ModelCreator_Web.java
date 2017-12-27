/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.dbUtils;
import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mukil
 */
public class ModelCreator_Web {

    private static String tableName = "usermst";
    static String AuthorName = "Mukil";
    static String fileSavePath = "/home/bayasys/Desktop/Master Minds/OptionCreator/";
    static String OptonName = "User";
    static String ModuleName = "security";
    static String SubModuleName = "def";

    private static String getFileToPath() throws Exception {
        JSONArray columnAry = dbUtils.getColumnArray(tableName);
        String MandatoryStr = "";
        String LabelStr = "";
        String ValueStr = "";
        for (int i = 0; i < columnAry.length(); i++) {
            JSONObject tempObj = columnAry.getJSONObject(i);
            MandatoryStr += "                " + tempObj.getString("column_name") + " : " + (tempObj.getString("is_nullable").equalsIgnoreCase("YES") ? true : false) + ",\n";

            LabelStr += "                " + tempObj.getString("column_name") + " : '" + stringUtils.getFirstCaps(tempObj.getString("column_name")) + "',\n";
            if (tempObj.getString("data_type").equalsIgnoreCase("character varying") || tempObj.getString("data_type").equalsIgnoreCase("character")) {
                ValueStr += "                " + tempObj.getString("column_name") + " : '',\n";
            } else {
                ValueStr += "                " + tempObj.getString("column_name") + " : 0,\n";
            }

        }

        String temp = "/************************************************************\n"
                + " Author:" + AuthorName + "\n"
                + " Created Date: " + new Date() + "\n"
                + " Purpose:\n"
                + " Option : " + OptonName + "\n"
                + " Module/Topic: " + ModuleName + " \n"
                + " Remarks:\n"
                + " **************************************************************\n"
                + " Revision History: \n"
                + " **************************************************************\n"
                + " DATE		MODIFIED BY	 DESCRIPTION\n"
                + " ***********************************************************\n"
                + " \n"
                + " ***********************************************************/\n"
                + "Ext.define('Application." + ModuleName + ".model." + SubModuleName + "." + OptonName + "." + OptonName + "Model', {\n"
                + "    extend: 'Application.common.model.BaseModel',\n"
                + "    alias: 'viewmodel." + OptonName + "Model',\n"
                + "    headerStore: 'headerStore',\n"
                + "    updateTrggerStores: [],\n"
                + "    data: {\n"
                + "        mandatory: {\n"
                + "            " + OptonName + "General: {\n"
                + MandatoryStr
                + "            },\n"
                + "            SearchGrid: {\n"
                + "                code: false,\n"
                + "                name: false,\n"
                + "                usertypebccid: false\n"
                + "            }\n"
                + "        },\n"
                + "        label: {\n"
                + "            " + OptonName + "General: {\n"
                + LabelStr
                + "            },\n"
                + "            SearchGrid: {\n"
                + "                title: 'Business Levels',\n"
                + "                code: 'Code',\n"
                + "                name: 'Name',\n"
                + "                usertypebccid: '" + OptonName + " Type'\n"
                + "            }\n"
                + "        },\n"
                + "        value: {\n"
                + "            " + OptonName + "General: {\n"
                + ValueStr
                + "            }\n"
                + "        }\n"
                + "    },\n"
                + "    stores: {\n"
                + "        headerStore: {\n"
                + "            fields: [],\n"
                + "            autoLoad: false,\n"
                + "            triggerUpdate: true\n"
                + "        },\n"
                + "        \n"
                + "    }\n"
                + "});\n"
                + "";
        return temp;
    }

    public static void main(String[] args) {
        try {
            fileUtils.WriteToFile(fileSavePath, stringUtils.getFirstCaps(OptonName) + "Model.js", getFileToPath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
