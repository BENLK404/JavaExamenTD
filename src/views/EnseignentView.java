package views;

import java.util.Scanner;

public class EnseignentView {
    private Scanner scanner;

    public EnseignentView() {
        this.scanner = scanner;
    }

    public void enseignantsChoice(){
        System.out.println("------------------------------------------------------");
        System.out.println("                   Option Enseignant                  ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter un enseignent");
        System.out.println("2. Modifier un enseignent");
        System.out.println("3. Supprimer un enseignent");
        System.out.println("4. Afficher les enseignent");
        System.out.println("0. Retour au menu");
    }
    public String[] demanderInformationsEnseignent(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                          Information de l'enseignent                           ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        String telephone = scanner.nextLine();
        System.out.print("Email(...@gmail.com): ");
        String email = scanner.nextLine();
        System.out.print("Matière: ");
        String matiere = scanner.nextLine();
        return new String[]{nom, prenom, telephone, email, matiere};
    }
}
