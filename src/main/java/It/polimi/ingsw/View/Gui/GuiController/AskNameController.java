package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.View.Gui.Gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AskNameController {

	@FXML
	Label nameLabel;
	@FXML
	TextField nameTextField;
	@FXML
	Button nameButton;

	public Gui gui;
	public String nickname;

	public AskNameController(Gui gui){
		this.gui=gui;
	}

	public void getAnswer(ActionEvent event){
		nickname=nameTextField.getText();
		if(nickname.isEmpty()){
			nameLabel.setText("Insert a valid name");
		}
		else{
			NicknameMessage nicknameMessage = new NicknameMessage(nickname);
			gui.sendMessage(nicknameMessage);
		}
	}
}
