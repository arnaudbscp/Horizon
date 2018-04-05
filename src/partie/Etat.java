package partie;

public enum Etat {
	EN_COURS('E'),
	IMMINENT('I'),
	NON_ENTAMEE('N'),
	TERMINEE('T');
	
	private char c; 
	
	private Etat(char c) {
		this.c = c;
	}
	
	public char getChar() {
		return this.c;
	}
}
