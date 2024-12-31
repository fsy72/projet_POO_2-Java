package projet_java1;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionContacts extends JFrame {
    private List<Contact> contacts;
    private JTable table;
    private DefaultTableModel model;
    private final String[] colonnes = {"Nom", "Prénom", "Téléphone", "Email"};

    public GestionContacts() {
        setTitle("Gestion de Contacts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contacts = new ArrayList<>();
        try {
            contacts = GestionCSV.chargerContacts("contacts.csv");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des contacts.");
        }

        // Table
        model = new DefaultTableModel(colonnes, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel panel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter");
        JButton modifierButton = new JButton("Modifier");
        JButton supprimerButton = new JButton("Supprimer");
        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        add(panel, BorderLayout.SOUTH);

        // Listeners
        ajouterButton.addActionListener(e -> ajouterContact());
        modifierButton.addActionListener(e -> modifierContact());
        supprimerButton.addActionListener(e -> supprimerContact());

        afficherContacts();
    }

    private void afficherContacts() {
        model.setRowCount(0);
        for (Contact c : contacts) {
            model.addRow(new Object[]{c.getNom(), c.getPrenom(), c.getTelephone(), c.getEmail()});
        }
    }

    private void ajouterContact() {
        Contact contact = obtenirContactDepuisFormulaire(null);
        if (contact != null) {
            contacts.add(contact);
            afficherContacts();
        }
    }

    private void modifierContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Contact contact = contacts.get(selectedRow);
            Contact modifie = obtenirContactDepuisFormulaire(contact);
            if (modifie != null) {
                contacts.set(selectedRow, modifie);
                afficherContacts();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contact à modifier.");
        }
    }

    private void supprimerContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            contacts.remove(selectedRow);
            afficherContacts();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contact à supprimer.");
        }
    }

    private Contact obtenirContactDepuisFormulaire(Contact contactExistant) {
        JTextField nomField = new JTextField(contactExistant != null ? contactExistant.getNom() : "");
        JTextField prenomField = new JTextField(contactExistant != null ? contactExistant.getPrenom() : "");
        JTextField telephoneField = new JTextField(contactExistant != null ? contactExistant.getTelephone() : "");
        JTextField emailField = new JTextField(contactExistant != null ? contactExistant.getEmail() : "");

        Object[] message = {
                "Nom:", nomField,
                "Prénom:", prenomField,
                "Téléphone:", telephoneField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Contact", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return new Contact(nomField.getText(), prenomField.getText(), telephoneField.getText(), emailField.getText());
        }
        return null;
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e) {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING) {
            try {
                GestionCSV.sauvegarderContacts("contacts.csv", contacts);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des contacts.");
            }
        }
        super.processWindowEvent(e);
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionContacts app = new GestionContacts();
            app.setVisible(true);
        });
    }
}
