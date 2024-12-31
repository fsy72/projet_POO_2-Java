package projet_java1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionCSV {
    public static List<Contact> chargerContacts(String cheminFichier) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(",");
                if (donnees.length == 4) {
                    contacts.add(new Contact(donnees[0], donnees[1], donnees[2], donnees[3]));
                }
            }
        }
        return contacts;
    }

    public static void sauvegarderContacts(String cheminFichier, List<Contact> contacts) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Contact contact : contacts) {
                bw.write(contact.toCSV());
                bw.newLine();
            }
        }
    }
    
}