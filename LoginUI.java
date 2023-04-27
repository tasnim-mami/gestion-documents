

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginUI extends JFrame {
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel Text;
	private JLabel Text2;
	private JLabel Text3;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnSignUp;
	private ConnexionBD con = new ConnexionBD();

	public LoginUI() {
		// Set up the UI components
		setTitle("Login");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblUsername = new JLabel("Username:");
		lblPassword = new JLabel("Password:");
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		btnLogin = new JButton("Login");
		btnSignUp = new JButton("SignUp");

		// Add the components to the UI
		Text = new JLabel("Welcome to our File Sharing Application !!!");
		setLayout(new BorderLayout());
		Text.setFont(new Font("Arial", Font.BOLD, 30));
		add(Text, BorderLayout.NORTH);
		Text2 = new JLabel("Login to access to our amazing file collection !!!");
		Text2.setFont(new Font("Arial", Font.BOLD, 15));
		Text3 = new JLabel("Don't have an account ? Sign Up for Free !!!");
		Text3.setFont(new Font("Arial", Font.BOLD, 15));

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(lblUsername);
		panel.add(txtUsername);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(Text3);
		panel.add(Text2);
		panel.add(btnSignUp);
		panel.add(btnLogin);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 30));
		txtUsername.setFont(new Font("Arial", Font.BOLD, 30));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 30));
		txtPassword.setFont(new Font("Arial", Font.BOLD, 30));
		add(panel);

		// Add an action listener to the login button
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String sql = "SELECT * FROM Etudiant WHERE username = '" + username + "' AND password = '" + password
						+ "'";
				ResultSet res;
				try {
					res = con.instruction.executeQuery(sql);
					if (res.next()) {
						System.out.println("Login successful!");
						dispose();
//						    MainPageFrame mainPageFrame = new MainPageFrame();
//						    mainPageFrame.setVisible(true);

					} else {
						System.out.println("Invalid username or password");
						JFrame Frame = new JFrame("Error");
						Frame.setSize(400, 200);
						Frame.setLocationRelativeTo(null);
						JLabel Message = new JLabel("Invalid username or password.");
						Frame.getContentPane().add(Message);
						Message.setFont(new Font("Arial", Font.BOLD, 24));
						Frame.setVisible(true);
						Frame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								Frame.dispose();
							}
						});
					}

				} catch (SQLException ex) {
					System.err.println("Requete incorrecte" + sql);
				}

			}

		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUpUI SignUpFrame = new SignUpUI();
				SignUpFrame.setVisible(true);
			}
		});

		// Show the UI
		setVisible(true);
	}
}
