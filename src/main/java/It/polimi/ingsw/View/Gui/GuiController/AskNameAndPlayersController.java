package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.View.Gui.SceneController;
import It.polimi.ingsw.View.Gui.fxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 * The controller of the ask name and player scene.
 */
public class AskNameAndPlayersController {

	@FXML
	RadioButton PlayersButton2, PlayersButton3, GameModeNormal, GameModeExpert;
	@FXML
	Button okButton;

	public int NumberOfPlayers;
	public int GameMode;
	public String nickname;

	/**
	 * The constructor of the ask name and player controller.
	 */
	public AskNameAndPlayersController(){
		this.NumberOfPlayers=2;
		this.GameMode=0;
	}

	/**
	 * It used to create the Start message and to change the scene to the next one.
	 * @param event: it receives an event.
	 */
	public void getAnswers(ActionEvent event){
		StartMessage startMessage=new StartMessage(NumberOfPlayers,GameMode);
		fxController.executeAction(startMessage);
		SceneController.changeScene("fxml/waitingScene.fxml");
	}

	/**
	 * It set the number of player depending on the button pressed.
	 * @param event: it receives an event.
	 */
	public void getNumberOfPlayers(ActionEvent event){
		if(PlayersButton2.isSelected()){
			   NumberOfPlayers=2;
		}
		if(PlayersButton3.isSelected()){
			NumberOfPlayers=3;
		}
	}

	/**
	 * It sets the game mode depending on the button pressed.
	 * @param event: it receives an event.
	 */
	public void getGameMode(ActionEvent event){
		if(GameModeNormal.isSelected()){
			GameMode=0;
		}
		if(GameModeExpert.isSelected()){
			GameMode=1;
		}
	}

}
