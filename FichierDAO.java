import java.util.List;

public interface FichierDAO {
//	public void addFichier(Fichier fichier);
	public void addFichier(String Nom, String Lien, String Niveau);
	public void updateFichier(Fichier fichier);
	public void deleteFichier(int id);
	public Fichier getFichierById(int id);
	public List<Fichier> getAllFichiers(String niveau);
	void addFichier();
}
