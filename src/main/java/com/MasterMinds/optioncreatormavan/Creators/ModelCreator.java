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
public class ModelCreator {

    public static void main(String[] args) {
        String tableName = "usermst";
        try {
            JSONArray ary = new JSONArray();
            JSONObject Obj = new JSONObject();
            Obj.put("tableName", "UserRoleMapping");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
            Obj = new JSONObject();
            Obj.put("tableName", "UserLoginDef");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
            Obj = new JSONObject();
            Obj.put("tableName", "UserBusinessLevelRight");
            Obj.put("joinColumn", "userid");
            Obj.put("refColumn", "Id");
            ary.put(Obj);
//            new ModelCreator(tableName, ary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exit(0);
    }

//    public ModelCreator(String tableName, boolean getsetYN, int gOrS) throws Exception {
//        JSONArray joinArray = new JSONArray();
//        getModelFileToPath(tableName, getsetYN, gOrS, joinArray);
//    }
//
//    public ModelCreator(String tableName, boolean getsetYN) throws Exception {
//        JSONArray joinArray = new JSONArray();
//        getModelFileToPath(tableName, getsetYN, 3, joinArray);
//    }
//
//    public ModelCreator(String tableName) throws Exception {
//        JSONArray joinArray = new JSONArray();
//        getModelFileToPath(tableName, true, 3, joinArray);
//    }
//
//    public ModelCreator(String tableName, boolean getsetYN, int gOrS, JSONArray joinArray) throws Exception {
//        getModelFileToPath(tableName, getsetYN, gOrS, joinArray);
//    }
//
//    public ModelCreator(String tableName, boolean getsetYN, JSONArray joinArray) throws Exception {
//        getModelFileToPath(tableName, getsetYN, 3, joinArray);
//    }
//
//    public ModelCreator(String tableName, JSONArray joinArray) throws Exception {
//        getModelFileToPath(tableName, true, 3, joinArray);
//    }
    public ModelCreator() throws Exception {
//        getModelFileToPath(tableName, true, 3, joinArray);
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

//    public static void getModelFileToPath(String tableName, boolean getsetYN, int gOrS, JSONArray joinArray) throws Exception {
//        JSONArray columnAry = dbUtils.getColumnArray(tableName);
//        String getSetStr = "";
//        String AuthorComment = "/**\n"
//                + " ***************************************\n"
//                + " *\n"
//                + " * @author " + AuthorName + "\n"
//                + " * Option " + OptonName + "\n"
//                + " * Date : " + new Date()
//                + " *\n"
//                + " **************************************/\n";
//        String OutString = AuthorComment
//                + "import com.baya.resources.persistence.GenericModel;\n"
//                + "import java.io.Serializable;\n"
//                + "import java.util.List;\n"
//                + "import javax.persistence.Column;\n"
//                + "import javax.persistence.Entity;\n"
//                + "import javax.persistence.FetchType;\n"
//                + "import javax.persistence.JoinColumn;\n"
//                + "import javax.persistence.OneToMany;\n"
//                + "import javax.persistence.Table;\n"
//                + "import org.hibernate.annotations.Cascade;\n"
//                + "import org.hibernate.annotations.CascadeType;\n"
//                + "import org.hibernate.annotations.Fetch;\n"
//                + "import org.hibernate.annotations.FetchMode;\n"
//                + "@Entity\n"
//                + "@Table(name = \"" + tableName + "\")\n"
//                + "public class " + tableName + "Model extends GenericModel implements Serializable {\n";
//        try {
//
//            System.out.println(columnAry);
//            for (int i = 0; i < columnAry.length(); i++) {
//                JSONObject tempObj = columnAry.getJSONObject(i);
//
////                if (!tempObj.getString("column_name").equalsIgnoreCase("id")
////                        && !tempObj.getString("column_name").equalsIgnoreCase("tableid")
////                        && !tempObj.getString("column_name").equalsIgnoreCase("lastmoddate")
////                        && !tempObj.getString("column_name").equalsIgnoreCase("lastmoduserid")) {
//                if (tempObj.getString("data_type").equalsIgnoreCase("character varying") || tempObj.getString("data_type").equalsIgnoreCase("character")) {
//                    tempObj.put("data_type", "String");
//                    OutString += ("@Column(name = \"" + tempObj.getString("column_name") + "\", nullable =  " + (tempObj.getString("is_nullable").equalsIgnoreCase("YES") ? true : false) + ", length = " + tempObj.getString("character_maximum_length") + " )\nprivate " + tempObj.getString("data_type") + " " + tempObj.getString("column_name") + ";") + "\n";
//                } else {
//                    if (tempObj.getString("data_type").equalsIgnoreCase("integer") || tempObj.getString("data_type").equalsIgnoreCase("bigint")) {
//                        tempObj.put("data_type", "Integer");
//                    }
//                    if (tempObj.getString("data_type").equalsIgnoreCase("numeric")) {
//                        tempObj.put("data_type", "Double");
//                    }
//                    if (tempObj.getString("numeric_scale").equals("null")) {
//                        tempObj.put("numeric_scale", "0");
//                    }
//                    if (tempObj.getString("numeric_precision").equals("null")) {
//                        tempObj.put("numeric_precision", "0");
//                    }
//                    OutString += ("@Column(name = \""
//                            + tempObj.getString("column_name")
//                            + "\", nullable = " + (tempObj.getString("is_nullable").equalsIgnoreCase("YES") ? true : false)
//                            + ", scale = " + ((tempObj.getString("numeric_scale").equals("0")) ? 0 : Integer.parseInt(tempObj.getString("numeric_scale")))
//                            + ", precision = " + ((tempObj.getString("numeric_precision").equals("0")) ? 0 : Integer.parseInt(tempObj.getString("numeric_precision")))
//                            + ")\nprivate " + tempObj.getString("data_type") + " " + tempObj.getString("column_name")
//                            + ";") + "\n";
//
//                }
//                getSetStr += genGetAndSet(tempObj.getString("column_name"), tempObj.getString("data_type"), gOrS);
////                }
//
//            }
//
//            for (int i = 0; i < joinArray.length(); i++) {
//                JSONObject joinObj = joinArray.getJSONObject(i);
//                OutString += "\n\n@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)\n"
//                        + "@Fetch(value = FetchMode.SUBSELECT)\n"
//                        + "@Cascade({CascadeType.SAVE_UPDATE, CascadeType.ALL})\n"
//                        + "@JoinColumn(name = \"" + joinObj.getString("joinColumn") + "\", referencedColumnName = \"" + joinObj.getString("refColumn") + "\")\n"
//                        + "private List<" + joinObj.getString("tableName") + "Model" + "> " + joinObj.getString("tableName") + "Model" + "Dtl;\n";
//
////                " + joinObj.getString("tableName")+"Model" + "Dtl
//                getSetStr += genGetAndSet(joinObj.getString("tableName") + "Model" + "Dtl", "List<" + joinObj.getString("tableName") + "Model" + "> ", gOrS);
//            }
//
//            if (getsetYN) {
//                OutString += getSetStr;
//            }
//            OutString += "}";
////            System.out.println(fileUtils.WriteToFile(fileSavePath, stringUtils.getFirstCaps(tableName) + "Model_tst.java", OutString));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static String getModelCode(String tableName, JSONArray joinArray, String AuthorName, String OptonName, String module, String subModule) throws Exception {
        JSONArray columnAry = dbUtils.getColumnArray(tableName);
        String getSetStr = "";
        String AuthorComment = ""
                + " package com.baya." + module + ".model." + subModule + ";"
                + " /***************************************\n"
                + " *\n"
                + " * @author " + AuthorName + "\n"
                + " * Option " + OptonName + "\n"
                + " * Date : " + new Date()
                + " *\n"
                + " **************************************/\n";
        String OutString = AuthorComment
                + "import com.baya.resources.persistence.GenericModel;\n"
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
                + "public class " + stringUtils.getFirstCaps(tableName) + "Model extends GenericModel implements Serializable {\n";
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
                    OutString += ("@Column(name = \"" + tempObj.getString("column_name") + "\", nullable =  " + (tempObj.getString("is_nullable").equalsIgnoreCase("YES") ? true : false) + ", length = " + tempObj.getString("character_maximum_length") + " )\nprivate " + tempObj.getString("data_type") + " " + tempObj.getString("column_name") + ";") + "\n";
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
                    OutString += ("@Column(name = \""
                            + tempObj.getString("column_name")
                            + "\", nullable = " + (tempObj.getString("is_nullable").equalsIgnoreCase("YES") ? true : false)
                            + ", scale = " + ((tempObj.getString("numeric_scale").equals("0")) ? 0 : Integer.parseInt(tempObj.getString("numeric_scale")))
                            + ", precision = " + ((tempObj.getString("numeric_precision").equals("0")) ? 0 : Integer.parseInt(tempObj.getString("numeric_precision")))
                            + ")\nprivate " + tempObj.getString("data_type") + " " + tempObj.getString("column_name")
                            + ";") + "\n";

                }
                getSetStr += genGetAndSet(tempObj.getString("column_name"), tempObj.getString("data_type"), 3);
//                }

            }

