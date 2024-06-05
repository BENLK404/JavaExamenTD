package views;

import models.Etudiant;

import java.util.Scanner;

public class NoteView {
    private Scanner scanner;
    public NoteView() {
        scanner = new Scanner(System.in);
    }
    public void notesChoice(){
        System.out.println("------------------------------------------------------");
        System.out.println("                   Option Note                        ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter une note");
        System.out.println("2. Modifier une note");
        System.out.println("3. Supprimer une note");
        System.out.println("4. Afficher les note");
        System.out.println("0. Retour au menu");
        System.out.println(" ");

    }
    public String[] demanderInformationsNote(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                             Information de la note                             ");
        System.out.println("--------------------------------------------------------------------------------");

        String nom = scanner.nextLine();
        System.out.print("Matière : ");
        String matiere = scanner.nextLine();
        System.out.print("Type d'evalution: ");
        String type_evaluation = scanner.nextLine();

        return new String[]{matiere, type_evaluation};
    }
    public void  saisirInfoNote(String nom ,String prenom){
        System.out.print("Saisir la note de '"+nom+",'"+prenom+"'': ");
        String note = scanner.nextLine();
    }
}
