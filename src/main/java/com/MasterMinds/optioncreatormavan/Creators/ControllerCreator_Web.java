/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author Mukil
 */
public class ControllerCreator_Web {

//    static String AuthorName = "Mukil";
    static String fileSavePath = "/home/bayasys/Desktop/Master Minds/OptionCreator/";
//    static String OptonName = "Usesr";
//    static String ModuleName = "security";
//    static String SubModuleName = "def1";

    private static String getControllerCode(String AuthorName, String OptonName, String module, String subModule) {
        String temp = "/************************************************************\n"
                + " Author:" + AuthorName + "\n"
                + " Created " + new Date() + "\n"
                + " Purpose: \n"
                + " Module/Topic:" + module + "\n"
                + " Sub Module:" + subModule + "\n"
                + " Remarks:\n"
                + " **************************************************************\n"
                + " Revision History: \n"
                + " **************************************************************\n"
                + " DATE		MODIFIED BY	 DESCRIPTION \n"
                + " ***********************************************************\n"
                + " \n"
                + " ***********************************************************/\n"
                + "\n"
                + "Ext.define('Application." + module + ".controller." + subModule + "." + OptonName + "." + OptonName + "Controller', {\n"
                + "    extend: 'Application.common.controller.BaseController',\n"
                + "    alias: 'controller." + OptonName + "Controller',\n"
                + "    onAfterRender: function () {\n"
                + "\n"
                + "    },\n"
                + "    clear: function (panelObj) {\n"
                + "        this.valueObj = {\n"
                + "            " + OptonName + "General: {\n"
                + "            }\n"
                + "        };\n"
                + "        var hdrStore = getHeaderStore();\n"
                + "        hdrStore.removeAll();\n"
                + "        hdrStore.add({\n"
                + "\n"
                + "        });\n"
                + "        commitStores([{storeName: 'headerStore'}]);\n"
                + "        focusToFirstField();\n"
                + "    },\n"
                + "    save: function (status) {\n"
                + "        var cntrlObj = this;\n"
                + "        var dataObj = [];\n"
                + "        if (status) {\n"
                + "\n"
                + "        }\n"
                + "        return args;\n"
                + "    },\n"
                + "    bind: function (id, result) {\n"
                + "        BASE.LOAD_MASK.show();\n"
                + "        var cntrlObj = this;\n"
                + "        var hdrStore = getHeaderStore();\n"
                + "        if (result.resultObject) {\n"
                + "\n"
                + "            commitStores([{storeName: 'headerStore'}]);\n"
                + "            BASE.LOAD_MASK.hide();\n"
                + "            focusToFirstField();\n"
                + "        }\n"
                + "    },\n"
                + "    collect: function () {\n"
                + "		var dataArr = [];\n"
                + "        return dataArr;\n"
                + "    },\n"
                + "    validateB4Save: function () {\n"
                + "        return true;\n"
                + "    },\n"
                + "});";
        return temp;

    }

    public static void main(String[] args) {
//        fileUtils.WriteToFile(fileSavePath, stringUtils.getFirstCaps(OptonName) + "Controller.js", getFileToPath());
    }
}
