package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppressionCours {
	private String user;
	private String password;
	
	public SuppressionCours(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void suppressionCours(String nom) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL

	    String sql = "DELETE FROM cours WHERE nom_cours = ?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	pstmt.setString(1, nom);
	    	int rowsAffected = pstmt.executeUpdate();
	    	if (rowsAffected > 0) {
				System.out.println("Cours supprimé avec succès");
	        } else {
	            System.out.println("Aucun étudiant trouvé avec cet email");
	        }
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

