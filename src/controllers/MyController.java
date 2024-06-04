package controllers;

import javafx.collections.ObservableList;
import models.*;
import tools.Tools;
import views.*;

import java.sql.SQLException;
import java.time.LocalDate;


public class MyController {
    private final MenuPrincipale menuPrincipale;
    private final EtudiantView etudiantView;
    private final LaClasseView laClasseView;
    private EnseignentView enseignentView;
    private final NoteView noteView;
    private final EvaluationView evaluationView;
    private MatiereView matiereView;

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
        menuPrincipale.afficherMenuPrincipale();
        int choix = MenuPrincipale.lireChoix();
        while (!quitter) {
            switch (choix) {
                case 1:
                    etudiantView.etudiantChoice();
                    int etudiantChoix = MenuPrincipale.lireChoix();
                    switch (etudiantChoix){
                        case 1:
                            String[] infosEtudiant = etudiantView.demanderInformationsEtudiant();
                            if (!Tools.checkIfExists("classes","nom_classe",infosEtudiant[7])){
                                System.out.println(" ");
                                Tools.textInRed("La classe " + infosEtudiant[7] + " n'existe pas");
                            }else {
                                Etudiant etudiant = new Etudiant(0,infosEtudiant[0],infosEtudiant[1],infosEtudiant[2],infosEtudiant[3],infosEtudiant[4],infosEtudiant[5], LocalDate.parse(infosEtudiant[6]),LaClasse.getIdClasseByName(infosEtudiant[7]));
                                etudiant.addEtudiant();
                            }
                            break;
                        case 2:
                            String[] infosUpdateEtudiant = etudiantView.demanderInformationsUpdateEtudiant();
                            Etudiant updateEtudiant = new Etudiant();
                            updateEtudiant.updateEtudiant(Integer.parseInt(infosUpdateEtudiant[0]),infosUpdateEtudiant[1],infosUpdateEtudiant[2],infosUpdateEtudiant[3],infosUpdateEtudiant[4],infosUpdateEtudiant[5],infosUpdateEtudiant[6],LocalDate.parse(infosUpdateEtudiant[7]),LaClasse.getIdClasseByName(infosUpdateEtudiant[8]));
                            break;
                        case 3:
                            String[] infosDeleteEtudiant = etudiantView.demanderInformationsDeleteEtudiant();
                            Etudiant deleteEtudiant = new Etudiant();
                            deleteEtudiant.deleteEtudiant(infosDeleteEtudiant[0],infosDeleteEtudiant[1]);
                            break;
                        case 4:
                            String[] infosAfficheEtudiant = etudiantView.demanderInformationsDeleteEtudiant();
                            Etudiant etudiantAffiche = new Etudiant();

                            etudiantAffiche.getEtudiantByName(infosAfficheEtudiant[0],infosAfficheEtudiant[1]);
                            break;
                        case 0:
                            menuPrincipale.afficherMenuPrincipale();
                            choix = MenuPrincipale.lireChoix();
                            break;
                        case 5:
                            ObservableList<Etudiant> etudiantObservableList;
                            etudiantObservableList = Etudiant.displayAllEtudiant();
                            EtudiantView.afficherEtudiant(etudiantObservableList);
                            System.out.println(" ");
                            break;
                    }
                    break;
                case 2:
                    laClasseView.classeChoice();
                    int classeChoix = MenuPrincipale.lireChoix();
                    switch (classeChoix){
                        case 1:
                            String[] infosClass = laClasseView.demanderInformationsClasse();
                            LaClasse laClasse = new LaClasse();
                            break;
                        case 0:
                            menuPrincipale.afficherMenuPrincipale();
                            choix = MenuPrincipale.lireChoix();
                            break;
                    }
                    break;
                case 3:
                    evaluationView.evaluationsChoice();
                    int evaluationsChoix = MenuPrincipale.lireChoix();
                    switch (evaluationsChoix){
                        case 1:
                            String[] infosEvaluation = evaluationView.demanderInformationsAddEvaluation();
                            if (!Tools.checkIfExists("matieres","nom_matiere",infosEvaluation[4])){
                                System.out.println(" ");
                                Tools.textInRed("La Matiere " + infosEvaluation[4] + " n'existe pas");
                            }else {
                            Evaluation evaluation = new Evaluation(infosEvaluation[0],infosEvaluation[1],LocalDate.parse(infosEvaluation[2]),infosEvaluation[3], Matiere.getIdMatierClasseByName(infosEvaluation[4]));
                            evaluation.addEvaluation();
                            }
                            break;
                        case 0:
                            menuPrincipale.afficherMenuPrincipale();
                            choix = MenuPrincipale.lireChoix();
                            break;
                        case 3:
                            String [] infosEvaluationDelete = evaluationView.demanderInformationsDeleteEvaluation();
                            if (!Tools.checkIfExists("matieres","nom_matiere",infosEvaluationDelete[2])
                                ||!Tools.checkIfExists("evaluations","nom_evaluation",infosEvaluationDelete[0])
                                ||!Tools.checkIfExists("evaluations","type",infosEvaluationDelete[1])){
                                System.out.println(" ");
                                Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
                            }else {
                                Evaluation.deleteEvaluation(infosEvaluationDelete[0],infosEvaluationDelete[1],infosEvaluationDelete[2]);
                            }
                            break;
                        case 4:
                            ObservableList<Evaluation> evaluationsObservableList;
                            evaluationsObservableList = Evaluation.displayAllEvaluation();
                            System.out.println(" ");
                            EvaluationView.afficherEvaluations(evaluationsObservableList);
                            System.out.println(" ");
                            break;
                        case 5:
                            evaluationView.classementEvaluationsChoice();
                            int classementsChoix = MenuPrincipale.lireChoix();
                            switch (classementsChoix){
                                case 1:
                                    System.out.print("Option 1");
                                    break;
                                case 2:
                                    String[] infosClassementMatiere = evaluationView.demanderInformationsClassementMatiere();
                                    if (!Tools.checkIfExists("matieres","nom_matiere",infosClassementMatiere[0])
                                            ||!Tools.checkIfExists("evaluations","type",infosClassementMatiere[1])){
                                        System.out.println(" ");
                                        Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
                                    }else {
                                        Classement.displayClassementByMatierType(infosClassementMatiere[0],infosClassementMatiere[1]);
                                        evaluationView.classementEvaluationsChoice();
                                        classementsChoix = MenuPrincipale.lireChoix();

                                    }
                                    break;
                                case 3:
                                    String[] infoClassementClasse = evaluationView.demanderInformationsClassementClasse();
                                    if (!Tools.checkIfExists("classes","nom_classe",infoClassementClasse[0])
                                            ||!Tools.checkIfExists("classes","niveau",infoClassementClasse[2])
                                            ||!Tools.checkIfExists("evaluations","type",infoClassementClasse[1])){
                                        System.out.println(" ");
                                        Tools.textInRed(" Une ou l'ensemble des donnees saisie est erronee");
                                    }else {
                                        Classement.displayClassementByClasseType(infoClassementClasse[0],infoClassementClasse[1],infoClassementClasse[2]);
                                    }
                                    break;
                                case 4:
                                    evaluationView.evaluationsChoice();
                                    evaluationsChoix = MenuPrincipale.lireChoix();
                                    break;
                                case 0:
                                    menuPrincipale.afficherMenuPrincipale();
                                    choix = MenuPrincipale.lireChoix();
                                    break;
                            }
                            break;
                    }
                    break;
                case 4:
                    enseignentView.enseignantsChoice();
                    int enseignentChoix = MenuPrincipale.lireChoix();
                    switch (enseignentChoix){
                        case 0:
                            menuPrincipale.afficherMenuPrincipale();
                            choix = MenuPrincipale.lireChoix();
                            break;
                    }
                    break;
                case 5:
                    noteView.notesChoice();
                    MenuPrincipale.lireChoix();
                    break;
                case 6:
                    matiereView.matiereChoice();
                    MenuPrincipale.lireChoix();
                    break;
                case 7:
                    quitter = true;
                    break;
            }
        }
    }
}
