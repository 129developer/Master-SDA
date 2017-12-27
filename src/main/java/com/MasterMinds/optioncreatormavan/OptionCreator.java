/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan;

import com.MasterMinds.optioncreatormavan.Creators.DAOCreator;
import com.MasterMinds.optioncreatormavan.Creators.ModelCreator;
import com.MasterMinds.optioncreatormavan.Creators.ServiceCreator;
import com.MasterMinds.optioncreatormavan.Define.CodeEditor;
import com.MasterMinds.optioncreatormavan.Define.ComponentDefinition;
import com.MasterMinds.optioncreatormavan.Define.FunctionDefinition;
import com.MasterMinds.optioncreatormavan.Define.ListenerDefinition;
import com.MasterMinds.optioncreatormavan.Utils.CustomOutputStream;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import com.MasterMinds.optioncreatormavan.Utils.dbUtils;
import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.genUtils;
import com.MasterMinds.optioncreatormavan.Utils.stringUtils;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.Properties;
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bayasys
 */
public class OptionCreator extends javax.swing.JFrame {

    /**
     * Creates new form OptionCreator
     */
    private static String jarPath = "", modelCode = "",
            daoCode = "",
            daoImplCode = "",
            serviceImplCode = "",
            serviceCode = "";

    private static joinListModel listMdl = new joinListModel();
    private static PrintStream standardOut;
    private static PrintStream printStream;

    public void setModelCode(String mc) {
        this.modelCode = mc;
    }

    public void setDAOCode(String mc) {
        this.daoCode = mc;
    }

    public void setDAOImplCode(String mc) {
        this.daoImplCode = mc;
    }

    public void setServiceCode(String mc) {
        this.serviceCode = mc;
    }

    public void setServiceImplCode(String mc) {
        this.serviceImplCode = mc;
    }

    public static class joinListModel extends AbstractListModel {

        private JSONArray joinAry = new JSONArray();

        @Override
        public int getSize() {
            return joinAry.length();
        }

        public JSONArray getJoinArray() {
            return joinAry;
        }

        private int findInAry(String tname) throws Exception {
            for (int i = 0; i < joinAry.length(); i++) {
                if (joinAry.getJSONObject(i).getString("tableName").equalsIgnoreCase(tname)) {
                    return i;
                }
            }
            return -1;
        }

        public void add(JSONObject obj) {
            try {
                if (findInAry(obj.getString("tableName")) != -1) {
                    int index = findInAry(obj.getString("tableName"));
                    JSONObject oldObj = joinAry.getJSONObject(index);
                    JSONArray OldJoinFields = oldObj.getJSONArray("joinFields");
                    JSONArray joinFields = obj.getJSONArray("joinFields");
                    for (int j = 0; j < joinFields.length(); j++) {
                        JSONObject joinFieldsObj = joinFields.getJSONObject(j);
                        OldJoinFields.put(joinFieldsObj);
                    }
                    oldObj.put("joinFields", OldJoinFields);
                    genUtils.removeAtIndex(joinAry, index);
                    joinAry.put(index, oldObj);
                    fireIntervalAdded(this, index, index);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            int index0 = joinAry.length() - 1;
            int index1 = index0;
            joinAry.put(obj);
            fireIntervalAdded(this, index0, index1);
        }

        public void remove(int index) {
            try {
                joinAry = genUtils.removeAtIndex(joinAry, index);
                fireIntervalRemoved(this, index, index);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public Object getElementAt(int index) {
            try {
                return joinAry.getJSONObject(index).getString("tableName");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(ImageIcon img) {
            this(img.getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(830, 472);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            Image scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(img, 0, 0, null);
        }

    }

    public OptionCreator() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parent_tab = new javax.swing.JTabbedPane();
        newOption_Pnl = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        option_txf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tableName_txb = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        module_txf = new javax.swing.JTextField();
        subModule_txf = new javax.swing.JTextField();
        generate_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        table_txf1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        genModel_chb = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        genDAO_chb = new javax.swing.JCheckBox();
        genService_chb = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        genModel_pnl = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        reftable_txb = new javax.swing.JTextField();
        refField_txb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        mainreffield_txb = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jointables_list = new javax.swing.JList<>();
        joinAdd_btn = new javax.swing.JButton();
        joinRemove_btn = new javax.swing.JButton();
        ModelFileName_txb = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        modelView_Btn = new javax.swing.JButton();
        daopannel_pnl = new javax.swing.JPanel();
        daoView_Btn = new javax.swing.JButton();
        daoImplView_Btn = new javax.swing.JButton();
        daoFileName_txb = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        daoImplFilename_txb2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        seervicepannel_pnl = new javax.swing.JPanel();
        serviceView_Btn = new javax.swing.JButton();
        serviceImplView_Btn1 = new javax.swing.JButton();
        serviceFileName_txb = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        serviceImplFileName_txb = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        genWebModel = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        webControllerPannel_pnl1 = new javax.swing.JPanel();
        webControllerView_Btn1 = new javax.swing.JButton();
        javax.swing.JTextField webControllerFileName_txb1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        webModelpannel_pnl3 = new javax.swing.JPanel();
        webControllerView_Btn3 = new javax.swing.JButton();
        javax.swing.JTextField webControllerFileName_txb = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        log_txtA = new javax.swing.JTextArea();
        settings_Pnl = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username_tf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apipath_tf = new javax.swing.JTextField();
        apipathChoose_btn = new javax.swing.JButton();
        webpathChoose_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        webpath_tf1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dbDriver_tf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dbURL_tf3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dbUsername_tf4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dbPasswd_tf5 = new javax.swing.JTextField();
        about_pnl = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file_mnu = new javax.swing.JMenu();
        edit_mnu = new javax.swing.JMenu();
        define_mnu = new javax.swing.JMenu();
        view_mnu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Master Dev");

        parent_tab.setFont(new java.awt.Font("NanumGothic", 1, 14)); // NOI18N

        newOption_Pnl.setDoubleBuffered(false);
        newOption_Pnl.setOpaque(false);

        jLabel9.setText("Option");

        jLabel10.setText("Table");

        tableName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableName_txbActionPerformed(evt);
            }
        });

        jLabel11.setText("Module");

        jLabel12.setText("Sub Module");

        generate_btn.setText("Generate");
        generate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_btnActionPerformed(evt);
            }
        });

