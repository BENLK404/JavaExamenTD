package controllers;

import javafx.collections.ObservableList;
import models.*;
import tools.Tools;
import views.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static models.Enseignent.*;
import static models.Etudiant.selectEtudientsFromEvaluation;
import static models.Note.addNote;
import static models.Note.selectIdEvaluation;

public class MyController {
    private final MenuPrincipale menuPrincipale;
    private final EtudiantView etudiantView;
    private final LaClasseView laClasseView;
    private final EnseignentView enseignentView;
    private final NoteView noteView;
    private final EvaluationView evaluationView;
    private final MatiereView matiereView;

    public MyController() {
        matiereView = new MatiereView();
        menuPrincipale = new MenuPrincipale();
        etudiantView = new EtudiantView();
        laClasseView = new LaClasseView();
        noteView = new NoteView();
        evaluationView = new EvaluationView();
        enseignentView = new EnseignentView();
    }

    public void run() throws SQLException {
        boolean quitter = false;
        while (!quitter) {
            menuPrincipale.afficherMenuPrincipale();
            int choix = MenuPrincipale.lireChoix();
            switch (choix) {
                case 1:
                    gererEtudiants();
                    break;
                case 2:
                    gererClasses();
                    break;
                case 3:
                    gererEvaluations();
                    break;
                case 4:
                    gererEnseignants();
                    break;
                case 5:
                    gererNotes();
                    break;
                case 6:
                    gererMatieres();
                    break;
                case 7:
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix non valide, veuillez réessayer.");
                    break;
            }
        }
    }

