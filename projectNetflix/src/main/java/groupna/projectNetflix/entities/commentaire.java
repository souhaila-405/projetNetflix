package groupna.projectNetflix.entities;

public class commentaire {
	private int id;
	private int idUser;
	private String contenu;
	public commentaire(int id, int idUser, String contenu) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.contenu = contenu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
}
