package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoJDBC {
	
	public static Connection con = null;

	public ConexaoJDBC() {
		
	}
	
	public static Connection conectar() {
		System.out.println("Conectando ao banco...");
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/facilit_imob?zeroDateTimeBehavior=convertToNull", "root",
					"1234567");
			
			System.out.println("Conectado.");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		return con;
	}
}
