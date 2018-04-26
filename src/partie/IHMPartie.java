package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;
import description.Tacheclass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IHMPartie extends Application {
	
	private ArrayList<Tacheclass> taches;
	private ArrayList<VBox> IHMTaches;
	private Description desc;
	
	public VBox IHMTache(Tacheclass t) {
		//Déclaration de tous les éléments nécessaires à l'IHM d'une tâche
		Label id = new Label(t.getId()); 
		Label intitule = new Label(t.getDescription());
		Label alea1 = new Label(t.getAlea(Couleur.ROUGE).getGravite() + " : " + t.getAlea(Couleur.ROUGE).getType());
		Label alea2 = new Label(t.getAlea(Couleur.JAUNE).getGravite() + " : " + t.getAlea(Couleur.JAUNE).getType());
		Label alea3 = new Label(t.getAlea(Couleur.VERT).getGravite() + " : " + t.getAlea(Couleur.VERT).getType());
		
		//Déclaration des éléments de positionnement (HBox, VBox, etc.) 
		HBox top = new HBox(); //HBox du haut contenant l'ID et l'intitulé de la tâche
		HBox bottom = new HBox(); //HBox du bas contenant les trois alés
		HBox middle = new HBox(); //Hbox du milieu qui contiendra les ronds des semaines, etc. 
		VBox tache = new VBox(); //VBox qui placera les 3 HBox précédentes de manière vertical (VBox principale)
		
		//Propriétés des éléments de positionnement (couleurs, espacements, etc.) 
		top.setStyle("-fx-background-color: lightblue;"
                + " -fx-font: 30px Arial;");
		
		bottom.setStyle("-fx-background-color: lightblue;"
                + " -fx-alignment: center;"
                + " -fx-font: 20px Arial;");
	
		bottom.setSpacing(100);
		
		
		//Ajout des données à ces éléments de positionnement 
		top.getChildren().addAll(id, intitule);
		bottom.getChildren().addAll(alea1, alea2, alea3);
		//middle sera à faire puisqu'il concerne les durées
		tache.getChildren().addAll(top, middle, bottom);
		
		return tache;
	}
	
	
	public void start(Stage primaryStage) throws Exception {
		this.desc = new Description(); 
		this.taches = desc.getTaches();
		
		for(Tacheclass t : taches) {
			this.IHMTaches.add(IHMTache(t));
		}
		
		HBox suite = new HBox(); 
		suite.getChildren().addAll(this.IHMTaches);
		
		Scene scene = new Scene(suite);
		
		primaryStage.setTitle("Test IHM Générale");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
