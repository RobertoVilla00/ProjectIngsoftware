package It.polimi.ingsw.View.Gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

	private static Stage stage;


	public static void SwitchToAskName(Event e){
		try {
			FXMLLoader loader =  new FXMLLoader(SceneController.class.getClassLoader().getResource("fxml/askName.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			//String css= this.getClass().getResource("/CSS/Style.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setTitle("ERIANTYS");
			stage.show();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
