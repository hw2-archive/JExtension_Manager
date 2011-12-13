/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.persistence;

import exman.common.SharedDefines;
import hw2.common.MyDBConnection;
import hw2.common.PropConnection;
import exman.main.model.BeanDatabases;
import hw2.common.MysqlBackup;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giuseppe
 */
public class HandlerMainQuery {
    
    public List<BeanDatabases> LoadTables(String searchText,PropConnection propConn) {
            List<BeanDatabases> V = null;
            MyDBConnection myConn = new MyDBConnection(propConn);

            try {
                    // searchtext is useless here
                
                    myConn.setRs(myConn.getConn().getMetaData().getTables(null, null, null, new String[]{"TABLE"}));

                    // inizializza oggetto lista
                    V = new ArrayList<BeanDatabases> ();
                    int id=0;
                    while (myConn.getRs().next()) {
                            BeanDatabases bean = new BeanDatabases();
                            bean.setId(id);
                            bean.setNome(myConn.getRs().getString("TABLE_NAME"));
                            //bean.setStato(myConn.rs.getInt("ordine.stato"));
                            V.add(bean);
                            id++;
                    }

            } catch (SQLException ex) {
                    ex.printStackTrace();

            }finally {
                    myConn.releaseAll();
            }

            return V;
    }

    public void exportTable( ArrayList<String> tables, PropConnection propConn,String filePath) {
        //MyDBConnection myConn = new MyDBConnection(propConn);   
        //db2sql.dumpDB(tables, myConn.getConn(),filePath+File.separator+"mod.sql");
        MysqlBackup backup = new MysqlBackup(propConn.getServer(), Integer.toString(propConn.getPort()), propConn.getUsername(), propConn.getPassword(), propConn.getDatabase() , tables);
        backup.data_to_file(filePath+File.separator+SharedDefines.getSqlInstFileName());
    }
}