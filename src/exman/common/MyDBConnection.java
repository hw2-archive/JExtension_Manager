package exman.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDBConnection extends MyDBMethods {

    public PropConnection propConn = null;
    public Connection conn = null;
    public PreparedStatement prepStat = null;
    public ResultSet rs = null;

    public MyDBConnection(PropConnection propConn) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        this.propConn = propConn;
        conn = startConn();
    }

    /**
     * Apre una connessione al database
     *
     * @return l'oggetto Connection appena aperto
     */
    private Connection startConn() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(propConn.getConnection(), propConn.getUsername(), propConn.getPassword());
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