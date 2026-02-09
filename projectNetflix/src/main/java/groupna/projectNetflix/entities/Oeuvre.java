package groupna.projectNetflix.entities;

import java.time.LocalDate;
import java.util.List;

public class Oeuvre {
	protected int id;
	protected String resume;
	protected Categorie cat;
	protected String titre;
	protected LocalDate DateDeSortie;
	protected List<Artiste> acteurs;
	protected List<Artiste> Directeurs;
	protected double rate;
	
	public Oeuvre(String resume, Categorie cat, String titre, LocalDate dateDeSortie, List<Artiste> acteurs,
			List<Artiste> directeurs, double rate) {
		super();
		this.resume = resume;
		this.cat = cat;
		this.titre = titre;
		DateDeSortie = dateDeSortie;
		this.acteurs = acteurs;
		Directeurs = directeurs;
		this.rate = rate;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Categorie getCat() {
		return cat;
	}
	public void setCat(Categorie cat) {
		this.cat = cat;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public LocalDate getDateDeSortie() {
		return DateDeSortie;
	}
	public void setDateDeSortie(LocalDate dateDeSortie) {
		DateDeSortie = dateDeSortie;
	}
	public List<Artiste> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<Artiste> acteurs) {
		this.acteurs = acteurs;
	}
	public List<Artiste> getDirecteurs() {
		return Directeurs;
	}
	public void setDirecteurs(List<Artiste> directeurs) {
		Directeurs = directeurs;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "Oeuvre [resume=" + resume + ", cat=" + cat + ", titre=" + titre + ", DateDeSortie=" + DateDeSortie
				+ ", acteurs=" + acteurs + ", Directeurs=" + Directeurs + ", rate=" + rate + "]";
	}
	
}
