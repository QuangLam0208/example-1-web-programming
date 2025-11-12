package ltweb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBSQLConnect {
	private final String SERVERNAME = "PERSYNOMERCY"; 
    private final String DBNAME = "ltwebct4st6";
    private final String PORT = "1433";
    private final String INSTANCE = "QUANGLAM0608"; // <--- Đưa instance name vào đây
    private final String USERID = "sa";
    private final String PASSWORD = "32632005";

    public Connection getConnection() throws Exception {
        String url;
        
        // Logic mới:
        // Nếu INSTANCE rỗng, kết nối bằng PORT (dành cho instance mặc định)
        if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORT 
                + ";databaseName=" + DBNAME
                + ";trustServerCertificate=true"; // Đã thêm fix
        } 
        // Nếu có INSTANCE, kết nối bằng instanceName (SQL Browser sẽ tự tìm port)
        else {
            url = "jdbc:sqlserver://" + SERVERNAME 
                + ";instanceName=" + INSTANCE 
                + ";databaseName=" + DBNAME
                + ";trustServerCertificate=true"; // Đã thêm fix
        }

        // In ra URL để kiểm tra (tùy chọn)
        System.out.println("Connecting to: " + url); 

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, USERID, PASSWORD);
    }
	
	public static void main(String[] args) {
		String sqlInsert = "INSERT INTO GiaoVien VALUES(?, ?, ?)";
		String selectAll = "SELECT * FROM GiaoVien";
		try {
			Connection conn = new DBSQLConnect().getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, 1);
			stmt.setString(2, "Trung");
			stmt.setString(3, "HCM");
			stmt.execute();
			stmt = conn.prepareStatement(selectAll);
			// get data from table ‘GiaoVien'
			ResultSet rs = stmt.executeQuery();
			// show data
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			stmt.close();
			conn.close(); // close connection
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
