/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.controller;

import exman.main.view.JFrameMain;

/**
 *
 * @author giuseppe
 */
public class ControllerJFrameMain {
    
    private static ControllerJFrameMain instance = null;
    private static JFrameMain frame = null;
    
    private ControllerJFrameMain() {
        frame = new JFrameMain();
        frame.setVisible(true);
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new ControllerJFrameMain();
            }
        });
    }
}
