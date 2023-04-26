
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private JButton btnDeposer;
    private JButton btnAfficher;

    public MainWindow() {
        super("Welcome to my Mini-Biblio");

        btnDeposer = new JButton("Déposer un fichier");
        btnAfficher = new JButton("Afficher les fichiers");

        // Customize the appearance of the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonFgColor = Color.WHITE;
        Color buttonBgColor = Color.DARK_GRAY;
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
            	Connection connection = null;
            	try {
            	    // Initialize the connection with the appropriate URL, username, and password
            	    String url = "jdbc:mysql://localhost:3306/Projet";
            	    String user = "root";
            	    String password = "sarra123";
            	    connection = DriverManager.getConnection(url, user, password);

            	    // Create a new FileChooserWindow with the initialized connection object
            	    FileChooserWindow fileChooserWindow = new FileChooserWindow(connection);
            	    fileChooserWindow.setVisible(true);
            	} catch (SQLException e1) {
            	    // Handle any errors that occur during connection initialization
            	    e1.printStackTrace();
            	} finally {
            	    // Close the connection when finished
            	    if (connection != null) {
            	        try {
            	            connection.close();
            	        } catch (SQLException e1) {
            	            e1.printStackTrace();
            	        }
            	    }
            	}
            }
        });

        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
				// code pour afficher les fichiers
             // Initialize the connection with the appropriate URL, username, and password
        	    String url = "jdbc:mysql://localhost:3306/Projet";
        	    String user = "root";
        	    String password = "sarra123";
        	    try {
					connection = DriverManager.getConnection(url, user, password);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            	new FilesWindow(connection);
            }
            
        });
    }   
}

