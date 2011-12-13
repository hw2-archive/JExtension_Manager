/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.controller;

import exman.common.SharedDefines;
import hw2.common.MyCommonMethods;
import hw2.common.PropConnection;
import exman.main.model.TableModelDatabases;
import exman.main.persistence.HandlerMainQuery;
import exman.main.view.JFrameMain;
import hw2.common.MyXML;
import java.io.File;
import java.util.ArrayList;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 *
 * @author giuseppe
 */
public class ControllerJFrameMain {

    public static ControllerJFrameMain instance = null;
    private static JFrameMain frame = null;
    public static PropConnection propConn;
    public TableModelDatabases tableModelDatabases = null;
    private String sep = File.separator;
    SharedDefines.emJava_ver extVer = null;
    SharedDefines.emJava_ext extType = null;
    private String extClient = null;
    private String extFolder = null;
    private String rootPath = null;
    private String xmlPath = null;
    MyXML xml = null;

    private ControllerJFrameMain() {
        tableModelDatabases = new TableModelDatabases();

        frame = new JFrameMain(this);
        frame.setVisible(true);
    }

    public PropConnection getPropConn() {
        return propConn;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                instance = new ControllerJFrameMain();
            }
        });
    }
    
    public void exportTables(String outPath) {
        ArrayList<String> tables = new ArrayList<String>();
        for (int i: frame.jTableDBList.getSelectedRows() ) {
            tables.add(frame.jTableDBList.getValueAt(i, 0).toString());  
        }
        new HandlerMainQuery().exportTable(tables,propConn,outPath);
        
        MyXML outXml = new MyXML(outPath+MyCommonMethods.getFullFileName(xmlPath));
        
        Element parent = outXml.writeElement("install","", null,null,true);
        parent = outXml.writeElement("sql", "", null, parent , true);
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("charset","utf8"));
        attributes.add(new Attribute("driver","mysql"));
        outXml.writeElement("file", "install_mod.sql",attributes,parent, true);
        attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("driver","mysql"));
        outXml.writeElement("file", SharedDefines.getSqlInstFileName(),attributes,parent, true);
        
        parent = outXml.writeElement("uninstall","", null, null,true);
        parent = outXml.writeElement("sql", "", null, parent , true);
        attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("charset","utf8"));
        attributes.add(new Attribute("driver","mysql"));
        outXml.writeElement("file", "uninstall_mod.sql",attributes,parent, true);
        attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("driver","mysql"));
        outXml.writeElement("file", SharedDefines.getSqlUninstFileName(),attributes,parent, true);
    }

    public void loadInfo(String xmlPath, String confPath) {
        this.xmlPath = xmlPath;
        xml = new MyXML(xmlPath);
        if (xml.getDoc() != null && (xml.getRootNode().getName().equalsIgnoreCase("install")
                || xml.getRootNode().getName().equalsIgnoreCase("extension"))) {

            String type = xml.getRootNode().getAttributeValue("type");
            String ver = xml.getRootNode().getAttributeValue("version");
            extClient = xml.getRootNode().getAttributeValue("client");
            Element eName = xml.getFirstElementByTag("name", null);
            if (eName == null) {
                return;
            }

            String name = eName.getValue().toLowerCase();

            extType = SharedDefines.emJava_ext.valueOf(type);

            if (ver.compareTo(SharedDefines.emJava_ver.LATEST.getVersion()) < 0) {
                extVer = SharedDefines.emJava_ver.J_15;
                extFolder = extType.getPrefix() + name;
            } else {
                extVer = SharedDefines.emJava_ver.LATEST;
                extFolder = name;
            }

            rootPath = MyCommonMethods.getParentDirectory(xmlPath, 4);

            if (!confPath.isEmpty()) {

                String user = MyCommonMethods.readPhpConf(confPath, "$user");
                String password = MyCommonMethods.readPhpConf(confPath, "$password");
                String dbName = MyCommonMethods.readPhpConf(confPath, "$db");
                String host = MyCommonMethods.readPhpConf(confPath, "$host");

                propConn = new PropConnection(host, dbName, user, password,true);

                tableModelDatabases.cleanList();
                tableModelDatabases.refreshList(0, "", propConn);
            }

            //XXX implements read <table> node

            frame.jButtonExport.setEnabled(true);
        }
    }

    public void processXml(String basePath, String outPath, boolean isAdmin) {
        Element parentSection = isAdmin ? xml.getFirstElementByTag("administration", null) : null;

        Element fileSection = xml.getFirstElementByTag("files", parentSection);
        Element langSection = xml.getFirstElementByTag("languages", parentSection);
        Element mediaSection = xml.getFirstElementByTag("media", parentSection);
        String type = extType.getFolder();

        if (isAdmin) {

            if (extVer == SharedDefines.emJava_ver.J_15) {
                Element instFile = xml.getFirstElementByTag("installfile", null);
                Element uninstFile = xml.getFirstElementByTag("uninstallfile", null);

                String file = null;
                if (instFile != null) {
                    file = instFile.getValue();
                    MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + file, outPath + file, "");
                }
                if (uninstFile != null) {
                    file = uninstFile.getValue();
                    MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + file, outPath + file, "");
                }
            } else {
                Element scriptFile = xml.getFirstElementByTag("uninstall", null);
                if (scriptFile != null) {
                    String file = scriptFile.getValue();
                    MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + file, outPath + file, "");
                }
            }
            
            Element instSection = xml.getFirstElementByTag("sql", xml.getFirstElementByTag("install", null));
            Element uninstSection = xml.getFirstElementByTag("sql", xml.getFirstElementByTag("uninstall", null));

            if (instSection != null) {
                ArrayList<String> instFiles = xml.getElementsValues(xml.getElementsByTag("file", instSection), null);
                for (String F : instFiles) {
                    MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + F, outPath + F, "");
                }
            }

            if (uninstSection != null) {
                ArrayList<String> uninstFiles = xml.getElementsValues(xml.getElementsByTag("file", uninstSection), null);
                for (String F : uninstFiles) {
                    MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + F, outPath + F, "");
                }
            }
        }


        if (fileSection != null) {
            String filesPath = fileSection.getAttributeValue("folder") == null ? "" : fileSection.getAttributeValue("folder") + sep;
            ArrayList<String> folders = xml.getElementsValues(xml.getElementsByTag("folder", fileSection), null);
            ArrayList<Element> fElements = xml.getElementsByTag("filename", fileSection);
            if (fElements.isEmpty()) //XXX workaround
            {
                fElements = xml.getElementsByTag("file", fileSection);
            }
            ArrayList<String> files = xml.getElementsValues(fElements, null);

            //XXX message box if you want overwrite and remove try catch
            for (String F : folders) {
                MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + F, outPath + filesPath + F, "");
            }

            for (String F : files) {
                MyCommonMethods.copyFileOrFolder(basePath + type + sep + extFolder + sep + F, outPath + filesPath + F, "");
            }

        }

        if (mediaSection != null) {
            String mediaPath = mediaSection.getAttributeValue("folder") == null ? "" : mediaSection.getAttributeValue("folder") + sep;
            String mediaDest = mediaSection.getAttributeValue("destination");

            ArrayList<String> mediaFolders = xml.getElementsValues(xml.getElementsByTag("folder", mediaSection), null);
            ArrayList<Element> fElements = xml.getElementsByTag("filename", mediaSection);
            if (fElements.isEmpty()) //XXX workaround
            {
                fElements = xml.getElementsByTag("file", mediaSection);
            }
            ArrayList<String> mediaFiles = xml.getElementsValues(fElements, null);

            for (String F : mediaFolders) {
                MyCommonMethods.copyFileOrFolder(basePath + "media" + sep + mediaDest + sep + F, outPath + mediaPath + sep + F, "");
            }
            for (String F : mediaFiles) {
                MyCommonMethods.copyFileOrFolder(basePath + "media" + sep + mediaDest + sep + F, outPath + mediaPath + sep + F, "");
            }
        }

        if (langSection != null) {
            String langPath = langSection.getAttributeValue("folder") == null ? "" : langSection.getAttributeValue("folder") + sep;
            ArrayList<Element> langFiles = xml.getElementsByTag("language", langSection);
            for (Element E : langFiles) {
                String folder = E.getAttributeValue("tag");
                String file = E.getValue();
                MyCommonMethods.copyFileOrFolder(basePath + "language" + sep + folder + sep + file, outPath + langPath + sep + E.getValue(), "");
            }
        }


    }

    public void export(String xmlPath, String outPath, boolean isZip) {


        String adminPath = rootPath + sep + "administrator" + sep;
        String outFolder = outPath + sep + extFolder + sep;

        // copy the xml also to create the path 
        MyCommonMethods.deleteDir(outFolder,null);
        MyCommonMethods.copyFileOrFolder(xmlPath, outFolder + sep + MyCommonMethods.getFullFileName(xmlPath), "");
         if ((new File(outFolder)).exists()) {
            if (extClient != null) {
                if (extClient.equalsIgnoreCase("administrator")) {
                    processXml(adminPath, outFolder, true);
                } else {
                    processXml(rootPath + sep, outFolder, false);
                }
            } else {
                processXml(adminPath, outFolder, true);
                processXml(rootPath + sep, outFolder, false);
            }
        }
         
        exportTables(outFolder);
    }
}
