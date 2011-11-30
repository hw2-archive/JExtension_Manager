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
    
    public static ControllerJFrameMain instance = null;
    private static JFrameMain frame = null;
   
    
    private ControllerJFrameMain() {
        frame = new JFrameMain(this);
        frame.setVisible(true);
      
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new ControllerJFrameMain();
            }
        });
    }
    
    public void export(int ver,int type,String folderName,boolean isAdmin,boolean isPub,String path,boolean isZip) {
        
    }
    
}
