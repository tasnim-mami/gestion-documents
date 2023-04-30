
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.ResultSet;


public class Fichier {

private int id;
private String nom;
private String lien;
private String niveau;

public Fichier() {
	}


public Fichier(String nomf, String lien, String niveau) {
	super();
	this.nom = nomf;
	this.lien = lien;
	this.niveau = niveau;
}

public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getLien() {
	return lien;
}


public void setLien(String lien) {
	this.lien = lien;
}


public String getNiveau() {
	return niveau;
}


public void setNiveau(String niveau) {
	this.niveau = niveau;
}


public void SetaBook(String nomf, String lien, String niveau) {

    this.nom = nomf;
    this.lien=lien;
    this.niveau=niveau;
    
    try{  
		   Class.forName("com.mysql.cj.jdbc.Driver");		    
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  	   		   
		    PreparedStatement stmt = con.prepareStatement("insert into fichier (nom,lien,niveau) values (?, ?, ?)");
		    stmt.setString(1, nomf);
		    stmt.setString(2, lien);
		    stmt.setString(3, niveau);
		    int rowsAffected = stmt.executeUpdate();
		    if (rowsAffected > 0) {
		        System.out.println("Le fichier a été ajouté avec succès !");
		    } else {
		        System.out.println("Le fichier n'a pas été ajouté.");
		    }
		    con.close();
		   }catch(Exception e){
		    System.out.println(e);
		   } 
    

}

public void deleteaBook(int idf) {   
    try{  
    	
		   Class.forName("com.mysql.cj.jdbc.Driver");		    
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  
		   PreparedStatement stmt0  = con.prepareStatement("delete from commentaire where idf ='" + idf + "'")  ;
		    PreparedStatement stmt  = con.prepareStatement("delete from fichier where id ='" + idf + "'")  ;
		    stmt0.executeUpdate();
		    stmt.executeUpdate();
		    con.close();
		   }catch(Exception e){
		    System.out.println(e);
		   } 
}

}