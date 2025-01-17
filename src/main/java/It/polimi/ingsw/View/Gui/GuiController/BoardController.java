package It.polimi.ingsw.View.Gui.GuiController;

import It.polimi.ingsw.Controller.GamePhase;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;
import It.polimi.ingsw.View.Gui.fxController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;


/**
 * The controller of the board.
 */
public class BoardController implements Initializable {

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
	@FXML
	ChoiceBox<String> assistantChoice, colorChoice;
	@FXML
	Label infoLabel;
	@FXML
	Label errorLabel;

	private int playerId;
	private ShowMatchInfoMessage msg;
	private boolean hasStudentSelected;
	private boolean hasStudentFromCharacter;
	private int studentFromCharacterIndex;
	private int selectedStudentIndex;
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
	private List<ImageView> clouds=new ArrayList<>();


	private List<List<ImageView>> allEntranceStudent=new ArrayList<>();
	private List<List<ImageView>> allIslandTowers=new ArrayList<>();
	private List<List<ImageView>> allCloudsStudent=new ArrayList<>();
	private List<ImageView> diningRoomStudents= new ArrayList<>();
	private List<List<ImageView>> allCharacterImage=new ArrayList<>();


	/**
	 * @param msg: it receives the message containing the info of the game.
	 * @param playerId: it receives the id of the player.
	 */
	public void showGameInformation(ShowMatchInfoMessage msg, int playerId){
		this.msg=msg;
		this.playerId=playerId;
		hasStudentSelected=false;
		hasStudentFromCharacter=false;
		colorChoice.setVisible(false);

		assistantChoice.setVisible(false);


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

		//hide the image views of expert mode elements during normal game
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

		for(int i=msg.getTable().size(); i<12; i++){
			islands.get(i).setVisible(false);
			motherNatures.get(i).setVisible(false);
			towers.get(i).setVisible(false);
			noEntryTiles.get(i).setVisible(false);
			allStudents.get(0).get(i).setVisible(false);
			allStudents.get(1).get(i).setVisible(false);
			allStudents.get(2).get(i).setVisible(false);
			allStudents.get(3).get(i).setVisible(false);
			allStudents.get(4).get(i).setVisible(false);
		}
		for(int i=0; i<msg.getTable().size();i++) {
			if (msg.getTable().get(i).GetNoEntryTile()) {
				noEntryTiles.get(i).setVisible(true);
			} else {
				noEntryTiles.get(i).setVisible(false);
			}
			if (msg.getTable().get(i).getNumberOfTowers() != 0) {
				towers.get(i).setVisible(true);
				String color=msg.getTable().get(i).getTowersColor().toString().toLowerCase();
				String towerUrl="Graphical_Assets/Pedine/" + color + "Tower.png";
				Image towersImage=new Image(towerUrl);
				towers.get(i).setImage(towersImage);
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

		//show Clouds
		for(int i=0;i<allCloudsStudent.size();i++){
			for(ImageView imageView:allCloudsStudent.get(i)){
				boardPane.getChildren().remove(imageView);
			}
		}
		for(int i=0; i<msg.getClouds().size();i++){
			ArrayList<ImageView> imageList=new ArrayList<>();
			allCloudsStudent.add(imageList);
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
				studentImage.setId("cloudStudent"+j);
				allCloudsStudent.get(i).add(studentImage);
			}
		}








		//show School information

		for(ImageView imageView:diningRoomStudents){
			boardPane.getChildren().remove(imageView);
		}
		for(int i=0; i<msg.getPlayers().length;i++){
			for(int j=0; j<5;j++){
				allTeachers.get(i).get(j).setVisible(false);
			}
		}
		for(int i=0;i<allEntranceStudent.size();i++){
			for(ImageView imageView:allEntranceStudent.get(i)){
				boardPane.getChildren().remove(imageView);
			}
		}
		for(int i=0;i<allIslandTowers.size();i++){
			for(ImageView imageView:allIslandTowers.get(i)){
				boardPane.getChildren().remove(imageView);
			}
		}
		for(int i=0; i<msg.getPlayers().length; i++){
			schools.get(i).setVisible(true);
			TowerColor playerColor;
			if(msg.getPlayers()[i].getPlayerId()==0){
				playerColor=TowerColor.WHITE;
			}
			else if(msg.getPlayers()[i].getPlayerId()==1){
				playerColor=TowerColor.BLACK;
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
			ArrayList<ImageView> imageList=new ArrayList<>();
			allEntranceStudent.add(imageList);
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
				studentImage.setId("entranceStudent"+j);
				allEntranceStudent.get(i).add(studentImage);
				studentImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
					public void handle(MouseEvent e){
						selectStudent((ImageView)e.getSource());
						e.consume();
					}
				});
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
				boardPane.getChildren().add(studentImage);
				diningRoomStudents.add(studentImage);
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
				boardPane.getChildren().add(studentImage);
				diningRoomStudents.add(studentImage);
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
				boardPane.getChildren().add(studentImage);
				diningRoomStudents.add(studentImage);
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
				boardPane.getChildren().add(studentImage);
				diningRoomStudents.add(studentImage);
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
				boardPane.getChildren().add(studentImage);
				diningRoomStudents.add(studentImage);
			}
			int maxTowers;
			if(msg.getPlayers().length==2) {
				maxTowers = 8;
			}
			else{
				maxTowers = 6;
			}
			String towerString=new String();
			switch (msg.getPlayers()[i].getPlayerId()){
				case 0:
					towerString="Graphical_Assets/Pedine/torreBianca.png";
					break;
				case 1:
					towerString="Graphical_Assets/Pedine/torreNera.png";
					break;
				case 2:
					towerString="Graphical_Assets/Pedine/torreGrigia.png";
					break;
			}
			ArrayList<ImageView> towerList=new ArrayList<>();
			allIslandTowers.add(towerList);
			for(int j=0;j<maxTowers-msg.getPlayers()[i].getTowersPlaced();j++){
				int xValue;
				int yValue;
				xValue=305+((j%2)*27)+(436*i);
				yValue=556+(25*(j/2));
				ImageView towerImage=new ImageView(towerString);
				towerImage.setVisible(true);
				towerImage.setX(xValue);
				towerImage.setY(yValue);
				towerImage.setFitHeight(22);
				towerImage.setFitWidth(22);
				towerImage.setPreserveRatio(true);
				boardPane.getChildren().add(towerImage);
				allIslandTowers.get(i).add(towerImage);
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

		for(int i=0;i<allCharacterImage.size();i++){
			for(ImageView imageView:allCharacterImage.get(i)){
				boardPane.getChildren().remove(imageView);
			}
		}
		allCharacterImage.clear();
		if(msg.isExpertMode()) {
			for(int i=0; i<3;i++){
				List<ImageView> characterImage=new ArrayList<>();
				allCharacterImage.add(characterImage);
				int idCharacterCard=msg.getCharacterCards()[i].getIdCharacterCard();
				String characterCard="Graphical_Assets/implementedCharacters/characterCard"+idCharacterCard+".jpg";
				Image cardImg=new Image(characterCard);
				characterCards.get(i).setVisible(true);
				characterCards.get(i).setImage(cardImg);
				cardCoins.get(i).setVisible(true);
				cardCosts.get(i).setVisible(true);
				cardCosts.get(i).setText(String.valueOf(msg.getCharacterCards()[i].getCardCost()));

				if(idCharacterCard==1 || idCharacterCard==10){
					Cards1and10 card=(Cards1and10) msg.getCharacterCards()[i];
					for(int j=0;j<card.getNumberOfStudents();j++){
						int xValue;
						int yValue;
						xValue = 354+(20*j)+(149*i);
						yValue = 260;
						String color=card.GetStudentColor(j).toString().toLowerCase();
						ImageView studentImage = new ImageView("Graphical_Assets/Pedine/"+color+"Student.png");
						studentImage.setVisible(true);
						studentImage.setX(xValue);
						studentImage.setY(yValue);
						studentImage.setFitHeight(20);
						studentImage.setFitWidth(20);
						studentImage.setPreserveRatio(true);
						boardPane.getChildren().add(studentImage);
						studentImage.setId("character"+i+"student"+j);
						allCharacterImage.get(i).add(studentImage);
						studentImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
							public void handle(MouseEvent e){
								selectCardStudent((ImageView)e.getSource());
								e.consume();
							}
						});
					}
				}
				if(idCharacterCard==5){
					Card5 card=(Card5) msg.getCharacterCards()[i];
					for(int j=0;j<card.getNoEntryTilesOnCard();j++) {
						int xValue;
						int yValue;
						xValue = 354+(20*j)+(149*i);
						yValue = 260;
						ImageView NoentryImage = new ImageView("Graphical_Assets/Pedine/No.png");
						NoentryImage.setVisible(true);
						NoentryImage.setX(xValue);
						NoentryImage.setY(yValue);
						NoentryImage.setFitHeight(20);
						NoentryImage.setFitWidth(20);
						NoentryImage.setPreserveRatio(true);
						boardPane.getChildren().add(NoentryImage);
						NoentryImage.setId("tile"+i+"student"+j);
						allCharacterImage.get(i).add(NoentryImage);
					}
				}
			}
		}

