package description;

public class Aleas implements Alea{
	private String TacheId;
	private String nom;
	private TypeAlea typeA;
	private int grav;
	
	public Aleas(String a, String b, TypeAlea type, int grav) {
		this.TacheId=a;
		this.nom=b;
		this.typeA=type;
		this.grav=grav;		
	}
	public int getGravite() {
		return this.grav;
	}
	public TypeAlea getType() {
		return this.typeA;
	}
	
}
