package projet_java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static boolean checkConnection(String user, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gestion_etudiants", user, password);
			if (conn != null) {
				System.out.println("Connexion réussie!");
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur de connexion à la base de données: " + e.getMessage());
		}
		return false;
	}
    public static void main(String[] args) {
    	Scanner donnees = new Scanner(System.in);
    	System.out.print("Entrez le nom d'utilisateur PostgreSQL: ");
		String user = donnees.next();
		System.out.print("Entrez le mot de passe: ");
		String password = donnees.next();

		if (checkConnection(user, password)) {
			
			int choixP;
			int choixE;
			int choixC;

			String nomEtu;
			String prenomEtu;
			String emailEtu;
			String newEmailEtu;
			String telEtu;

			String nomCours;
			String descriptionCours;
			String newNomCours;
			do {
				affichageMenuPrincipal();
				choixP = donnees.nextInt();
				System.out.println();
				switch (choixP) {
					case 1:
						affichageMenuEtudiants();
						choixE = donnees.nextInt();
						switch (choixE) {
							case 1:
								System.out.println("Tous les étudiants: ");
								AfficherEtudiants affEtu = new AfficherEtudiants(user, password);
								affEtu.afficherEtudiants();
								break;
							
							case 2:
								System.out.print("Entrez le nom de l'étudiant: ");
								donnees.nextLine();
								nomEtu = donnees.nextLine();
								System.out.print("Entrez son prénom: ");
								prenomEtu = donnees.nextLine();
								System.out.print("Entrez son email: ");
								emailEtu = donnees.next();
								System.out.print("Entrez son numéro de téléphone: ");
								donnees.nextLine();
								telEtu = donnees.nextLine();
								AjouterEtudiant ajoutEtu = new AjouterEtudiant(user, password);
								ajoutEtu.ajouterEtudiant(nomEtu, prenomEtu, emailEtu, telEtu);
								break;

							case 3:
								System.out.print("Entrez le nouveau nom de l'étudiant: ");
								donnees.nextLine();
								nomEtu = donnees.nextLine();
								System.out.print("Entrez son nouveau prénom: ");
								prenomEtu = donnees.nextLine();
								System.out.print("Entrez son ancien email: ");
								emailEtu = donnees.next();
								System.out.print("Entrez son nouvel email: ");
								newEmailEtu = donnees.next();
								System.out.print("Entrez son numéro de téléphone: ");
								donnees.nextLine();
								telEtu = donnees.nextLine();
								UpdateEtudiant updateEtu = new UpdateEtudiant(user, password);
								updateEtu.updateEtudiant(nomEtu, prenomEtu, newEmailEtu, telEtu, emailEtu);
								System.out.println("Etudiant mis à jour avec succes");
								break;

							case 4:
								System.out.print("Entrez l'email de l'étudiant: ");
								donnees.nextLine();
								emailEtu = donnees.next();
								SuppressionEtudiant suppEtu = new SuppressionEtudiant(user, password);
								suppEtu.suppressionEtudiant(emailEtu);
								System.out.println("Etudiant supprimé avec succes");
								break;
						
							default:
								break;
						}
						break;

					case 2:
						affichageMenuCours();
						choixC = donnees.nextInt();
						switch (choixC) {
							case 1:
								System.out.println("Tous les cours: ");
								AfficherCours affCours = new AfficherCours(user, password);
								affCours.afficherCours();
								break;

							case 2:
								System.out.print("Entrez le nom du cours: ");
								donnees.nextLine();
								nomCours = donnees.nextLine();
								System.out.print("Entrez sa description: ");
								descriptionCours = donnees.nextLine();
								AjouterCours ajoutCours = new AjouterCours(user, password);
								ajoutCours.ajouterCours(nomCours, descriptionCours);
								break;
							
							case 3:
								System.out.print("Entrez l'ancien nom du cours:");
								donnees.nextLine();
								nomCours = donnees.nextLine();
								// System.out.println(nomCours);
								System.out.print("Entrez le nouveau nom du cours:");
								newNomCours = donnees.nextLine();
								System.out.print("Entrez sa description:");
								descriptionCours = donnees.nextLine();
								UpdateCours updateCours = new UpdateCours(user, password);
								updateCours.updateCours(nomCours, descriptionCours, newNomCours);
								break;

							case 4:
								System.out.print("Entrez le nom du cours: ");
								donnees.nextLine();
								nomCours = donnees.nextLine();
								SuppressionCours suppCours = new SuppressionCours(user, password);
								suppCours.suppressionCours(nomCours);
								break;
						
							default:
								break;
						}
						break;
				
					default:
						break;
				}
				System.out.println();
			} while(choixP == 1 || choixP == 2);
		} else {
			System.out.println("Connexion échouée. Veuillez réessayer.");
		}
		
		donnees.close();
    }
	public static void affichageMenuPrincipal() {
		System.out.println("Choisissez une option:");
        System.out.println("1. CRUD sur les étudiants");
        System.out.println("2. CRUD sur les cours");
        System.out.println("Autre. Quitter");
        System.out.print("Votre choix: ");
	}
	public static void affichageMenuEtudiants() {
		System.out.println("Choisissez une option pour les étudiants:");
        System.out.println("1. Afficher tous les étudiants");
        System.out.println("2. Ajouter un étudiant");
        System.out.println("3. Modifier un étudiant");
        System.out.println("4. Supprimer un étudiant");
        System.out.println("Autre. Retour au menu principal");
        System.out.print("Votre choix: ");
	}
	public static void affichageMenuCours() {
		System.out.println("Choisissez une option pour les cours:");
        System.out.println("1. Afficher tous les cours");
        System.out.println("2. Ajouter un cours");
        System.out.println("3. Modifier un cours");
        System.out.println("4. Supprimer un cours");
        System.out.println("Autre. Retour au menu principal");
        System.out.print("Votre choix: ");
	}
}

