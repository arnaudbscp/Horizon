package strategie;

import java.io.File;
import java.net.MalformedURLException;

import com.sun.prism.paint.Color;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import partie.Realisation;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;

public class MoteurIHM extends Application {

	public TabPane jalon;
	public Description desc;
	public VueJoueurs vj;
	public VBox resume;
	public static Label caisse;
	
	private VBox creerResume() {
		VBox resume = new VBox();
		Font font = new Font("Arial", 20);
		File billetFile = new File("ressources/billet.png");
		Button valider = new Button();
		valider.setText("Valider décisions");
		Image billet = null;
		ImageView iv = null;
		try {
			billet = new Image(billetFile.toURI().toURL().toString());
			iv = new ImageView(billet);
		} catch (MalformedURLException e) {	e.printStackTrace();}
		caisse = new Label();
		Label init = new Label("Caisse initiale: 300€\n\n");
		Label op = new Label();
		for(Tacheclass t : (Tacheclass[]) desc.getSequence()[0].taches) {
			int coutTache = 0;
			int cptProtec = 0;
			Label onOff = new Label(); 
			op.setText(op.getText()+"- Tâche "+t.getId()+" ");
			if(vj.getJoueur().getRealisation(t.getId()).estAccelerer()) {
				coutTache += t.coutAcceleration();
				op.setText(op.getText()+"Accélération ON  et ");
			} else {
				op.setText(op.getText()+"Accélération OFF et ");
			}
			if(vj.getJoueur().getRealisation(t.getId()).isProtec(Couleur.ROUGE)) { cptProtec++; coutTache += 10;}
			if(vj.getJoueur().getRealisation(t.getId()).isProtec(Couleur.JAUNE)) { cptProtec++; coutTache += 10;}
			if(vj.getJoueur().getRealisation(t.getId()).isProtec(Couleur.VERT)) { cptProtec++; coutTache += 10;}
			op.setText(op.getText()+" "+cptProtec+" protections soit: " + coutTache + "€\n");
		}
		caisse.setText("\nCaisse actuelle: " + vj.getCaisse()+"€");
		init.setFont(font);
		op.setFont(font);
		caisse.setFont(font);
		resume.getChildren().addAll(iv, init, op, caisse, valider);
		resume.setMargin(iv, new Insets(10, 0, 0, 250));
		resume.setMargin(init, new Insets(0, 0, 0, 200));
		resume.setMargin(op, new Insets(0, 0, 0, 35));
		resume.setMargin(caisse, new Insets(-5, 0, 0, 200));
		resume.setMargin(valider, new Insets(15, 0, 0, 240));
		return resume;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage) throws Exception {
		desc = new Description();
		vj = new VueJoueurs("Samuel");
		IHMTache tache = new IHMTache((Tacheclass)desc.getDebut(), vj);
		for(Tour t : desc.getSequence()) {
			if(t.getTour() == 0) {
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
			IHMTache bunny = new IHMTache((Tacheclass)t, vj);
			Tab onglet = new Tab();
			onglet.setText("Tâche " + t.getId());
			try {
				onglet.setContent(bunny.creerIHM());
			} catch (Exception e) {e.printStackTrace();}
			onglet.setClosable(false);
			jalon.getTabs().add(onglet);
		}
		
		Tab submit = new Tab(); 
		submit.setText("Résumé");
		submit.setContent(creerResume());
		submit.setClosable(false);
		submit.setOnSelectionChanged(e -> {submit.setContent(creerResume());});
		jalon.getTabs().add(submit);
	}
	
	public void jouerTest(VueJoueur vue) {
		
	}
	
	
	
}
