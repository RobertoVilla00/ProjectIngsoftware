package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.Message.StartMessage;

public class fxController {
	private static Gui gui;

	public fxController(Gui gui){
		this.gui=gui;
	}

	public static void setName(NicknameMessage nicknameMessage){
		gui.sendMessage(nicknameMessage);
	}

	public static void setGameOptions(StartMessage startMessage){
		gui.sendMessage(startMessage);
	}
}
