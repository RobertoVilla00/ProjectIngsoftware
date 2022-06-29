package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.View.Gui.GuiController.AskNameController;
import It.polimi.ingsw.View.Gui.GuiController.BoardController;
import It.polimi.ingsw.View.Gui.GuiController.EndGameController;

public class fxController {
	private static Gui gui;

	public fxController(Gui gui){
		this.gui=gui;
	}

	public static void setName(NicknameMessage nicknameMessage){
		gui.sendMessage(nicknameMessage);
		if(gui.getPlayerId()!=0){
			SceneController.changeScene("fxml/waitingScene.fxml");
		}
	}
	public static void setBoardController(BoardController boardController){
		gui.setBoardController(boardController);
	}

	public static void setNicknameController(AskNameController askNameController){
		gui.setNicknameController(askNameController);
	}
	public static void executeAction(Message message){
		gui.sendMessage(message);
	}

	public static void setEndGameController(EndGameController endGameController){
		gui.setEndGameController(endGameController);
	}
}
