package description;

import java.util.Collection;

public interface Tache {
	
	public int coutAcceleration();
	public Alea getAlea(Couleur couleur);
	public String getDescription();
	public int getDureeInitiale();
	public int getDureeMax();
	public String getId();
	public Collection<Tache> getPredecesseurs();
	public Collection<Tache> getSuccesseurs();
	public int getAvancement();
	public void avancer();


}
