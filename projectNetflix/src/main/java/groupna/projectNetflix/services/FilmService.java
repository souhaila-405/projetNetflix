package groupna.projectNetflix.services;

import java.util.List;

import groupna.projectNetflix.DAO.FilmDAO;
import groupna.projectNetflix.entities.Film;

import java.util.List;

public class FilmService {
	private FilmDAO filmDAO ;
	
	public  List<Artiste> getCastByFilm(int filmId){
		filmDAO.getCastByFilm(int filmId);
		
	}
	private  void saveFilmActors(Film f) {
		filmDAO.saveFilmActors(Film f);
	}
	public List<Artiste> getDirecteursByFilm(int filmId){
		filmDAO.getDirecteursByFilm(int filmId);
	}
	
    public int ajouterFilm(Film f) {

        if (f == null) {
            throw new IllegalArgumentException("Film invalide.");
        }

        if (f.getTitre() == null || f.getTitre().isEmpty()) {
            throw new IllegalArgumentException("Le titre du film est obligatoire.");
        }

        return FilmDAO.save(f);
    }

    public Film getFilmById(int id) {

        if (id <= 0) {
            return null;
        }

        return FilmDAO.findById(id);
    }

    public List<Film> getAllFilms() {

        return FilmDAO.findAll();
    }


    public boolean supprimerFilm(int id) {

        if (id <= 0) {
            return false;
        }

        return FilmDAO.delete(id);
    }

    public void ajouterListeFilms(List<Film> films) {

        if (films == null || films.isEmpty()) {
            return;
        }

        FilmDAO.saveAll(films);
    }
	

}
