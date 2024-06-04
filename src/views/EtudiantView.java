package views;

import javafx.collections.ObservableList;
import models.Etudiant;
import tools.Tools;

import java.sql.SQLException;
import java.util.Scanner;

public class EtudiantView {
    Scanner scanner;
    public EtudiantView() {
        scanner = new Scanner(System.in);
    }
    public  void etudiantChoice(){
        System.out.println("------------------------------------------------------");
          Tools.textInBlue("                   Option Étudiant                    ");
        System.out.println("------------------------------------------------------");


        System.out.println("1. Ajouter un etudiant");
        System.out.println("2. Modifier un etudiant");
        System.out.println("3. Supprimer un etudiant");
        System.out.println("4. Afficher un etudiant");
        System.out.println("5. Afficher les etudiants");
        System.out.println("0. Retour au menu");
        System.out.println(" ");
    }
    public String[] demanderInformationsEtudiant(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de l'étudiant                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Sexe(M/F): ");
        String sexe = scanner.nextLine();
        System.out.print("Date de naissance (YYYY-MM-DD): ");
        String dateDeNaissance = scanner.nextLine();
        System.out.print("Adresse: ");
        String adresse = scanner.nextLine();
        System.out.print("Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Email(...@gmail.com): ");
        String email = scanner.nextLine();
        System.out.print("Classe: ");
        String classe = scanner.nextLine();
        return new String[]{nom,prenom, sexe, adresse, email, telephone,dateDeNaissance,classe};

    }
    public String[] demanderInformationsUpdateEtudiant(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de l'étudiant                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("ID de l'étudient: ");
        String id = scanner.nextLine();
        System.out.print("Nouveau Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Nouveau Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Nouveau Sexe(M/F): ");
        String sexe = scanner.nextLine();
        System.out.print("Nouvelle Date de naissance (YYYY-MM-DD): ");
        String dateDeNaissance = scanner.nextLine();
        System.out.print("Nouvelle Adresse: ");
        String adresse = scanner.nextLine();
        System.out.print("Nouveau Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Nouveau Email(...@gmail.com): ");
        String email = scanner.nextLine();
        System.out.print("Nouvelle Classe: ");
        String classe = scanner.nextLine();
        return new String[]{id,nom,prenom, sexe, adresse, email, telephone,dateDeNaissance,classe};

    }
    public String[] demanderInformationsDeleteEtudiant(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de l'étudiant                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();

        return new String[]{nom,prenom};
    }

    public static void afficherEtudiant(ObservableList<Etudiant> listEtudiantsAffiche) throws SQLException {
        System.out.printf("%-10s %-20s %-20s %-10s %-20s %-20s %-15s %-10s %-40s\n",
                "ID", "Nom", "Prenom", "Sexe", "Classe","Adresse", "Telephone", "Date Naissance", "Email");
        printSeparator(160);
        for (Etudiant etudiant : listEtudiantsAffiche) {
            System.out.println(etudiant);
        }
    }
    private static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

}
