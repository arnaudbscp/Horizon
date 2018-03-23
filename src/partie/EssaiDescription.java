package partie;

import description.Description;
import description.Tache;
public class EssaiDescription {

	public static void main(String[] args) {
		Description d= new Description();
		
		System.out.println(d.getTacheById("1").getId());
	}
}
