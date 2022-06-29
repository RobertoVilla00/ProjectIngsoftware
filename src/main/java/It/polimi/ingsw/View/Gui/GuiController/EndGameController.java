package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.EndgameMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndGameController {
	@FXML
	Label endGameLabel;

	public void displayWinner(EndgameMessage endgameMessage){
		StringBuilder str = new StringBuilder();
		for(String winner : endgameMessage.getWinners()){
			str.append(winner+", ");
		}
		str.append("YOU WIN!");
		endGameLabel.setText(str.toString());
	}
}
