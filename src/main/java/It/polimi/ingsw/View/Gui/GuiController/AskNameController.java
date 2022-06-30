package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.NicknameMessage;
import It.polimi.ingsw.View.Gui.fxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The controller of the ask name scene.
 */
public class AskNameController {

	@FXML
	Label nameLabel;
	@FXML
	TextField nameTextField;
	@FXML
	Button nameButton;

	public String nickname;

	/**
	 * It used to receive the nickname of the player and control if the nickname is empty.
	 * @param event: it receives an event.
	 */
	public void getAnswer(ActionEvent event){
		nickname=nameTextField.getText();
		if(nickname.isEmpty()){
			nameLabel.setText("Insert a valid name");
		}
		else{
			NicknameMessage nicknameMessage = new NicknameMessage(nickname);
			fxController.setName(nicknameMessage);
		}
	}

	/**
	 * It used to show that the nickname received is almost used, so insert a new one.
	 */
	public void askCorrectName(){
		nameLabel.setText("The name is already used, insert a valid one");
	}
}
