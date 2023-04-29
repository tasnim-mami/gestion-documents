

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {
	public Connection connexion;
	public Statement instruction;
	public ResultSet res;

	public ConnexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Ok");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/miniProjet", "root", "");
			instruction = connexion.createStatement();
			
			
			System.out.println("Connexion Ok");

		} catch (ClassNotFoundException e) {
			System.err.println("Probleme de pilote");

		} catch (SQLException e) {
			System.err.println("Base de donnee non trouve");
		}

	}

	public PreparedStatement prepareStatement(String sql, int returnGeneratedKeys) {
		// TODO Auto-generated method stub
		return null;
	}

	public Statement createStatement() {
		// TODO Auto-generated method stub
		return null;
	}



	public void close() {
		// TODO Auto-generated method stub
		
	}



}