        reset_btn.setText("Reset");
        reset_btn.setToolTipText("");

        jLabel13.setText("Table");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(option_txf))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(table_txf1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(module_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subModule_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generate_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reset_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(module_txf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(table_txf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(subModule_txf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(option_txf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(generate_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reset_btn)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        genModel_chb.setText("Generate Model");
        genModel_chb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genModel_chbActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Generate DTO");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        genDAO_chb.setText("Generate DAO & Implimentation");
        genDAO_chb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genDAO_chbActionPerformed(evt);
            }
        });

        genService_chb.setText("Generate Service & Implimentation");
        genService_chb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genService_chbActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Generate Controller");

        jLabel8.setText("Table");

        jLabel14.setText("Ref");

        mainreffield_txb.setText("Id");

        jLabel15.setText("Id");

        jointables_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jointables_list);

        joinAdd_btn.setText("Add");
        joinAdd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinAdd_btnActionPerformed(evt);
            }
        });

        joinRemove_btn.setText("Remove");
        joinRemove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinRemove_btnActionPerformed(evt);
            }
        });

        ModelFileName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModelFileName_txbActionPerformed(evt);
            }
        });

        jLabel16.setText("File");

        javax.swing.GroupLayout genModel_pnlLayout = new javax.swing.GroupLayout(genModel_pnl);
        genModel_pnl.setLayout(genModel_pnlLayout);
        genModel_pnlLayout.setHorizontalGroup(
            genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genModel_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(genModel_pnlLayout.createSequentialGroup()
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(genModel_pnlLayout.createSequentialGroup()
                                .addComponent(joinAdd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(joinRemove_btn))
                            .addGroup(genModel_pnlLayout.createSequentialGroup()
                                .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(25, 25, 25)
                                .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(refField_txb)
                                    .addComponent(mainreffield_txb))))
                        .addGap(181, 181, 181))
                    .addGroup(genModel_pnlLayout.createSequentialGroup()
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(genModel_pnlLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reftable_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(genModel_pnlLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ModelFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))))))
        );
        genModel_pnlLayout.setVerticalGroup(
            genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, genModel_pnlLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModelFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, genModel_pnlLayout.createSequentialGroup()
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(reftable_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(refField_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(mainreffield_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(genModel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(joinAdd_btn)
                            .addComponent(joinRemove_btn)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        modelView_Btn.setText("View Code");
        modelView_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelView_BtnActionPerformed(evt);
            }
        });

        daoView_Btn.setText("View Code");
        daoView_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daoView_BtnActionPerformed(evt);
            }
        });

        daoImplView_Btn.setText("View Impl Code");
        daoImplView_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daoImplView_BtnActionPerformed(evt);
            }
        });

        daoFileName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daoFileName_txbActionPerformed(evt);
            }
        });

        jLabel17.setText("DAO File");

        daoImplFilename_txb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daoImplFilename_txb2ActionPerformed(evt);
            }
        });

        jLabel18.setText("DAO Impl File");

        javax.swing.GroupLayout daopannel_pnlLayout = new javax.swing.GroupLayout(daopannel_pnl);
        daopannel_pnl.setLayout(daopannel_pnlLayout);
        daopannel_pnlLayout.setHorizontalGroup(
            daopannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daopannel_pnlLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(daoView_Btn)
                .addGap(18, 18, 18)
                .addComponent(daoImplView_Btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(daopannel_pnlLayout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daoFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(daopannel_pnlLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daoImplFilename_txb2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        daopannel_pnlLayout.setVerticalGroup(
            daopannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, daopannel_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(daopannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daoFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(daopannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daoImplFilename_txb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(daopannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daoView_Btn)
                    .addComponent(daoImplView_Btn)))
        );

        serviceView_Btn.setText("View Code");
        serviceView_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceView_BtnActionPerformed(evt);
            }
        });

        serviceImplView_Btn1.setText("View Impl Code");
        serviceImplView_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceImplView_Btn1ActionPerformed(evt);
            }
        });

        serviceFileName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceFileName_txbActionPerformed(evt);
            }
        });

        jLabel19.setText("Service File");

        serviceImplFileName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceImplFileName_txbActionPerformed(evt);
            }
        });

        jLabel20.setText("Service Impl File");

        javax.swing.GroupLayout seervicepannel_pnlLayout = new javax.swing.GroupLayout(seervicepannel_pnl);
        seervicepannel_pnl.setLayout(seervicepannel_pnlLayout);
        seervicepannel_pnlLayout.setHorizontalGroup(
            seervicepannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seervicepannel_pnlLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(serviceView_Btn)
                .addGap(18, 18, 18)
                .addComponent(serviceImplView_Btn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(seervicepannel_pnlLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serviceFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(seervicepannel_pnlLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serviceImplFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        seervicepannel_pnlLayout.setVerticalGroup(
            seervicepannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seervicepannel_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seervicepannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(seervicepannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceImplFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(seervicepannel_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceView_Btn)
                    .addComponent(serviceImplView_Btn1)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(genModel_chb)
                        .addGap(123, 123, 123)
                        .addComponent(modelView_Btn))
                    .addComponent(genModel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(genService_chb)
                        .addGap(280, 280, 280)
                        .addComponent(jCheckBox5))
                    .addComponent(genDAO_chb)
                    .addComponent(daopannel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seervicepannel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genModel_chb)
                    .addComponent(genService_chb)
                    .addComponent(jCheckBox5)
                    .addComponent(modelView_Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(genModel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(seervicepannel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genDAO_chb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(daopannel_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("API", jPanel2);

        genWebModel.setText("Generate Model");
        genWebModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genWebModelActionPerformed(evt);
            }
        });

        jCheckBox7.setText("Generate Controller");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        webControllerView_Btn1.setText("View Code");
        webControllerView_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webControllerView_Btn1ActionPerformed(evt);
            }
        });

        webControllerFileName_txb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webControllerFileName_txb1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Controller File");

        javax.swing.GroupLayout webControllerPannel_pnl1Layout = new javax.swing.GroupLayout(webControllerPannel_pnl1);
        webControllerPannel_pnl1.setLayout(webControllerPannel_pnl1Layout);
        webControllerPannel_pnl1Layout.setHorizontalGroup(
            webControllerPannel_pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webControllerPannel_pnl1Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(webControllerPannel_pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webControllerPannel_pnl1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(webControllerView_Btn1))
                    .addComponent(webControllerFileName_txb1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        webControllerPannel_pnl1Layout.setVerticalGroup(
            webControllerPannel_pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webControllerPannel_pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webControllerPannel_pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webControllerFileName_txb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(webControllerView_Btn1)
                .addContainerGap())
        );

        webControllerView_Btn3.setText("View Code");
        webControllerView_Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webControllerView_Btn3ActionPerformed(evt);
            }
        });

        webControllerFileName_txb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webControllerFileName_txbActionPerformed(evt);
            }
        });

        jLabel23.setText("Model File");

        javax.swing.GroupLayout webModelpannel_pnl3Layout = new javax.swing.GroupLayout(webModelpannel_pnl3);
        webModelpannel_pnl3.setLayout(webModelpannel_pnl3Layout);
        webModelpannel_pnl3Layout.setHorizontalGroup(
            webModelpannel_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webModelpannel_pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(webModelpannel_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webModelpannel_pnl3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(webControllerView_Btn3))
                    .addComponent(webControllerFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        webModelpannel_pnl3Layout.setVerticalGroup(
            webModelpannel_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webModelpannel_pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webModelpannel_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webControllerFileName_txb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(webControllerView_Btn3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genWebModel)
                    .addComponent(webModelpannel_pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webControllerPannel_pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7))
                .addGap(0, 343, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genWebModel)
                    .addComponent(jCheckBox7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(webControllerPannel_pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webModelpannel_pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("WEB", jPanel3);

        log_txtA.setEditable(false);
        log_txtA.setColumns(20);
        log_txtA.setRows(5);
        jScrollPane2.setViewportView(log_txtA);

        javax.swing.GroupLayout newOption_PnlLayout = new javax.swing.GroupLayout(newOption_Pnl);
        newOption_Pnl.setLayout(newOption_PnlLayout);
        newOption_PnlLayout.setHorizontalGroup(
            newOption_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addComponent(jScrollPane2)
        );
        newOption_PnlLayout.setVerticalGroup(
            newOption_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOption_PnlLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
        );

        parent_tab.addTab("New Option", newOption_Pnl);

        settings_Pnl.setOpaque(false);

        jLabel1.setText("User Name");

        username_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_tfActionPerformed(evt);
            }
        });

        jLabel2.setText("API Path");

        apipath_tf.setToolTipText("");

        apipathChoose_btn.setText("Browse");
        apipathChoose_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apipathChoose_btnActionPerformed(evt);
            }
        });

        webpathChoose_btn.setText("Browse");
        webpathChoose_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webpathChoose_btnActionPerformed(evt);
            }
        });

        jLabel3.setText("WEB Path");

        webpath_tf1.setToolTipText("");

        jLabel4.setText("DB Driver");

        dbDriver_tf.setToolTipText("");

        jLabel5.setText("URL");

        dbURL_tf3.setToolTipText("");

        jLabel6.setText("User Name");

        dbUsername_tf4.setToolTipText("");

        jLabel7.setText("Password");

        jButton1.setText("Apply");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dbPasswd_tf5.setToolTipText("");

        javax.swing.GroupLayout settings_PnlLayout = new javax.swing.GroupLayout(settings_Pnl);
        settings_Pnl.setLayout(settings_PnlLayout);
        settings_PnlLayout.setHorizontalGroup(
            settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settings_PnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settings_PnlLayout.createSequentialGroup()
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settings_PnlLayout.createSequentialGroup()
                                .addComponent(username_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settings_PnlLayout.createSequentialGroup()
                                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(settings_PnlLayout.createSequentialGroup()
                                        .addComponent(dbPasswd_tf5, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                                        .addGap(173, 173, 173)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(apipath_tf, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(webpath_tf1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(webpathChoose_btn)
                                    .addGroup(settings_PnlLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(apipathChoose_btn))))))
                    .addGroup(settings_PnlLayout.createSequentialGroup()
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dbURL_tf3)
                            .addComponent(dbDriver_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addComponent(dbUsername_tf4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        settings_PnlLayout.setVerticalGroup(
            settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settings_PnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(apipath_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apipathChoose_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(webpath_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webpathChoose_btn))
                .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settings_PnlLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dbDriver_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dbURL_tf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dbUsername_tf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settings_PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(dbPasswd_tf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(settings_PnlLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(491, Short.MAX_VALUE))
        );

        parent_tab.addTab("Settings", settings_Pnl);

        about_pnl.setOpaque(false);

        javax.swing.GroupLayout about_pnlLayout = new javax.swing.GroupLayout(about_pnl);
        about_pnl.setLayout(about_pnlLayout);
        about_pnlLayout.setHorizontalGroup(
            about_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1151, Short.MAX_VALUE)
        );
        about_pnlLayout.setVerticalGroup(
            about_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );

        parent_tab.addTab("About", about_pnl);

        file_mnu.setText("File");
        jMenuBar1.add(file_mnu);

        edit_mnu.setText("Edit");
        jMenuBar1.add(edit_mnu);

        define_mnu.setText("Define");
        jMenuBar1.add(define_mnu);

        view_mnu.setText("View");
        jMenuBar1.add(view_mnu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parent_tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parent_tab)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void username_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_tfActionPerformed

    private void apipathChoose_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apipathChoose_btnActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            apipath_tf.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_apipathChoose_btnActionPerformed

    private void webpathChoose_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webpathChoose_btnActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            webpath_tf1.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_webpathChoose_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        applyDbProperties();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableName_txbActionPerformed

    }//GEN-LAST:event_tableName_txbActionPerformed

    private void generate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_btnActionPerformed

        try {
            // Model Generation
            if (genModel_chb.isSelected()) {
                String filepath = genUtils.getAPIFilePath(apipath_tf.getText(), module_txf.getText(), subModule_txf.getText(), "model");
//                dbUtils.showAlert(filepath);
                System.out.println(filepath);
                if (modelCode.length() > 0) {
                    fileUtils.WriteToFile(filepath, "/" + ModelFileName_txb.getText() + ".java", modelCode);
                } else {
                    ModelCreator mc = new ModelCreator();
                    modelCode = mc.getModelCode(tableName_txb.getText(), listMdl.getJoinArray(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    fileUtils.WriteToFile(filepath, "/" + ModelFileName_txb.getText() + ".java", modelCode);
                }
            }
            //DAO Generation
            if (genDAO_chb.isSelected()) {
                String filepath = genUtils.getAPIFilePath(apipath_tf.getText(), module_txf.getText(), subModule_txf.getText(), "dao");
//                dbUtils.showAlert(filepath);
                System.out.println(filepath);
                if (daoCode.length() > 0) {
                    fileUtils.WriteToFile(filepath, "/" + daoFileName_txb.getText() + ".java", daoCode);
                } else {
                    DAOCreator mc = new DAOCreator();
                    daoCode = mc.getDaoCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    fileUtils.WriteToFile(filepath, "/" + daoFileName_txb.getText() + ".java", daoCode);
                }

                String filepath1 = genUtils.getAPIFilePath(apipath_tf.getText(), module_txf.getText(), subModule_txf.getText(), "dao");
//                dbUtils.showAlert(filepath);
                System.out.println(filepath);
                if (daoImplCode.length() > 0) {
                    fileUtils.WriteToFile(filepath, "/" + daoImplFilename_txb2.getText() + ".java", daoImplCode);
                } else {
                    DAOCreator mc = new DAOCreator();
                    daoImplCode = mc.getDaoImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    fileUtils.WriteToFile(filepath1, "/" + daoImplFilename_txb2.getText() + ".java", daoImplCode);
                }
            }
            //SERVICE Generation
            if (genService_chb.isSelected()) {
                String filepath = genUtils.getAPIFilePath(apipath_tf.getText(), module_txf.getText(), subModule_txf.getText(), "service");
//                dbUtils.showAlert(filepath);
                System.out.println(filepath);
                if (serviceCode.length() > 0) {
                    fileUtils.WriteToFile(filepath, "/" + serviceFileName_txb.getText() + ".java", serviceCode);
                } else {
                    ServiceCreator mc = new ServiceCreator();
                    serviceCode = mc.getServiceCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    fileUtils.WriteToFile(filepath, "/" + serviceFileName_txb.getText() + ".java", serviceCode);
                }

                String filepath1 = genUtils.getAPIFilePath(apipath_tf.getText(), module_txf.getText(), subModule_txf.getText(), "service");
//                dbUtils.showAlert(filepath);
                System.out.println(filepath);
                if (serviceImplCode.length() > 0) {
                    fileUtils.WriteToFile(filepath, "/" + serviceImplFileName_txb.getText() + ".java", serviceImplCode);
                } else {
                    ServiceCreator mc = new ServiceCreator();
                    serviceImplCode = mc.getServiceImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    fileUtils.WriteToFile(filepath1, "/" + serviceImplFileName_txb.getText() + ".java", serviceImplCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_generate_btnActionPerformed

    private void serviceImplFileName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceImplFileName_txbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceImplFileName_txbActionPerformed

    private void serviceFileName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceFileName_txbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceFileName_txbActionPerformed

    private void serviceImplView_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceImplView_Btn1ActionPerformed
        try {
            if (tableName_txb.getText().length() > 0) {
                if (serviceImplCode.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Any changes in the code will be lost, Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        ServiceCreator mc = new ServiceCreator();
                        serviceImplCode = mc.getServiceImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                        CodeEditor ce = new CodeEditor(jarPath, this, "SERVICEIMPL", serviceImplFileName_txb.getText(), serviceImplCode);
                        ce.invoke();
                    } else if (response == JOptionPane.NO_OPTION) {
                        CodeEditor ce = new CodeEditor(jarPath, this, "SERVICEIMPL", serviceImplFileName_txb.getText(), serviceImplCode);
                        ce.invoke();
                    }
                } else {
                    ServiceCreator mc = new ServiceCreator();
                    serviceImplCode = mc.getServiceImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    CodeEditor ce = new CodeEditor(jarPath, this, "SERVICEIMPL", serviceImplFileName_txb.getText(), serviceImplCode);
                    ce.invoke();
                }

            } else {
                dbUtils.showAlert("Please enter a table name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_serviceImplView_Btn1ActionPerformed

    private void serviceView_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceView_BtnActionPerformed
        try {
            if (tableName_txb.getText().length() > 0) {
                if (serviceCode.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Any changes in the code will be lost, Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        ServiceCreator mc = new ServiceCreator();
                        serviceCode = mc.getServiceCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                        CodeEditor ce = new CodeEditor(jarPath, this, "SERVICE", serviceImplFileName_txb.getText(), serviceCode);
                        ce.invoke();
                    } else if (response == JOptionPane.NO_OPTION) {
                        CodeEditor ce = new CodeEditor(jarPath, this, "SERVICE", serviceImplFileName_txb.getText(), serviceCode);
                        ce.invoke();
                    }
                } else {
                    ServiceCreator mc = new ServiceCreator();
                    serviceCode = mc.getServiceCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    CodeEditor ce = new CodeEditor(jarPath, this, "SERVICE", serviceImplFileName_txb.getText(), serviceCode);
                    ce.invoke();
                }

            } else {
                dbUtils.showAlert("Please enter a table name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_serviceView_BtnActionPerformed

    private void daoImplFilename_txb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daoImplFilename_txb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daoImplFilename_txb2ActionPerformed

    private void daoFileName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daoFileName_txbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daoFileName_txbActionPerformed

    private void daoImplView_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daoImplView_BtnActionPerformed
        try {
            if (tableName_txb.getText().length() > 0) {
                if (daoImplCode.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Any changes in the code will be lost, Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        DAOCreator mc = new DAOCreator();
                        daoImplCode = mc.getDaoImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                        CodeEditor ce = new CodeEditor(jarPath, this, "DAOIMPL", daoImplFilename_txb2.getText(), daoImplCode);
                        ce.invoke();
                    } else if (response == JOptionPane.NO_OPTION) {
                        CodeEditor ce = new CodeEditor(jarPath, this, "DAOIMPL", daoImplFilename_txb2.getText(), daoImplCode);
                        ce.invoke();
                    }
                } else {
                    DAOCreator mc = new DAOCreator();
                    daoImplCode = mc.getDaoImplCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    CodeEditor ce = new CodeEditor(jarPath, this, "DAOIMPL", daoImplFilename_txb2.getText(), daoImplCode);
                    ce.invoke();
                }

            } else {
                dbUtils.showAlert("Please enter a table name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_daoImplView_BtnActionPerformed

    private void daoView_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daoView_BtnActionPerformed
        try {
            if (tableName_txb.getText().length() > 0) {
                if (daoCode.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Any changes in the code will be lost, Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        DAOCreator mc = new DAOCreator();
                        daoCode = mc.getDaoCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                        CodeEditor ce = new CodeEditor(jarPath, this, "DAO", daoFileName_txb.getText(), daoCode);
                        ce.invoke();
                    } else if (response == JOptionPane.NO_OPTION) {
                        CodeEditor ce = new CodeEditor(jarPath, this, "DAO", daoFileName_txb.getText(), daoCode);
                        ce.invoke();
                    }
                } else {
                    DAOCreator mc = new DAOCreator();
                    daoCode = mc.getDaoCode(tableName_txb.getText(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    CodeEditor ce = new CodeEditor(jarPath, this, "DAO", daoFileName_txb.getText(), daoCode);
                    ce.invoke();
                }

            } else {
                dbUtils.showAlert("Please enter a table name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_daoView_BtnActionPerformed

    private void modelView_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelView_BtnActionPerformed
        try {
            if (tableName_txb.getText().length() > 0) {
                if (modelCode.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Any changes in the code will be lost, Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        ModelCreator mc = new ModelCreator();
                        modelCode = mc.getModelCode(tableName_txb.getText(), listMdl.getJoinArray(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                        CodeEditor ce = new CodeEditor(jarPath, this, "MODEL", ModelFileName_txb.getText(), modelCode);
                        ce.invoke();
                    } else if (response == JOptionPane.NO_OPTION) {
                        CodeEditor ce = new CodeEditor(jarPath, this, "MODEL", ModelFileName_txb.getText(), modelCode);
                        ce.invoke();
                    }
                } else {
                    ModelCreator mc = new ModelCreator();
                    modelCode = mc.getModelCode(tableName_txb.getText(), listMdl.getJoinArray(), dbUtils.getUserProp().getProperty("username"), option_txf.getText(), module_txf.getText(), subModule_txf.getText());
                    CodeEditor ce = new CodeEditor(jarPath, this, "MODEL", ModelFileName_txb.getText(), modelCode);
                    ce.invoke();
                }

            } else {
                dbUtils.showAlert("Please enter a table name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_modelView_BtnActionPerformed

    private void ModelFileName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModelFileName_txbActionPerformed
        // TODO add your handling code here:q
    }//GEN-LAST:event_ModelFileName_txbActionPerformed

    private void joinRemove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinRemove_btnActionPerformed
        try {
            listMdl.remove(jointables_list.getSelectedIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_joinRemove_btnActionPerformed

    private void joinAdd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinAdd_btnActionPerformed
        try {
            JSONObject obj = new JSONObject();
            obj.put("tableName", reftable_txb.getText());
            JSONObject fieldOb = new JSONObject();
            JSONArray fieldObAry = new JSONArray();
            fieldOb.put("joinColumn", mainreffield_txb.getText());
            fieldOb.put("refColumn", refField_txb.getText());
            fieldObAry.put(fieldOb);
            obj.put("joinFields", fieldObAry);
            reftable_txb.setText("");
            refField_txb.setText("");
            mainreffield_txb.setText("Id");
            listMdl.add(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_joinAdd_btnActionPerformed

    private void genService_chbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genService_chbActionPerformed
        if (genService_chb.isSelected()) {
            seervicepannel_pnl.setVisible(true);
        } else {
            seervicepannel_pnl.setVisible(false);
        }
    }//GEN-LAST:event_genService_chbActionPerformed

    private void genDAO_chbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genDAO_chbActionPerformed
        if (genDAO_chb.isSelected()) {
            daopannel_pnl.setVisible(true);
        } else {
            daopannel_pnl.setVisible(false);
        }
    }//GEN-LAST:event_genDAO_chbActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void genModel_chbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genModel_chbActionPerformed
        if (genModel_chb.isSelected()) {
            genModel_pnl.setVisible(true);
            modelView_Btn.setEnabled(true);
        } else {
            genModel_pnl.setVisible(false);
            modelView_Btn.setEnabled(false);

        }
    }//GEN-LAST:event_genModel_chbActionPerformed

    private void webControllerView_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webControllerView_Btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webControllerView_Btn1ActionPerformed

    private void webControllerFileName_txb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webControllerFileName_txb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webControllerFileName_txb1ActionPerformed

    private void webControllerView_Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webControllerView_Btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webControllerView_Btn3ActionPerformed

    private void webControllerFileName_txbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webControllerFileName_txbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webControllerFileName_txbActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        if (jCheckBox7.isSelected()) {
            webControllerPannel_pnl1.setVisible(true);
//            modelView_Btn.setEnabled(true);
        } else {
            webControllerPannel_pnl1.setVisible(false);
//            modelView_Btn.setEnabled(false);

        }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void genWebModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genWebModelActionPerformed
        // TODO add your handling code here:  webModelpannel_pnl3
        if (genWebModel.isSelected()) {
            webModelpannel_pnl3.setVisible(true);
//            modelView_Btn.setEnabled(true);
        } else {
            webModelpannel_pnl3.setVisible(false);
//            modelView_Btn.setEnabled(false);

        }
    }//GEN-LAST:event_genWebModelActionPerformed

    private static void tableNameChange(OptionCreator opc) {
        opc.ModelFileName_txb.setText(stringUtils.getFirstCaps(opc.tableName_txb.getText()) + "Model");
        opc.daoFileName_txb.setText(stringUtils.getFirstCaps(opc.tableName_txb.getText()) + "DAO");
        opc.daoImplFilename_txb2.setText(stringUtils.getFirstCaps(opc.tableName_txb.getText()) + "DAO_Impl");
        opc.serviceFileName_txb.setText(stringUtils.getFirstCaps(opc.tableName_txb.getText()) + "Service");
        opc.serviceImplFileName_txb.setText(stringUtils.getFirstCaps(opc.tableName_txb.getText()) + "Service_Impl");
    }

    public static JTextArea getLogTA(OptionCreator oc) {
        return oc.log_txtA;
    }

    private static void showHideLog(OptionCreator oc) {
        if (oc.log_txtA.isVisible()) {
            oc.log_txtA.setVisible(false);
            oc.log_txtA.setText("");
            System.setOut(standardOut);
            System.setErr(standardOut);
        } else {
            oc.log_txtA.setVisible(true);
            printStream = new PrintStream(new CustomOutputStream(oc));
            System.setOut(printStream);
            System.setErr(printStream);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        setDefaultLookAndFeelDecorated(true);

        //</editor-fold>
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    jarPath = new File(OptionCreator.class.getProtectionDomain().getCodeSource().getLocation().toString()).getParentFile().getPath().replace("file:", "") + "/";
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
                    OptionCreator oc = new OptionCreator();
                    SwingUtilities.updateComponentTreeUI(oc);
                    Image image = Toolkit.getDefaultToolkit().getImage(oc.getClass().getResource("/icon.png"));
                    oc.setIconImage(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                    oc.setDbFields();
                    oc.genModel_pnl.setVisible(false);
                    oc.daopannel_pnl.setVisible(false);
                    oc.webModelpannel_pnl3.setVisible(false);
                    oc.webControllerPannel_pnl1.setVisible(false);
                    oc.seervicepannel_pnl.setVisible(false);
                    oc.modelView_Btn.setEnabled(false);
                    oc.setResizable(true);
                    developMenu(oc);
                    oc.setExtendedState(JFrame.MAXIMIZED_BOTH);
//                    oc.log_txtA.setVisible(true);
//                    printStream = new PrintStream(new CustomOutputStream(oc));
//                    System.setOut(printStream);
//                    System.setErr(printStream);
                    checkFileDB();
                    setupList(oc);
                    standardOut = System.out;
                    oc.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Is your UI already created? So you will have to update the component-tree
                // of your current frame (or actually all of them...)

            }
        });

    }

    private static void setupList(OptionCreator oc) {
        oc.jointables_list.setModel(listMdl);

        oc.tableName_txb.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                tableNameChange(oc);
            }

            public void removeUpdate(DocumentEvent e) {
                tableNameChange(oc);
            }

            public void insertUpdate(DocumentEvent e) {
                tableNameChange(oc);
            }
        });
    }

    private static void checkFileDB() {
        if (!new File(jarPath + "data").exists()) {
            new File(jarPath + "data").mkdir();
        }
        if (!new File(jarPath + "data/Components.data").exists()) {
            fileUtils.WriteToFile(jarPath, "data/Components.data", "");
        }

        if (!new File(jarPath + "data/Functions.data").exists()) {
            fileUtils.WriteToFile(jarPath, "data/Functions.data", "");
        }

        if (!new File(jarPath + "data/Listeners.data").exists()) {
            fileUtils.WriteToFile(jarPath, "data/Listeners.data", "");
        }
    }

    public void setDbFields() {
        Properties prop = dbUtils.getProp();
        if (prop != null) {
            dbURL_tf3.setText(prop.getProperty("hibernate.connection.url"));
            dbDriver_tf.setText(prop.getProperty("hibernate.connection.driver_class"));
            dbUsername_tf4.setText(prop.getProperty("hibernate.connection.username"));
            dbPasswd_tf5.setText(prop.getProperty("hibernate.connection.password"));
        }
        Properties userProp = dbUtils.getUserProp();
        if (userProp != null) {
            username_tf.setText(userProp.getProperty("username"));
            apipath_tf.setText(userProp.getProperty("apipath"));
            webpath_tf1.setText(userProp.getProperty("webpath"));
        }
    }

    private static void developMenu(OptionCreator oc) {
        JMenuItem menuItem;
        menuItem = new JMenuItem("Function", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("define A new function");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionDefinition fd = new FunctionDefinition(jarPath);
                fd.invoke();
            }
        });
        oc.define_mnu.add(menuItem);

        menuItem = new JMenuItem("Component", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("define A new Component");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComponentDefinition fd = new ComponentDefinition(jarPath);
                fd.invoke();
            }
        });
        oc.define_mnu.add(menuItem);

        menuItem = new JMenuItem("Listener", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("define A new listener");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListenerDefinition fd = new ListenerDefinition(jarPath);
                fd.invoke();
            }
        });
        oc.define_mnu.add(menuItem);

        menuItem = new JMenuItem("Code Editor", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Open Code Editor");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodeEditor fd = new CodeEditor(jarPath);
                fd.invoke();
            }
        });
        oc.define_mnu.add(menuItem);
        menuItem = new JMenuItem("Show/Hide Log", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Open Code Editor");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHideLog(oc);
            }
        });
        oc.view_mnu.add(menuItem);

    }

    public void applyDbProperties() {
        Properties prop = dbUtils.getProp();
        if (prop != null) {
            prop.setProperty("hibernate.connection.url", dbURL_tf3.getText());
            prop.setProperty("hibernate.connection.driver_class", dbDriver_tf.getText());
            prop.setProperty("hibernate.connection.username", dbUsername_tf4.getText());
            prop.setProperty("hibernate.connection.password", dbPasswd_tf5.getText());
            try {
                dbUtils.saveConnectionProperty(prop);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Properties userProp = dbUtils.getUserProp();
        if (prop != null) {
            userProp.setProperty("username", username_tf.getText());
            userProp.setProperty("webpath", webpath_tf1.getText());
            userProp.setProperty("apipath", apipath_tf.getText());
            try {
                dbUtils.saveUserProperty(userProp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void showAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ModelFileName_txb;
    private javax.swing.JPanel about_pnl;
    private javax.swing.JButton apipathChoose_btn;
    private javax.swing.JTextField apipath_tf;
    private javax.swing.JTextField daoFileName_txb;
    private javax.swing.JTextField daoImplFilename_txb2;
    private javax.swing.JButton daoImplView_Btn;
    private javax.swing.JButton daoView_Btn;
    private javax.swing.JPanel daopannel_pnl;
    private javax.swing.JTextField dbDriver_tf;
    private javax.swing.JTextField dbPasswd_tf5;
    private javax.swing.JTextField dbURL_tf3;
    private javax.swing.JTextField dbUsername_tf4;
    private javax.swing.JMenu define_mnu;
    private javax.swing.JMenu edit_mnu;
    private javax.swing.JMenu file_mnu;
    private javax.swing.JCheckBox genDAO_chb;
    private javax.swing.JCheckBox genModel_chb;
    private javax.swing.JPanel genModel_pnl;
    private javax.swing.JCheckBox genService_chb;
    private javax.swing.JCheckBox genWebModel;
    private javax.swing.JButton generate_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton joinAdd_btn;
    private javax.swing.JButton joinRemove_btn;
    private javax.swing.JList<String> jointables_list;
    private javax.swing.JTextArea log_txtA;
    private javax.swing.JTextField mainreffield_txb;
    private javax.swing.JButton modelView_Btn;
    private javax.swing.JTextField module_txf;
    private javax.swing.JPanel newOption_Pnl;
    private javax.swing.JTextField option_txf;
    private javax.swing.JTabbedPane parent_tab;
    private javax.swing.JTextField refField_txb;
    private javax.swing.JTextField reftable_txb;
    private javax.swing.JButton reset_btn;
    private javax.swing.JPanel seervicepannel_pnl;
    private javax.swing.JTextField serviceFileName_txb;
    private javax.swing.JTextField serviceImplFileName_txb;
    private javax.swing.JButton serviceImplView_Btn1;
    private javax.swing.JButton serviceView_Btn;
    private javax.swing.JPanel settings_Pnl;
    private javax.swing.JTextField subModule_txf;
    private javax.swing.JTextField tableName_txb;
    private javax.swing.JTextField table_txf1;
    private javax.swing.JTextField username_tf;
    private javax.swing.JMenu view_mnu;
    private javax.swing.JPanel webControllerPannel_pnl1;
    private javax.swing.JButton webControllerView_Btn1;
    private javax.swing.JButton webControllerView_Btn3;
    private javax.swing.JPanel webModelpannel_pnl3;
    private javax.swing.JButton webpathChoose_btn;
    private javax.swing.JTextField webpath_tf1;
    // End of variables declaration//GEN-END:variables
}
