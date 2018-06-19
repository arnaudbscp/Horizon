package strategie;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.sun.prism.paint.Color;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import partie.Partie;
import partie.Realisation;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;

public class MoteurIHM extends Application {
	
	public Scene scene;
	public Stage stage;
	public static Partie partie;
	
	//Attributs et éléments nécessaires pour la création de l'IHM Jalon
	public TabPane jalon;
	public Description desc;
	public VueJoueurs vj;
	public VBox resume;
	public static Label caisse;
	
	//Attributs et éléments nécessaires pour la création d'une IHM Semaine
	public VBox semaine;
	public Button valider;
	
	public IHMTache constructeur;
	
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
	
	public class EventPasserSemaine implements EventHandler<MouseEvent> {
		public Tacheclass tache;
		public GraphicsContext gc;
		public Label avancement;
		
		public EventPasserSemaine(Tacheclass t, GraphicsContext g, Label lab) {
			tache = t; 
			gc = g; 
			avancement = lab;
		}
		
		
		public void handle(MouseEvent event) {
			File fileAvancement = new File("ressources/rond_avancement.png");
			Image imgAvancement = null;
			try {
				imgAvancement = new Image(fileAvancement.toURI().toURL().toString());
			} catch (MalformedURLException e) {e.printStackTrace();}
			if(tache.getAvancement() < tache.getDureeInitiale()) {
			tache.avancer();
			}
			if(tache.getAvancement() <= tache.getDureeInitiale()) {
				if(tache.getAvancement() == 1) {gc.drawImage(imgAvancement, 50, 10);}
				else if(tache.getAvancement() == 2) {gc.drawImage(imgAvancement, 180, 10);}
				else if(tache.getAvancement() == 3) {gc.drawImage(imgAvancement, 310, 10);}
			avancement.setText("Avancement: "+tache.getAvancement()+" / "+tache.getDureeInitiale()); 
				}
			try {
				if(constructeur.tache.getAvancement() == constructeur.tache.getDureeInitiale()) {
					partie.passerTour();
					jouerEtape(vj);
				
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
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
		Label horizon = new Label("   Horizon v2.0   ");
		horizon.setFont(font);
		horizon.setStyle("-fx-text-fill: blue");
		Button jouer = new Button("JOUER");
		jouer.setFont(font);
		Button quitter = new Button("QUITTER");
		quitter.setFont(font);
		menu.getChildren().addAll(horizon, jouer, quitter);
		menu.setSpacing(40);
		menu.setMargin(horizon, new Insets(10, 0, 0, 0));
		menu.setMargin(jouer, new Insets(0, 0, 0, 60));
		menu.setMargin(quitter, new Insets(0, 0, 10, 40));
		jouer.setOnMouseClicked(e -> {jouerJalon(vj, 0); scene = new Scene(jalon); stage.setScene(scene);});
		quitter.setOnMouseClicked(e -> {primaryStage.close();});

		
		scene = new Scene(menu);
		stage.setTitle("Horizon v2.0");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void jouerEtape(VueJoueur vue) throws Exception { 
		ArrayList<Realisation> re = vue.getSemainesaAvancer();
		
		if(re.size()>1) {
			
			/*for(Realisation r : re) {
				IHM bunny = new IHMSemaine((Tacheclass)t, vj);
				Tab onglet = new Tab();
				onglet.setText("Tâche " + r.getTache().getId());
				try {
					onglet.setContent(bunny.creerIHMJalon());
				} catch (Exception e) {e.printStackTrace();}
				onglet.setClosable(false);
				jalon.getTabs().add(onglet);
			
			}*/
				
			
		}else {
		
			constructeur = new IHMTache((Tacheclass) desc.getTacheById(String.valueOf(partie.getTour()+1)), vj);
		
			semaine = new VBox();
			semaine = constructeur.creerIHMSemaine();
			EventPasserSemaine event = new EventPasserSemaine(constructeur.tache, constructeur.gc, constructeur.avancement);
			constructeur.passer.setOnMouseClicked(event);
		
			System.out.println("Avancement tâche: " + constructeur.tache.getAvancement());
			System.out.println("Durée initiale Tâche: " + constructeur.tache.getDureeInitiale());
		
		
			scene = new Scene(semaine);
			stage.setScene(scene);
		}
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
			IHMTache bunny = new IHMTache((Tacheclass)t, vj);
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
