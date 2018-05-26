package strategie;


import java.io.File;

import description.Alea;
import description.Aleas;
import description.Couleur;
import description.Description;
import description.Tacheclass;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IHMTache extends Application {

	public Tacheclass tache;
	public VBox main;
	
	public static void main(String[] args) {
		Application.launch(IHMTache.class, args);
	}

	
	public void start(Stage stage) throws Exception {
			Description description = new Description(); 
			this.tache = (Tacheclass) description.getDebut();
			
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
			id.setMinWidth(70);
			id.setAlignment(Pos.CENTER);
			Label intitule = new Label();
			intitule.setText(tache.getDescription());
			intitule.setFont(new Font("Arial", 38));
			top.setSpacing(120);
			top.getChildren().addAll(id, intitule);
			
			//Elements pour la HBox des semaines et ajout de ces éléments
			File fileRondVide = new File("ressources/rond_vide.png");
			File fileRondPlein = new File("ressources/rond_plein.png");
			Image rondVide = new Image(fileRondVide.toURI().toURL().toString());
			Image rondPlein = new Image(fileRondPlein.toURI().toURL().toString());
			Canvas canvas = new Canvas(500, 150);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			for(int i = 0; i < tache.getDureeMax(); i++) {
				if(i < tache.getDureeInitiale()) {
					if(i == 0) {
					gc.drawImage(rondVide, 20, 25);
					} else {
						gc.drawImage(rondVide, 130, 25);
					}
				} else {
					if(i == tache.getDureeInitiale()) {
						gc.drawImage(rondPlein, 240, 25);
					} else {
						gc.drawImage(rondPlein, 350, 25);
					}
				}
			}
			HBox acceleration = new HBox();
			acceleration.setAlignment(Pos.CENTER);
			acceleration.setSpacing(-10);
			File accelFile = new File("ressources/bouton_accelerer.png");
			Image accelImg = new Image(accelFile.toURI().toURL().toString());
			Canvas accelCanvas = new Canvas(250, 60);
			GraphicsContext gc2 = accelCanvas.getGraphicsContext2D();
			gc2.drawImage(accelImg, 0, 0);
			Label prix = new Label();
			prix.setText(tache.coutAcceleration()+"€");
			prix.setFont(new Font("Arial", 38));
			acceleration.getChildren().addAll(accelCanvas, prix);
			semaines.getChildren().addAll(canvas, acceleration);
			
			//Elements pour les Aléas et ajout de ces éléments
			Aleas alea1 = (Aleas) tache.getAlea(Couleur.ROUGE);
			Label labelAlea1 = new Label(); 
			String txtAlea1 = "";
			for(int i = 0; i<alea1.getGravite(); i++) {
				txtAlea1 += "A";
			}
			labelAlea1.setText(txtAlea1);
			labelAlea1.setFont(new Font("Arial", 38));
			labelAlea1.setTextFill(Color.RED);
			Aleas alea2 = (Aleas) tache.getAlea(Couleur.VERT);
			Label labelAlea2 = new Label(); 
			String txtAlea2 = "";
			for(int i = 0; i<alea2.getGravite(); i++) {
				txtAlea2 += "B";
			}
			labelAlea2.setText(txtAlea2);
			labelAlea2.setFont(new Font("Arial", 38));
			labelAlea2.setTextFill(Color.GREEN);
			Aleas alea3 = (Aleas) tache.getAlea(Couleur.JAUNE);
			Label labelAlea3 = new Label(); 
			String txtAlea3 = "";
			for(int i = 0; i<alea3.getGravite(); i++) {
				txtAlea3 += "C";
			}
			labelAlea3.setText(txtAlea3);
			labelAlea3.setFont(new Font("Arial", 38));
			labelAlea3.setTextFill(Color.YELLOW);
			aleas.setSpacing(100);
			aleas.setAlignment(Pos.CENTER);
			aleas.getChildren().addAll(labelAlea1, labelAlea2, labelAlea3);
			
			//Personnalisation de la VBox principale et ajout de tous les éléments à cette dernière
			main.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
			main.getChildren().addAll(top, semaines, aleas);
			
			
			Scene scene = new Scene(main);
			stage.setTitle("Test IHMTâche");
			stage.setScene(scene);
			stage.show();
	}
}
