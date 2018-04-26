package partie;

import description.Aleas;
import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
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
		
	private Scene scene;
	private Description description = new Description(); 
	private Label id; 
	private Label intitule; 
	private Label alea1; 
	private Label alea2; 
	private Label alea3; 
	private HBox top; 
	private HBox bottom; 
	private HBox middle; 
	private VBox tache;
	
	
	//Les durées seront à ajouter au constructeur
	public Scene IHMTache(Tache t) {
		
		//Déclaration de tous les éléments nécessaires à l'IHM d'une tâche
		this.id = new Label(t.getId()); 
		this.intitule = new Label(t.getDescription());
		this.alea1 = new Label(t.getAlea(Couleur.ROUGE).getGravite() + " : " + t.getAlea(Couleur.ROUGE).getType());
		this.alea2 = new Label(t.getAlea(Couleur.JAUNE).getGravite() + " : " + t.getAlea(Couleur.JAUNE).getType());
		this.alea3 = new Label(t.getAlea(Couleur.VERT).getGravite() + " : " + t.getAlea(Couleur.VERT).getType());
		
		//Déclaration des éléments de positionnement (HBox, VBox, etc.) 
		this.top = new HBox(); //HBox du haut contenant l'ID et l'intitulé de la tâche
		this.bottom = new HBox(); //HBox du bas contenant les trois alés
		this.middle = new HBox(); //Hbox du milieu qui contiendra les ronds des semaines, etc. 
		this.tache = new VBox(); //VBox qui placera les 3 HBox précédentes de manière vertical (VBox principale)
		
		//Ajout des données à ces éléments de positionnement 
		this.top.getChildren().addAll(this.id, this.intitule);
		this.bottom.getChildren().addAll(this.alea1, this.alea2, this.alea3);
		//middle sera à faire puisqu'il concerne les durées
		this.tache.getChildren().addAll(top, middle, bottom);
		
		//Propriétés des éléments de positionnement (couleurs, espacements, etc.) 
		this.top.setStyle("-fx-background-color: lightblue;"
                + " -fx-font: 30px Arial;");
		
		this.bottom.setStyle("-fx-background-color: lightblue;"
                + " -fx-alignment: center;"
                + " -fx-font: 20px Arial;");
		this.bottom.setMinWidth(scene.getWidth()/3);
		bottom.setSpacing(100);
		
		return new Scene(tache);
	}
	
	public void start(Stage stage)  {
        stage.setTitle("Tâche initiale");
        stage.setScene(this.IHMTache(this.description.getDebut()));
        stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
