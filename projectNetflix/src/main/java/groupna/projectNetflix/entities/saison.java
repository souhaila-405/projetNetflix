package groupna.projectNetflix.entities;

import java.time.LocalDate;

public class saison {
	private int id;
	private LocalDate dateDeSortie;
	private String Titre;
	private String resume;
	public saison(int id, LocalDate dateDeSortie, String titre, String resume) {
		super();
		this.id = id;
		this.dateDeSortie = dateDeSortie;
		Titre = titre;
		this.resume = resume;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateDeSortie() {
		return dateDeSortie;
	}
	public void setDateDeSortie(LocalDate dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
}
