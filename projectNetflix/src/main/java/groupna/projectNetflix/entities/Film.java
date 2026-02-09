package groupna.projectNetflix.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Film extends Oeuvre {
	private LocalTime duree;

	public Film(String resume, Categorie cat, String titre, LocalDate dateDeSortie, List<Artiste> acteurs,
			List<Artiste> directeurs, double rate, LocalTime duree) {
		super(resume, cat, titre, dateDeSortie, acteurs, directeurs, rate);
		this.duree = duree;
	}

	public LocalTime getDuree() {
		return duree;
	}

	public void setDuree(LocalTime duree) {
		this.duree = duree;
	}
}
