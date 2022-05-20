package It.polimi.ingsw.View.Cli;

import It.polimi.ingsw.Controller.GamePhase;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;
import It.polimi.ingsw.View.View;

import java.io.PrintStream;
import java.util.Scanner;

public class Cli extends Observable implements View, Observer {
	private PrintStream out;
	private Scanner in = new Scanner(System.in) ;
	private ShowMatchInfoMessage msg;
	private int PlayerId;

	public Cli(){
		out=System.out;
	}

	public void startGame(){
		out.println(StrColor.ANSI_RED+"                                                                                                                      \n" +
				"         .**ooooOOOOOOOOOOOOOO*                 .oOOo.                                                                    \n" +
				"      .o##o**°*@@@#.°°°°°*o#@@@°                #@@@@o                                   °#                               \n" +
				"     *@@*     #@@@#          *#@o                *O#O*                                  *@@                               \n" +
				"    .@@O     o@@@@#  °oOOOO*   °*  ***   °***.              .°*oo**o*   °**°  .****  °oO@@@oo*  *oo°   .oOO*.  °ooOOo**O. \n" +
				"    °@@o     o@@@@O*#@@O**O@*     #@@@*°#@@@@# oO@@@#     *O#@O*O@@@O   @@@#.o@@@@@o *o@@@@o**o @@@@O   @@@@# @#*  *O@@o  \n" +
				"    .#@#.    o@@@@#@@o    °O      #@@@##*o@@#°   @@@#   .O@@#.  o@@@O   @@@###*o@@@O   #@@@     °O@@@#   O@@@ @@#Oo       \n" +
				"     °@@O°   o@@@@@#              O@@@o  o#*     @@@#   O@@@°   o@@@O   @@@@o  *@@@O   @@@@       o@@@#* *@@* *@@@@@@#o*  \n" +
				"      .o##Oo o@@@@@            °* #@@@*          @@@#  .@@@@*..*O@@@O   @@@@.  *@@@O   #@@@       *@@@#o#@*      #@@@@@°  \n" +
				"             o@@@@O          .*#* O@@@*          @@@#° .#@@@@@@#O@@@O   @@@@.  *@@@o   #@@@*oo°     *@@@@O°  *@O°   *#@@° \n" +
				"             o@@@@O°      °*o#@*  o@#o.          o@@#o* °#@@@#* .O@@Oo° #@#o   °#@@O*  o@@@O*        *@@o.   °o@#OOooOO*  \n" +
				"          °*oO####OoooooooOO##*                                                         °°°         *O#*.       °°°°°     \n" +
				"                                                                                                  °o#@o°                  \n" +
				"                                                                                                °o@@@*°                   \n" +
				"                       																		 °*OO°                     \n"+StrColor.ANSI_RESET);

	}

