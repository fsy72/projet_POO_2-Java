package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppressionEtudiant {
	private String user;
	private String password;
	
	public SuppressionEtudiant(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void suppressionEtudiant(String email) {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL

	    String sql = "DELETE FROM etudiants WHERE email = ?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	pstmt.setString(1, email);
	    	int rowsAffected = pstmt.executeUpdate();
	    	if (rowsAffected > 0) {
	            // System.out.println("Suppression réussie : " + rowsAffected + " étudiant(s) supprimé(s)");
	            System.out.println("Suppression réussie");
	        } else {
	            System.out.println("Aucun étudiant trouvé avec cet email");
	        }
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

