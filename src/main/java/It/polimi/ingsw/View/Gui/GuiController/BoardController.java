package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.Player;
import It.polimi.ingsw.Model.Teacher;
import It.polimi.ingsw.Model.TowerColor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
	@FXML
	ImageView mothernature0,mothernature1,mothernature2,mothernature3,mothernature4,mothernature5,mothernature6,
			mothernature7,mothernature8,mothernature9,mothernature10,mothernature11;
	@FXML
	ImageView tower0,tower1,tower2,tower3,tower4,tower5,tower6,tower7,tower8,tower9,tower10,tower11;
	@FXML
	ImageView noEntryTile0, noEntryTile1, noEntryTile2,noEntryTile3,noEntryTile4,noEntryTile5,noEntryTile6,
	noEntryTile7,noEntryTile8,noEntryTile9,noEntryTile10,noEntryTile11;
	
	@FXML
	ImageView greenTeacher0, redTeacher0, pinkTeacher0, yellowTeacher0, blueTeacher0;
	@FXML
	ImageView greenTeacher1, redTeacher1, pinkTeacher1, yellowTeacher1, blueTeacher1;
	@FXML
	ImageView greenTeacher2, redTeacher2, pinkTeacher2, yellowTeacher2, blueTeacher2;

	@FXML
	Label blueStudentIsland0,blueStudentIsland1,blueStudentIsland2,blueStudentIsland3,blueStudentIsland4,blueStudentIsland5,
			blueStudentIsland6,blueStudentIsland7,blueStudentIsland8,blueStudentIsland9,blueStudentIsland10,blueStudentIsland11;
	@FXML
	Label redStudentIsland0,redStudentIsland1,redStudentIsland2,redStudentIsland3,redStudentIsland4,redStudentIsland5,
			redStudentIsland6,redStudentIsland7,redStudentIsland8,redStudentIsland9,redStudentIsland10,redStudentIsland11;
	@FXML
	Label yellowStudentIsland0,yellowStudentIsland1,yellowStudentIsland2,yellowStudentIsland3,yellowStudentIsland4,yellowStudentIsland5,
			yellowStudentIsland6,yellowStudentIsland7,yellowStudentIsland8,yellowStudentIsland9,yellowStudentIsland10,yellowStudentIsland11;
	@FXML
	Label greenStudentIsland0,greenStudentIsland1,greenStudentIsland2,greenStudentIsland3,greenStudentIsland4,greenStudentIsland5,
			greenStudentIsland6,greenStudentIsland7,greenStudentIsland8,greenStudentIsland9,greenStudentIsland10,greenStudentIsland11;
	@FXML
	Label pinkStudentIsland0,pinkStudentIsland1,pinkStudentIsland2,pinkStudentIsland3,pinkStudentIsland4,pinkStudentIsland5,
			pinkStudentIsland6,pinkStudentIsland7,pinkStudentIsland8,pinkStudentIsland9,pinkStudentIsland10,pinkStudentIsland11;

	@FXML
	Label towersNumber0,towersNumber1,towersNumber2,towersNumber3,towersNumber4,towersNumber5,towersNumber6,towersNumber7,
			towersNumber8,towersNumber9,towersNumber10,towersNumber11;
	@FXML
	AnchorPane boardPane;
	@FXML
	ImageView cardCoin0, cardCoin1, cardCoin2;
	@FXML
	Label cardCost0, cardCost1, cardCost2;


	private List<Label> playerNicknames=new ArrayList<Label>();
	private List<Label> playerCoins=new ArrayList<Label>();

	private List<ImageView> islands=new ArrayList<ImageView>();
	private List<ImageView> motherNatures=new ArrayList<ImageView>();
	private List<ImageView> towers=new ArrayList<ImageView>();
	private List<ImageView> noEntryTiles=new ArrayList<ImageView>();
	private List<ImageView> teachers0=new ArrayList<ImageView>();
	private List<ImageView> teachers1=new ArrayList<ImageView>();
	private List<ImageView> teachers2=new ArrayList<ImageView>();
	private List<ImageView> schools=new ArrayList<ImageView>();
	private List<List<ImageView>> allTeachers=new ArrayList<>();

	private List<Label> blueStudents=new ArrayList<Label>();
	private List<Label> redStudents=new ArrayList<Label>();
	private List<Label> yellowStudents=new ArrayList<Label>();
	private List<Label> pinkStudents=new ArrayList<Label>();
	private List<Label> greenStudents=new ArrayList<Label>();
	private List<List<Label>> allStudents=new ArrayList<>();

	private List<Label> towersNumber=new ArrayList<>();
	private List<ImageView> assistantCards=new ArrayList<>();
	private List<ImageView> characterCards=new ArrayList<>();
	private List<ImageView> cardCoins=new ArrayList<>();
	private List<Label> cardCosts=new ArrayList<>();

	public void showGameInformation(ShowMatchInfoMessage msg){
		playerNicknames.add(nickname0);
		playerNicknames.add(nickname1);
		playerNicknames.add(nickname2);
		for(int i=0; i<msg.getPlayers().length ; i++){
			playerNicknames.get(i).setText(msg.getPlayers()[i].getName());
		}

		teachers0.add(greenTeacher0);
		teachers0.add(redTeacher0);
		teachers0.add(yellowTeacher0);
		teachers0.add(pinkTeacher0);
		teachers0.add(blueTeacher0);

		teachers1.add(greenTeacher1);
		teachers1.add(redTeacher1);
		teachers1.add(yellowTeacher1);
		teachers1.add(pinkTeacher1);
		teachers1.add(blueTeacher1);

		teachers2.add(greenTeacher2);
		teachers2.add(redTeacher2);
		teachers2.add(yellowTeacher2);
		teachers2.add(pinkTeacher2);
		teachers2.add(blueTeacher2);

		allTeachers.add(teachers0);
		allTeachers.add(teachers1);
		allTeachers.add(teachers2);

		blueStudents.add(blueStudentIsland0);
		blueStudents.add(blueStudentIsland1);
		blueStudents.add(blueStudentIsland2);
		blueStudents.add(blueStudentIsland3);
		blueStudents.add(blueStudentIsland4);
		blueStudents.add(blueStudentIsland5);
		blueStudents.add(blueStudentIsland6);
		blueStudents.add(blueStudentIsland7);
		blueStudents.add(blueStudentIsland8);
		blueStudents.add(blueStudentIsland9);
		blueStudents.add(blueStudentIsland10);
		blueStudents.add(blueStudentIsland11);

		redStudents.add(redStudentIsland0);
		redStudents.add(redStudentIsland1);
		redStudents.add(redStudentIsland2);
		redStudents.add(redStudentIsland3);
		redStudents.add(redStudentIsland4);
		redStudents.add(redStudentIsland5);
		redStudents.add(redStudentIsland6);
		redStudents.add(redStudentIsland7);
		redStudents.add(redStudentIsland8);
		redStudents.add(redStudentIsland9);
		redStudents.add(redStudentIsland10);
		redStudents.add(redStudentIsland11);

		yellowStudents.add(yellowStudentIsland0);
		yellowStudents.add(yellowStudentIsland1);
		yellowStudents.add(yellowStudentIsland2);
		yellowStudents.add(yellowStudentIsland3);
		yellowStudents.add(yellowStudentIsland4);
		yellowStudents.add(yellowStudentIsland5);
		yellowStudents.add(yellowStudentIsland6);
		yellowStudents.add(yellowStudentIsland7);
		yellowStudents.add(yellowStudentIsland8);
		yellowStudents.add(yellowStudentIsland9);
		yellowStudents.add(yellowStudentIsland10);
		yellowStudents.add(yellowStudentIsland11);

		pinkStudents.add(pinkStudentIsland0);
		pinkStudents.add(pinkStudentIsland1);
		pinkStudents.add(pinkStudentIsland2);
		pinkStudents.add(pinkStudentIsland3);
		pinkStudents.add(pinkStudentIsland4);
		pinkStudents.add(pinkStudentIsland5);
		pinkStudents.add(pinkStudentIsland6);
		pinkStudents.add(pinkStudentIsland7);
		pinkStudents.add(pinkStudentIsland8);
		pinkStudents.add(pinkStudentIsland9);
		pinkStudents.add(pinkStudentIsland10);
		pinkStudents.add(pinkStudentIsland11);

		greenStudents.add(greenStudentIsland0);
		greenStudents.add(greenStudentIsland1);
		greenStudents.add(greenStudentIsland2);
		greenStudents.add(greenStudentIsland3);
		greenStudents.add(greenStudentIsland4);
		greenStudents.add(greenStudentIsland5);
		greenStudents.add(greenStudentIsland6);
		greenStudents.add(greenStudentIsland7);
		greenStudents.add(greenStudentIsland8);
		greenStudents.add(greenStudentIsland9);
		greenStudents.add(greenStudentIsland10);
		greenStudents.add(greenStudentIsland11);

		allStudents.add(blueStudents);
		allStudents.add(pinkStudents);
		allStudents.add(redStudents);
		allStudents.add(yellowStudents);
		allStudents.add(greenStudents);
		//show the information based on the number of players
		if(msg.getPlayers().length==2){
			nickname2.setVisible(false);
			school2.setVisible(false);
			coin2.setVisible(false);
			coinCount2.setVisible(false);
			cloud2.setVisible(false);
			assistant2.setVisible(false);
			for(ImageView t:teachers2){
				t.setVisible(false);
			}
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
			cardCoin0.setVisible(false);
			cardCoin1.setVisible(false);
			cardCoin2.setVisible(false);
			cardCost0.setVisible(false);
			cardCost1.setVisible(false);
			cardCost2.setVisible(false);
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

		noEntryTiles.add(noEntryTile0);
		noEntryTiles.add(noEntryTile1);
		noEntryTiles.add(noEntryTile2);
		noEntryTiles.add(noEntryTile3);
		noEntryTiles.add(noEntryTile4);
		noEntryTiles.add(noEntryTile5);
		noEntryTiles.add(noEntryTile6);
		noEntryTiles.add(noEntryTile7);
		noEntryTiles.add(noEntryTile8);
		noEntryTiles.add(noEntryTile9);
		noEntryTiles.add(noEntryTile10);
		noEntryTiles.add(noEntryTile11);

		towers.add(tower0);
		towers.add(tower1);
		towers.add(tower2);
		towers.add(tower3);
		towers.add(tower4);
		towers.add(tower5);
		towers.add(tower6);
		towers.add(tower7);
		towers.add(tower8);
		towers.add(tower9);
		towers.add(tower10);
		towers.add(tower11);

		towersNumber.add(towersNumber0);
		towersNumber.add(towersNumber1);
		towersNumber.add(towersNumber2);
		towersNumber.add(towersNumber3);
		towersNumber.add(towersNumber4);
		towersNumber.add(towersNumber5);
		towersNumber.add(towersNumber6);
		towersNumber.add(towersNumber7);
		towersNumber.add(towersNumber8);
		towersNumber.add(towersNumber9);
		towersNumber.add(towersNumber10);
		towersNumber.add(towersNumber11);

		motherNatures.add(mothernature0);
		motherNatures.add(mothernature1);
		motherNatures.add(mothernature2);
		motherNatures.add(mothernature3);
		motherNatures.add(mothernature4);
		motherNatures.add(mothernature5);
		motherNatures.add(mothernature6);
		motherNatures.add(mothernature7);
		motherNatures.add(mothernature8);
		motherNatures.add(mothernature9);
		motherNatures.add(mothernature10);
		motherNatures.add(mothernature11);
		for(int i=msg.getTable().size(); i<12; i++){
			islands.get(i).setVisible(false);
			motherNatures.get(i).setVisible(false);
			towers.get(i).setVisible(false);
			noEntryTiles.get(i).setVisible(false);
		}
		for(int i=0; i<msg.getTable().size();i++) {
			if (msg.getTable().get(i).GetNoEntryTile()) {
				noEntryTiles.get(i).setVisible(true);
			} else {
				noEntryTiles.get(i).setVisible(false);
			}
			if (msg.getTable().get(i).getNumberOfTowers() != 0) {
				towers.get(i).setVisible(true);
				towersNumber.get(i).setVisible(true);
			} else {
				towers.get(i).setVisible(false);
				towersNumber.get(i).setVisible(false);
			}
			if (msg.getMotherNaturePosition() == i) {
				motherNatures.get(i).setVisible(true);
			} else {
				motherNatures.get(i).setVisible(false);
			}
			allStudents.get(0).get(i).setVisible(true);
			allStudents.get(0).get(i).setText(String.valueOf(msg.getTable().get(i).getBlueStudents()));
			allStudents.get(1).get(i).setVisible(true);
			allStudents.get(1).get(i).setText(String.valueOf(msg.getTable().get(i).getPinkStudents()));
			allStudents.get(2).get(i).setVisible(true);
			allStudents.get(2).get(i).setText(String.valueOf(msg.getTable().get(i).getRedStudents()));
			allStudents.get(3).get(i).setVisible(true);
			allStudents.get(3).get(i).setText(String.valueOf(msg.getTable().get(i).getYellowStudents()));
			allStudents.get(4).get(i).setVisible(true);
			allStudents.get(4).get(i).setText(String.valueOf(msg.getTable().get(i).getGreenStudents()));
			towersNumber.get(i).setText(String.valueOf(msg.getTable().get(i).getNumberOfTowers()));
		}

		//showClouds
		for(int i=0; i<msg.getClouds().size();i++){
			for(int j=0;j<msg.getClouds().get(i).CloudSize();j++){
				String student = new String();
				switch (msg.getClouds().get(i).getCloudStudents().get(j)){
					case RED:
						student="Graphical_Assets/Pedine/redStudent.png";
						break;
					case YELLOW:
						student="Graphical_Assets/Pedine/yellowStudent.png";
						break;
					case BLUE:
						student="Graphical_Assets/Pedine/blueStudent.png";
						break;
					case PINK:
						student="Graphical_Assets/Pedine/pinkStudent.png";
						break;
					case GREEN:
						student="Graphical_Assets/Pedine/greenStudent.png";
						break;
				}

				int xValue;
				int yValue;
				xValue=1013+(j*20);
				yValue=116+(143*i);
				ImageView studentImage=new ImageView(student);
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(20);
				studentImage.setFitWidth(20);
				studentImage.setPreserveRatio(true);
				boardPane.getChildren().add(studentImage);
			}
		}








		//show School information
		schools.add(school0);
		schools.add(school1);
		schools.add(school2);

		for(int i=0; i<msg.getPlayers().length;i++){
			for(int j=0; j<5;j++){
				allTeachers.get(i).get(j).setVisible(false);
			}
		}
		for(int i=0; i<msg.getPlayers().length; i++){
			schools.get(i).setVisible(true);
			TowerColor playerColor;
			if(i==0){
				playerColor=TowerColor.WHITE;
			}
			else if(i==1){
				playerColor=TowerColor.WHITE;
			}
			else {
				playerColor=TowerColor.GREY;
			}
			for(Teacher t : msg.getTeachers()){
				try {
					if (t.getControllingPlayerColor().equals(playerColor)) {
						switch (t.getTeacherColor()) {
							case GREEN:
								allTeachers.get(i).get(0).setVisible(true);
								break;
							case RED:
								allTeachers.get(i).get(1).setVisible(true);
								break;
							case YELLOW:
								allTeachers.get(i).get(2).setVisible(true);
								break;
							case PINK:
								allTeachers.get(i).get(3).setVisible(true);
								break;
							case BLUE:
								allTeachers.get(i).get(4).setVisible(true);
								break;
						}
					}
				}
				catch (NullPointerException e){

				}
			}
			for(int j=0; j<msg.getPlayers()[i].getPlayersSchool().getEntranceStudentsNumber();j++){
				String student = new String();
				switch (msg.getPlayers()[i].getPlayersSchool().GetEntranceStudentColor(j)){
					case RED:
						student="Graphical_Assets/Pedine/redStudent.png";
						break;
					case YELLOW:
						student="Graphical_Assets/Pedine/yellowStudent.png";
						break;
					case BLUE:
						student="Graphical_Assets/Pedine/blueStudent.png";
						break;
					case PINK:
						student="Graphical_Assets/Pedine/pinkStudent.png";
						break;
					case GREEN:
						student="Graphical_Assets/Pedine/greenStudent.png";
						break;
				}
				int xValue;
				int yValue;
				xValue=28+i*437+((j+1)%2)*20;
				yValue=546+((j+1)/2)*25;
				ImageView studentImage=new ImageView(student);
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(20);
				studentImage.setFitWidth(20);
				studentImage.setPreserveRatio(true);
				boardPane.getChildren().add(studentImage);
			}
			for(int j = 0; j<msg.getPlayers()[i].getPlayersSchool().getStudentNumber(Color.GREEN); j++){
				int xValue;
				int yValue;
				xValue=84+(j*17)+(i*436);
				yValue=547;
				ImageView studentImage=new ImageView("Graphical_Assets/Pedine/greenStudent.png");
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(15);
				studentImage.setFitWidth(15);
				studentImage.setPreserveRatio(true);
			}
			for(int j = 0; j<msg.getPlayers()[i].getPlayersSchool().getStudentNumber(Color.RED); j++){
				int xValue;
				int yValue;
				xValue=84+(j*17)+(i*436);
				yValue=572;
				ImageView studentImage=new ImageView("Graphical_Assets/Pedine/redStudent.png");
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(15);
				studentImage.setFitWidth(15);
				studentImage.setPreserveRatio(true);
			}
			for(int j = 0; j<msg.getPlayers()[i].getPlayersSchool().getStudentNumber(Color.YELLOW); j++){
				int xValue;
				int yValue;
				xValue=84+(j*17)+(i*436);
				yValue=597;
				ImageView studentImage=new ImageView("Graphical_Assets/Pedine/yellowStudent.png");
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(15);
				studentImage.setFitWidth(15);
				studentImage.setPreserveRatio(true);
			}
			for(int j = 0; j<msg.getPlayers()[i].getPlayersSchool().getStudentNumber(Color.PINK); j++){
				int xValue;
				int yValue;
				xValue=84+(j*17)+(i*436);
				yValue=622;
				ImageView studentImage=new ImageView("Graphical_Assets/Pedine/pinkStudent.png");
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(15);
				studentImage.setFitWidth(15);
				studentImage.setPreserveRatio(true);
			}
			for(int j = 0; j<msg.getPlayers()[i].getPlayersSchool().getStudentNumber(Color.BLUE); j++){
				int xValue;
				int yValue;
				xValue=84+(j*17)+(i*436);
				yValue=647;
				ImageView studentImage=new ImageView("Graphical_Assets/Pedine/blueStudent.png");
				studentImage.setVisible(true);
				studentImage.setX(xValue);
				studentImage.setY(yValue);
				studentImage.setFitHeight(15);
				studentImage.setFitWidth(15);
				studentImage.setPreserveRatio(true);
			}
		}

		//show last played assistantCard
		assistantCards.add(assistant0);
		assistantCards.add(assistant1);
		assistantCards.add(assistant2);
		for(int i=0;i<msg.getPlayers().length;i++){
			int orderValue=msg.getPlayers()[i].GetPlayedOrderValue();
			if(orderValue!=0) {
				String assistantCard = "Graphical_Assets/Assistenti/2x/Assistente (" + orderValue + ").png";
				Image cardImg = new Image(assistantCard);
				assistantCards.get(i).setVisible(true);
				assistantCards.get(i).setImage(cardImg);
			}
		}


		//show the character cards and their information
		if(msg.isExpertMode()) {
			characterCards.add(character0);
			characterCards.add(character1);
			characterCards.add(character2);
			cardCosts.add(cardCost0);
			cardCosts.add(cardCost1);
			cardCosts.add(cardCost2);
			cardCoins.add(cardCoin0);
			cardCoins.add(cardCoin1);
			cardCoins.add(cardCoin2);
			for(int i=0; i<3;i++){
				int idCharacterCard=msg.getCharacterCards()[i].getIdCharacterCard();
				String characterCard="Graphical_Assets/implementedCharacters/characterCard"+idCharacterCard+".jpg";
				Image cardImg=new Image(characterCard);
				characterCards.get(i).setVisible(true);
				characterCards.get(i).setImage(cardImg);
				cardCoins.get(i).setVisible(true);
				cardCosts.get(i).setVisible(true);
				cardCosts.get(i).setText(String.valueOf(msg.getCharacterCards()[i].getCardCost()));
			}
		}
	}


}
