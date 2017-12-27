/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Define;

import com.MasterMinds.optioncreatormavan.OptionCreator;
import com.MasterMinds.optioncreatormavan.Utils.dbUtils;
import com.MasterMinds.optioncreatormavan.Utils.fileUtils;
import com.MasterMinds.optioncreatormavan.Utils.genUtils;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author bayasys
 */
public class CodeEditor extends javax.swing.JFrame {

    /**
     * Creates new form ComponentsDefinition
     */
    private static String jarPath = "", action, code, FileName;
    private static OptionCreator oc;

    public CodeEditor(String jarPath, OptionCreator oc, String action, String FileName, String code) {
        this.oc = oc;
        this.jarPath = jarPath;
        this.action = action;
        this.code = code;
        this.FileName = FileName;
        initComponents();

    }

    public CodeEditor(String jarPath) {
        this.jarPath = jarPath;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        fname_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        def_txa = new RSyntaxTextArea();
        save_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("File Name");

        fname_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fname_txtActionPerformed(evt);
            }
        });

        def_txa.setColumns(20);
        def_txa.setRows(5);
        def_txa.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        def_txa.setCodeFoldingEnabled(true);
        jScrollPane1.setViewportView(def_txa);

        save_btn.setText("Save & Back");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(fname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save_btn)
                    .addComponent(cancel_btn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        System.out.println("Saving edited code.");
        if (action.equalsIgnoreCase("MODEL")) {
            oc.setModelCode(def_txa.getText());
            this.dispose();
        }
        if (action.equalsIgnoreCase("DAO")) {
            oc.setDAOCode(def_txa.getText());
            this.dispose();
        }
        if (action.equalsIgnoreCase("DAOIMPL")) {
            oc.setDAOImplCode(def_txa.getText());
            this.dispose();
        }
        if (action.equalsIgnoreCase("SERVICE")) {
            oc.setServiceCode(def_txa.getText());
            this.dispose();
        }
        if (action.equalsIgnoreCase("SERVICEIMPL")) {
            oc.setServiceImplCode(def_txa.getText());
            this.dispose();
        }
    }//GEN-LAST:event_save_btnActionPerformed

    private void fname_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fname_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fname_txtActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancel_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        /* Create and display the form */
        CodeEditor ex = new CodeEditor(new File(CodeEditor.class.getProtectionDomain().getCodeSource().getLocation().toString()).getParentFile().getPath().replace("file:", "") + "/");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ex.setTitle("Component Definition");
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(ex);
                    ex.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        try {
            genUtils.generateMenu(ex.def_txa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void invoke() {
        CodeEditor ex = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ex.setTitle("Component Definition");
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(ex);
                    ex.setVisible(true);
                    ex.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    def_txa.setText(code);
                    fname_txt.setText(FileName);
                    fname_txt.setEditable(false);
                    System.out.println("Code Editor started for : " + FileName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        try {
            genUtils.generateMenu(ex.def_txa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_btn;
    private RSyntaxTextArea def_txa;
    private javax.swing.JTextField fname_txt;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save_btn;
    // End of variables declaration//GEN-END:variables
}
