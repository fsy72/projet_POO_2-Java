package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCours {
	private String user;
	private String password;
	
	public UpdateCours(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void updateCours(String oldNom, String description, String newNom) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL

	    String sql = "UPDATE cours SET nom_cours = ?, description = ? WHERE nom_cours = ?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, newNom);
	        pstmt.setString(2, description);
	        pstmt.setString(3, oldNom);
	        pstmt.executeUpdate();
	        System.out.println("Cours mis à jour avec succes");
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
}
