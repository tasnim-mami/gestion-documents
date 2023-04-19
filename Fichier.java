package swingproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fichier {
public String fichier ;
public String nomf ;


public Fichier() {
	
}


public Fichier(String fichier, String nomf) {
	super();
	this.fichier = fichier;
	this.nomf = nomf;
}

public String getFichier() {
	return fichier;
}

public void setFichier(String fichier) {
	this.fichier = fichier;
}


public void SetaBook(String nomf, String fichier) {

    this.fichier = fichier;
    this.nomf= nomf;
    try{  
		   Class.forName("com.mysql.cj.jdbc.Driver");
		    
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  

		  // Statement stmt=con.createStatement();		    
		 // con = stmt.executeUpdate("insert into fichier (nomf,urlfichier) values(nomf,fichier)");
		   
		 
		   
		    PreparedStatement stmt = con.prepareStatement("insert into fichier (nomf,urlfichier) values (?, ?)");
		    stmt.setString(1, nomf);
		    stmt.setString(2, fichier);

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

}
