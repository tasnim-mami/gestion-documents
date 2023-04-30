
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private JButton btnDeposer;
    private JButton btnAfficher;
    static public String niveau;
    public ConnexionBD con = new ConnexionBD();
    
    public MainWindow() {
        super("Welcome to my Mini-Biblio");

        btnDeposer = new JButton("Déposer un fichier");
        btnAfficher = new JButton("Afficher les fichiers");

        // Customize the appearance of the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonFgColor = Color.WHITE;
        Color buttonBgColor = Color.PINK;
        btnDeposer.setFont(buttonFont);
        btnDeposer.setForeground(buttonFgColor);
        btnDeposer.setBackground(buttonBgColor);
        btnAfficher.setFont(buttonFont);
        btnAfficher.setForeground(buttonFgColor);
        btnAfficher.setBackground(buttonBgColor);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(btnDeposer);
        panel.add(btnAfficher);

        add(panel);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        btnDeposer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Object[] options = {"II1", "II2", "II3"};
            	int selectedOption = JOptionPane.showOptionDialog(null, "Sélectionnez un niveau", "Niveau d'étude", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            	if (selectedOption == 0) {
            	    niveau = "II1"; 
            	} else if (selectedOption == 1) {
            		 niveau = "II2";
            	} else if (selectedOption == 2) {
            		 niveau = "II2";
            	
            	}
            	
            	JFileChooser choose = new JFileChooser(
		            FileSystemView.getFileSystemView().getHomeDirectory());
		            choose.setDialogTitle("Selectionnez un fichier");
		            choose.setAcceptAllFileFilterUsed(false);
		            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images JPG et GIF", "jpg", "gif", "pdf");
		            choose.addChoosableFileFilter(filter);
            	
		            int res = choose.showOpenDialog(null);
		            if (res == JFileChooser.APPROVE_OPTION) {
		                try (FileInputStream fis = new FileInputStream(
		                        choose.getSelectedFile());
		                        ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
		                    byte[] buffer = new byte[1024];
		                    int bytesRead;
		                    while ((bytesRead = fis.read(buffer)) != -1) {
		                        bos.write(buffer, 0, bytesRead);         }
		                    byte[] pdfContent = bos.toByteArray();
                  //Fichier f = new Fichier(choose.getSelectedFile().getName(), choose.getSelectedFile().getPath()
		          // f.SetaBook(choose.getSelectedFile().getName(), choose.getSelectedFile().getPath(),niveau);
		                    FichierDAO fichierDAO = new FichierDAOImpl();
		                   Fichier f = new Fichier();
		                   f.setNom(choose.getSelectedFile().getName());
		                   f.setLien(choose.getSelectedFile().getPath());
		                   f.setNiveau(niveau);
		                 //  fichierDAO.addFichier(f);
		                   fichierDAO.addFichier(choose.getSelectedFile().getName(),choose.getSelectedFile().getPath(), niveau);
		                   JOptionPane.showMessageDialog(null, " Votre fichier était insére avec succé ! ", "Succé", JOptionPane.INFORMATION_MESSAGE);
		                } catch (IOException ex) {
		                    ex.printStackTrace(); }	                
		            }   }


        });

        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
            	Object[] options = {"II1", "II2", "II3"};
            	int selectedOption = JOptionPane.showOptionDialog(null, "Sélectionnez un niveau", "Niveau d'étude", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            	if (selectedOption == 0) {
            	    niveau = "II1"; 
            	} else if (selectedOption == 1) {
            		 niveau = "II2";
            	} else if (selectedOption == 2) {
            		 niveau = "II2";
            	
            	}
        	    try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost/miniprojet","root","");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            	new FilesWindow(connection, niveau);
            }
            
        });
    }   
}

