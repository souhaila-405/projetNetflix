package groupna.projectNetflix.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import groupna.projectNetflix.entities.Artiste;
import groupna.projectNetflix.entities.Categorie;
import groupna.projectNetflix.entities.Film;
import groupna.projectNetflix.utils.ConxDB;
public class FilmDAO {
	private static Connection conn = ConxDB.getInstance();
	public static List<Artiste> getCastByFilm(int filmId) {
	    List<Artiste> cast = new ArrayList<>();
	    String sql = "SELECT a.* FROM artistes a " +
	                 "JOIN film_acteurs fa ON a.id = fa.id_artiste " +
	                 "WHERE fa.id_film = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, filmId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Artiste artiste = new Artiste(
	                    rs.getInt("id"),
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("bio"),
	                    null 
	                );
	                cast.add(artiste);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cast;
	}
//----------------------------------------------------------------------------------------------
	private static void saveFilmActors(Film f) throws SQLException {
	    String sql = "INSERT INTO film_acteurs (id_film, id_artiste) VALUES (?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        for (Artiste a : f.getActeurs()) {
	            pstmt.setInt(1, f.getId());
	            pstmt.setInt(2, a.getId());
	            pstmt.addBatch();
	        }
	        pstmt.executeBatch();
	    }
	}
//---------------------------------------------------------------------------------
	public static List<Artiste> getDirecteursByFilm(int filmId) {
	    List<Artiste> directeurs = new ArrayList<>();
	    String sql = "SELECT a.* FROM artistes a " +
	                 "JOIN film_directeurs fd ON a.id = fd.id_artiste " +
	                 "WHERE fd.id_film = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, filmId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Artiste artiste = new Artiste(
	                    rs.getInt("id"),
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("bio"),
	                    null
	                );
	                directeurs.add(artiste);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return directeurs;
	}
//---------------------------------------------------------------------------------
	private static void saveFilmDirectors(Film f) throws SQLException {
	    String sql = "INSERT INTO film_directeurs (id_film, id_artiste) VALUES (?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        for (Artiste d : f.getDirecteurs()) {
	            pstmt.setInt(1, f.getId());
	            pstmt.setInt(2, d.getId());
	            pstmt.addBatch();
	        }
	        pstmt.executeBatch();
	    }
	}
