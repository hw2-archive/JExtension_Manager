/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.controller;

import hw2.common.MyCommonMethods;
import hw2.common.PropConnection;
import hw2.common.MyDefines;
import exman.main.model.TableModelDatabases;
import exman.main.view.JFrameMain;
import java.util.ArrayList;

/**
 *
 * @author giuseppe
 */
public class ControllerJFrameMain {

    public static ControllerJFrameMain instance = null;
    private static JFrameMain frame = null;
    public static PropConnection propConn;
    public TableModelDatabases tableModelDatabases = null;

    private ControllerJFrameMain() {
        tableModelDatabases = new TableModelDatabases();

        frame = new JFrameMain(this);
        frame.setVisible(true);
    }

    public PropConnection getPropConn(String rootPath) {

        if (propConn == null) {
            String confPath = rootPath + MyDefines.getFileSep() + "configuration.php";
            String user = MyCommonMethods.readPhpConf(confPath, "$user");
            String password = MyCommonMethods.readPhpConf(confPath, "$password");
            String dbName = MyCommonMethods.readPhpConf(confPath, "$db");
            String host = MyCommonMethods.readPhpConf(confPath, "$host");

            propConn = new PropConnection(host, dbName, user, password);
        }

        return propConn;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                instance = new ControllerJFrameMain();
            }
        });
    }

    private ArrayList<String> readXML() {
        ArrayList<String> tables = new ArrayList<String>();


        return tables;
    }

    private String readConf(String rootPath) {

        return "";
    }

    public void loadTable(String rootPath) {
        tableModelDatabases.refreshList(0, "", getPropConn(rootPath));
    }

    public void export(int ver, int type, String rootPath, String folderName, boolean isAdmin, boolean isPub, String outPath, boolean isZip) {
        
        
    }
}
