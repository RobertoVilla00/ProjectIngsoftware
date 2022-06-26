package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BoardController {

	@FXML
	ImageView Island0, Island1, Island2, Island3, Island4, Island5, Island6, Island7, Island8, Island9, Island10, Island11;
	@FXML
	Label nickname0, nickname1, nickname2, coinCount0, coinCount1, coinCount2;
	@FXML
	ImageView school0, school1, school2, assistant0, assistant1, assistant2, coin0, coin1, coin2;
	@FXML
	ImageView character0, character1,character2, cloud0, cloud1, cloud2;

	private List<Label> playerNicknames=new ArrayList<Label>();
	private List<Label> playerCoins=new ArrayList<Label>();

	private List<ImageView> islands=new ArrayList<ImageView>();



	public void showGameInformation(ShowMatchInfoMessage msg){
		playerNicknames.add(nickname0);
		playerNicknames.add(nickname1);
		playerNicknames.add(nickname2);
		for(int i=0; i<msg.getPlayers().length ; i++){
			playerNicknames.get(i).setText(msg.getPlayers()[i].getName());
		}


		//show the information based on the number of players
		if(msg.getPlayers().length==2){
			nickname2.setVisible(false);
			school2.setVisible(false);
			coin2.setVisible(false);
			coinCount2.setVisible(false);
			cloud2.setVisible(false);
			assistant2.setVisible(false);
		}

		//hide the imageviews of expert mode elements during normal game
		if(!msg.isExpertMode()){
			character0.setVisible(false);
			character1.setVisible(false);
			character2.setVisible(false);
			coin0.setVisible(false);
			coin1.setVisible(false);
			coin2.setVisible(false);
			coinCount0.setVisible(false);
			coinCount1.setVisible(false);
			coinCount2.setVisible(false);
		}
		//print the information only shown in expert mode
		if(msg.isExpertMode()){
			playerCoins.add(coinCount0);
			playerCoins.add(coinCount1);
			playerCoins.add(coinCount2);
			for(int i=0; i<msg.getPlayers().length ; i++){
				playerCoins.get(i).setText(String.valueOf(msg.getPlayers()[i].getCoins()));
			}
		}


		//showing Islands informations
		islands.add(Island0);
		islands.add(Island1);
		islands.add(Island2);
		islands.add(Island3);
		islands.add(Island4);
		islands.add(Island5);
		islands.add(Island6);
		islands.add(Island7);
		islands.add(Island8);
		islands.add(Island9);
		islands.add(Island10);
		islands.add(Island11);
		for(int i=msg.getTable().size(); i<12; i++){
			islands.get(i).setVisible(false);
		}
	}


}
