package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.View.Gui.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController {
	@FXML
	static
	Label myLabel;

	public static void ChangeToAskName(){
		try {
			Parent root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("/FXML/askName.fxml"));
			Stage stage = (Stage)myLabel.getScene().getWindow();
			Scene scene = new Scene(root);
			//String css= this.getClass().getResource("/CSS/Style.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setTitle("ERIANTYS");
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
