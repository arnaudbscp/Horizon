package strategie;


import java.io.File;
import java.net.MalformedURLException;

import description.Alea;
import description.Aleas;
import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import description.TypeAlea;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import partie.DonneesJoueurs;
import partie.Partie;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;
import javafx.scene.input.MouseEvent;

public class IHMTache {

	public VueJoueurs joueur;
	public DonneesJoueurs donnees;
	public Tacheclass tache;
	public VBox main;
	public GraphicsContext gc2;
	public Label prix;
	public int cptClickAccel = 0;
	public int cptClickBouclier1 = 0;
	public int cptClickBouclier2 = 0;
	public int cptClickBouclier3 = 0;
	public ImageView ivBouclier1; 
	public ImageView ivBouclier2;
	public ImageView ivBouclier3;
	public ImageView ivCroix1;
	public ImageView ivCroix2;
	public ImageView ivCroix3;
	public Image bouclier_off;
	public File fileRondVide = new File("ressources/rond_vide.png");
	public File fileRondPlein = new File("ressources/rond_plein.png");
	public Image rondVide;
	public Image rondPlein;
	public Description desc;
	public TabPane jalon;
	public Canvas canvasSemaines = new Canvas(600, 130);
	public GraphicsContext gc = canvasSemaines.getGraphicsContext2D();
	public Button passer;
	public Label avancement;
	
	//Elements pour gérer les aléas
	public Couleur[] listeAleas;
	
	public IHMTache(Tacheclass t, VueJoueurs v, Couleur[] al) {
		this.tache = t;
		this.joueur = v;
		this.donnees = v.getJoueur();
		this.listeAleas = al;
	}
	

	class EventAcceleration implements EventHandler<MouseEvent> {
		public void handle(MouseEvent event) {
			cptClickAccel += 1;
			File accelFileClicked = new File("ressources/bouton_accelerer_clicked.png");
			Image accelImgClicked = null;
			try {
				accelImgClicked = new Image(accelFileClicked.toURI().toURL().toString());
			} catch (MalformedURLException e) {e.printStackTrace();}
			if(cptClickAccel == 1 || !(cptClickAccel % 2 == 0)) {
			gc2.drawImage(accelImgClicked, 0, 5);
			prix.setTextFill(Color.web("009c00"));
			donnees.getRealisation(tache.getId()).setAcceleration(true);
			donnees.depense(tache.coutAcceleration());
			donnees.getRealisation(tache.getId()).getTache().setDuree(tache.getDureeInitiale()-1);
			tache.setDuree(tache.getDureeInitiale()-1);
			System.out.println(tache.getDureeInitiale() +"   !  "+ donnees.getRealisation(tache.getId()).getTache().getDureeInitiale());
			MoteurIHM.caisse.setText("Votre caisse: " + joueur.getCaisse()); 
			} else {
				donnees.getRealisation(tache.getId()).setAcceleration(false);
				donnees.depense(-(tache.coutAcceleration()));
				//donnees.getRealisation(tache.getId()).reculer();
				tache.setDuree(tache.getDureeInitiale()+1);
			}
			redessinerSemaines(rondVide, rondPlein);
		}	 
	}
	
	class EventBouclierSurvol implements EventHandler<MouseEvent> {
		public ImageView iv; 
		
		public EventBouclierSurvol(ImageView iv) {
			this.iv = iv;
		}
		
		public void handle(MouseEvent event) {
			int cpt = 0;
			switch(iv.getId()) { 
			case "bouclier1": cpt = cptClickBouclier1; break; 
			case "bouclier2": cpt = cptClickBouclier2; break; 
			case "bouclier3": cpt = cptClickBouclier3;
			}
			if(cpt == 0 || cpt % 2 == 0) {
			File bouclierOn = new File("ressources/bouclier_on.png");
			Image bouclierOnImg = null;
			try {
				bouclierOnImg = new Image(bouclierOn.toURI().toURL().toString());
			} catch (MalformedURLException e) {	e.printStackTrace();}
			iv.setImage(bouclierOnImg);
			}
			}
		}
	
	class EventBouclierClick implements EventHandler<MouseEvent> {
		public ImageView iv1;
		public ImageView iv2;
		public Couleur couleurAlea;
		
		public EventBouclierClick(ImageView iv1, ImageView iv2) {
			this.iv1 = iv1;
			this.iv2 = iv2;
		}
		
