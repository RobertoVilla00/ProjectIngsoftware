package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.View.Gui.Gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AskNameAndPlayersController {

	@FXML
	TextField  nicknameTextField;
	@FXML
	RadioButton PlayersButton2, PlayersButton3, GameModeNormal, GameModeExpert;
	@FXML
	Button okButton;
	@FXML
	Label nameLabel;

	public int NumberOfPlayers;
	public int GameMode;
	public String nickname;
	public Gui gui;

	public AskNameAndPlayersController(Gui gui){
		this.gui=gui;
		this.NumberOfPlayers=2;
		this.GameMode=0;
	}

	public void getAnswers(ActionEvent event){
		nickname=nicknameTextField.getText();
		if(nickname.isEmpty()){
			nameLabel.setText("Insert a valid name");
		}
		else{
			NicknameMessage nicknameMessage = new NicknameMessage(nickname);
			gui.sendMessage(nicknameMessage);
		}
		StartMessage startMessage=new StartMessage(NumberOfPlayers,GameMode);
		gui.sendMessage(startMessage);
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
