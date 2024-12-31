package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AfficherEtudiants {
	private String user;
	private String password;
	
	public AfficherEtudiants(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public void afficherEtudiants() {
		String url = "jdbc:postgresql://localhost:5432/gestion_etudiants"; // URL de la base PostgreSQL

	    String sql = "SELECT * FROM etudiants";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			if(rs.next()) {
				do {
					System.out.println("ID: " + rs.getInt("id") +
									", Nom: " + rs.getString("nom") +
									", Prénom: " + rs.getString("prenom") +
									", Email: " + rs.getString("email") +
									", Téléphone: " + rs.getString("telephone"));
				} while (rs.next());
			} else {
	            System.out.println("Aucun étudiant trouvé avec cet email");
	        }
			conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
