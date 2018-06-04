package strategie;

import description.Description;
import description.Tache;
import description.Tacheclass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;

public class CreerIHM extends Application {

	public TabPane jalon;
	public Description desc;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage) throws Exception {
		desc = new Description();
		IHMTache tache = new IHMTache((Tacheclass)desc.getDebut());
		VueJoueurs vj = new VueJoueurs("Samuel");
		for(Tour t : desc.getSequence()) {
			switch(t.getType()) {
			case "Jalon":
				jouerJalon(vj);
				break;
			case "Semaine":
				jouerEtape(vj);
				break;
			case "Quizz": 
				jouerTest(vj);
			}
		}
		
		Scene scene = new Scene(jalon);
		stage.setTitle("Jalon");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void jouerEtape(VueJoueur vue) {
		
	}
	
	public void jouerJalon(VueJoueur vue) {
		jalon = new TabPane(); 
		Tache[] tab = null;
		for(Tour t : desc.getSequence()) {
			if(t.getType() == "Jalon" && t.getTour() == 0) {
				tab = t.taches;
			}
		}
		for(Tache t : tab) {
			IHMTache bunny = new IHMTache((Tacheclass)t);
			Tab onglet = new Tab();
			onglet.setText("Tâche " + t.getId());
			try {
				onglet.setContent(bunny.creerIHM());
			} catch (Exception e) {e.printStackTrace();}
			onglet.setClosable(false);
			jalon.getTabs().add(onglet);
		}
	}
	
	public void jouerTest(VueJoueur vue) {
		
	}
	
}