		public void handle(MouseEvent event) {
			int cpt = 0;
			switch(iv1.getId()) { 
			case "bouclier1": cptClickBouclier1++; cpt = cptClickBouclier1; break; 
			case "bouclier2": cptClickBouclier2++; cpt = cptClickBouclier2; break; 
			case "bouclier3": cptClickBouclier3++; cpt = cptClickBouclier3;
			}
			
			if(cpt == 1 || !(cpt % 2 == 0)) {
				File bouclierOn = new File("ressources/bouclier_on.png");
				File tickFile = new File("ressources/tick.png");
				Image bouclierOnImg = null;
				Image tick = null;
				try {
					bouclierOnImg = new Image(bouclierOn.toURI().toURL().toString());
					tick = new Image(tickFile.toURI().toURL().toString());
				} catch (MalformedURLException e) {	e.printStackTrace();}
				switch(iv1.getId()) {
				case "bouclier1":
					couleurAlea = Couleur.ROUGE;
					break;
				case "bouclier2": 
					couleurAlea = Couleur.JAUNE;
					break;
				case "bouclier3":
					couleurAlea = Couleur.VERT;
				}
				donnees.getRealisation(tache.getId()).setProtection(couleurAlea, true);
				donnees.depense(10);
				MoteurIHM.caisse.setText("Votre caisse: " + joueur.getCaisse()); //A mettre à chaque dépense
				iv1.setImage(bouclierOnImg);
				iv2.setImage(tick);
			} else {
				iv1.setImage(bouclier_off);
				File croixFile = new File("ressources/croix.png");
				Image croix = null;
				try {
					croix = new Image(croixFile.toURI().toURL().toString());
				} catch (MalformedURLException e) {e.printStackTrace();}
				iv2.setImage(croix); 
				donnees.getRealisation(tache.getId()).setProtection(couleurAlea, false);
				donnees.depense(-10);
			}
		}
	}
	
