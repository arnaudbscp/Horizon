package description;

public class Aleas implements Alea{
	
	private TypeAlea typeA;
	private int grav;
	private Couleur color;
	
	public Aleas(TypeAlea type, int grav, Couleur color) {
		
		this.typeA=type;
		this.grav=grav;
		this.color=color;
	}
	public int getGravite() {
		return this.grav;
	}
	public TypeAlea getType() {
		return this.typeA;
	}
	
}
