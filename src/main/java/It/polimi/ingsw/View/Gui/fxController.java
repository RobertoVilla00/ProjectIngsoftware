package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.View.Gui.GuiController.BoardController;

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

	public static void setGameOptions(StartMessage startMessage){
		gui.sendMessage(startMessage);
	}

	public static void setBoardController(BoardController boardController){
		gui.setBoardController(boardController);

	}
}