	@Override
	public void showGameInformation() {
		int schoolSpace = 20;
		int defaultSpace = 25;
		int studentCnt = 0;
		int towerCnt = 0;
		int toRemove = 0;
		int cardCnt = 0;



		StringBuilder str = new StringBuilder();
		str.append("ISLANDS:\n\n");


		// SHOWING THE TABLE

		//first row

		if(msg.getTable().size()>0) {
			toRemove = 6 - msg.getTable().size();
			if (toRemove < 0) toRemove = 0;
			for (int i = 0; i < 156 - toRemove * 26; i++) str.append("-");
			str.append("-");
			str.append("\n");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("  ISLAND " + (i + 1));

				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("  " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					for (int j = 0; j < 12; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("  " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					for (int j = 0; j < 9; j++) str.append(" ");
				} else for (int j = 0; j < 15; j++) {
					str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 156 - toRemove * 26; i++) str.append("-");
			str.append("-");
			str.append("\n");

			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getGreenStudents(); j++) {
					str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getYellowStudents(); j++) {
					str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getRedStudents(); j++) {
					str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getBlueStudents(); j++) {
					str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getPinkStudents(); j++) {
					str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");

					//show towers

			str.append("|");
			for (int i = 0; i < 6 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getNumberOfTowers(); j++) {

					if (msg.getTable().get(i).getTowersColor()== TowerColor.BLACK) {
						str.append(StrColor.ANSI_BLACK + "█ " + StrColor.ANSI_RESET);
						towerCnt+=2;
					}
					if (msg.getTable().get(i).getTowersColor()== TowerColor.GREY) {
						str.append(StrColor.ANSI_GREY + "█ " + StrColor.ANSI_RESET);
						towerCnt+=2;
					}
					if (msg.getTable().get(i).getTowersColor()== TowerColor.WHITE) {
						str.append("█ ");
						towerCnt+=2;
					}
				}
				for (int k = 0; k < defaultSpace - towerCnt; k++) str.append(" ");
				str.append("|");
				towerCnt = 0;
			}
			str.append("\n");
			for (int i = 0; i < 156 - toRemove * 26; i++) str.append("-");
			str.append("-");
			str.append("\n");
		}

		//second row

		if(msg.getTable().size()>6) {
			toRemove = 12 - msg.getTable().size();
			if (toRemove < 0) toRemove = 0;

			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("  ISLAND " + (i + 1));

				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("  " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					if(i>8) for(int j=0;j<11;j++) str.append(" ");
					else for (int j = 0; j < 12; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("  " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					if(i>8) for (int j = 0; j < 8; j++) str.append(" ");
					else for(int j=0; j<9;j++) str.append(" ");
				} else for (int j = 0; j < 15; j++) {
					if (i > 8 && j< 14) str.append(" ");
					else if (i<9) str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 156 - toRemove * 26; i++) str.append("-");
			str.append("-");
			str.append("\n");


			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getGreenStudents(); j++) {
					str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getYellowStudents(); j++) {
					str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getRedStudents(); j++) {
					str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getBlueStudents(); j++) {
					str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getPinkStudents(); j++) {
					str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				for (int k = 0; k < defaultSpace - studentCnt; k++) str.append(" ");
				str.append("|");
				studentCnt = 0;
			}
			str.append("\n");
			str.append("|");
			for (int i = 6; i < 12 && i < msg.getTable().size(); i++) {
				for (int j = 0; j < msg.getTable().get(i).getNumberOfTowers(); j++) {

					if (msg.getTable().get(i).getTowersColor()== TowerColor.BLACK) {
						str.append(StrColor.ANSI_BLACK + "█ " + StrColor.ANSI_RESET);
						towerCnt+=2;
					}
					if (msg.getTable().get(i).getTowersColor()== TowerColor.GREY) {
						str.append(StrColor.ANSI_GREY + "█ " + StrColor.ANSI_RESET);
						towerCnt+=2;
					}
					if (msg.getTable().get(i).getTowersColor()== TowerColor.WHITE) {
						str.append("█ ");
						towerCnt+=2;
					}
				}
				for (int k = 0; k < defaultSpace - towerCnt; k++) str.append(" ");
				str.append("|");
				towerCnt = 0;
			}

			str.append("\n");
			for (int i = 0; i < 156 - toRemove * 26; i++) str.append("-");
			str.append("-");
			str.append("\n");
		}

		if(msg.isExpertMode()) printCharacterCards(str);



		//PRINTING THE CLOUDS

		for(int i=0;i<msg.getClouds().size();i++){
			str.append("CLOUD "+(i+1)+(": "));
			str.append("[");
			for(Color student: msg.getClouds().get(i).getCloudStudents()){
				if(student == Color.BLUE) {
					str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
				}
				if(student == Color.GREEN) {
					str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				}
				if(student == Color.YELLOW) {
					str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
				}
				if(student == Color.RED) {
					str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
				}
				if(student == Color.PINK) {
					str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
				}
			}
			str.append("]    ");
		}
		str.append("\n\n");


		//PRINTING SCHOOLS

		for(Player p: msg.getPlayers()) {

			for (int i = 0; i < 13; i++) str.append("-");
			for(int i=0;i<20;i++) str.append(" ");
		}
		str.append("\n");
		for(Player p: msg.getPlayers()){
			str.append("| ");
			for(int i=0;i<p.getPlayersSchool().getEntranceStudentsNumber();i++){
				if(p.getPlayersSchool().GetEntranceStudentColor(i)==Color.GREEN){
					str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				if(p.getPlayersSchool().GetEntranceStudentColor(i)==Color.BLUE){
					str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				if(p.getPlayersSchool().GetEntranceStudentColor(i)==Color.RED){
					str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				if(p.getPlayersSchool().GetEntranceStudentColor(i)==Color.YELLOW){
					str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
				if(p.getPlayersSchool().GetEntranceStudentColor(i)==Color.PINK){
					str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
					studentCnt++;
				}
			}
			for(int i=0;i<10-studentCnt;i++){
				str.append(" ");
			}
			studentCnt=0;
			str.append("|");
			for(int i=0;i<20;i++) str.append(" ");
		}

		str.append("\n");



		for(Player p: msg.getPlayers()) {

			for (int i = 0; i < 13; i++) str.append("-");
			for(int i=0;i<20;i++) str.append(" ");
		}

		//printing green students
		str.append("\n");
		for(Player p: msg.getPlayers()){
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			studentCnt=0;
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.GREEN){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_GREEN + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n");

		//printing red students
		for(Player p: msg.getPlayers()){
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.RED);i++){
				str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			studentCnt=0;
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.RED){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_RED + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n");

		//printing blue students
		for(Player p: msg.getPlayers()){
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.BLUE);i++){
				str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			studentCnt=0;
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.BLUE){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_BLUE + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n");

		//printing yellow students
		for(Player p: msg.getPlayers()){
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.YELLOW);i++){
				str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			studentCnt=0;
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.YELLOW){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_YELLOW + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n");

		//printing pink students
		for(Player p: msg.getPlayers()){
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.PINK);i++){
				str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			studentCnt=0;
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.PINK){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_PURPLE + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n");

		//printing towers
		for(Player p: msg.getPlayers()){
			str.append("|");
			str.append(" ");
			for(int i=0;i<4&&i<maxTowersNumber()-p.getTowersPlaced();i++){
				if(p.getPlayerColor()==TowerColor.BLACK) {
					str.append(StrColor.ANSI_BLACK + " █" + StrColor.ANSI_RESET);
					towerCnt += 2;
				}
				if(p.getPlayerColor()==TowerColor.WHITE) {
					str.append(" █");
					towerCnt += 2;
				}

				if(p.getPlayerColor()==TowerColor.GREY) {
					str.append(StrColor.ANSI_GREY + " █" + StrColor.ANSI_RESET);
					towerCnt += 2;
				}
			}
			for(int i=0;i< 10-towerCnt; i++) str.append(" ");
			towerCnt=0;
			str.append("|");
			for(int i=0;i<20;i++) str.append(" ");

		}

		str.append("\n");

		for(Player p: msg.getPlayers()){
			str.append("|");
			str.append(" ");
			for(int i=4;i<maxTowersNumber()&&i<maxTowersNumber()-p.getTowersPlaced();i++){
				if(p.getPlayerColor()==TowerColor.BLACK) {
					str.append(StrColor.ANSI_BLACK + " █" + StrColor.ANSI_RESET);
					towerCnt += 2;
				}
				if(p.getPlayerColor()==TowerColor.WHITE) {
					str.append(" █");
					towerCnt += 2;
				}

				if(p.getPlayerColor()==TowerColor.GREY) {
					str.append(StrColor.ANSI_GREY + " █" + StrColor.ANSI_RESET);
					towerCnt += 2;
				}
			}
			for(int i=0;i< 10-towerCnt; i++) str.append(" ");
			towerCnt=0;
			str.append("|");
			for(int i=0;i<20;i++) str.append(" ");

		}
		str.append("\n");
		for(Player p: msg.getPlayers()) {

			for (int i = 0; i < 13; i++) str.append("-");
			for(int i=0;i<20;i++) str.append(" ");
		}
		str.append("\n");
		if(msg.isExpertMode()) printCoins(str);

		for(Player p: msg.getPlayers()){
			if(p.getPlayerId()==PlayerId)
			{
				for(AssistantCard c: p.getDeck().getCards()){
					str.append("["+StrColor.ANSI_BLUE+"OV:"+StrColor.ANSI_RESET+c.getOrderValue()+StrColor.ANSI_BLUE+"  M:"+StrColor.ANSI_RESET+c.getMovement()+"]   ");
				}
			}
		}
		str.append("\n\n");


		System.out.println(str);
	}

	public void printCoins(StringBuilder str) {
		for (Player p : msg.getPlayers()) {
			str.append("|");
			str.append("COINS = " + p.getCoins());
			if (p.getCoins() < 10) str.append("  ");
			else str.append(" ");
			str.append("|");
			for (int i = 0; i < 20; i++) str.append(" ");
		}
		str.append("\n");
		for (Player p : msg.getPlayers()) {
			for (int i = 0; i < 13; i++) str.append("-");
			for (int i = 0; i < 20; i++) str.append(" ");
		}

		str.append("\n\n");
	}

	public void printCharacterCards(StringBuilder str){
		int i=1;
		str.append("\n");
		for(CharacterCard c: msg.getCharacterCards()){
			str.append("CHARACTER "+i+": ");
			if(c.getIdCharacterCard()==1){
				str.append("Take 1 Student from this card and place it on an island of you choice" +StrColor.ANSI_GREEN+ "\n(COST = 1)"+StrColor.ANSI_RESET);
				str.append("             [");
				c=(Cards1and10)c;
				for(int j=0;j<((Cards1and10) c).getNumberOfStudents();j++) {
					if(((Cards1and10) c).GetStudentColor(j)== Color.BLUE) {
						str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.GREEN) {
						str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.YELLOW) {
						str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.RED) {
						str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.PINK) {
						str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
					}
				}
				str.append("]\n\n");
			}

			if(c.getIdCharacterCard()==3){
				str.append("Choose an Island and resolve it as if Mother Nature had ended her movement there " +StrColor.ANSI_GREEN+ "\n(COST = 3)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==4){
				str.append("You may move Mother Nature up to 2 additional Island than indicated on the Assistant card you've played " +StrColor.ANSI_GREEN+"\n(COST = 1)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==5) {
				str.append("Place a No Entry tile on an island on your choice. Influence will not be calculated next time Mother Nature ends there  " +StrColor.ANSI_GREEN+ "\n(COST = 2)"+StrColor.ANSI_RESET);
				str.append("             [");
				c=(Card5)c;
				for(int j=0; j<((Card5) c).getNoEntryTilesOnCard();j++){
					str.append(StrColor.ANSI_RED+"Ø"+StrColor.ANSI_RESET);
				}
				str.append("]\n\n");
			}

			if(c.getIdCharacterCard()==6){
				str.append("when resolving a Conquering on an island, Towers do not count towards influence " +StrColor.ANSI_GREEN+ "\n(COST = 3)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==9){
				str.append("During the influence calculation this turn, you count as having 2 more influence " +StrColor.ANSI_GREEN+ "\n(COST = 2)\n\n"+StrColor.ANSI_RESET);
			}
			if(c.getIdCharacterCard()==10){
				str.append("Move one Student from this card to the Dining Room, then draw one Student from the Bag and place it on this card  " +StrColor.ANSI_GREEN+ "\n(COST = 2)"+StrColor.ANSI_RESET);
				str.append("             [");
				c=(Cards1and10)c;
				for(int j=0;j<((Cards1and10) c).getNumberOfStudents();j++) {
					if(((Cards1and10) c).GetStudentColor(j)== Color.BLUE) {
						str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.GREEN) {
						str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.YELLOW) {
						str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.RED) {
						str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
					}
					if(((Cards1and10) c).GetStudentColor(j)== Color.PINK) {
						str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
					}
				}
				str.append("]\n\n");
			}
			if(c.getIdCharacterCard()==12){
				str.append("Choose a Student Color. Every player must return 3 Students of that Color from their Dining Room to the Bag " + StrColor.ANSI_GREEN + "\n(COST = 3)\n\n" + StrColor.ANSI_RESET);
			}
			i++;
		}
	}

	public Player getActivePlayerById(){
		for(Player p: msg.getPlayers()){
			if(p.getPlayerId()==msg.getActivePlayerId()) return p;
		}
		return null;
	}


	public int maxTowersNumber(){
		int i=0;
		for(Player p: msg.getPlayers()) i++;
		if(i==2) return 8;
		else return 6;
	}

	public void setMsg(ShowMatchInfoMessage msg){
		this.msg = msg;
	}

	@Override
	public void askInformation() {

		if(msg.getActivePlayerId()==PlayerId) {
			if (msg.getGamePhase() == GamePhase.ASSISTANT_CARD) {
				out.println("Choose which Assistant Card you want to play\n");
				int CardIndex = in.nextInt();
				AssistantCardMessage assistantCardMessage = new AssistantCardMessage(CardIndex);
				notifyObserver(assistantCardMessage);
			}

			if (msg.getGamePhase() == GamePhase.MOVE_STUDENT) {
				if (msg.isExpertMode()) {
					while (true) {
						out.println("Type C if you want to play a Character Card, M if you want to move a Student\n");
						if (in.nextLine().toUpperCase() == "C") {
							out.println("Choose the Character Card you want to play\n");
							int CharacterCardIndex = in.nextInt();
							CharacterCardMessage characterCardMessage = new CharacterCardMessage(CharacterCardIndex);
							notifyObserver(characterCardMessage);
							break;
						}
						else if (in.nextLine().toUpperCase() == "M") {
							while (true) {
								out.println("Type D if you want to move a Student to the Dining room, I if you want to move it to an Island\n");
								if (in.nextLine().toUpperCase() == "D") {
									out.println("Which Student do you want to move?\n");
									int position = in.nextInt();
									MoveStudentMessage moveStudentMessage = new MoveStudentMessage(position, 0);
									break;
								} else if (in.nextLine().toUpperCase() == "I") {
									out.println("Which Student do you want to move?\n");
									int position = in.nextInt();
									out.println("Select the Island\n");
									int destination = in.nextInt();
									MoveStudentMessage moveStudentMessage = new MoveStudentMessage(position, destination);
									break;
								}
								else out.println(StrColor.ANSI_RED + "Invalid command!\n" + StrColor.ANSI_RESET);
							}
							break;
						}
						else out.println(StrColor.ANSI_RED + "Invalid command!\n" + StrColor.ANSI_RESET);
					}
				}
				else{
					while (true) {
						out.println("Type D if you want to move a Student to the Dining room, I if you want to move it to an Island\n");
						if (in.nextLine().toUpperCase() == "D") {
							out.println("Which Student do you want to move?\n");
							int position = in.nextInt();
							MoveStudentMessage moveStudentMessage = new MoveStudentMessage(position, 0);
							break;
						} else if (in.nextLine().toUpperCase() == "I") {
							out.println("Which Student do you want to move?\n");
							int position = in.nextInt();
							out.println("Select the Island\n");
							int destination = in.nextInt();
							MoveStudentMessage moveStudentMessage = new MoveStudentMessage(position, destination);
							break;
						} else out.println(StrColor.ANSI_RED + "Invalid command!\n" + StrColor.ANSI_RESET);
					}
				}
			}

			if (msg.getGamePhase() == GamePhase.MOVE_MN) {
				if (msg.isExpertMode()) {
					while (true) {
						out.println("Type C if you want to play a Character Card, M if you want to move Mother nature\n");
						if (in.nextLine().toUpperCase() == "C") {
							out.println("Choose the Character Card you want to play\n");
							int CharacterCardIndex = in.nextInt();
							CharacterCardMessage characterCardMessage = new CharacterCardMessage(CharacterCardIndex);
							notifyObserver(characterCardMessage);
							break;
						} else if (in.nextLine().toUpperCase() == "M") {
							out.println("How many steps do you want Mother Nature to make?\n");
							int steps = in.nextInt();
							MotherNatureMessage motherNatureMessage = new MotherNatureMessage(steps);
							notifyObserver(motherNatureMessage);
							break;
						} else out.println(StrColor.ANSI_RED + "Invalid command!\n" + StrColor.ANSI_RESET);
					}
				}
				else{
					out.println("How many steps do you want Mother Nature to make?\n");
					int steps = in.nextInt();
					MotherNatureMessage motherNatureMessage = new MotherNatureMessage(steps);
					notifyObserver(motherNatureMessage);
				}
			}


			if (msg.getGamePhase() == GamePhase.CHOOSE_CLOUD) {
				if (msg.isExpertMode()) {
					while (true) {
						out.println("Type C if you want to play a Character Card, P if you want to pick Students from a Cloud\n");
						if (in.nextLine().toUpperCase() == "C") {
							out.println("Choose the Character Card you want to play\n");
							int CharacterCardIndex = in.nextInt();
							CharacterCardMessage characterCardMessage = new CharacterCardMessage(CharacterCardIndex);
							notifyObserver(characterCardMessage);
							break;
						} else if (in.nextLine().toUpperCase() == "P") {
							out.println("Choose the Cloud you want to pick the Students from\n");
							int index = in.nextInt();
							CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(index);
							notifyObserver(cloudChoiceMessage);
							break;
						} else out.println(StrColor.ANSI_RED + "Invalid command!\n" + StrColor.ANSI_RESET);
					}
				}
				else{
					out.println("Choose the Cloud you want to pick the Students from\n");
					int index = in.nextInt();
					CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(index);
					notifyObserver(cloudChoiceMessage);
				}
			}

			if(msg.getGamePhase()==GamePhase.CHARACTER_CARD){
				if(msg.getExpectedCardMessage()==1){
					out.println("Choose the Student you want to move\n");
					int studentIndex = in.nextInt();
					out.println("Choose the Island where you want to move the Student\n");
					int islandIndex = in.nextInt();

					Card1Message card1Message = new Card1Message(studentIndex, islandIndex);
					notifyObserver(card1Message);
				}
				if(msg.getExpectedCardMessage()==3){
					out.println("Choose the Island to resolve\n");
					int islandIndex = in.nextInt();

					Card3and5Message card3and5Message = new Card3and5Message(islandIndex);
					notifyObserver(card3and5Message);
				}
				if(msg.getExpectedCardMessage()==5){
					out.println("Choose the Island where you want to put the no entry tile\n");
					int islandindex = in.nextInt();

					Card3and5Message card3and5Message = new Card3and5Message(islandindex);
					notifyObserver(card3and5Message);

				}
				if(msg.getExpectedCardMessage()==10){
					out.println("Choose the Student you want to move to the Dining Room\n");
					int index = in.nextInt();

					Card10Message card10Message = new Card10Message(index);
					notifyObserver(card10Message);

				}
				if(msg.getExpectedCardMessage()==12){
					String color;
					while (true) {
						out.println("Type the Color of the Student to move back to the Bag\n");
						color = in.nextLine().toUpperCase();
						if(color=="GREEN" || color=="YELLOW" || color=="RED" || color=="BLUE" || color=="PINK"){
							break;
						}
						else out.println(StrColor.ANSI_RED+"Choose a valid Color!\n"+StrColor.ANSI_RESET);
					}
					Card12Message card12Message= new Card12Message(color);
					notifyObserver(card12Message);
				}
			}
		}
	}

	public void askName(){
		out.println("Insert your name\n");
		String name = in.nextLine();

		NicknameMessage nicknameMessage = new NicknameMessage(name);
		notifyObserver(nicknameMessage);
	}

	public void printError(String error){
		out.println(error);
	}

	public void askPlayers(){
		out.println("Insert the number of Players (must be 2 or 3)\n");
		int numberOfPlayers = in.nextInt();
		while(numberOfPlayers != 2 && numberOfPlayers != 3)
		{
			out.println(StrColor.ANSI_RED+"Invalid number! Please choose 2 or 3\n"+StrColor.ANSI_RESET);
			numberOfPlayers = in.nextInt();
		}
		out.println("Do you want to play with expert rules? (y/n)");
		String response=in.nextLine();
		while(!response.toUpperCase().equals("Y") && !response.toUpperCase().equals("N")){
			out.println(StrColor.ANSI_RED+"Invalid response! type y or n\n"+StrColor.ANSI_RESET);
			response = in.nextLine();
		}
		int gamemode;
		if(response=="Y") gamemode = 1;
		else gamemode = 0;

		StartMessage startMessage = new StartMessage(numberOfPlayers, gamemode);
		notifyObserver(startMessage);
	}

	@Override
	public void update(Message message) {
		switch (message.getMessageContent()){
			case SHOWMATCHINFO:
				this.setMsg((ShowMatchInfoMessage) message);
				showGameInformation();
				askInformation();
				break;
			case PLAYERID:
				PlayerIdMessage msg=(PlayerIdMessage)message;
				this.PlayerId= msg.getPlayerId();
				askName();
				break;
			case ERROR:
				ErrorMessage errorMessage=(ErrorMessage) message;
				String error=errorMessage.getError();
				printError(error);
				break;
			case PLAYERS:
				askPlayers();
		}
	}
}