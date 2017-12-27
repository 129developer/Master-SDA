/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Utils;

import com.MasterMinds.optioncreatormavan.OptionCreator;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bayasys
 */
public class genUtils {

    private static String jarPath = "";

    static {
        jarPath = new File(genUtils.class.getProtectionDomain().getCodeSource().getLocation().toString()).getParentFile().getPath().replace("file:", "") + "/";
    }

    public static void generateMenu(Component cmp) throws Exception {
        String Function_data = fileUtils.ReadFromFile(jarPath, "data/Functions.data");
        String Components_data = fileUtils.ReadFromFile(jarPath, "data/Components.data");
        String Listeners_data = fileUtils.ReadFromFile(jarPath, "data/Listeners.data");

        class EditorPopUp extends JPopupMenu {

            JMenuItem anItem;

            public EditorPopUp() {
                try {
                    JMenuItem anItemChild;
                    JSONArray fun_Ary = new JSONArray();
                    JSONArray comp_Ary = new JSONArray();
                    JSONArray lis_Ary = new JSONArray();

                    if (Function_data.length() > 0) {
                        fun_Ary = new JSONArray(Function_data);
                    }
                    if (Components_data.length() > 0) {
                        comp_Ary = new JSONArray(Components_data);
                    }
                    if (Listeners_data.length() > 0) {
                        lis_Ary = new JSONArray(Listeners_data);
                    }
                    anItem = new JMenu("Add Functions");
                    for (int i = 0; i < fun_Ary.length(); i++) {
                        JSONObject obj = fun_Ary.getJSONObject(i);
                        anItemChild = new JMenuItem(obj.getString("name"));
                        anItemChild.setActionCommand(obj.getString("def"));
                        anItemChild.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                    stringUtils.copyToCb(evt.getActionCommand());
                                    stringUtils.Paste();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        anItem.add(anItemChild);
                    }
                    add(anItem);

                    anItem = new JMenu("Add Components");
                    for (int i = 0; i < comp_Ary.length(); i++) {
                        JSONObject obj = comp_Ary.getJSONObject(i);
                        anItemChild = new JMenuItem(obj.getString("name"));
                        anItemChild.setActionCommand(obj.getString("def"));
                        anItemChild.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                    stringUtils.copyToCb(evt.getActionCommand());
                                    stringUtils.Paste();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        anItem.add(anItemChild);
                    }
                    add(anItem);

                    anItem = new JMenu("Add Listeners");
                    for (int i = 0; i < lis_Ary.length(); i++) {
                        JSONObject obj = lis_Ary.getJSONObject(i);
                        anItemChild = new JMenuItem(obj.getString("name"));
                        anItemChild.setActionCommand(obj.getString("def"));
                        anItemChild.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                    stringUtils.copyToCb(evt.getActionCommand());
                                    stringUtils.Paste();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        anItem.add(anItemChild);
                    }
                    add(anItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        class PopClickListener extends MouseAdapter {

            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            private void doPop(MouseEvent e) {
                EditorPopUp menu = new EditorPopUp();
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        cmp.addMouseListener(new PopClickListener());
    }

    public static JSONArray removeAtIndex(JSONArray ary, int index) throws Exception {
        JSONArray newArray = new JSONArray();
        int len = ary.length();
        if (ary != null) {
            for (int i = 0; i < len; i++) {
                if (i != index) {
                    newArray.put(ary.get(i));
                }
            }
        }
        return newArray;
    }

    public static void showErrorAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showAlert(String msg, String title, int type) {
        JOptionPane.showMessageDialog(null, msg, title,
                type != 0 ? type : JOptionPane.INFORMATION_MESSAGE);
    }

    public static String getAPIFilePath(String projPath, String module, String subModule, String type) {
        String temp = "%APIPATH%/src/com/baya/%MOD%/%FILETYP%/%SUMMOD%";
        temp = temp.replace("%APIPATH%", projPath);
        temp = temp.replace("%MOD%", module);
        temp = temp.replace("%SUMMOD%", subModule);
        temp = temp.replace("%FILETYP%", type);
        return temp;
    }

}
