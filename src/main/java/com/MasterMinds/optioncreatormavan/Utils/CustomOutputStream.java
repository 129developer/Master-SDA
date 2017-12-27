/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MasterMinds.optioncreatormavan.Utils;

import com.MasterMinds.optioncreatormavan.OptionCreator;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class CustomOutputStream extends OutputStream {

    OptionCreator oc;

    public CustomOutputStream(OptionCreator oc) {
        this.oc = oc;
    }

    @Override
    public void write(int b) throws IOException {
        oc.getLogTA(oc).append(String.valueOf((char) b));
        oc.getLogTA(oc).setCaretPosition(oc.getLogTA(oc).getDocument().getLength());
    }
}
