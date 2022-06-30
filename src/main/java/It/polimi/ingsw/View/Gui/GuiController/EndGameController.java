package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.EndgameMessage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static java.lang.System.exit;

public class EndGameController {
	@FXML
	Label endGameLabel;
	@FXML
	Button exitButton;

	public void displayWinner(EndgameMessage endgameMessage){
		StringBuilder str = new StringBuilder();
		for(String winner : endgameMessage.getWinners()){
			str.append(winner+", ");
		}
		str.append("YOU WIN!");
		endGameLabel.setText(str.toString());
	}

	public void closeGame(Event e){
		exit(0);
	}
}
