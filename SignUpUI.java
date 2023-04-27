package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpUI extends JFrame {
	private JLabel lblFirstName;
	private JLabel lblUserName;
	private JLabel lblLastName;
	private JLabel lblPassword;
	private JLabel lblNiv;
	private JLabel Text;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUserName;
	private JTextField txtNiv;
	private JPasswordField txtPassword;
	private JButton btnSignup;
	private ConnexionBD con = new ConnexionBD();

	public SignUpUI() {
		// Set up the UI components
		setTitle("Singup");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblFirstName = new JLabel("FirstName:");
		lblLastName = new JLabel("LastName:");
		lblUserName = new JLabel("UserName:");
		lblNiv = new JLabel("Niv:");
		lblPassword = new JLabel("Password:");
		txtFirstName = new JTextField();
		txtLastName = new JTextField();
		txtUserName = new JTextField();
		txtNiv = new JTextField();
		txtPassword = new JPasswordField();
		btnSignup = new JButton("SignUp");

		// Add the components to the UI
		Text = new JLabel("Create your account here !!!");
		setLayout(new BorderLayout());
		Text.setFont(new Font("Arial", Font.BOLD, 30));
		add(Text, BorderLayout.NORTH);

		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(lblFirstName);
		panel.add(txtFirstName);
		panel.add(lblLastName);
		panel.add(txtLastName);
		panel.add(lblNiv);
		panel.add(txtNiv);
		panel.add(lblUserName);
		panel.add(txtUserName);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(new JLabel());
		panel.add(btnSignup);
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 24));
		txtFirstName.setFont(new Font("Arial", Font.BOLD, 24));
		lblLastName.setFont(new Font("Arial", Font.BOLD, 24));
		txtLastName.setFont(new Font("Arial", Font.BOLD, 24));
		lblNiv.setFont(new Font("Arial", Font.BOLD, 24));
		txtNiv.setFont(new Font("Arial", Font.BOLD, 24));
		lblUserName.setFont(new Font("Arial", Font.BOLD, 24));
		txtUserName.setFont(new Font("Arial", Font.BOLD, 24));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 24));
		txtPassword.setFont(new Font("Arial", Font.BOLD, 24));
		add(panel, BorderLayout.CENTER);

		// Add an action listener to the login button
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUserName.getText();
				String firstname = txtFirstName.getText();
				String lastname = txtLastName.getText();
				String niv = txtNiv.getText();
				String password = new String(txtPassword.getPassword());
				if (username.length() == 0 || password.length() == 0 || firstname.length() == 0 || niv.length() == 0
						|| lastname.length() == 0) {
					System.out.println("Please Fill all the Fields");
					JFrame Frame = new JFrame("Error");
					Frame.setSize(400, 200);
					Frame.setLocationRelativeTo(null);
					JLabel Message = new JLabel("Please Fill all the Fields!!");
					Message.setFont(new Font("Arial", Font.BOLD, 24));
					Frame.getContentPane().add(Message);
					Frame.setVisible(true);
					Frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							Frame.dispose();
						}
					});
				} else {

					try {
						String sql = "SELECT * FROM Etudiant WHERE username = '" + username + "'";
						ResultSet res = con.instruction.executeQuery(sql);

						if (res.next()) {
							System.out.println("Username Used!");
							JFrame Frame = new JFrame("Error");
							Frame.setSize(400, 200);
							Frame.setLocationRelativeTo(null);
							JLabel Message = new JLabel("Username used.");
							Message.setFont(new Font("Arial", Font.BOLD, 24));
							Frame.getContentPane().add(Message);
							Frame.setVisible(true);
							Frame.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									Frame.dispose();
								}
							});

						} else {

							sql = "INSERT INTO Etudiant (first_name, last_name, niveau_detude, username, password) "
									+ "VALUES ('" + firstname + "','" + lastname + "','" + niv + "','" + username
									+ "','" + password + "')";

							try {
								con.instruction.executeUpdate(sql);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							System.out.println("Compte Crée");
							JFrame Frame = new JFrame("Félicitations");
							Frame.setSize(400, 200);
							Frame.setLocationRelativeTo(null);
							JLabel Message = new JLabel("Compte Crée.");
							Message.setFont(new Font("Arial", Font.BOLD, 24));
							Frame.getContentPane().add(Message);
							Frame.setVisible(true);
							Frame.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									Frame.dispose();
								}
							});
							dispose();

							LoginUI LoginFrame = new LoginUI();
							LoginFrame.setVisible(true);

						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// Show the UI
		setVisible(true);
	}

}
