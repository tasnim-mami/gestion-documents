import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDAOImpl implements CommentaireDAO {
public static Connection conn ;
	@Override
	public void addCommentaire(String commentaire,int idf) {

		try{  
			   Class.forName("com.mysql.cj.jdbc.Driver");				    
			   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  
			   	   
			   PreparedStatement  stmt = conn.prepareStatement("insert into commentaire (text,idf) values (?, ?)");
			     stmt.setString(1, commentaire);
			     stmt.setInt(2, idf);
			    int rowsAffected = stmt.executeUpdate();
			    if (rowsAffected > 0) {
			        System.out.println("Lecommentaire a été ajouté avec succès !");
			    } else {
			        System.out.println("Le commentaire n'a pas été ajouté.");
			    }			   
			   //conn.close();  		   
			   }catch(Exception e){
			    System.out.println(e);
			   } 
		
	}

	@Override
	public void updateCommentaire(Commenatire commentaire) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommentaire(int id) {
		try{  
			  // Class.forName("com.mysql.cj.jdbc.Driver");				    
			  // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root","");  			   	   
			   PreparedStatement stmt  = conn.prepareStatement("delete from commentaire where id ='" + id + "'")  ;
			    stmt.executeUpdate();		   
			   conn.close();  		   
			   }catch(Exception e){
			    System.out.println(e);
			   } 	
	}

	@Override
	public Commenatire getCommentaireById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commenatire> getAllCommentaires(int idf) {
		 List<Commenatire> commentaires = new ArrayList<>();
		    try {
		    	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet","root",""); 
		        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM commentaire WHERE idf = ?");
		        stmt.setInt(1, idf);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		        	Commenatire commentaire = new Commenatire();
		        	commentaire.setId(rs.getInt("id"));
		            commentaire.setText(rs.getString("text"));
		            commentaire.setIdf(rs.getInt("idf"));
		            commentaires.add(commentaire);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return(commentaires);
	}

	



}
