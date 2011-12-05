/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.persistence;

import exman.common.MyDBConnection;
import exman.common.PropConnection;
import exman.main.model.BeanDatabases;
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
                
                    myConn.rs = myConn.conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

                    // inizializza oggetto lista
                    V = new ArrayList<BeanDatabases> ();
                    int id=0;
                    while (myConn.rs.next()) {
                            BeanDatabases bean = new BeanDatabases();
                            bean.setId(id);
                            bean.setNome(myConn.rs.getString("TABLE_NAME"));
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
}
