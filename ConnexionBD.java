

import java.sql.Connection;
import java.sql.DriverManager;
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
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/amine", "root", "Big_Mom123");
			instruction = connexion.createStatement();
			System.out.println("Connexion Ok");

		} catch (ClassNotFoundException e) {
			System.err.println("Probleme de pilote");

		} catch (SQLException e) {
			System.err.println("Base de donnee non trouve");
		}

	}



}

