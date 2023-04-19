package swingproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Commenatire {
	public String comm ;
	public String idcomm ;
	public String nomf ;
	public int idf;
public Commenatire(String comm, String nomf) {
		super();
		this.comm = comm;
		this.nomf = nomf;
	}
			
	
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	};

	
	public void ajouterCommentaire() {
		
	};
	
	  public void ajouterComm(String commentaire, int idf) {

	        this.comm = commentaire;	        

			try{  
				   Class.forName("com.mysql.cj.jdbc.Driver");
				    
				   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  

				   Statement stmt=con.createStatement();
				    
				
				   ResultSet rs=stmt.executeQuery("insert into colonne (id,text) values(?,?) where idf = idf ");
				   
				   while(rs.next())  
				    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				     
				   con.close();  
				   
				   }catch(Exception e){
				    System.out.println(e);
				   } 
	        
	        

	    }
	  
	  public void afficherComm(String commentaire, int idf) {

	        this.comm = commentaire;	        

			try{  
				   Class.forName("com.mysql.cj.jdbc.Driver");
				    
				   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  

				   Statement stmt=con.createStatement();
				    
				   ResultSet rs=stmt.executeQuery("select * from colonne"); 
				  
				   
				   while(rs.next())  
				    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				     
				   con.close();  
				   
				   }catch(Exception e){
				    System.out.println(e);
				   } 
	        
	        

	    }
}
