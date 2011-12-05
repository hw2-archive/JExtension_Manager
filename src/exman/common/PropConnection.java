package exman.common;

import java.util.Properties;

import javax.swing.JOptionPane;

public class PropConnection {
	
	Properties  prop = null;
    String conn = null;
	
	public PropConnection(String host,String dbname, String username,String password) {
		prop = new Properties();
        prop.setProperty("connection", "jdbc:mysql://");
        prop.setProperty("server", host);
        prop.setProperty("username", username);
        prop.setProperty("password", password);
        prop.setProperty("dbname", dbname);
        
        		
		conn = prop.getProperty("connection");
		conn = conn+prop.getProperty("server")+"/";
		conn = conn+prop.getProperty("dbname");	
	}
	
	public String getConnection() {
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
		return prop.getProperty("password");
	}
	
}
