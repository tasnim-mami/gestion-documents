

public class Etudiant {
    private int id;
    private String prenom;
    private String nom;
    private String niv;
    private String username;
    private String password;

    // Constructor
    public Etudiant(int id, String nom, String prenom,String niv, String username, String password) {
        this.id = id;
        this.prenom = nom;
        this.nom = prenom;
        this.niv=niv;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

	public String getNiv() {
		return niv;
	}

	public void setNiv(String niv) {
		this.niv = niv;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}

