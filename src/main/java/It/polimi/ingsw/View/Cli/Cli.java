package It.polimi.ingsw.View.Cli;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Model.TowerColor;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.View.View;

import java.io.PrintStream;

public class Cli extends Observable implements View {
	private final PrintStream out;
	private ShowMatchInfoMessage msg;
	private int PlayerId;

	public Cli(){
		out=System.out;
	}

	public void startGame(){
		System.out.println(StrColor.ANSI_RED+"                                                                                                                      \n" +
				"         .**ooooOOOOOOOOOOOOOO*                 .oOOo.                                                                    \n" +
				"      .o##o**°*@@@#.°°°°°*o#@@@°                #@@@@o                                   °#.                              \n" +
				"     *@@*     #@@@#          *#@o               .O#O*                                   *@@.                              \n" +
				"    .@@O     o@@@@#  °oOOOO*   °* .***   °***.  °***.       .°*oo**o*   °**°  .****  °oO@@@oo* .*oo°   .oOO*.  °ooOOo**O. \n" +
				"    °@@o     o@@@@O*#@@O**O@*   .o#@@@*°#@@@@#°oO@@@#     *O#@O*O@@@O *O@@@#.o@@@@@o *o@@@@o**o#@@@@O. o@@@@#°O@#***O@@o  \n" +
				"    .#@#.    o@@@@#@@o.   °O.   *°#@@@##*o@@#°...@@@#   .O@@#.  o@@@O.**@@@###*o@@@O   #@@@  oo*°O@@@#° .O@@@o@@#Oo*°   . \n" +
				"     °@@O°   o@@@@@#.             O@@@o. o#*    .@@@#.  O@@@°   o@@@O  .@@@@o  *@@@O. .@@@@.      o@@@#* *@@* *@@@@@@#o*..\n" +
				"      .o##Oo.o@@@@@.           °* #@@@*  .      .@@@#  .@@@@*..*O@@@O  °@@@@.  *@@@O  .#@@@. ..  ..*@@@#o#@* *o°°o#@@@@@°.\n" +
				"         ..°.*@@@@O          .*#* O@@@*         .@@@#°°.#@@@@@@#O@@@O.°°@@@@.  *@@@o.°.#@@@*oo°..  .*@@@@O°  *@O°.  *#@@°.\n" +
				"            .o@@@@O°......°*o#@*  o@#o.          o@@#o* °#@@@#* .O@@Oo°.#@#o   °#@@O*° o@@@O*... .  .*@@o..  °o@#OOooOO*..\n" +
				"          °*oO####OoooooooOO##*   ..              .°.     .°.     .°.   ..      .°°.    °°°. .   ...*O#*.°.    .°°°°°...  \n" +
				"                                                                                                ..°o#@o°°...  .           \n" +
				"                                                                                              ...°o@@@*°°.                \n" +
				"                       																		°°*OO°...                  \n"+StrColor.ANSI_RESET);

	}

	@Override
	public void showGameInformation() {

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
				if (msg.getMotherNaturePosition() == i) {
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
				if (msg.getMotherNaturePosition() == i) {
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
				if (msg.getMotherNaturePosition() == i) {
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
				if (msg.getMotherNaturePosition() == i) {
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
		if(msg.isExpertMode()) printCharacterCards();




		System.out.println(str);
	}

	@Override
	public void askInformation() {

	}


	public void printCharacterCards(){}

	public void setMsg(ShowMatchInfoMessage msg){
		this.msg = msg;
	}
}
