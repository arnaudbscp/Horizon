package strategie;

import description.Tacheclass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CorrectionIHM extends Application {
	
	public Tacheclass t;
	
	public CorrectionIHM(Tacheclass t) {
		this.t = t;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
	public void start(Stage stage) {
			HBox top = new HBox();
			Label id = new Label(); 
			id.setText("ID");
			Label intitule = new Label();
			intitule.setText("TEXTE");
			top.getChildren().addAll(id, intitule);
			
			Scene scene = new Scene(top);
			stage.setTitle("Test IHMTâche");
			stage.setScene(scene);
			stage.show();
	}
}
