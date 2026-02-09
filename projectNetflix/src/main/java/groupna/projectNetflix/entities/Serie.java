package groupna.projectNetflix.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Serie extends Oeuvre{
	private Map<Integer,List<Episode>> saisons;
	private int hassen;

	public Serie(String resume, Categorie cat, String titre, LocalDate dateDeSortie, List<Artiste> acteurs,
			List<Artiste> directeurs, double rate, Map<Integer, List<Episode>> saisons) {
		super(resume, cat, titre, dateDeSortie, acteurs, directeurs, rate);
		this.saisons = saisons;
	}

	public Map<Integer, List<Episode>> getSaisons() {
		return saisons;
	}

	public void setSaisons(Map<Integer, List<Episode>> saisons) {
		this.saisons = saisons;
	}
	
}
