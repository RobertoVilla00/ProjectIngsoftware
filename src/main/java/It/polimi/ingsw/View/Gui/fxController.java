package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.View.Gui.GuiController.AskNameController;
import It.polimi.ingsw.View.Gui.GuiController.BoardController;
import It.polimi.ingsw.View.Gui.GuiController.EndGameController;

/**
 * The controller of the Gui.
 */
public class fxController {
	private static Gui gui;

	/**
	 * The constructor of the fxController.
	 * @param gui: it receives the parameter Gui.
	 */
	public fxController(Gui gui){
		this.gui=gui;
	}

	/**
	 * It used to send the message of the nickname.
	 * @param nicknameMessage: the message nickname.
	 */
	public static void setName(NicknameMessage nicknameMessage){
		gui.sendMessage(nicknameMessage);
		SceneController.changeScene("fxml/waitingScene.fxml");
	}

	/**
	 * It used to set the Board controller.
	 * @param boardController: it receives the Board controller.
	 */
	public static void setBoardController(BoardController boardController){
		gui.setBoardController(boardController);
	}

	/**
	 * It used to set the controller of nickname.
	 * @param askNameController: it receives the ask name controller.
	 */
	public static void setNicknameController(AskNameController askNameController){
		gui.setNicknameController(askNameController);
	}

	/**
	 * It used to send a message to Gui.
	 * @param message: it receives a message.
	 */
	public static void executeAction(Message message){
		gui.sendMessage(message);
	}

	/**
	 * It used to set the end game controller.
	 * @param endGameController: it receives the end game controller.
	 */
	public static void setEndGameController(EndGameController endGameController){
		gui.setEndGameController(endGameController);
	}
}
