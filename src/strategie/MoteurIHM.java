package strategie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.sun.prism.paint.Color;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
	public File fileAvancement = new File("ressources/rond_avancement.png");
	public Image imgAvancement;
	
	public float nbSemaines = 0;
	
	private VBox creerResume(int id) {
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
		for(Tacheclass t : (Tacheclass[]) desc.getSequence()[id].taches) {
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
			partie.tourSemaine(vj);
			nbSemaines++;
			imgAvancement = null;
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
					if(tache.getId() != "8") {
					jouerEtape(vj);
					} else {
						jouerScore(vj);
					}
				
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public class EventPasserSemainePara implements EventHandler<MouseEvent> {
		public Collection<Tache> listeTache; 
		public ArrayList<VBox> listeVbox;
		public int avancementMax = 0;
		public int dureeMax = 0;
		
		public EventPasserSemainePara(Collection<Tache> t, ArrayList<VBox> liste) {
			this.listeTache = t; 
			this.listeVbox = liste;
			
		}
		
		public void handle(MouseEvent event) {
			nbSemaines++;
			partie.tourSemaine(vj);
			try { imgAvancement = new Image(fileAvancement.toURI().toURL().toString());
			} catch (MalformedURLException e) {e.printStackTrace();}
			int indice = -1;
			for(Tache t : listeTache) {
				if(t.getDureeInitiale() > dureeMax) {
					dureeMax = t.getDureeInitiale();
				}
				if(t.getAvancement() > avancementMax) {
					avancementMax = t.getAvancement();
				}
			}
			for(Tache t : listeTache) {
				indice++;
				VBox current = listeVbox.get(indice);
				VBox semaines = (VBox) current.getChildren().get(1);
				Canvas ronds = (Canvas)semaines.getChildren().get(0);
				GraphicsContext gc = ronds.getGraphicsContext2D();
				Label avancement = (Label)semaines.getChildren().get(1);
				if(t.getAvancement() < t.getDureeInitiale()) {
					t.avancer();
				}
					if(t.getAvancement() <= t.getDureeInitiale()) {
						if(t.getId() != "7") {
						if(t.getAvancement() == 1) {gc.drawImage(imgAvancement, 50, 10);}
						else if(t.getAvancement() == 2) {gc.drawImage(imgAvancement, 180, 10);}
						else if(t.getAvancement() == 3) {gc.drawImage(imgAvancement, 310, 10);}
						} else {
							if(t.getAvancement() == 1) {gc.drawImage(imgAvancement, 10, 10);}
							else if(t.getAvancement() == 2) {gc.drawImage(imgAvancement, 105, 10);}
							else if(t.getAvancement() == 3) {gc.drawImage(imgAvancement, 200, 10);}
						}
						avancement.setText("Avancement: "+t.getAvancement()+" / "+t.getDureeInitiale()); 
				}
					try {
						if(avancementMax == dureeMax) {
							partie.passerTour();
							if(partie.getTour() == 4 || partie.getTour() == 7) {
							System.out.println("TOUR :" + partie.getTour());
							jouerTest(vj);
							} else {
								jouerJalon(vj, 6);
								scene = new Scene(jalon);
								stage.setScene(scene);
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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
	
	
	public void jouerEtape(VueJoueurs vue) throws Exception { 
		Tacheclass avant = null;
		avant = (Tacheclass) desc.getTacheById(String.valueOf(partie.getTour()));
		//Si on est au-dessus du tour 0 et que la tache précédente à des successeurs
		partie.tourSemaine(vj);
		if(partie.getTour() > 0 && avant.getSuccesseurs().size() > 1 && !avant.getSuccesseurs().isEmpty()) {
			HBox parallele = new HBox();  
			ArrayList<VBox> listeVbox = new ArrayList<>();
			ArrayList<Tache> tacheVue = new ArrayList<Tache>();
			for(Tache t : avant.getSuccesseurs()) {
				tacheVue.add(vue.getJoueur().getRealisation(t.getId()).getTache());
			}
			
			EventPasserSemainePara para = new EventPasserSemainePara(tacheVue, listeVbox);
			boolean suppr = false;
			for(Tache t : tacheVue) {
				IHMTache temp = new IHMTache((Tacheclass)t, vj, partie.getAleas());
				System.out.println(t.getDureeInitiale());
				VBox tache = temp.creerIHMSemaine();
				listeVbox.add(tache);
				if(t.getId() == "2" || t.getId() == "4" || t.getId() == "5" || t.getId() == "7") {
					tache.getChildren().remove(temp.passer);
				}
				temp.passer.setOnMouseClicked(para);
				parallele.getChildren().add(tache);
			}
			scene = new Scene(parallele);
			stage.setScene(scene);
		} else if(partie.getTour() == 0) {
			constructeur = new IHMTache((Tacheclass) desc.getTacheById(String.valueOf(partie.getTour()+1)), vj, partie.getAleas());
			semaine = new VBox();
			semaine = constructeur.creerIHMSemaine();
			EventPasserSemaine event = new EventPasserSemaine(constructeur.tache, constructeur.gc, constructeur.avancement);
			constructeur.passer.setOnMouseClicked(event);
			System.out.println("Avancement tâche: " + constructeur.tache.getAvancement());
			System.out.println("Durée initiale Tâche: " + constructeur.tache.getDureeInitiale());
			scene = new Scene(semaine);
			stage.setScene(scene);
			} else if(partie.getTour() == 7){
				constructeur = new IHMTache((Tacheclass) desc.getTacheById("8"), vj, partie.getAleas());
				semaine = new VBox(); 
				semaine = constructeur.creerIHMSemaine();
				EventPasserSemaine event = new EventPasserSemaine(constructeur.tache, constructeur.gc, constructeur.avancement);
				constructeur.passer.setOnMouseClicked(event);
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
			IHMTache bunny = new IHMTache((Tacheclass)t, vj, partie.getAleas());
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
		submit.setContent(creerResume(id));
		submit.setClosable(false);
		submit.setOnSelectionChanged(e -> {submit.setContent(creerResume(id));});
		jalon.getTabs().add(submit);
	}
	
	public void jouerTest(VueJoueur vue) throws IOException {
		File quizz = new File("src/Quizz.csv");
		FileReader fr = new FileReader(quizz);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> liste = new ArrayList<>();
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			liste.add(line);
		}
		Random rand = new Random(); 
		int alea = 0;
		while(alea == 0) {
			alea = rand.nextInt(liste.size());
		}
		//Creation du visuel pour le quizz
		VBox quizzBox = new VBox();
		Label title = new Label("QUIZZ");
		title.setFont(new Font("Arial", 38));
		title.setStyle("-fx-text-fill: blue");
		String aleaLigne = liste.get(alea);
		String[] decompose = aleaLigne.split(",");
		Label question = new Label(decompose[0]);
		question.setAlignment(Pos.CENTER);
		question.setMinWidth(quizzBox.getWidth());
		question.setFont(new Font("Arial", 24));
		question.setStyle("-fx-text-fill: blue");
		quizzBox.getChildren().addAll(title, question);
		for(int i = 1; i<decompose.length; i++) {
			Label reponse = new Label(i + " - " + decompose[i]);
			reponse.setOnMouseClicked(new EventQuizz(i));
			reponse.setFont(new Font("Arial", 22));
			quizzBox.getChildren().add(reponse);
		}
		quizzBox.setSpacing(30);
		quizzBox.setAlignment(Pos.CENTER);
		scene = new Scene(quizzBox);
		stage.setScene(scene);
	}
	
	public void jouerScore(VueJoueur vue) throws Exception {
		VBox scoreBox = new VBox(); 
		Label title = new Label("SCORE");
		title.setFont(new Font("Arial", 32));
		title.setStyle("-fx-text-fill: blue");
		Font font = new Font("Arial", 24);
		Label infos = new Label("Nombre de semaines: " + (nbSemaines-2) +"\tVotre caisse: " + vj.getCaisse() + "\tQualité: " + vj.getQualite()+"\n");
		float partInit = ((32 + (24-(nbSemaines-2)))*((float)vj.getCaisse()+20))/8000;
		String stringPartInit = String.valueOf(partInit);
		Label partMarcheInit = new Label("Part de marché initiale (sans la qualité): " + stringPartInit.substring(2, 4) + "%");
		Label partMarcheFinal = new Label("Part de marché finale (avec qualité): " + "A FAIRE");
		infos.setFont(font);
		partMarcheInit.setFont(font);
		partMarcheFinal.setFont(font);
		scoreBox.getChildren().addAll(title, infos, partMarcheInit, partMarcheFinal);
		scoreBox.setSpacing(30);
		scene = new Scene(scoreBox);
		stage.setScene(scene);
	}
	
	public class EventQuizz implements EventHandler<MouseEvent> {
		public int id;
		
		public EventQuizz(int i) {
			id = i;
		}
		
		public void handle(MouseEvent event) {
			if(id == 1) {
				System.out.println("GAGNER !");
				try {
					if(partie.getTour() == 4) {
						jouerJalon(vj, 6);
						scene = new Scene(jalon);
						stage.setScene(scene);
					} else {
					jouerEtape(vj);
					}
				} catch (Exception e) {e.printStackTrace();}
			} else {
				System.out.println("PERDU !");
				try {
					if(partie.getTour() == 4) {
						jouerJalon(vj, 6);
						scene = new Scene(jalon);
						stage.setScene(scene);
					} else {
					jouerEtape(vj);
					}
				} catch (Exception e) {e.printStackTrace();}
			}
			
		}
	}
	
	
	
}
