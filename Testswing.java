package swingproject;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;


public class Testswing extends JFrame{

	private JPanel contentPane;

	public Testswing () {
	
	};
	
	
	public static JTable table;
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
	 
		JLabel label = new JLabel("Bienvenue dans ma modeste application");
	 
		panel.add(label);
	 
		return panel;
	}
	
	
	
	public static void main(String[] args) {
		
		
		
		  DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("ID");
	        model.addColumn("NomFichier");
	        model.addColumn("UrlFichier");
		 
		try{  
			   Class.forName("com.mysql.cj.jdbc.Driver");			    
			   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  
			   Statement stmt=con.createStatement();			    
			   ResultSet rs=stmt.executeQuery("select * from user"); 
			   
			 // stmt.executeUpdate("ALTER TABLE `fichier` ADD COLUMN `commentaire` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL") ;
			   
			  Statement statement = con.prepareStatement("insert into fichier (commentaire) values (?)");
			 // statement.setString(1,("commentaire1,commentaire2"));		 
			//    int rowsAffected = stmt.executeUpdate();			  
			  
			  
			   while(rs.next())  
			    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));			     
			   con.close();  			   
			   }catch(Exception e){
			    System.out.println(e);
			   } 
		
	
		 
		  JFrame mafenet = new JFrame() ;
		  
		    JTextField tf0 = new JTextField();   
		    final JTextField tf2 = new JTextField(); 
		    tf2.setBounds(50, 50, 180, 30); 
		     
		    tf0.setBounds(50, 50, 230, 30);    
		    tf0.setText("Welcome to the app"); 
		     
		    JButton b2 = new JButton("Déposer");  
		    b2.setBounds(150, 100, 95, 30);  
		    b2.addActionListener(new ActionListener(){  
		    	
		        public void actionPerformed(ActionEvent e){  
		            JFileChooser choose = new JFileChooser(
		                FileSystemView.getFileSystemView().getHomeDirectory());
		            choose.setDialogTitle("Selectionnez un fichier");
		            choose.setAcceptAllFileFilterUsed(false);
		            FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "Images JPG et GIF", "jpg", "gif", "pdf");
		            choose.addChoosableFileFilter(filter);
		            	    
		            
     ///selectionner un fichier et le sauvegarder dans la base de données 
		           int res = choose.showOpenDialog(null);
		            if (res == JFileChooser.APPROVE_OPTION) {
		                try (FileInputStream fis = new FileInputStream(
		                        choose.getSelectedFile());
		                        ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
		                    byte[] buffer = new byte[1024];
		                    int bytesRead;
		                    while ((bytesRead = fis.read(buffer)) != -1) {
		                        bos.write(buffer, 0, bytesRead);
		                    }
		                    byte[] pdfContent = bos.toByteArray();

		                    Fichier f = new Fichier(choose.getSelectedFile().getName(), choose.getSelectedFile().getPath());
		                    f.SetaBook(choose.getSelectedFile().getName(), choose.getSelectedFile().getPath());
  
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		                
		            } 
		          try {  
		            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  

					   Statement stmt=con.createStatement();
		            ResultSet rs=stmt.executeQuery("select * from fichier"); 

					   while(rs.next())  
					    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
					     
					   con.close();  
					   
					   }catch(Exception ex){
					    System.out.println(ex);
					   } 
				
		        }    }); 
		    	
		    
		    JButton b1 = new JButton("Afficher");  
		    b1.setBounds(200, 200, 200, 50); 
		 
	        b1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	 JFrame afficher = new JFrame() ;  
	            	 try {  
	 		            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  

	 					   Statement stmt=con.createStatement();
	 		            ResultSet rs=stmt.executeQuery("select * from fichier"); 

	 					   while(rs.next())  
	 					    {Object[] row = {rs.getInt(1) , rs.getString(2) , rs.getString(3)};
	 					     model.addRow(row);}
	 					   con.close();  
	 					   
	 					   }catch(Exception ex){
	 					    System.out.println(ex);
	 					   } 
	            	 
	            	 try {
						table = new JTable(model);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	 
	            	 JScrollPane scrollPane = new JScrollPane(table);
	     	      //  Container container = new Container();
	     			//container.add(scrollPane);
	            	 
	                 afficher.add(scrollPane);
	                 
	            	 afficher.setSize(600,600);  
	          
	     	      	afficher.setTitle("Liste des Fichiers");
	     	        afficher.setVisible(true);
	            }});
	        
	        
	            	
	        /*   afficher.add ;	
	           mafenet.setSize(500,500);  
	   	    mafenet.setLayout(null);  
	   	    mafenet.setTitle("Liste des Fichiers");
	   	
	   		mafenet.setVisible(true);	
	                };});*/
		    
		    
		
		// TODO Afficher la JFrame
        mafenet.add(tf0);mafenet.add(b2); mafenet.add(b1);  
	    mafenet.setSize(500,500);  
	    mafenet.setLayout(null);  
	    mafenet.setTitle("Mini projet");
	   
		mafenet.setVisible(true);	

	}

		
}
