package tours;

import description.Tache;

public abstract class Tour {

	private String type;
	private int tour;
	private Tache[] taches;
	
	public Tour(String type, int tour, Tache[] taches) {
		
		this.type = type;
		this.tour = tour;
		this.taches = taches;
	}

	public String getType() {
		return type;
	}

	public int getTour() {
		return tour;
	}

	public Tache[] getTaches() {
		return taches;
	}
	
	
	
}
