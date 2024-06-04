package views;

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
        System.out.print("Nom de l'étudiant: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom de l'étudiant : ");
        String prenom = scanner.nextLine();
        System.out.print("Evalution: ");
        String evalution = scanner.nextLine();
        System.out.print("Note : ");
        String note = scanner.nextLine();
        return new String[]{nom, prenom,evalution, note};
    }
}
