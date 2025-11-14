package ltweb.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSQLConnect {
	private final String SERVERNAME = "PERSYNOMERCY"; 
    private final String DBNAME = "ltwebct4st6";
    private final String PORT = "1433";
    private final String INSTANCE = "QUANGLAM0608";
    private final String USERID = "sa";
    private final String PASSWORD = "32632005";

    public Connection getConnection() throws Exception {
        String url;
        
        if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORT 
                + ";databaseName=" + DBNAME
                + ";trustServerCertificate=true"; 
        } 

        else {
            url = "jdbc:sqlserver://" + SERVERNAME 
                + ";instanceName=" + INSTANCE 
                + ";databaseName=" + DBNAME
                + ";trustServerCertificate=true";
        }

        System.out.println("Connecting to: " + url); 

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, USERID, PASSWORD);
    }
	
	public static void main(String[] args) {
		try (@SuppressWarnings("unused")
		Connection conn = new DBSQLConnect().getConnection();) {
			
			System.out.println("Connect thanh cong");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
