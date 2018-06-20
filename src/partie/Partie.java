package partie;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import description.Couleur;
import description.Description;
import description.TypeAlea;
import strategie.Strategie;
import tours.Tour;
import description.Tache;
import description.Tacheclass;

/**
 * 
 * @author bascopa
 *
 */

public class Partie {

	private Description description;
	private int tour;
	private String joueur;
	private ArrayList<VueJoueurs> liste;
	private Couleur[] aleas;
	private int indiceA =0;
	
	public ArrayList<VueJoueurs> getVueJoueurs() {
		return this.liste;
	}

	public Partie(Description d, String j) {
		liste = new ArrayList<>();
		this.description = d;
		this.joueur = j;
		liste.add(new VueJoueurs(j));
		for (VueJoueurs a : liste) {
			a.getJoueur().getRealisation("1").setEtat(Etat.IMMINENT);

		}
		aleas = new Couleur[8];
	}

	public DonneesJoueurs getDonneesJoueur(String joueur) {
		for (VueJoueurs j : liste) {
			if (j.getNom().equalsIgnoreCase(joueur)) {
				return j.getJoueur();
			}
		}
		return null;
	}

	public VueJoueurs getVueJoueur(String joueur) {
		for (VueJoueurs j : liste) {
			if (j.getNom().equalsIgnoreCase(joueur)) {
				return j;
			}
		}
		return null;
	}

	public void passerTour() {
		ArrayList<Realisation> l = getActu();

		for (Realisation r : l) {
			if (r.getEtat() == Etat.EN_COURS) {
				r.getTache().avancer();
				if (r.getTache().getDureeInitiale() == r.getTache().getAvancement()) {
					r.setEtat(Etat.TERMINEE);
					
				}
			}

		}
		tour++;
	}

	public int getTour() {
		return tour;
	}

	private void setImminent(Realisation r) {
		// TODO Auto-generated method stub
		Collection<Tache> a = r.getTache().getSuccesseurs();
		String[] b = null;
		int c = 0;
		for (Tache t : a) {
			b[c++] = t.getId();
		}

	}

	public ArrayList<Realisation> getActu() {
		ArrayList<Realisation> r = new ArrayList<>();
		for (int i = 0; i < liste.size(); i++) {
			for (Realisation rea : liste.get(i).getJoueur().getRealisation()) {
				r.add(rea);
			}
		}
		return r;
	}
	public void tourSemaine(VueJoueurs j) {
		ArrayList<Realisation> tache = j.getSemainesaAvancer();  
		for(Realisation r : tache) {
			if(r.getEtat().equals(Etat.IMMINENT)) {
				int id =Integer.parseInt(r.getTache().getId())-1;
				 if(aleas[id] == null) {
					 Couleur c = Couleur.tirage();
					 aleas[id] = c;
				}
				 j.tourSemaine(aleas[id], r);
				
			}
		}
		
		
		
		
	}
	

	public void tourJalon(int tour) {
		Tache[] taches = null;
		for (Tour t : description.getSequence()) {
			if (t.getTour() == tour) {
				taches = t.getTaches();
			}
		}
		// On va appliquer les décisions pour chaque tâche et pour chaque VueJoueur de
		// la liste
		for (Tache x : taches) {
			for (VueJoueurs v : this.liste) {
				System.out.println("DECISIONS A PRENDRE POUR LA TACHE " + x.getId());
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
						v.setProtection(x.getId(), Couleur.ROUGE, true);
					}
					if (decisionProtJaune == 0) {
						v.setProtection(x.getId(), Couleur.JAUNE, true);
					}
					if (decisionProtVert == 0) {
						v.setProtection(x.getId(), Couleur.VERT, true);
					}
			}
		}
	}
	
	public Couleur[] getAleas() {
		return aleas;
	}

}
