package strategie;


import java.io.File;

import description.Description;
import description.Tacheclass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
			main = new VBox();
			
			//Déclaration de tous les éléments nécessaires
			HBox top = new HBox();
			HBox semaines = new HBox(); 
			HBox aleas = new HBox(); 
			HBox accelerer = new HBox();
			
			//Elements pour la HBox Top (ID + Intitulé de la tâche) et ajout de ces éléments à la HBox 
			Label id = new Label(); 
			id.setText(tache.getId());
			Label intitule = new Label();
			intitule.setText(tache.getDescription());
			top.getChildren().addAll(id, intitule);
			
			//Elements pour la HBox des semaines et ajout de ces éléments
			File fileRondVide = new File("ressources/rond_vide.png");
			File fileRondPlein = new File("ressources/rond_plein.png");
			Image rondVide = new Image(fileRondVide.toURI().toURL().toString());
			Image rondPlein = new Image(fileRondPlein.toURI().toURL().toString());
			Canvas canvas = new Canvas(400, 50);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.drawImage(rondVide, 0, 0);
			gc.drawImage(rondPlein, 50, 0);
			semaines.getChildren().addAll(canvas);
			main.getChildren().addAll(top, semaines);
			Scene scene = new Scene(main);
			stage.setTitle("Test IHMTâche");
			stage.setScene(scene);
			stage.show();
	}
}
