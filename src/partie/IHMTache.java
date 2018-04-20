package partie;

import description.Couleur;
import description.Description;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHMTache extends Application {
		
	Scene scene;
	
	public void start(Stage stage)  {
		Description d = new Description();
		Label id = new Label(d.getDebut().getId());
		id.setStyle("-fx-background-color: lightgrey;"
                 + " -fx-alignment: left;"
                 + " -fx-font: 30px Arial;");
		Label intitule = new Label(d.getDebut().getDescription());
		intitule.setStyle("-fx-background-color: lightgrey;"
                 + " -fx-alignment: center;"
                 + " -fx-font: 30px Arial;");
		intitule.setMinWidth(400);
		Label alea1 = new Label(d.getDebut().getAlea(Couleur.ROUGE).getGravite() + " : " + d.getDebut().getAlea(Couleur.ROUGE).getType());
		Label alea2 = new Label(d.getDebut().getAlea(Couleur.JAUNE).getGravite() + " : " + d.getDebut().getAlea(Couleur.JAUNE).getType());
		Label alea3 = new Label(d.getDebut().getAlea(Couleur.VERT).getGravite() + " : " + d.getDebut().getAlea(Couleur.VERT).getType());
		Canvas ronds = new Canvas(300, 100);
		GraphicsContext gc = ronds.getGraphicsContext2D();
		gc.setFill(Color.BLUE); 
		gc.setStroke(Color.BLACK);
		
		HBox top = new HBox();
		top.getChildren().addAll(id, intitule);
		HBox middle = new HBox(); 
		middle.getChildren().addAll(ronds);
		HBox bottom = new HBox(); 
		bottom.getChildren().addAll(alea1, alea2, alea3);
		VBox tache = new VBox();
		tache.getChildren().addAll(top, middle, bottom);
		
		
		
		scene = new Scene(tache);
        stage.setTitle("Tâche initiale");
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
