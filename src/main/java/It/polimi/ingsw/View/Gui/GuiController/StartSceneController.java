package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.View.Gui.SceneController;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The controller of the start scene.
 */
public class StartSceneController {
	@FXML
	Button startButton;


	/**
	 * It used to switch to the ask name scene.
	 * @param e: it receives an event.
	 */
	public void SwitchToAskName(Event e){
		Platform.runLater(() -> SceneController.SwitchToAskName(e));
	}
}
