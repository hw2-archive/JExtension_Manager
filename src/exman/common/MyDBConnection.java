package exman.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDBConnection extends MyDBMethods {

	public PropConnection propConn = null;


        public class DBConnection {
            public Connection conn = null;
            public PreparedStatement prepStat = null;
            public ResultSet rs = null;

            public DBConnection (String dbName) {
                conn = startConn(dbName);
            }

            /**
             * Apre una connessione al database
             *
             * @param dbName il nome del database a cui collegarsi
             * @return l'oggetto Connection appena aperto
             */
            private Connection startConn (String dbName) {
                    Connection c = null;
                    try {
                        c = DriverManager.getConnection(propConn.getConnection(dbName), propConn.getUsername(), propConn.getPassword());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return c;
            }

            /**
             * Chiude le connessioni e annulla i puntatori
            **/
            public void releaseAll() {
                /* Release the resources */
                if (rs != null) {
                        try {
                                rs.close();
                        } catch (SQLException sqlEx) {
                                System.out.println("SQLException: " + sqlEx.getMessage());
                        }
                        rs = null;
                }

                if (prepStat != null) {
                        try {
                                prepStat.close();
                        } catch (SQLException sqlEx) {
                                System.out.println("SQLException: " + sqlEx.getMessage());
                        }

                        prepStat = null;
                }

                if (conn != null) {
                        try {
                                conn.close();
                        } catch (SQLException sqlEx) {
                                // Ignore
                        }

                        conn = null;
                }
            }

        }

	public MyDBConnection () {
		propConn = new PropConnection();

		try {
			Class.forName("org.gjt.mm.mysql.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}