		//write the assistant cards that are yet to be played
		List<String> assistantCards=new ArrayList<>();
		for (Player p : msg.getPlayers()) {
			if (p.getPlayerId() == playerId) {
				for (AssistantCard c : p.getDeck().getCards()) {
					assistantCards.add("Movement="+c.getMovement()+" Order="+c.getOrderValue());
				}
			}
		}
		assistantChoice.getItems().clear();
		assistantChoice.getItems().addAll(assistantCards);

	}

	/**
	 * It used to play an Assistant Card depending on the choice of the player.
	 * @param event: it receives an action event.
	 */
	public void playAssistantCard(ActionEvent event){
		try {
			String assistantCard = assistantChoice.getValue();
			if(assistantCard!=null) {
				int cardIndex = assistantChoice.getItems().indexOf(assistantCard) + 1;
				AssistantCardMessage assistantCardMessage = new AssistantCardMessage(cardIndex);
				errorLabel.setVisible(false);
				fxController.executeAction(assistantCardMessage);
			}
		}
		catch (NullPointerException nullPointerException){
			//catch the value change to null
		}
	}


	/**
	 * It used to ask information to the active player depending on the phase of the game and tell the other player to wait.
	 * @param msg: it receives the message containing the info of the game.
	 * @param PlayerId: the id of the player.
	 */
	//ask information to the active player, tell the others to wait
	public void askInformation(ShowMatchInfoMessage msg, int PlayerId){
		if(msg.getActivePlayerId()==PlayerId){
			if(msg.getGamePhase()== GamePhase.ASSISTANT_CARD){
				assistantChoice.setVisible(true);
				infoLabel.setText("Choose which assistant you want to play");
			}
			if(msg.getGamePhase()== GamePhase.MOVE_STUDENT){
				if(msg.isExpertMode()){
					infoLabel.setText("Choose which student you want to move or play a character");
				}
				else{
					infoLabel.setText("Choose which student you want to move");
				}
			}
			if(msg.getGamePhase()== GamePhase.MOVE_MN){
				if(msg.isExpertMode()){
					infoLabel.setText("Choose where you want to move Mother Nature or play a character");
				}
				else{
					infoLabel.setText("Choose where you want to move Mother Nature");
				}
			}
			if(msg.getGamePhase()== GamePhase.CHOOSE_CLOUD){
				if(msg.isExpertMode()){
					infoLabel.setText("Choose the cloud you want to take the students from or play a character");
				}
				else{
					infoLabel.setText("Choose the cloud you want to take the students from");
				}
			}
			if(msg.getGamePhase()== GamePhase.CHARACTER_CARD){
				if(msg.getExpectedCardMessage()==1){
					infoLabel.setText("Choose the Student you want to move");

				}
				if(msg.getExpectedCardMessage()==3){
					infoLabel.setText("Choose the island to resolve");
				}
				if(msg.getExpectedCardMessage()==5){
					infoLabel.setText("Choose where to put the no entry tile");
				}
				if(msg.getExpectedCardMessage()==10){
					infoLabel.setText("Choose the Student you want to move");
				}
				if(msg.getExpectedCardMessage()==12){
					infoLabel.setText("Choose the color of students you want to discard");
					colorChoice.setVisible(true);
				}
			}
		}
		else{
			infoLabel.setText("wait for your turn");
		}
	}


	/**
	 * It used to select a student from the Entrance.
	 * @param student: it receives an Image View.
	 */
	public void selectStudent(ImageView student){
		int index=0;
		for(int i=0; i<msg.getPlayers().length;i++){
			if(allEntranceStudent.get(i).contains(student)){
				index=i;
			}
		}
		if(msg.getPlayers()[index].getPlayerId()==playerId) {
			if (msg.getActivePlayerId() == playerId && msg.getGamePhase() == GamePhase.MOVE_STUDENT) {
				infoLabel.setText("where do you want to put the student?");
				hasStudentSelected = true;
				selectedStudentIndex = Integer.parseInt(student.getId().substring(15)) + 1;
			}
		}
	}

	/**
	 * It used to move a student to the selected island.
	 * @param destinationIsland: the Image View of the destination island.
	 */
	public void moveStudentToIsland(ImageView destinationIsland){
		if(msg.getGamePhase()==GamePhase.MOVE_STUDENT) {
			hasStudentSelected = false;
			int destination = Integer.parseInt(destinationIsland.getId().substring(6)) + 1;
			MoveStudentMessage moveStudentMessage = new MoveStudentMessage(selectedStudentIndex, destination);
			fxController.executeAction(moveStudentMessage);
		}
		if(msg.getExpectedCardMessage()==1 && hasStudentFromCharacter){
			hasStudentFromCharacter = false;
			int destination= Integer.parseInt(destinationIsland.getId().substring(6)) + 1;
			Card1Message card1Message=new Card1Message(studentFromCharacterIndex,destination);
			fxController.executeAction(card1Message);
		}
	}

	/**
	 * It used to move a student to the selected school.
	 * @param destinationSchool: the Image View of the destination school.
	 */
	public void moveStudentToDiningRoom(ImageView destinationSchool){
		if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.MOVE_STUDENT && hasStudentSelected){
			hasStudentSelected=false;
			int destination=Integer.parseInt(destinationSchool.getId().substring(6));
			if(msg.getActivePlayerId()==msg.getPlayers()[destination].getPlayerId()) {
				MoveStudentMessage moveStudentMessage = new MoveStudentMessage(selectedStudentIndex, 0);
				fxController.executeAction(moveStudentMessage);
			}
		}
	}

	/**
	 * It used to move mother nature to the selected island.
	 * @param destinationIsland: the Image View of destination island.
	 */
	public void moveMotherNature(ImageView destinationIsland){
		int destination=Integer.parseInt(destinationIsland.getId().substring(6));
		int numberOfSteps=((destination)-msg.getMotherNaturePosition())%(msg.getTable().size());
		if(numberOfSteps<0){
			numberOfSteps=numberOfSteps+(msg.getTable().size());
		}
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(numberOfSteps);
		fxController.executeAction(motherNatureMessage);
	}

	/**
	 * It used to take students from the cloud selected.
	 * @param chosenCloud: the Image View of the cloud selected.
	 */
	public void takeStudentFromClouds(ImageView chosenCloud){
		int cloudIndex=Integer.parseInt(chosenCloud.getId().substring(5))+1;
		if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.CHOOSE_CLOUD){
			CloudChoiceMessage cloudChoiceMessage=new CloudChoiceMessage(cloudIndex);
			fxController.executeAction(cloudChoiceMessage);
		}
	}

	/**
	 * It used to play a Character Card.
	 * @param character: the Image View of a Character Card.
	 */
	public void playCharacterCard(ImageView character){
		int characterIndex=Integer.parseInt(character.getId().substring(9))+1;
		if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.MOVE_STUDENT || msg.getGamePhase()==GamePhase.MOVE_MN
				|| msg.getGamePhase()==GamePhase.CHOOSE_CLOUD){
			CharacterCardMessage cardMessage=new CharacterCardMessage(characterIndex);
			fxController.executeAction(cardMessage);
		}
	}

	/**
	 * It used to select a student from a Character Card.
	 * @param student: the Image View of the student selected from the Character Card.
	 */
	public void selectCardStudent(ImageView student){
		if (msg.getActivePlayerId() == playerId && msg.getGamePhase() == GamePhase.CHARACTER_CARD && (msg.getExpectedCardMessage()==1 ||
				msg.getExpectedCardMessage()==10)){
			int index=0;
			for(int i=0; i<3;i++){
				if(allCharacterImage.get(i).contains(student)){
					index=i;
				}
			}
			if(msg.getCharacterCards()[index].getIdCharacterCard()==msg.getExpectedCardMessage()){
				hasStudentFromCharacter=true;
				studentFromCharacterIndex=allCharacterImage.get(index).indexOf(student)+1;
				if(msg.getExpectedCardMessage()==1) {
					infoLabel.setText("select the island where you want to move the student");
				}
				else{
					Card10Message card10Message=new Card10Message(studentFromCharacterIndex);
					fxController.executeAction(card10Message);
				}
			}
		}
	}

	/**
	 * It used to choose an island for the Character Cards 3 and 5.
	 * @param island: the Image View of the island selected.
	 */
	public void resolveIsland(ImageView island){
		int islandIndex= Integer.parseInt(island.getId().substring(6)) + 1;
		Card3and5Message card3= new Card3and5Message(islandIndex);
		fxController.executeAction(card3);
	}

	/**
	 * It used to put the prohibition cards for Character Cards 3 and 5.
	 * @param island: the Image View of the island selected.
	 */
	public void putNoEntryTile(ImageView island){
		int islandIndex= Integer.parseInt(island.getId().substring(6)) + 1;
		Card3and5Message card5= new Card3and5Message(islandIndex);
		fxController.executeAction(card5);
	}


	/**
	 * It used to remove color for Character Card 12.
	 * @param event: it receives an action event.
	 */
	public void removeColor(ActionEvent event){
		try {
			String color = colorChoice.getValue();
			Card12Message card12Message = new Card12Message(color);
			errorLabel.setVisible(false);
			fxController.executeAction(card12Message);
		}
		catch (NullPointerException nullPointerException){
			//catch the value change to null
		}
	}

	/**
	 * It used to initialize the controller after its root element has been completely processed.
	 * @param url: the location used to resolve relative paths for the root object.
	 * @param resourceBundle: the resources used to localize the root object.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		assistantChoice.setOnAction(this::playAssistantCard);
		colorChoice.setOnAction(this::removeColor);
		errorLabel.setVisible(false);

		ArrayList<String> colorList=new ArrayList<>();
		colorList.add("BLUE");
		colorList.add("RED");
		colorList.add("GREEN");
		colorList.add("YELLOW");
		colorList.add("PINK");
		colorChoice.getItems().addAll(colorList);

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

		schools.add(school0);
		schools.add(school1);
		schools.add(school2);

		clouds.add(cloud0);
		clouds.add(cloud1);
		clouds.add(cloud2);


		characterCards.add(character0);
		characterCards.add(character1);
		characterCards.add(character2);
		cardCosts.add(cardCost0);
		cardCosts.add(cardCost1);
		cardCosts.add(cardCost2);
		cardCoins.add(cardCoin0);
		cardCoins.add(cardCoin1);
		cardCoins.add(cardCoin2);

		for(ImageView i: islands){
			i.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.MOVE_STUDENT && hasStudentSelected) {
						moveStudentToIsland((ImageView) e.getSource());
						errorLabel.setVisible(false);
						e.consume();
					}
					if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.MOVE_MN){
						moveMotherNature((ImageView)e.getSource());
						errorLabel.setVisible(false);
						e.consume();
					}
					if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.CHARACTER_CARD && msg.getExpectedCardMessage()==1
							&& hasStudentFromCharacter){
						moveStudentToIsland((ImageView) e.getSource());
						errorLabel.setVisible(false);
						e.consume();
					}

					if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.CHARACTER_CARD && msg.getExpectedCardMessage()==3){
						resolveIsland((ImageView) e.getSource());
						errorLabel.setVisible(false);
						e.consume();
					}

					if(msg.getActivePlayerId()==playerId && msg.getGamePhase()==GamePhase.CHARACTER_CARD && msg.getExpectedCardMessage()==5){
						putNoEntryTile((ImageView) e.getSource());
						errorLabel.setVisible(false);
						e.consume();
					}
				}
			});
		}
		for(ImageView i: schools){
			i.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					moveStudentToDiningRoom((ImageView)e.getSource());
					errorLabel.setVisible(false);
					e.consume();
				}
			});
		}
		for(ImageView i: clouds){
			i.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					takeStudentFromClouds((ImageView)e.getSource());
					errorLabel.setVisible(false);
					e.consume();
				}
			});
		}
		for(ImageView i: characterCards){
			i.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					playCharacterCard((ImageView)e.getSource());
					errorLabel.setVisible(false);
					e.consume();
				}
			});
		}

	}

	/**
	 * It used to show the error to active player.
	 * @param error: the string containing the error message.
	 */
	public void showError(String error){
		if(playerId==msg.getActivePlayerId()) {
			errorLabel.setVisible(true);
			errorLabel.setText(error);
		}
	}
}