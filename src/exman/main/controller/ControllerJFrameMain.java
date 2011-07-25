/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.controller;

private static controller instance = null;

/**
 *
 * @author giuseppe
 */
public class ControllerJFrameMain {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new MainClass();
            }
        });
    }
}
