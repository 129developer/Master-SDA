package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bayasys
 */
public class ViewCreator_Web {

    static String AuthorName = "Mukil";
    static String fileSavePath = "/home/bayasys/Desktop/Master Minds/OptionCreator/";
    static String OptonName = "User";
    static String ModuleName = "security";
    static String SubModuleName = "def";

    private static String getModelFileToPath() {
        String temp = "/************************************************************\n"
                + " Author:" + AuthorName + "\n"
                + " Option " + OptonName + "\n"
                + " Created Date: " + new Date() + "\n"
                + " Purpose:\n"
                + " Module/Topic: " + ModuleName + "\n"
                + " Remarks:\n"
                + " **************************************************************\n"
                + " Revision History: \n"
                + " **************************************************************\n"
                + " DATE		MODIFIED BY	 DESCRIPTION\n"
                + " ***********************************************************\n"
                + " \n"
                + " ***********************************************************/\n"
                + "Ext.define('Application." + ModuleName + ".view." + SubModuleName + "." + OptonName + "." + OptonName + "View', {\n"
                + "    extend: '" + "Application.common.view.BaseView" + "',\n"
                + "    xtype: '" + OptonName + "View',\n"
                + "    layout: 'fit',\n"
                + "    closable: true,\n"
                + "    controller: '" + "UserController" + "',\n"
                + "//    initField: 'code',\n"
                + "    requires: [\n"
                + "        'Application." + ModuleName + ".controller." + SubModuleName + "." + OptonName + "." + OptonName + "Controller',\n"
                + "        'Application." + ModuleName + ".model." + SubModuleName + "." + OptonName + "." + OptonName + "Model" + "'\n"
                + "    ],\n"
                + "    style: {\n"
                + "        background: '#fff'\n"
                + "    },\n"
                + "    viewModel: {\n"
                + "        type: '" + OptonName + "Model'\n"
                + "    },\n"
                + "    initDataConfig: {\n"
                + "        dropDownParams: [\n"
                + "            \n"
                + "        ]\n"
                + "    },\n"
                + "    searchConfig: {\n"
                + "        xtype: 'SearchGrid',\n"
                + "        maxHeight: 480,\n"
                + "        width: 700,\n"
                + "        bindKey: 'SearchGrid',\n"
                + "        viewModel: {\n"
                + "            type: 'UserModel'\n"
                + "        },\n"
                + "       // url: '" + "/" + ModuleName + "/controller/" + SubModuleName + "/getuserdtls" + "',\n"
                + "        paramFields: [],\n"
                + "        columns: [\n"
                + "            {dataIndex: '" + "code" + "', flex: 1},\n"
                + "            {dataIndex: '" + "name" + "', flex: 1},\n"
                + "            {dataIndex: '" + "usertypebccid" + "', flex: 1, renderer: function (value, meta, record) {\n"
                + "                    var comboStroe = getStoreByName('" + "userTypeDtlStore" + "');\n"
                + "                    return gridColumnRenderer(comboStroe, value, \"id\", \"description\");\n"
                + "                }\n"
                + "            }\n"
                + "        ],\n"
                + "        paging: true,\n"
                + "        extraParams: true\n"
                + "    },\n"
                + "    items: [{\n"
                + "            xtype: 'container',\n"
                + "            default: true,\n"
                + "            icon: 'resources/icons/toolbar/general_home.png',\n"
                + "            layout: 'fit',\n"
                + "            name: 'tabGeneral',\n"
                + "            bindKey: 'UserGeneral',\n"
                + "            initField: {name: 'code'},\n"
                + "            optTabMaxWidth: 120,\n"
                + "            style: 'background:transparent',\n"
                + "            items: [{\n"
                + "                    xtype: 'container',\n"
                + "                    autoScroll: true,\n"
                + "                    style: 'background:transparent',\n"
                + "                    items: [{\n"
                + "                            xtype: 'form',\n"
                + "                            updateSts: 0,\n"
                + "                            name: 'frmHeader',\n"
                + "                            border: false,\n"
                + "                            items: [\n"
                + "\n"
                + "                            ]\n"
                + "                        }]\n"
                + "                }]\n"
                + "        }]\n"
                + "});\n";
        return temp;

    }

    public static void main(String[] args) {
        fileUtils.WriteToFile(fileSavePath, stringUtils.getFirstCaps(OptonName) + "View.js", getModelFileToPath());
    }

}