            for (int i = 0; i < joinArray.length(); i++) {
                JSONObject joinObj = joinArray.getJSONObject(i);
                OutString += "\n\n@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)\n"
                        + "@Fetch(value = FetchMode.SUBSELECT)\n"
                        + "@Cascade({CascadeType.SAVE_UPDATE, CascadeType.ALL})\n";
                JSONArray jFileds = joinObj.has("joinFields") ? joinObj.getJSONArray("joinFields") : new JSONArray();
                for (int j = 0; j < jFileds.length(); j++) {
                    JSONObject joinFieldsObj = jFileds.getJSONObject(j);
                    OutString += "@JoinColumn(name = \"" + joinFieldsObj.getString("joinColumn") + "\", referencedColumnName = \"" + joinFieldsObj.getString("refColumn") + "\")\n";
                }
                OutString += "private List<" + joinObj.getString("tableName") + "Model" + "> " + joinObj.getString("tableName") + "Model" + "Dtl;\n";

//                " + joinObj.getString("tableName")+"Model" + "Dtl
                getSetStr += genGetAndSet(joinObj.getString("tableName") + "Model" + "Dtl", "List<" + joinObj.getString("tableName") + "Model" + "> ", 3);
            }

            if (true) {
                OutString += getSetStr;
            }
            OutString += "}";
            return OutString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
