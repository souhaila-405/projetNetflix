package groupna.projectNetflix.entities;

import java.time.LocalTime;

public class Episode {
	private int id;
	private String resume;
	private String Titre;
	private LocalTime duree;
	public Episode(String resume, String titre, LocalTime duree) {
		this.resume = resume;
		Titre = titre;
		this.duree = duree;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public LocalTime getDuree() {
		return duree;
	}
	public void setDuree(LocalTime duree) {
		this.duree = duree;
	}
	@Override
	public String toString() {
		return "Episode [resume=" + resume + ", Titre=" + Titre + ", duree=" + duree + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
