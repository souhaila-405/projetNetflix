package groupna.projectNetflix.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import groupna.projectNetflix.entities.Film;
import groupna.projectNetflix.entities.Oeuvre;
import groupna.projectNetflix.entities.User;
import groupna.projectNetflix.utils.ConxDB;

import java.sql.Statement;

public class UserDAO {										
	private static Connection conn = ConxDB.getInstance();
	public static void loadUserFavorites(User u) {
		int id=u.getId();
	    Set<Oeuvre> favorites = new HashSet<>();
	    String sqlFilms = "SELECT * FROM films " +
	                      "JOIN fav_film ON films.id = fav_film.id_film " +
	                      "WHERE fav_film.id_user = "+id;
	    String sqlSeries = "SELECT * FROM series  " +
	                       "JOIN fav_serie ON series.id = fav_serie.id_serie " +
	                       "WHERE fav_serie.id_user = "+id;

	    try {
	        PreparedStatement pstmtF = conn.prepareStatement(sqlFilms);
	        pstmtF.setInt(1, u.getId());
	        ResultSet rsF = pstmtF.executeQuery();
	        while (rsF.next()) {
	            favorites.add(new Film(rsF.getInt("id"), rsF.getString("titre"), ));
	        }

	        // Chargement des Séries
	        PreparedStatement pstmtS = conn.prepareStatement(sqlSeries);
	        pstmtS.setInt(1, u.getId());
	        ResultSet rsS = pstmtS.executeQuery();
	        while (rsS.next()) {
	            // Créer l'objet Serie et l'ajouter au Set
	            favorites.add(new Serie(rsS.getInt("id"), rsS.getString("titre"), ...));
	        }
	        
	        u.setFavs(favorites); // Mise à jour de l'objet User

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static user findBy_email(String email) {
		Statement stmt =null;
		ResultSet rs =null;
		List<User> Users = new ArrayList<>();
		
		String SQL ="SELECT * FROM user Where user.email like email";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
				int id = rs.getInt(1);
				String LastName= rs.getString(2);
				String firstName= rs.getString(3);
				User personne = new user(id, LastName, firstName);
			conn.close();	

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static List<User> findAll(){
		
		Statement stmt =null;
		ResultSet rs =null;
		List<User> Users = new ArrayList<>();
		
		String SQL ="SELECT * FROM user";
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(SQL);
				while (rs.next()) {
					int id = rs.getInt(1);
					String LastName= rs.getString(2);
					String firstName= rs.getString(3);
					User personne = new user(id, LastName, firstName);
					personnes.add(personne);
					
				}
				conn.close();	

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return personnes;
		
		}
	
	public static int save(Personne p) {
		int personneID=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="INSERT INTO personne (nom,prenom) VALUES (?,?) ";
			pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, p.getNom());
			pstmt.setString(2, p.getPrenom());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				personneID=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personneID;
	}
}