//------------------------------------------------------------------------------------
	public static List<Categorie> getCategoriesByFilm(int filmId) {
	    List<Categorie> categories = new ArrayList<>();
	    String sql = "SELECT c.nom FROM categories c " +
	                 "JOIN film_categorie fc ON c.id = fc.id_categorie " +
	                 "WHERE fc.id_film = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, filmId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                String nomCat = rs.getString("nom");
	                try {
	                    categories.add(Categorie.valueOf(nomCat));
	                } catch (IllegalArgumentException e) {
	                    System.err.println("La catégorie " + nomCat + " n'existe pas dans l'Enum Categorie.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categories;
	}
//------------------------------------------------------------------------------------------------
	private static void saveFilmCategories(Film f) throws SQLException {
	    String sql = "INSERT INTO film_categorie (id_film, id_categorie) VALUES (?, (SELECT id FROM categories WHERE nom = ?))";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        for (Categorie cat : f.getCat()) {
	            pstmt.setInt(1, f.getId());
	            pstmt.setString(2, cat.name());
	            pstmt.addBatch();
	        }
	        pstmt.executeBatch();
	    }
	}
//------------------------------------------------------------------------------------------------
	public static Film findById(int id) {
	    Film film = null;
	    String sql = "SELECT * FROM films WHERE id = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                int filmId = rs.getInt("id");
	                String resume = rs.getString("resume");
	                String titre = rs.getString("titre");
	                double rate = rs.getDouble("rating");
	                String urlAnn = rs.getString("url_annonce");
	                String urlVideo = rs.getString("url_video");
	                LocalDate dateSortie = (rs.getDate("date_de_sortie") != null) 
	                                       ? rs.getDate("date_de_sortie").toLocalDate() : null;
	                LocalTime duree = (rs.getTime("duree") != null) 
	                                  ? rs.getTime("duree").toLocalTime() : null;
	                List<Categorie> categories = getCategoriesByFilm(filmId);
	                List<Artiste> acteurs = getCastByFilm(filmId);
	                List<Artiste> directeurs = getDirecteursByFilm(filmId);
	                film = new Film(
	                    filmId,
	                    resume,
	                    categories,
	                    titre,
	                    dateSortie,
	                    acteurs,
	                    directeurs,
	                    rate,
	                    urlAnn,
	                    duree,
	                    urlVideo
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return film;
	}
//-----------------------------------------------------------------------------------------------------
	public static int save(Film f) {
	    int generatedId = 0;
	    String sql = "INSERT INTO films (resume, titre, date_de_sortie, rating, url_annonce, duree, url_video) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        pstmt.setString(1, f.getResume());
	        pstmt.setString(2, f.getTitre());
	        pstmt.setDate(3, f.getDateDeSortie() != null ? java.sql.Date.valueOf(f.getDateDeSortie()) : null);
	        pstmt.setDouble(4, f.getRate());
	        pstmt.setString(5, f.getURLann());
	        pstmt.setTime(6, f.getDuree() != null ? java.sql.Time.valueOf(f.getDuree()) : null);
	        pstmt.setString(7, f.getURL());

	        pstmt.executeUpdate();
	        try (ResultSet rs = pstmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	                f.setId(generatedId);
	            }
	        }
	        if (generatedId > 0) {
	            saveFilmCategories(f);
	            saveFilmActors(f);
	            saveFilmDirectors(f);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return generatedId;
	}
//------------------------------------------------------------------------------------------------------
	public static List<Film> findAll() {
	    List<Film> films = new ArrayList<>();
	    String sql = "SELECT id FROM films";

	    try (Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Film f = findById(id);
	            
	            if (f != null) {
	                films.add(f);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return films;
	}
//-----------------------------------------------------------------------------------------------------
	public static void saveAll(List<Film> films) {
	    if (films == null || films.isEmpty()) {
	        System.out.println("Aucun film à enregistrer.");
	        return;
	    }
	    for (Film f : films) {
	        try {
	            save(f); 
	        } catch (Exception e) {
	            System.err.println("Erreur lors de l'enregistrement de : " + f.getTitre());
	            e.printStackTrace();
	        }
	    }
	}
//-----------------------------------------------------------------------------------------------------------
	public static boolean delete(int filmId) {
	    boolean isDeleted = false;
	    String deleteCats = "DELETE FROM film_categorie WHERE id_film = ?";
	    String deleteActors = "DELETE FROM film_acteurs WHERE id_film = ?";
	    String deleteDirectors = "DELETE FROM film_directeurs WHERE id_film = ?";
	    String deleteFilm = "DELETE FROM films WHERE id = ?";

	    try {
	        try (PreparedStatement pstmt = conn.prepareStatement(deleteCats)) {
	            pstmt.setInt(1, filmId);
	            pstmt.executeUpdate();
	        }
	        try (PreparedStatement pstmt = conn.prepareStatement(deleteActors)) {
	            pstmt.setInt(1, filmId);
	            pstmt.executeUpdate();
	        }
	        try (PreparedStatement pstmt = conn.prepareStatement(deleteDirectors)) {
	            pstmt.setInt(1, filmId);
	            pstmt.executeUpdate();
	        
	        try (PreparedStatement pstmt1 = conn.prepareStatement(deleteFilm)) {
	            pstmt1.setInt(1, filmId);
	            int rowsAffected = pstmt1.executeUpdate();
	            isDeleted = (rowsAffected > 0);
	        }
	        }

	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression du film id : " + filmId);
	        e.printStackTrace();
	    }
	    
	    return isDeleted;
	    
	}
//--------------------------------------------------------------------------------------------
	
}
