package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.View.Gui.Gui;
import It.polimi.ingsw.View.Gui.SceneController;
import It.polimi.ingsw.View.Gui.fxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AskNameAndPlayersController {

	@FXML
	RadioButton PlayersButton2, PlayersButton3, GameModeNormal, GameModeExpert;
	@FXML
	Button okButton;

	public int NumberOfPlayers;
	public int GameMode;
	public String nickname;

	public AskNameAndPlayersController(){
		this.NumberOfPlayers=2;
		this.GameMode=0;
	}

	public void getAnswers(ActionEvent event){
		StartMessage startMessage=new StartMessage(NumberOfPlayers,GameMode);
		fxController.executeAction(startMessage);
		SceneController.changeScene("fxml/waitingScene.fxml");
	}

	public void getNumberOfPlayers(ActionEvent event){
		if(PlayersButton2.isSelected()){
			   NumberOfPlayers=2;
		}
		if(PlayersButton3.isSelected()){
			NumberOfPlayers=3;
		}
	}

	public void getGameMode(ActionEvent event){
		if(GameModeNormal.isSelected()){
			GameMode=0;
		}
		if(GameModeExpert.isSelected()){
			GameMode=1;
		}
	}

}
