
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Commenatire {
	public String comm ;
	public String idcomm ;
	public String nomf ;
	public int idf;
	
	private int id;
    private String text;

  
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
	
public Commenatire(int id, String text) {
		super();
		  this.id = id;
	      this.text = text;
	}
public Commenatire() {
	
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
				   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  
				   	   
				   PreparedStatement  stmt = con.prepareStatement("insert into commentaire (text,idf) values (?, ?)");
				     stmt.setString(1, commentaire);
				     stmt.setInt(2, idf);
				    int rowsAffected = stmt.executeUpdate();
				    if (rowsAffected > 0) {
				        System.out.println("Lecommentaire a été ajouté avec succès !");
				    } else {
				        System.out.println("Le commentaire n'a pas été ajouté.");
				    }			   
				   con.close();  		   
				   }catch(Exception e){
				    System.out.println(e);
				   } 	        
	    }
	  

	  
	  public void deleteaCommentaire(int idc) {        

			try{  
				   Class.forName("com.mysql.cj.jdbc.Driver");				    
				   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  			   	   
				   PreparedStatement stmt  = con.prepareStatement("delete from commentaire where id ='" + idc + "'")  ;
				    stmt.executeUpdate();		   
				   con.close();  		   
				   }catch(Exception e){
				    System.out.println(e);
				   } 	        
	    }
	  
	  
	  
	  public void afficherComm(String commentaire, int idf) {
	        this.comm = commentaire;	        
			try{  
				   Class.forName("com.mysql.cj.jdbc.Driver");
				    
				   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  
				   Statement stmt=con.createStatement();				    
				   ResultSet rs=stmt.executeQuery("select * from commentaire"); 			  
				   
				   while(rs.next())  
				    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				     
				   con.close();  
				   
				   }catch(Exception e){
				    System.out.println(e);
				   } 
	        
	        

	    }
}
