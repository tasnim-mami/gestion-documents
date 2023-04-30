import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FilesWindow extends JFrame {
	
	
  private Connection connection;
  private String niveau;
    private JTable table;
    private JTable Comtable;
    JPanel panel = new JPanel();
    
   static int idfichier  ;
	static int idcommentaire ;
	static String texte ;
	
    private DefaultTableModel tableModel;
    private DefaultTableModel tableComModel;
    
    public FilesWindow(Connection connection, String niveau) {  	
    	tableModel = new DefaultTableModel();
        tableComModel = new DefaultTableModel();
        
        tableModel.addColumn("ID");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Lien");
        
        tableComModel.addColumn("ID");
        tableComModel.addColumn("Comment");
         
        table = new JTable(tableModel);
        Comtable = new JTable(tableComModel);
        
        this.connection = connection;
        setTitle("Liste des fichiers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {    
        FichierDAO fichierDAO = new FichierDAOImpl();
        for (Fichier fichier : fichierDAO.getAllFichiers(niveau)) {
            Object[] rowData = {fichier.getId(),fichier.getNom(), fichier.getLien()};
            tableModel.addRow(rowData);   }
        }catch(Exception ex){
    	    System.out.println(ex);
    	   } 

        try {
        CommentaireDAO commentaireDAO = new CommentaireDAOImpl();
        for (Commenatire commentaire : commentaireDAO.getAllCommentaires(idfichier)) {
            Object[] rowData = {commentaire.getId(),commentaire.getText()};
            tableComModel.addRow(rowData);
        }
        }catch(Exception ex){
	    System.out.println(ex);
	    } 

        
// lors de la selection d'une ligne on affiche les commentaire associé à ce fichier selectionné 
      
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    	    public void valueChanged(ListSelectionEvent event) {
    	        if (event.getValueIsAdjusting()) {
    	            // La sélection de la ligne est en cours, ne rien faire
    	            return;
    	        }
    	        // Récupérer l'index de la ligne sélectionnée
    	        int rowIndex = table.getSelectedRow();
    	        if (rowIndex != -1) {
    	        System.out.println("Ligne fichier sélectionnée : " + rowIndex);                	        	                	        
    	        int selectedRowIndex = table.getSelectedRow();
    	        Object value = table.getValueAt(selectedRowIndex, 0);
    	        try {
					idfichier= (int) value ;
					 tableComModel.setRowCount(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
    	        CommentaireDAO commentaireDAO = new CommentaireDAOImpl();
    	        for (Commenatire commentaire : commentaireDAO.getAllCommentaires(idfichier)) {
    	            Object[] rowData = {commentaire.getId(),commentaire.getText()};
    	            tableComModel.addRow(rowData);
    	        System.out.println("Valeur de l'id fichier pour la ligne sélectionnée : "+ idfichier);}
    	        }         }	});     
        
        
  /*      table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    return;
                }
                int rowIndex = table.getSelectedRow();
                if (rowIndex != -1) { // vérifie si une ligne a été sélectionnée
                    System.out.println("Ligne du commentaire sélectionnée : " + rowIndex);                
                    int selectedRowIndex = table.getSelectedRow();
                    Object value = table.getValueAt(selectedRowIndex, 0);
                    try {               
                        idfichier= (int) value ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }           
                    System.out.println("Valeur de l'id fichier pour la ligne sélectionnée  : "+ idfichier);
                }
            }
        });*/

        






        
        
        
        
        

        Comtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    return;
                }
                int rowIndex = Comtable.getSelectedRow();
                if (rowIndex != -1) { // vérifie si une ligne a été sélectionnée
                    System.out.println("Ligne du commentaire sélectionnée : " + rowIndex);                
                    int selectedRowIndex = Comtable.getSelectedRow();
                    Object value = Comtable.getValueAt(selectedRowIndex, 0);
                    try {               
                        idcommentaire= (int) value ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }           
                    System.out.println("Valeur de la colonne commentaire pour la ligne sélectionnée : "+ idcommentaire);
                }
            }
        });






             
        
        
     	JButton badd = new JButton("Ajouter un commentaire"); 
     	JButton bsuppf = new JButton("Supprimer un fichier"); 
     	JButton bsuppc = new JButton("Supprimer un commentaire"); 
     	
     	 badd.setBounds(260, 380, 180, 40); 
     	 bsuppf.setBounds(150, 300, 180, 40);
     	 bsuppc.setBounds(580, 380, 200, 40);
     	 
     	JTextField tadd = new JTextField();   		  
	    tadd.setBounds(10, 380, 220, 40); 
	    tadd.setText(""); 	    
	 
	    
