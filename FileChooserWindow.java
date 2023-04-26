import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FileChooserWindow extends JFrame implements ActionListener {
    private JButton chooseFileButton;
    private JFileChooser fileChooser;
    private Connection connection;
    
    
    public FileChooserWindow(Connection connection) {
        this.connection = connection;

        setTitle("Sélectionner un fichier");
        setSize(300, 300);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        chooseFileButton = new JButton("Sélectionner un fichier");
        chooseFileButton.addActionListener(this);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonFgColor = Color.WHITE;
        Color buttonBgColor = Color.DARK_GRAY;
        chooseFileButton.setFont(buttonFont);
        chooseFileButton.setForeground(buttonFgColor);
        chooseFileButton.setBackground(buttonBgColor);
        
        panel.add(chooseFileButton);
        add(panel);
        setVisible(true);
    }
    @Override
    
        public void actionPerformed(ActionEvent e) {
            try {
                // Establish a database connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Projet", "root", "sarra123");

                // Create a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                // If the user chooses a file
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // Insert the file's name and path into the "fichier" table
                    String sql = "INSERT INTO fichier (lien_fichier, nom_fichier) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, selectedFile.getAbsolutePath());
                    statement.setString(2, selectedFile.getName());
                    statement.executeUpdate();
                    
                    // Get the auto-generated ID of the newly inserted record
                    ResultSet rs = statement.getGeneratedKeys();
                    int id_fichier = 0;
                    if (rs.next()) {
                        id_fichier = rs.getInt(1);
                    }
                  
                    // Print a message to the console
                    
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                    	System.out.println("File inserted successfully with id_fichier: " + id_fichier);
                        new FilesWindow(connection); // create new FilesWindow object to display all files
                    }

                }

                // Close the database connection
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
    }
    
    
