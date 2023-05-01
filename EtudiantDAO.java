


import java.sql.ResultSet;
import java.sql.SQLException;



public class EtudiantDAO {
	private ConnexionBD con = new ConnexionBD();

	public String ajoutEtudiant(Etudiant e) {
		String msg = "";
		if (e.getPrenom().length() == 0 || e.getNom().length() == 0 || e.getNiv().length() == 0
				|| e.getUsername().length() == 0 || e.getPassword().length() == 0) {
			msg = "Please Fill all the Fields";
		} else {

			try {
				String sql = "SELECT * FROM Etudiant WHERE username = '" + e.getUsername() + "'";
				ResultSet res = con.instruction.executeQuery(sql);

				if (res.next()) {
					msg = "Username Used!";

				} else {

					sql = "INSERT INTO Etudiant (first_name, last_name, niveau_detude, username, password) "
							+ "VALUES ('" + e.getPrenom() + "','" + e.getNom() + "','" + e.getNiv() + "','"
							+ e.getUsername() + "','" + e.getPassword() + "')";
					try {
						con.instruction.executeUpdate(sql);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					msg = "Compte Crée";

				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
     return msg;
	}
}
