package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.View.Gui.SceneController;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController {
	@FXML
	Button StartButton;


	public void SwitchToAskName(Event e){
		Platform.runLater(() -> SceneController.SwitchToAskName(e));
	}

}
