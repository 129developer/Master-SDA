/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Creators;

import com.MasterMinds.optioncreatormavan.Utils.dbUtils;
import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import static java.lang.System.exit;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * provision to add custom desc
 *
 */
//CREATE OR REPLACE FUNCTION getTableDetails(val text) 
//RETURNS character varying AS
//$$
//select JSON_AGG((SELECT x FROM (SELECT column_name,data_type,character_maximum_length,numeric_precision,numeric_scale,is_nullable) x))::text AS OutputJSON from information_schema.columns where Table_name = lower($1)
//$$ LANGUAGE sql;
public class DTOCreator {

    static String AuthorName = "usermst";
    static String fileSavePath = "/home/bayasys/Desktop/Master Minds/OptionCreator/";
    static String OptonName = "OptonName";

    public static void main(String[] args) {
        String tableName = "usermst";
        try {
            JSONArray ary = new JSONArray();
            JSONObject Obj = new JSONObject();
            Obj.put("DTOName", "UserRoleMappingDTO");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
            Obj = new JSONObject();
            Obj.put("DTOName", "UserLoginDefDTO");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
            Obj = new JSONObject();
            Obj.put("DTOName", "UserBusinessLevelRightDTO");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
            new DTOCreator(tableName, ary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exit(0);
    }

    public DTOCreator(String tableName, boolean getsetYN, int gOrS) throws Exception {
        JSONArray joinArray = new JSONArray();
        getDTOFileToPath(tableName, getsetYN, gOrS, joinArray);
    }

    public DTOCreator(String tableName, boolean getsetYN) throws Exception {
        JSONArray joinArray = new JSONArray();
        getDTOFileToPath(tableName, getsetYN, 3, joinArray);
    }

    public DTOCreator(String tableName) throws Exception {
        JSONArray joinArray = new JSONArray();
        getDTOFileToPath(tableName, true, 3, joinArray);
    }

    public DTOCreator(String tableName, boolean getsetYN, int gOrS, JSONArray joinArray) throws Exception {
        getDTOFileToPath(tableName, getsetYN, gOrS, joinArray);
    }

    public DTOCreator(String tableName, boolean getsetYN, JSONArray joinArray) throws Exception {
        getDTOFileToPath(tableName, getsetYN, 3, joinArray);
    }

    public DTOCreator(String tableName, JSONArray joinArray) throws Exception {
        getDTOFileToPath(tableName, true, 3, joinArray);
    }

    private static String genGetAndSet(String Col, String dT, int gOrS) {
        String temp = "\n";
        if (gOrS == 1 || gOrS == 3) {
            temp += "    public " + dT + " get" + stringUtils.getFirstCaps(Col) + "() {\n"
                    + "        return " + Col + ";\n"
                    + "    }\n";
        }
        if (gOrS == 2 || gOrS == 3) {
            temp += "\n    public void set" + stringUtils.getFirstCaps(Col) + "(" + dT + " " + Col + ") {\n"
                    + "        this." + Col + " = " + Col + ";\n"
                    + "    }\n";
        }
        return temp;
    }

    public static void getDTOFileToPath(String tableName, boolean getsetYN, int gOrS, JSONArray joinArray) throws Exception {
        JSONArray columnAry = dbUtils.getColumnArray(tableName);
        String getSetStr = "";
        String AuthorComment = "/**\n"
                + " ***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n";
        String OutString = AuthorComment
                + "import com.baya.resources.persistence.GenericDTO;\n"
                + "import java.io.Serializable;\n"
                + "import java.util.List;\n"
                + "import javax.persistence.Column;\n"
                + "import javax.persistence.Entity;\n"
                + "import javax.persistence.FetchType;\n"
                + "import javax.persistence.JoinColumn;\n"
                + "import javax.persistence.OneToMany;\n"
                + "import javax.persistence.Table;\n"
                + "import org.hibernate.annotations.Cascade;\n"
                + "import org.hibernate.annotations.CascadeType;\n"
                + "import org.hibernate.annotations.Fetch;\n"
                + "import org.hibernate.annotations.FetchMode;\n"
                + "@Entity\n"
                + "@Table(name = \"" + tableName + "\")\n"
                + "public class " + tableName + "DTO extends GenericDTO implements Serializable {\n";
        try {

            System.out.println(columnAry);
            for (int i = 0; i < columnAry.length(); i++) {
                JSONObject tempObj = columnAry.getJSONObject(i);

//                if (!tempObj.getString("column_name").equalsIgnoreCase("id")
//                        && !tempObj.getString("column_name").equalsIgnoreCase("tableid")
//                        && !tempObj.getString("column_name").equalsIgnoreCase("lastmoddate")
//                        && !tempObj.getString("column_name").equalsIgnoreCase("lastmoduserid")) {
                if (tempObj.getString("data_type").equalsIgnoreCase("character varying") || tempObj.getString("data_type").equalsIgnoreCase("character")) {
                    tempObj.put("data_type", "String");
                    OutString += "private " + tempObj.getString("data_type") + " " + tempObj.getString("column_name") + ";" + "\n";
                } else {
                    if (tempObj.getString("data_type").equalsIgnoreCase("integer") || tempObj.getString("data_type").equalsIgnoreCase("bigint")) {
                        tempObj.put("data_type", "Integer");
                    }
                    if (tempObj.getString("data_type").equalsIgnoreCase("numeric")) {
                        tempObj.put("data_type", "Double");
                    }
                    if (tempObj.getString("numeric_scale").equals("null")) {
                        tempObj.put("numeric_scale", "0");
                    }
                    if (tempObj.getString("numeric_precision").equals("null")) {
                        tempObj.put("numeric_precision", "0");
                    }
                    OutString += "private " + tempObj.getString("data_type") + " " + tempObj.getString("column_name") + ";" + "\n";
                }
                getSetStr += genGetAndSet(tempObj.getString("column_name"), tempObj.getString("data_type"), gOrS);
//                }

            }

            for (int i = 0; i < joinArray.length(); i++) {
                JSONObject joinObj = joinArray.getJSONObject(i);
                OutString += "\n\n@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)\n"
                        + "@Fetch(value = FetchMode.SUBSELECT)\n"
                        + "@Cascade({CascadeType.SAVE_UPDATE, CascadeType.ALL})\n"
                        + "@JoinColumn(name = \"" + joinObj.getString("joinColumn") + "\", referencedColumnName = \"" + joinObj.getString("refColumn") + "\")\n"
                        + "private List<" + joinObj.getString("DTOName") + "> " + joinObj.getString("DTOName") + "Dtl;\n";

//                " + joinObj.getString("DTOName") + "Dtl
                getSetStr += genGetAndSet(joinObj.getString("DTOName") + "Dtl", "List<" + joinObj.getString("DTOName") + "> ", gOrS);
            }

            if (getsetYN) {
                OutString += getSetStr;
            }
            OutString += "}";
            System.out.println(fileUtils.WriteToFile(fileSavePath, stringUtils.getFirstCaps(tableName) + "DTO_tst.java", OutString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
