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
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class IHMTache extends Application {

	public Tacheclass tache;
	public VBox main;
	public GraphicsContext gc2;
	public Label prix;
	public int cptClickedAccel = 0;
	public ImageView ivBouclier1; 
	public ImageView ivBouclier2;
	public ImageView ivBouclier3;
	public Image bouclier_off;
	
	public static void main(String[] args) {
		Application.launch(IHMTache.class, args);
	}
	
	class EventAcceleration implements EventHandler<MouseEvent> {
		public void handle(MouseEvent event) {
			cptClickedAccel += 1;
			File accelFileClicked = new File("ressources/bouton_accelerer_clicked.png");
			Image accelImgClicked = null;
			try {
				accelImgClicked = new Image(accelFileClicked.toURI().toURL().toString());
			} catch (MalformedURLException e) {e.printStackTrace();}
			if(cptClickedAccel == 1 || cptClickedAccel % 3 == 0) {
			gc2.drawImage(accelImgClicked, 0, 5);
			prix.setTextFill(Color.web("009c00"));
			}
		}	
	}
	
	class EventBouclier implements EventHandler<MouseEvent> {
		public void handle(MouseEvent event) {
			if(ivBouclier1.contains(new Point2D(event.getX(), event.getY()))) {
			File bouclierOn = new File("ressources/bouclier_on.png");
			Image bouclierOnImg = null;
			try {
				bouclierOnImg = new Image(bouclierOn.toURI().toURL().toString());
			} catch (MalformedURLException e) {	e.printStackTrace();}
			ivBouclier1.setImage(bouclierOnImg);
			} else {
				ivBouclier1.setImage(bouclier_off);
			}
		}
		
	}
	
	public VBox creerIHM(Tacheclass t) throws Exception {
		this.tache = t;
		
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
		
		//Elements pour la HBox des semaines et ajout de ces éléments
		File fileRondVide = new File("ressources/rond_vide.png");
		File fileRondPlein = new File("ressources/rond_plein.png");
		Image rondVide = new Image(fileRondVide.toURI().toURL().toString());
		Image rondPlein = new Image(fileRondPlein.toURI().toURL().toString());
		Canvas canvas = new Canvas(600, 130);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(int i = 0; i < tache.getDureeMax(); i++) {
			if(i < tache.getDureeInitiale()) {
				if(i == 0) {
				gc.drawImage(rondVide, 50, 10);
				} else {
					gc.drawImage(rondVide, 180, 10);
				}
			} else {
				if(i == tache.getDureeInitiale()) {
					gc.drawImage(rondPlein, 310, 10);
				} else {
					gc.drawImage(rondPlein, 440, 10);
				}
			}
		}
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
			if(cptClickedAccel == 0  || cptClickedAccel % 2 == 0) {
			gc2.drawImage(accelImgOnMouseMove, 0, 4);
			prix.setTextFill(Color.web("0066ff"));
			}
			});
		acceleration.setOnMouseExited(e -> {
			if(cptClickedAccel == 0 || cptClickedAccel % 2 == 0) {
			gc2.drawImage(accelImg, 0, 5);
			prix.setTextFill(Color.web("#368D81"));
			}
			});
		//Gestion des évènements + modification des données lors du clic sur le bouton 
		acceleration.setOnMouseClicked(new EventAcceleration());
		semaines.getChildren().addAll(canvas, acceleration);
		
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
		ivBouclier1.setOnMouseMoved(new EventBouclier());
		ivBouclier2 = new ImageView(bouclier_off);
		ivBouclier2.setOnMouseMoved(new EventBouclier());
		ivBouclier3 = new ImageView(bouclier_off);
		ivBouclier3.setOnMouseMoved(new EventBouclier());
		ImageView ivCroix1 = new ImageView(croix);
		ImageView ivCroix2 = new ImageView(croix);
		ImageView ivCroix3 = new ImageView(croix);
		ImageView ivTick1 = new ImageView(tick);
		ImageView ivTick2 = new ImageView(tick);
		ImageView ivTick3 = new ImageView(tick);
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

	
	public void start(Stage stage) throws Exception {
			Description desc = new Description(); 
			VBox main = creerIHM((Tacheclass)desc.getDebut());
			Scene scene = new Scene(main);
			stage.setTitle("Test IHMTâche");
			stage.setScene(scene);
			stage.show();
	}
}
