package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class BoardController {

	@FXML
	ImageView Island0, Island1, Island2, Island3, Island4, Island5, Island6, Island7, Island8, Island9, Island10, Island11;
	@FXML
	Label nickname0, nickname1, nickname2, coinCount0, coinCount1, coinCount2;
	@FXML
	ImageView school0, school1, school2, assistant0, assistant1, assistant2, coin0, coin1, coin2;
	@FXML
	ImageView character0, character1,character2, cloud0, cloud1, cloud2;

	public void showGameInformation(ShowMatchInfoMessage msg){
		if(!msg.isExpertMode()){
			character0.setVisible(false);
			character1.setVisible(false);
			character2.setVisible(false);
		}
	}
}
