package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjouterCours {
	private String user;
	private String password;
	
	public AjouterCours(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void ajouterCours(String nom, String description) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL
		
	    String sql = "INSERT INTO cours (nom_cours, description) VALUES (?, ?)";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, nom);
	        pstmt.setString(2, description);
	        pstmt.executeUpdate();
            System.out.println("Cours ajouté avec succes");
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
