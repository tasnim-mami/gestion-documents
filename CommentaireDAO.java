import java.util.List;

public interface CommentaireDAO {
	public void addCommentaire(String text, int idf);
	public void updateCommentaire(Commenatire commentaire);
	public void deleteCommentaire(int id);
	public Commenatire getCommentaireById(int id);
	public List<Commenatire> getAllCommentaires(int idf);
	
}
