package It.polimi.ingsw.View.Cli;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Model.*;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.View.View;

import java.io.PrintStream;

public class Cli extends Observable implements View {
	private final PrintStream out;
	private ShowMatchInfoMessage msg;
	private int PlayerId;

	public Cli(int playerId){
		this.PlayerId=playerId;
		out=System.out;
	}

	public void startGame(){
		System.out.println(StrColor.ANSI_RED+"                                                                                                                      \n" +
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
		int defaultSpace = 47;
		int studentCnt = 0;
		int towerCnt = 0;
		int toRemove = 0;
		StringBuilder str = new StringBuilder();
		str.append("ISLANDS:\n\n");


		// SHOWING THE TABLE

		//first row

		if(msg.getTable().size()>0) {
			toRemove = 3 - msg.getTable().size();
			if (toRemove < 0) toRemove = 0;
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("    ISLAND " + (i + 1));
				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("    " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					for (int j = 0; j < 30; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("    " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					for (int j = 0; j < 27; j++) str.append(" ");
				} else for (int j = 0; j < 35; j++) {
					str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");

			str.append("|");
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 3 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
		}

		//second row

		if(msg.getTable().size()>3) {
			toRemove = 6 - msg.getTable().size();
			if (toRemove < 0) toRemove = 0;
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("    ISLAND " + (i + 1));
				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("    " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					for (int j = 0; j < 30; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("    " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					for (int j = 0; j < 27; j++) str.append(" ");
				} else for (int j = 0; j < 35; j++) {
					str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");


			str.append("|");
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 3; i < 6 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
		}

		//third row
		if(msg.getTable().size()>6) {
			toRemove = 9 - msg.getTable().size();
			if (toRemove < 0) toRemove = 0;
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("    ISLAND " + (i + 1));
				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("    " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					for (int j = 0; j < 30; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("    " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					for (int j = 0; j < 27; j++) str.append(" ");
				} else for (int j = 0; j < 35; j++) {
					str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");


			str.append("|");
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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
			for (int i = 6; i < 9 && i < msg.getTable().size(); i++) {
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



			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
		}
		//fourth row

		if(msg.getTable().size()>9) {
			toRemove = 12 - msg.getTable().size();
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
				str.append("|");
				str.append("    ISLAND " + (i + 1));
				if (msg.getTable().get(i).GetNoEntryTile()) {
					str.append("    " + StrColor.ANSI_RED + "Ø" + StrColor.ANSI_RESET);
					for (int j = 0; j < 30; j++) str.append(" ");
				}
				else if (msg.getMotherNaturePosition() == i) {
					str.append("    " + StrColor.ANSI_RED + "-MN-" + StrColor.ANSI_RESET);
					for (int j = 0; j < 27; j++) str.append(" ");
				} else for (int j = 0; j < 34; j++) {
					str.append(" ");
				}
			}
			str.append("|");
			str.append("\n");
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");


			str.append("|");
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 9; i < 12 && i < msg.getTable().size(); i++) {
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
			for (int i = 0; i < 143 - toRemove * defaultSpace; i++) str.append("-");
			if (toRemove == 1) str.append("-");
			if (toRemove == 0) str.append("--");
			str.append("\n");
		}
		if(msg.isExpertMode()) printCharacterCards(str);



		//PRINTING THE CLOUDS

		for(int i=0;i<msg.getClouds().size();i++){
			str.append(StrColor.ANSI_YELLOW+"CLOUD "+(i+1)+StrColor.ANSI_RESET+(": "));
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

			for (int i = 0; i < 12; i++) str.append("-");
			for(int i=0;i<20;i++) str.append(" ");
		}

		//printing green students
		str.append("\n|");
		for(Player p: msg.getPlayers()){
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.GREEN){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_GREEN + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n|");

		//printing red students
		for(Player p: msg.getPlayers()){
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.GREEN){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_GREEN + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n|");


		for(Player p: msg.getPlayers()){
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.GREEN){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_GREEN + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n|");


		for(Player p: msg.getPlayers()){
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				studentCnt++;
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			for(Teacher t: msg.getTeachers()){
				if(t.getTeacherColor()==Color.GREEN){
					if(t.getControllingPlayer()==p) str.append(StrColor.ANSI_GREEN + "@"+ StrColor.ANSI_RESET);
					else str.append(" ");
				}
			}
			str.append("|                    ");
		}
		str.append("\n|");



		//entrance
		/*
			for(int j=0;j<p.getPlayersSchool().getEntranceStudentsNumber();j++) {
				if (p.getPlayersSchool().GetEntranceStudentColor(j) == Color.BLUE) {
					str.append(StrColor.ANSI_BLUE + "O" + StrColor.ANSI_RESET);
				}
				if (p.getPlayersSchool().GetEntranceStudentColor(j) == Color.GREEN) {
					str.append(StrColor.ANSI_GREEN + "O" + StrColor.ANSI_RESET);
				}
				if (p.getPlayersSchool().GetEntranceStudentColor(j) == Color.YELLOW) {
					str.append(StrColor.ANSI_YELLOW + "O" + StrColor.ANSI_RESET);
				}
				if (p.getPlayersSchool().GetEntranceStudentColor(j) == Color.RED) {
					str.append(StrColor.ANSI_RED + "O" + StrColor.ANSI_RESET);
				}
				if (p.getPlayersSchool().GetEntranceStudentColor(j) == Color.PINK) {
					str.append(StrColor.ANSI_PURPLE + "O" + StrColor.ANSI_RESET);
				}
			}



			for(int i=0;i<12;i++) str.append("-");
			str.append("\n");
			str.append("|");
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.BLUE);i++){
				studentCnt++;
				str.append(StrColor.ANSI_BLUE+"O"+StrColor.ANSI_RESET);
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			str.append("|\n|");
			studentCnt=0;
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.GREEN);i++){
				studentCnt++;
				str.append(StrColor.ANSI_GREEN+"O"+StrColor.ANSI_RESET);
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			str.append("|\n|");
			studentCnt=0;
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.RED);i++){
				studentCnt++;
				str.append(StrColor.ANSI_RED+"O"+StrColor.ANSI_RESET);
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			str.append("|\n|");
			studentCnt=0;
			for(int i=0;i<p.getPlayersSchool().getStudentNumber(Color.YELLOW);i++){
				studentCnt++;
				str.append(StrColor.ANSI_YELLOW+"O"+StrColor.ANSI_RESET);
			}
			for(int i=0;i<10-studentCnt;i++) str.append(" ");
			str.append("|\n|");
			studentCnt=0;

*/

		System.out.println(str);
	}

	@Override
	public void askInformation() {

	}


	public void printCharacterCards(StringBuilder str){
		int i=1;
		str.append("\n");
		for(CharacterCard c: msg.getCharacterCards()){
			str.append(StrColor.ANSI_BLUE+"CHARACTER "+i+": "+StrColor.ANSI_RESET);
			if(c.getIdCharacterCard()==1){
				str.append("Take 1 Student from this card and place it on an island of you choice" +StrColor.ANSI_GREEN+ "  (COST = 1)\n"+StrColor.ANSI_RESET);
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
				str.append("Choose an Island and resolve it as if Mother Nature had ended her movement there " +StrColor.ANSI_GREEN+ " (COST = 3)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==4){
				str.append("You may move Mother Nature up to 2 additional Island than indicated on the Assistant card you've played " +StrColor.ANSI_GREEN+" (COST = 1)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==5) {
				str.append("Place a No Entry tile on an island on your choice. Influence will not be calculated next time Mother Nature ends there  " +StrColor.ANSI_GREEN+ "(COST = 2)\n"+StrColor.ANSI_RESET);
				str.append("             [");
				c=(Card5)c;
				for(int j=0; j<((Card5) c).getNoEntryTilesOnCard();j++){
					str.append(StrColor.ANSI_RED+"Ø"+StrColor.ANSI_RESET);
				}
				str.append("]\n\n");
			}

			if(c.getIdCharacterCard()==6){
				str.append("when resolving a Conquering on an island, Towers do not count towards influence " +StrColor.ANSI_GREEN+ " (COST = 3)\n\n"+StrColor.ANSI_RESET);
			}

			if(c.getIdCharacterCard()==9){
				str.append("During the influence calculation this turn, you count as having 2 more influence " +StrColor.ANSI_GREEN+ " (COST = 2)\n\n"+StrColor.ANSI_RESET);
			}
			if(c.getIdCharacterCard()==10){
				str.append("Move one Student from this card to the Dining Room, then draw one Student from the Bag and place it on this card  " +StrColor.ANSI_GREEN+ "(COST = 2)\n"+StrColor.ANSI_RESET);
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
				str.append("Choose a Student Color. Every player must return 3 Students of that Color from their Dining Room to the Bag " + StrColor.ANSI_GREEN + " (COST = 3)\n\n" + StrColor.ANSI_RESET);
			}
			i++;
		}

		str.append("\n\n");
	}

	public void setMsg(ShowMatchInfoMessage msg){
		this.msg = msg;
	}
}