//bouton de suppression d'un fichier	   	    
	    bsuppf.addActionListener(new ActionListener(){  	    	
   		      public void actionPerformed(ActionEvent e){  		        	
   		         try {  
   		          int confirm = JOptionPane.showConfirmDialog(FilesWindow.this, "Voulez-vous vraiment supprimer ce fichier?");
                  if (confirm == JOptionPane.YES_OPTION) {
                	  FichierDAO fichierDAO = new FichierDAOImpl();
                	  fichierDAO.deleteFichier(idfichier);
                	  }
                     // FilesWindow.this.dispose()                }   		        	 	        
 		        System.out.println("fichier supprimer d'index "+ idfichier +"est supprime");
 		       tableModel.setRowCount(0);
   		         }			      
 				        catch(Exception ex){
 					    System.out.println(ex);
 					   }    
   		      FichierDAO fichierDAO = new FichierDAOImpl();
   	        for (Fichier fichier : fichierDAO.getAllFichiers(niveau)) {
   	            Object[] rowData = {fichier.getId(),fichier.getNom(), fichier.getLien()};
   	            tableModel.addRow(rowData);
   	        }}});
	    
 //bouton de suppression d'un commentaire	    
	    bsuppc.addActionListener(new ActionListener(){  	    	
   		      public void actionPerformed(ActionEvent e){   		        	
   		         try {   
  	      CommentaireDAO commentaireDAO = new CommentaireDAOImpl();
    	  commentaireDAO.deleteCommentaire(idcommentaire);
  	        System.out.println("le commentaire est supprimé  " );       		         		         
   		      tableComModel.setRowCount(0);
   		      }   	   		 
			        catch(Exception ex){
				    System.out.println(ex);	   }
   		      CommentaireDAO newcommentaireDAO = new CommentaireDAOImpl();
  	           for (Commenatire newcommentaire : newcommentaireDAO.getAllCommentaires(idfichier)) {
  	               Object[] rowData = {newcommentaire.getId(),newcommentaire.getText()};
  	               tableComModel.addRow(rowData);
  	           }
  	          
   		         }});
        
	       
	    
// bouton pour ajouter un commentaire	    
        badd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                texte = tadd.getText();
                System.out.println("Texte saisi : " + texte);			            		            
       if(texte != null && !texte.isEmpty())    
       {  try {  
    	          	tableComModel.setRowCount(0); 	   	 		         	
        CommentaireDAO commentaireDAO = new CommentaireDAOImpl();
  	  commentaireDAO.addCommentaire(texte,idfichier);
        tadd.setText("");
               
        CommentaireDAO newcommentaireDAO = new CommentaireDAOImpl();
          for (Commenatire newcommentaire : newcommentaireDAO.getAllCommentaires(idfichier)) {
              Object[] rowData = {newcommentaire.getId(),newcommentaire.getText()};
              tableComModel.addRow(rowData);
          }
				   }catch(Exception ex){
					    System.out.println(ex);
					   } 	  	}       else  System.out.println(" champ est vide  " );   }
	    	  
	                 });   
        
        
	    add(bsuppf); 
        add(tadd);
        add(badd);  
            add(bsuppc); 
            
            getContentPane().add(panel);
            setVisible(true);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            JScrollPane scrollPaneCom = new JScrollPane(Comtable);
            panel.add(scrollPaneCom);
            pack();
       
    }
	
    
    
}