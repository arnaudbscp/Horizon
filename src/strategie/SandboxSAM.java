package strategie;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import description.Couleur;
import description.Tache;
import description.Description;
import partie.VueJoueurs;
import partie.Partie;
import tours.Tour;

public class SandboxSAM {
	
	public static void main(String[] args) {
		Description description = new Description();
		Tour[] sequence = description.getSequence();
		Partie partie = new Partie(description, "Joueur");
		ArrayList<VueJoueurs> liste = partie.getVueJoueurs();
		
		Tache[] taches = null;
		for (Tour t : description.getSequence()) {
			if (t.getType() == "Jalon") {
				taches = t.getTaches();
			}
		}
		// On va appliquer les décisions pour chaque tâche et pour chaque VueJoueur de
		// la liste
		for (Tache x : taches) {
			for (VueJoueurs v : liste) {
					// On stock les décision du joueur dans des int (YES = 0, NO = 1)
					int decisionAccel = JOptionPane.showConfirmDialog(null, "Accélérer la tâche " + x.getId() + "?",
							"Tâche " + x.getId(), JOptionPane.YES_NO_OPTION);
					int decisionProtRouge = JOptionPane.showConfirmDialog(null, "Protéger l'aléa rouge ?",
							"Tâche " + x.getId(), JOptionPane.YES_NO_OPTION);
					int decisionProtJaune = JOptionPane.showConfirmDialog(null, "Protéger l'aléa jaune ?",
							"Tâche " + x.getId(), JOptionPane.YES_NO_OPTION);
					int decisionProtVert = JOptionPane.showConfirmDialog(null, "Protéger l'aléa vert ?",
							"Tâche " + x.getId(), JOptionPane.YES_NO_OPTION);
					// On réalise ou non les décisions prises par le joueur (penser à ajouter les
					// dépenses)
					if (decisionAccel == 0) {
						v.setAcceleration(x.getId(), true);
					}
					if (decisionProtRouge == 0) {
						v.setProtection(x.getId(), Couleur.ROUGE);
					}
					if (decisionProtJaune == 0) {
						v.setProtection(x.getId(), Couleur.JAUNE);
					}
					if (decisionProtVert == 0) {
						v.setProtection(x.getId(), Couleur.VERT);
					}
			}
		}
	}
}