	public VBox creerIHMJalon() throws Exception {
		rondVide = new Image(fileRondVide.toURI().toURL().toString());
		rondPlein = new Image(fileRondPlein.toURI().toURL().toString());
		
		//Déclaration de la VBox principale (qui contiendra tous les éléments d'une tâche) 
		main = new VBox();
		main.setMinSize(500, 300);
		
		//Déclaration de tous les éléments nécessaires
		HBox top = new HBox();
		VBox semaines = new VBox(); 
		HBox aleas = new HBox(); 
		
		//Elements pour la HBox Top (ID + Intitulé de la tâche) et ajout de ces éléments à la HBox 
		Label id = new Label(); 
		id.setText(tache.getId());
		id.setFont(new Font("Arial", 38));
		id.setMinWidth(100);
		id.setAlignment(Pos.TOP_CENTER);
		Label intitule = new Label();
		intitule.setText(tache.getDescription());
		intitule.setFont(new Font("Arial", 38));
		top.setMinHeight(50);
		top.setSpacing(100);
		top.getChildren().addAll(id, intitule);
		
		redessinerSemaines(rondVide, rondPlein);
		
		HBox acceleration = new HBox();
		acceleration.setAlignment(Pos.CENTER);
		File accelFile = new File("ressources/bouton_accelerer.png");
		File accelFileOnMouseMove = new File("ressources/bouton_accelerer_onmousemove.png");
		Image accelImg = new Image(accelFile.toURI().toURL().toString());
		Image accelImgOnMouseMove = new Image(accelFileOnMouseMove.toURI().toURL().toString());
		Canvas accelCanvas = new Canvas(210, 60);
		this.gc2 = accelCanvas.getGraphicsContext2D();
		gc2.drawImage(accelImg, 0, 5);
		prix = new Label();
		prix.setText(tache.coutAcceleration()+"€");
		prix.setFont(new Font("Arial", 38));
		prix.setTextFill(Color.web("#368D81"));
		acceleration.setSpacing(5);
		acceleration.getChildren().addAll(accelCanvas, prix);
		//Gestion des évènements visuels lors du passage de la souris sur le bouton accélérer
		acceleration.setOnMouseMoved(e -> {
			if(cptClickAccel == 0  || cptClickAccel % 2 == 0) {
			gc2.drawImage(accelImgOnMouseMove, 0, 4);
			prix.setTextFill(Color.web("0066ff"));
			}
			});
		acceleration.setOnMouseExited(e -> {
			if(cptClickAccel == 0 || cptClickAccel % 2 == 0) {
			gc2.drawImage(accelImg, 0, 5);
			prix.setTextFill(Color.web("#368D81"));
			}
			});
		//Gestion des évènements + modification des données lors du clic sur le bouton 
		acceleration.setOnMouseClicked(new EventAcceleration());
		semaines.getChildren().addAll(canvasSemaines, acceleration);
		
		//Elements pour les Aléas et ajout de ces éléments
		Aleas aleaRouge = (Aleas) this.tache.getAlea(Couleur.ROUGE);
		Aleas aleaJaune = (Aleas) this.tache.getAlea(Couleur.JAUNE);
		Aleas aleaVert = (Aleas) this.tache.getAlea(Couleur.VERT);
		VBox alea1 = new VBox(); 
		VBox alea2 = new VBox(); 
		VBox alea3 = new VBox(); 
		File bouclierFile = new File("ressources/bouclier_off.png");
		File croixFile = new File("ressources/croix.png");
		File tickFile = new File("ressources/tick.png");
		bouclier_off = new Image(bouclierFile.toURI().toURL().toString());
		Image croix = new Image(croixFile.toURI().toURL().toString());
		Image tick = new Image(tickFile.toURI().toURL().toString());
		ivBouclier1 = new ImageView(bouclier_off);
		ivBouclier1.setId("bouclier1");
		//Gestion des évènements visuels pour les boucliers de protection des aléas
		ivCroix1 = new ImageView(croix);
		ivCroix2 = new ImageView(croix);
		ivCroix3 = new ImageView(croix);
		ivBouclier1.setOnMouseMoved(new EventBouclierSurvol(ivBouclier1));
		ivBouclier1.setOnMouseExited(e -> {
			if(cptClickBouclier1 == 0 || cptClickBouclier1 % 2 == 0) {
			ivBouclier1.setImage(bouclier_off);}});
		ivBouclier1.setOnMouseClicked(new EventBouclierClick(ivBouclier1, ivCroix1));
		ivBouclier2 = new ImageView(bouclier_off);
		ivBouclier2.setId("bouclier2");
		ivBouclier2.setOnMouseExited(e -> {
			if(cptClickBouclier2 == 0 || cptClickBouclier2 % 2 == 0) {
			ivBouclier2.setImage(bouclier_off);}});
		ivBouclier2.setOnMouseMoved(new EventBouclierSurvol(ivBouclier2));
		ivBouclier2.setOnMouseClicked(new EventBouclierClick(ivBouclier2, ivCroix2));
		ivBouclier3 = new ImageView(bouclier_off);
		ivBouclier3.setId("bouclier3");
		ivBouclier3.setOnMouseMoved(new EventBouclierSurvol(ivBouclier3));
		ivBouclier3.setOnMouseExited(e -> {
			if(cptClickBouclier3 == 0 || cptClickBouclier3 % 2 == 0) {
			ivBouclier3.setImage(bouclier_off);}});
		ivBouclier3.setOnMouseClicked(new EventBouclierClick(ivBouclier3, ivCroix3));
		Label labelAlea1 = new Label(); 
		labelAlea1.setText("ROUGE: "+aleaRouge.getType().toString().toUpperCase()+"\nGravité: "+aleaRouge.getGravite());
		labelAlea1.setFont(new Font("Arial", 22));
		labelAlea1.setTextFill(Color.RED);
		labelAlea1.setAlignment(Pos.CENTER_RIGHT);
		labelAlea1.setTextAlignment(TextAlignment.CENTER);
		Label labelAlea2 = new Label(); 
		labelAlea2.setText("JAUNE: "+aleaJaune.getType()+"\nGravité: "+aleaJaune.getGravite());
		labelAlea2.setFont(new Font("Arial", 22));
		labelAlea2.setTextFill(Color.web("#C0C702"));
		labelAlea2.setTextAlignment(TextAlignment.CENTER);
		Label labelAlea3 = new Label();
		labelAlea3.setText("VERT: "+aleaVert.getType()+"\nGravité: "+aleaVert.getGravite());
		labelAlea3.setFont(new Font("Arial", 22));
		labelAlea3.setTextFill(Color.GREEN);
		labelAlea3.setAlignment(Pos.CENTER_LEFT);
		labelAlea3.setTextAlignment(TextAlignment.CENTER);
		alea1.getChildren().addAll(labelAlea1, ivBouclier1, ivCroix1);
		alea2.getChildren().addAll(labelAlea2, ivBouclier2, ivCroix2);
		alea3.getChildren().addAll(labelAlea3, ivBouclier3, ivCroix3);
		aleas.setSpacing(60);
		aleas.setMinHeight(148);
		aleas.setMaxWidth(600);
		aleas.setAlignment(Pos.CENTER);
		alea1.setAlignment(Pos.BOTTOM_CENTER);
		alea2.setAlignment(Pos.BOTTOM_CENTER);
		alea3.setAlignment(Pos.BOTTOM_CENTER);
		aleas.getChildren().addAll(alea1, alea2, alea3);
		
		//Personnalisation de la VBox principale et ajout de tous les éléments à cette dernière
		File backgroundFile = new File("ressources/fond.png");
		Image background = new Image(backgroundFile.toURI().toURL().toString());
		main.setBackground(new Background(new BackgroundImage(background, null, null, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		main.setMinSize(600, 400);
		main.setMaxSize(600, 400);
		main.getChildren().addAll(top, semaines, aleas);
		
		return main;
	}
	
	public VBox creerIHMSemaine() throws Exception {
		//On reprend la même IHM que pour le jalon en supprimant les capacités de décision
		VBox boite = creerIHMJalon();
		VBox semaines = (VBox) boite.getChildren().get(1);
		//On supprime le bouton permettant d'accélérer: 
		semaines.getChildren().remove(semaines.getChildren().get(1));
		//De même pour les boucliers permettant la protection: 
		HBox aleas = (HBox) boite.getChildren().get(2);
		VBox alea1 = (VBox) aleas.getChildren().get(0);
		VBox alea2 = (VBox) aleas.getChildren().get(1);
		VBox alea3 = (VBox) aleas.getChildren().get(2);
		alea1.getChildren().remove(alea1.getChildren().get(1));
		alea1.getChildren().remove(alea1.getChildren().get(1));
		alea2.getChildren().remove(alea2.getChildren().get(1));
		alea2.getChildren().remove(alea2.getChildren().get(1));
		alea3.getChildren().remove(alea3.getChildren().get(1));
		alea3.getChildren().remove(alea3.getChildren().get(1));
		boite.setMargin(semaines, new Insets(20, 0, 0, 0));
		boite.setMargin(aleas, new Insets(-50, 0, 0, 0));
		//On créer et affiche l'avancement en-dessous des semaines
		avancement = new Label(); 
		avancement.setText("Avancement: " + tache.getAvancement() + " / " + tache.getDureeInitiale());
		avancement.setFont(new Font("Arial", 20));
		avancement.setTextFill(Color.BLUE);
		semaines.getChildren().add(avancement);
		semaines.setMargin(avancement, new Insets(0, 0, 0, 200));
		//On affiche les informations du joueur en bas de la VBox
		Label infos = new Label();
		infos.setText("Joueur: " + joueur.getNom() + " - Votre caisse: " + joueur.getCaisse() + "€ - Qualité: " + joueur.getQualite()+"%");
		infos.setFont(new Font("Arial", 18));
		infos.setTextFill(Color.BLUE);
		boite.getChildren().add(infos);
		boite.setMargin(infos, new Insets(10, 0, 0, 70));
		
		//On gère l'affichage visuel des Aléas
		Couleur aleaTirer = listeAleas[Integer.valueOf(tache.getId())-1];
		if(aleaTirer == Couleur.ROUGE) {
			System.out.println("ON A TIRER L'ALEA ROUGE");
			Label lab = (Label) alea1.getChildren().get(0);
			lab.setStyle("-fx-border-style: solid;" + "-fx-border-color: blue;"+"-fx-border-width: 2px;"+"-fx-border-collapse: separate;" + "-fx-border-spacing: 5px 5px 5px 5px");		
		} else if(aleaTirer == Couleur.JAUNE) {
			System.out.println("ON A TIRER L'ALEA JAUNE");
			Label lab = (Label) alea2.getChildren().get(0);
			lab.setStyle("-fx-border-style: solid;" + "-fx-border-color: blue;"+"-fx-border-width: 2px;"+"-fx-border-collapse: separate;" + "-fx-border-spacing: 5px 5px 5px 5px");
		} else if(aleaTirer == Couleur.VERT) {
			System.out.println("ON A TIRER L'ALEA VERT");
			Label lab = (Label) alea3.getChildren().get(0);
			lab.setStyle("-fx-border-style: solid;" + "-fx-border-color: blue;"+"-fx-border-width: 2px;"+"-fx-border-collapse: separate;" + "-fx-border-spacing: 5px 5px 5px 5px");
		}
		
		passer = new Button(); 
		passer.setText("Passer semaine");
		boite.getChildren().add(passer);
		boite.setMargin(passer, new Insets(8, 0, 0, 250));
		
		return boite;
	}
	
	public void redessinerSemaines(Image vide, Image plein) {
		if(tache.getId() != "7") {
		int marge = 50;
		for(int i = 0; i < tache.getDureeMax(); i++) {
			if(i < tache.getDureeInitiale()) {
				gc.drawImage(vide, marge, 10);
				marge += 130;
			} else {
				gc.drawImage(plein, marge, 10);
				marge += 130;
				}
			}
		} else {
			int marge = 10; 
			for(int i = 0; i < tache.getDureeMax(); i++) {
				if(i < tache.getDureeInitiale()) {
					gc.drawImage(vide, marge, 10);
					marge += 95;
				} else {
					gc.drawImage(plein, marge, 10);
					marge += 95;
					}
				}
		}
	}
	
	

	
}
