import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FichierDAOImpl implements FichierDAO {
	 static Connection conn;

	@Override
	public void addFichier(String Nom, String Lien, String Niveau){
	    try {
	    	   Class.forName("com.mysql.cj.jdbc.Driver");		    
			   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  
	        PreparedStatement stmt = conn.prepareStatement("INSERT INTO fichier(nom, lien, niveau) VALUES (?, ?, ?)");
	        stmt.setString(1, Nom);
	        stmt.setString(2, Lien);
	        stmt.setString(3, Niveau);
	        int rowsAffected = stmt.executeUpdate();
		    if (rowsAffected > 0) {
		        System.out.println("Le fichier a été ajouté avec succès !");
		    } else {
		        System.out.println("Le fichier n'a pas été ajouté.");
		    }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void updateFichier(Fichier fichier) {
	    try {
	        PreparedStatement stmt = conn.prepareStatement("UPDATE fichier SET nom = ?, lien = ?, niveau = ? WHERE id = ?");
	        stmt.setString(1, fichier.getNom());
	        stmt.setString(2, fichier.getLien());
	        stmt.setString(3, fichier.getNiveau());
	        stmt.setInt(4, fichier.getId());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteFichier(int idf) {
	    try {
	    	//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root",""); 
	        PreparedStatement stmt0 = conn.prepareStatement("DELETE FROM commentaire WHERE idf = ?");
	        PreparedStatement stmt = conn.prepareStatement("DELETE FROM fichier WHERE id = ?");
	        stmt0.setInt(1, idf);
	        stmt.setInt(1, idf);
	        stmt0.executeUpdate();
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Fichier getFichierById(int id) {
	    Fichier fichier = null;
	    try {
	    	//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root",""); 
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fichier WHERE idf = ?");
	        stmt.setInt(1, id);
	        stmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return fichier;
	}

	@Override
	public List<Fichier> getAllFichiers(String niveau) {
		 List<Fichier> fichiers = new ArrayList<>();
	    try {
	    	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root",""); 
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fichier WHERE niveau = ?");
	        stmt.setString(1, niveau);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Fichier fichier = new Fichier();
	            fichier.setId(rs.getInt("id"));
	            fichier.setNom(rs.getString("nom"));
	            fichier.setLien(rs.getString("lien"));
	            fichiers.add(fichier);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return(fichiers);
	}

	@Override
	public void addFichier() {
		// TODO Auto-generated method stub
		
	}

	

}
