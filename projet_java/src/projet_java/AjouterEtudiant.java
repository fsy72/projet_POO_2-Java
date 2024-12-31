package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjouterEtudiant {
	private String user;
	private String password;
	
	public AjouterEtudiant(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void ajouterEtudiant(String nom, String prenom, String email, String telephone) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL
		
	    String sql = "INSERT INTO etudiants (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, nom);
	        pstmt.setString(2, prenom);
	        pstmt.setString(3, email);
	        pstmt.setString(4, telephone);
	        pstmt.executeUpdate();
			System.out.println("Etudiant ajouté avec succes");
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
