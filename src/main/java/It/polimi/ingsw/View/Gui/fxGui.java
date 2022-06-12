package It.polimi.ingsw.View.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fxGui extends Application {

	@Override
	public void start(Stage stage){
		try{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/StartScene.fxml"));
			Scene scene = new Scene(root);
			//String css= this.getClass().getResource("/CSS/Style.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setTitle("ERIANTYS");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
