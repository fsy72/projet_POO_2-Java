package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEtudiant {
	private String user;
	private String password;
	
	public UpdateEtudiant(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void updateEtudiant(String nom, String prenom, String newEmail, String telephone, String oldEmail) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL

	    String sql = "UPDATE etudiants SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE email = ?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, nom);
	        pstmt.setString(2, prenom);
	        pstmt.setString(3, newEmail);
	        pstmt.setString(4, telephone);
	        pstmt.setString(5, oldEmail);
	        pstmt.executeUpdate();
	        System.out.println("Etudiant mis à jour avec succes");
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
}
