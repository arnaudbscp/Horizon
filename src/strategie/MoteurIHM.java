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
import partie.Partie;
import partie.Realisation;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;

public class MoteurIHM extends Application {
	
	public Scene scene;
	public Stage stage;
	public Partie partie;

	//Attributs et éléments nécessaires pour la création de l'IHM Jalon
	public TabPane jalon;
	public Description desc;
	public VueJoueurs vj;
	public VBox resume;
	public static Label caisse;
	
	//Attributs et éléments nécessaires pour la création d'une IHM Semaine
	public VBox semaine;
	public Button valider;
	
	private VBox creerResume() {
		VBox resume = new VBox();
		Font font = new Font("Arial", 20);
		File billetFile = new File("ressources/billet.png");
		valider = new Button();
		valider.setText("Valider décisions");
		valider.setOnMouseClicked(e -> {try {
			jouerEtape(vj);
		} catch (Exception e1) {e1.printStackTrace();}});
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

	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		desc = new Description();
		partie = new Partie(desc, "Samuel");
		vj = partie.getVueJoueur("Samuel");
		scene = null;
		
		Font font = new Font("Arial", 32);
		VBox menu = new VBox(); 
		Label horizon = new Label("Horizon v2.0");
		Button jouer = new Button("JOUER");
		jouer.setFont(font);
		Button quitter = new Button("QUITTER");
		quitter.setFont(font);
		menu.getChildren().addAll(horizon, jouer, quitter);
		menu.setSpacing(30);
		jouer.setOnMouseClicked(e -> {jouerJalon(vj, 0); scene = new Scene(jalon); stage.setScene(scene);});
		quitter.setOnMouseClicked(e -> {primaryStage.close();});
		
		scene = new Scene(menu);
		stage.setTitle("Horizon v2.0");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void jouerEtape(VueJoueur vue) throws Exception { 
		IHMTache constructeur = new IHMTache((Tacheclass) desc.getTacheById(String.valueOf(partie.getTour()+1)), vj, partie);
		semaine = new VBox();
		semaine = constructeur.creerIHMSemaine();
		scene = new Scene(semaine);
		stage.setScene(scene);
		}
		
	
	public void jouerJalon(VueJoueur vue, int id) {
		jalon = new TabPane(); 
		Tache[] tab = null;
		for(Tour t : desc.getSequence()) {
			if(t.getType() == "Jalon" && t.getTour() == id) {
				tab = t.taches;
			}
		}
		for(Tache t : tab) {
			IHMTache bunny = new IHMTache((Tacheclass)t, vj, partie);
			Tab onglet = new Tab();
			onglet.setText("Tâche " + t.getId());
			try {
				onglet.setContent(bunny.creerIHMJalon());
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