    private void gererEtudiants() throws SQLException {
        etudiantView.etudiantChoice();
        int etudiantChoix = MenuPrincipale.lireChoix();
        switch (etudiantChoix) {
            case 1:
                ajouterEtudiant();
                break;
            case 2:
                modifierEtudiant();
                break;
            case 3:
                supprimerEtudiant();
                break;
            case 4:
                afficherEtudiantParNom();
                break;
            case 5:
                afficherTousLesEtudiants();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void gererClasses() throws SQLException {
        laClasseView.classeChoice();
        int classeChoix = MenuPrincipale.lireChoix();
        switch (classeChoix) {
            case 1:
                ajouterClasse();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void gererEvaluations() throws SQLException {
        evaluationView.evaluationsChoice();
        int evaluationsChoix = MenuPrincipale.lireChoix();
        switch (evaluationsChoix) {
            case 1:
                ajouterEvaluation();
                break;
            case 3:
                supprimerEvaluation();
                break;
            case 4:
                afficherToutesLesEvaluations();
                break;
            case 5:
                gererClassements();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void gererClassements() throws SQLException {
        evaluationView.classementEvaluationsChoice();
        int classementsChoix = MenuPrincipale.lireChoix();
        switch (classementsChoix) {
            case 1:
                afficherClassementGeneral();
                break;
            case 2:
                afficherClassementParMatiere();
                break;
            case 3:
                afficherClassementParClasse();
                break;
            case 4:
                gererEvaluations();
                break;
            case 0:
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void gererEnseignants() throws SQLException {
        enseignentView.enseignantsChoice();
        int enseignentChoix = MenuPrincipale.lireChoix();
        switch (enseignentChoix) {
            case 0:
                break;
            case 1:
                ajouterEnseignent();
                break;
            case 2:
                modifierEnseignent();
                break;
            case 3:
                supprimerEnseignant();
                break;
            case 4:
                Enseignent.getAllEnseignants();
                gererEnseignants();
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void gererNotes() throws SQLException {
        noteView.notesChoice();
        int noteChoix = MenuPrincipale.lireChoix();
        switch (noteChoix) {
            case 0:
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
            case 1:
                modifierNote();
                break;
            case 4:
                Note.displayNote();
                break;
        }
    }

    private void gererMatieres() throws SQLException {
        matiereView.matiereChoice();
        int matiereChoix = MenuPrincipale.lireChoix();
        switch (matiereChoix) {
            case 0:
                break;
            case 1:
                ajouterMatiere();
                break;
            case 2:
                modifierMatiere();
                break;
            case 3:
                supprimerMatiere();
                break;
            case 4:
                afficherMatiere();
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
                break;
        }
    }

    private void ajouterEtudiant() throws SQLException {
        String[] infosEtudiant = etudiantView.demanderInformationsEtudiant();
        if (!Tools.checkIfExists("classes", "nom_classe", infosEtudiant[7])) {
            System.out.println(" ");
            Tools.textInRed("La classe " + infosEtudiant[7] + " n'existe pas");
        } else {
            Etudiant etudiant = new Etudiant(0, infosEtudiant[0], infosEtudiant[1], infosEtudiant[2], infosEtudiant[3], infosEtudiant[4], infosEtudiant[5], LocalDate.parse(infosEtudiant[6]), LaClasse.getIdClasseByName(infosEtudiant[7]));
            etudiant.addEtudiant();
            gererEtudiants();
        }
    }

    private void modifierEtudiant() throws SQLException {
        String[] infosUpdateEtudiant = etudiantView.demanderInformationsUpdateEtudiant();
        Etudiant updateEtudiant = new Etudiant();
        updateEtudiant.updateEtudiant(Integer.parseInt(infosUpdateEtudiant[0]), infosUpdateEtudiant[1], infosUpdateEtudiant[2], infosUpdateEtudiant[3], infosUpdateEtudiant[4], infosUpdateEtudiant[5], infosUpdateEtudiant[6], LocalDate.parse(infosUpdateEtudiant[7]), LaClasse.getIdClasseByName(infosUpdateEtudiant[8]));
        gererEtudiants();
    }

    private void supprimerEtudiant() throws SQLException {
        String[] infosDeleteEtudiant = etudiantView.demanderInformationsDeleteEtudiant();
        Etudiant deleteEtudiant = new Etudiant();
        deleteEtudiant.deleteEtudiant(infosDeleteEtudiant[0], infosDeleteEtudiant[1]);
        gererEtudiants();
    }

    private void afficherEtudiantParNom() throws SQLException {
        String[] infosAfficheEtudiant = etudiantView.demanderInformationsDeleteEtudiant();
        Etudiant etudiantAffiche = new Etudiant();
        etudiantAffiche.getEtudiantByName(infosAfficheEtudiant[0], infosAfficheEtudiant[1]);
        gererEtudiants();
    }

    private void afficherTousLesEtudiants() throws SQLException {
        ObservableList<Etudiant> etudiantObservableList = Etudiant.displayAllEtudiant();
        EtudiantView.afficherEtudiant(etudiantObservableList);
        System.out.println(" ");
        gererEtudiants();
    }

    private void ajouterClasse() {
        String[] infosClass = laClasseView.demanderInformationsClasse();
        LaClasse laClasse = new LaClasse();
    }

    private void ajouterEvaluation() throws SQLException {
        String[] infosEvaluation = evaluationView.demanderInformationsAddEvaluation();
        if (!Tools.checkIfExists("matieres", "nom_matiere", infosEvaluation[4])) {
            System.out.println(" ");
            Tools.textInRed("La Matiere " + infosEvaluation[4] + " n'existe pas");
        } else {
            Evaluation evaluation = new Evaluation(infosEvaluation[0], infosEvaluation[1], LocalDate.parse(infosEvaluation[2]), infosEvaluation[3], Matiere.getIdMatiereByName(infosEvaluation[4]));
            evaluation.addEvaluation();
            gererEvaluations();
        }
    }

    private void supprimerEvaluation() throws SQLException {
        String[] infosEvaluationDelete = evaluationView.demanderInformationsDeleteEvaluation();
        if (!Tools.checkIfExists("matieres", "nom_matiere", infosEvaluationDelete[2])
                || !Tools.checkIfExists("evaluations", "nom_evaluation", infosEvaluationDelete[0])
                || !Tools.checkIfExists("evaluations", "type", infosEvaluationDelete[1])) {
            System.out.println(" ");
            Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
        } else {
            Evaluation.deleteEvaluation(infosEvaluationDelete[0], infosEvaluationDelete[1], infosEvaluationDelete[2]);
            gererEvaluations();
        }
    }

    private void afficherToutesLesEvaluations() throws SQLException {
        ObservableList<Evaluation> evaluationsObservableList = Evaluation.displayAllEvaluation();
        System.out.println(" ");
        EvaluationView.afficherEvaluations(evaluationsObservableList);
        System.out.println(" ");
        gererEvaluations();
    }

    private void afficherClassementGeneral() throws SQLException {
        String infosClassementGenaral = evaluationView.demanderInformationsClassementGeneral();
        if (!Tools.checkIfExists("evaluations", "type", infosClassementGenaral)) {
            System.out.println(" ");
            Tools.textInRed(" Type d'evaluation n'existe pas");
        } else {
            Classement.displayClassementGeneral(infosClassementGenaral);
            gererClassements();
        }
    }

    private void afficherClassementParMatiere() throws SQLException {
        String[] infosClassementMatiere = evaluationView.demanderInformationsClassementMatiere();
        if (!Tools.checkIfExists("matieres", "nom_matiere", infosClassementMatiere[0])
                || !Tools.checkIfExists("evaluations", "type", infosClassementMatiere[1])) {
            System.out.println(" ");
            Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
        } else {
            Classement.displayClassementByMatierType(infosClassementMatiere[0], infosClassementMatiere[1]);
            gererClassements();
        }
    }

    private void afficherClassementParClasse() throws SQLException {
        String[] infoClassementClasse = evaluationView.demanderInformationsClassementClasse();
        if (!Tools.checkIfExists("classes", "nom_classe", infoClassementClasse[0])
                || !Tools.checkIfExists("classes", "niveau", infoClassementClasse[2])
                || !Tools.checkIfExists("evaluations", "type", infoClassementClasse[1])) {
            System.out.println(" ");
            Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
        } else {
            Classement.displayClassementByClasseType(infoClassementClasse[0], infoClassementClasse[1], infoClassementClasse[2]);
            gererClassements();
        }
    }

    public void modifierNote() throws SQLException {
        String[] infosEvaluationNote = noteView.demanderInformationsAddNote();

        if (!Tools.checkIfExists("matieres", "nom_matiere",  infosEvaluationNote[0])
                || !Tools.checkIfExists("evaluations", "type", infosEvaluationNote[1])) {
            System.out.println(" ");
            Tools.textInRed(" Donnees incorrecte");
            gererNotes();
        } else {
            int idEtudiant = 0;
            int idEvaluation = selectIdEvaluation(infosEvaluationNote[0], infosEvaluationNote[1]);
            ObservableList<Etudiant> listEtudiant = selectEtudientsFromEvaluation(infosEvaluationNote[0], infosEvaluationNote[1]);
            for (Etudiant etudiant : listEtudiant) {
                String[] infoNote = noteView.saisirInfoNote(etudiant.getNomEtudiant(),etudiant.getPrenomEtudiant());
                Double note = Double.parseDouble(infoNote[0]);
                idEtudiant = etudiant.getIdEtudiant();
                addNote(note, idEtudiant, idEvaluation,infoNote[1]);
            }
            gererNotes();
        }
    }
    public void ajouterEnseignent() throws SQLException {
        String [] infosEnseignents = enseignentView.demanderInformationsAddEnseignent();
        if (!Tools.checkIfExists("matieres", "nom_matiere",  infosEnseignents[4])) {
            System.out.println(" ");
            Tools.textInRed(" Matière incorrecte");
            gererEnseignants();
        }else {
            int idMatiere = Matiere.getIdMatiereByName(infosEnseignents[4]);
            Enseignent enseignant  = new Enseignent(infosEnseignents[0],infosEnseignents[1],infosEnseignents[2],infosEnseignents[3],idMatiere);
            addEnseignant(enseignant);
        }
    }
    public void modifierEnseignent() throws SQLException {
        String[] infosEnseignents = enseignentView.demanderInformationsUpdateEnseignent();
        if (!Tools.checkIfExists("enseignants","id_enseignant",infosEnseignents[0])
        || !Tools.checkIfExists("matieres", "nom_matiere",  infosEnseignents[5])) {
            System.out.println(" ");
            Tools.textInRed(" ID de l'enseignant ou nom de matière incorrecte");
            gererEnseignants();
        }else {
            int idEnseignant = Integer.parseInt(infosEnseignents[0]);
            int idMatiere = Matiere.getIdMatiereByName(infosEnseignents[5]);
            Enseignent enseignent = new Enseignent(infosEnseignents[1],infosEnseignents[2],infosEnseignents[3],infosEnseignents[4],idMatiere);
            updateEnseignant(idEnseignant,enseignent);
        }
    }
    public void supprimerEnseignant() throws SQLException {
        String[] infosEnseignents = enseignentView.demanderInformationsDeleteEnseigent();
        if (!Tools.checkIfExists("enseignants","nom",infosEnseignents[0])
        || !Tools.checkIfExists("enseignants","prenom",infosEnseignents[1])) {
            System.out.println(" ");
            Tools.textInRed("Ces informations ne correspondent à aucun enseignant.");
            gererEnseignants();
        }else {
            deleteEnseignant(infosEnseignents[0],infosEnseignents[1]);
        }
    }
    public void ajouterMatiere() throws SQLException {
        String [] infosMatiere = matiereView.demanderInformationsAddMatiere();
        Matiere matiere = new Matiere(infosMatiere[0],infosMatiere[1]);
        matiere.addMatiere();
        gererMatieres();
    }
    public void modifierMatiere() throws SQLException {
        int idMatiere=0;
        String [] infosMatiere = matiereView.demanderInformationsUpdateMatiere();
        try {
            idMatiere = Integer.parseInt(infosMatiere[0]);
        }catch (NumberFormatException e){
            Tools.textInRed("L'ID de la matiere doit etre un entier");
            modifierMatiere();
        }
        if (!Tools.checkIfExists("matieres","id_matiere",infosMatiere[0])){
            System.out.println(" ");
            Tools.textInRed("ID de la matière incorrecte");
        }
        Matiere.updateMatiere( idMatiere,infosMatiere[1],infosMatiere[2]);
        gererMatieres();
    }
    public void  supprimerMatiere() throws SQLException {
        String infoMatiere = matiereView.demanderInformationsDeleteMatiere();
        if (!Tools.checkIfExists("matieres","nom_matiere",infoMatiere)) {
            System.out.println(" ");
            Tools.textInRed("La matière specifier n'existe pas");
        }else {
            Matiere.deleteMatiere(infoMatiere);
            gererMatieres();
        }
    }
    public void afficherMatiere() throws SQLException {
        MatiereView.afficherMatieres(Matiere.displayMatiere());
        gererMatieres();
    }
}
