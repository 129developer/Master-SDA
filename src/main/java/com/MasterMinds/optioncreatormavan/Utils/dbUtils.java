/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hsqldb.User;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mukil
 */
public class dbUtils {

    static SessionFactory factory;

    private static SessionFactory concreteSessionFactory;
    private static Properties prop = new Properties();
    private static Properties userProp = new Properties();

    static {
        try {

//            File jarPath = new File(dbUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//            String propertiesPath = jarPath.getParentFile().getAbsolutePath();
//            File file = new File(propertiesPath + "/connection.properties");
            InputStream ins = dbUtils.class.getResourceAsStream("/connection.properties");
            InputStream userPropIns = dbUtils.class.getResourceAsStream("/userProperties.properties");
//            InputStream ins = new FileInputStream(file);
            prop.load(ins);
            userProp.load(userPropIns);

//            saveConnectionProperty(prop);
            concreteSessionFactory = new AnnotationConfiguration()
                    .addPackage("com.concretepage.persistence")
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static Properties getProp() {
        return prop;
    }

    public static Properties getUserProp() {
        return userProp;
    }

    public static void setProp(Properties prop) {
        dbUtils.prop = prop;
    }

    public static void saveConnectionProperty(Properties properties) throws Exception {
        prop = properties;
        File file = new File(dbUtils.class.getResource("/connection.properties").getPath());
//        boolean a = file.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file);
        properties.store(fileOut, "Favorite Things");
        fileOut.close();

    }

    public static void saveUserProperty(Properties properties) throws Exception {
        userProp = properties;
        File file = new File(dbUtils.class.getResource("/userProperties.properties").getPath());
//        boolean a = file.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file);
        properties.store(fileOut, "Favorite Things");
        fileOut.close();

    }

//    public void dbUtils() {
//        try {
//            Properties prop = new Properties();
//            InputStream ins = getClass().getResourceAsStream("/connection.properties");
//            prop.load(ins);
//            concreteSessionFactory = new AnnotationConfiguration()
//                    .addPackage("com.concretepage.persistence")
//                    .addProperties(prop)
//                    .addAnnotatedClass(User.class)
//                    .buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
    public static void SetSessionFactory(String URL, String USERNAME, String PASSWORD) {
        try {
//            new dbUtils().dbUtils();
            AnnotationConfiguration conf = new AnnotationConfiguration().configure();
            // <!-- Database connection settings -->
            conf.setProperty("connection.url", URL);
            conf.setProperty("connection.username", USERNAME);
            conf.setProperty("connection.password", PASSWORD);
            conf.setProperty("connection.driver_class", "org.postgresql.Driver");
            conf.configure();
            factory = conf.buildSessionFactory();

        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static JSONArray getRes(String query) throws Exception {
//        new dbUtils().dbUtils();
//        SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = concreteSessionFactory.openSession();
        session.beginTransaction();
        SQLQuery exQuery = session.createSQLQuery(query);
        System.out.println(exQuery.toString());
        List res = exQuery.list();
        if (res.size() > 0) {
            return new JSONArray((String) res.get(0));
        }
        return new JSONArray();
    }

    public static String QueryGenerator(String tableName) {
        return "SELECT GetTableDetails(cast('" + tableName + "' as text));";
    }

    public static JSONArray getColumnArray(String tableName) throws Exception {
        JSONArray columnAry = dbUtils.getRes(dbUtils.QueryGenerator(tableName));
        JSONArray RetcolumnAry = new JSONArray();
        for (int i = 0; i < columnAry.length(); i++) {
            JSONObject tempObj = columnAry.getJSONObject(i);
            if (!tempObj.getString("column_name").equalsIgnoreCase("id")
                    && !tempObj.getString("column_name").equalsIgnoreCase("tableid")
                    && !tempObj.getString("column_name").equalsIgnoreCase("lastmoddate")
                    && !tempObj.getString("column_name").equalsIgnoreCase("lastmoduserid")) {
                RetcolumnAry.put(tempObj);
            }
        }
        return RetcolumnAry;
    }

    public static void showAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
