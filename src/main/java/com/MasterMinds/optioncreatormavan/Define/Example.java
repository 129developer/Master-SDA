/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Define;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

public class Example extends JFrame {

    public Example() {
        setTitle("Simple example");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        invoke();
    }

    public static void invoke() {
        Example ex = new Example();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(ex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ex.setVisible(true);
    }
}
