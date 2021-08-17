/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips;

import com.ufjf.mips.ui.FileSelector;
import com.ufjf.mips.ui.MipsForm;
import javax.swing.JFrame;

/**
 *
 * @author Nova
 */
public class Program {
    public static void main(String[] args) {
        FileSelector loader = new FileSelector();
        loader.setVisible(true);
    }
}
