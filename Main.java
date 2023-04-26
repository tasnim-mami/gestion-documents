import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class Main {
    public static void main(String[] args) {
    	MainWindow mainWindow = new MainWindow();
        String url = "jdbc:mysql://localhost:3306/Projet";
        String user = "root";
        String password = "sarra123";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie à la base de données MySQL.");

            
        } catch (SQLException e) {
            System.out.println("La connexion à la base de données a échoué.");
            e.printStackTrace();
        }
    
               
            

            
    } 

    }




