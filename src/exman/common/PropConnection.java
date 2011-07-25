package exman.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class PropConnection {

	public final String DB_NAME = "cartoleria";
	
	Properties  prop = null;
	
	public PropConnection() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(new File("properties/connection.properties")));
		} catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Connetti l'unita' di rete", "Avviso", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	
	public String getConnection(String dbName) {
//		jdbc:mysql://192.168.1.40:3306/dbName
		
		String conn = prop.getProperty("connection");
		conn = conn+prop.getProperty("server")+":";
		conn = conn+prop.getProperty("port")+"/";
		conn = conn+dbName;		
		
		return conn;
	}
	
	public String getServer() {
		return prop.getProperty("server");
	}
	
	public int getPort() {
		try {
			return Integer.parseInt(prop.getProperty("port"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Server Port Errata.", "Connection Error",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}
	
	public String getUsername() {
		return prop.getProperty("username");
	}
	
	public String getPassword() {
		return "root";
	}
	
	public String getMDB_Legacy() {
		return prop.getProperty("MDB_Legacy");
	}
}
