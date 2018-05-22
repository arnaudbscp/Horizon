package tours;

import description.Tache;

public class TourJalon extends Tour{

	public TourJalon(String type, int tour, Tache[] taches) {
		super(type, tour, taches);
		// TODO Auto-generated constructor stub
	}

	public String[] getIdJ() {
		String[] a= new String[8];
		int i=0;
		for(Tache b: taches) {
			a[0] = b.getId();
			
		}
		
		return a;
		
	}
	
}
