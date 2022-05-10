package It.polimi.ingsw.View.Cli;

import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.View.View;

import java.io.PrintStream;

public class Cli extends Observable implements View {
	private final PrintStream out;


	public Cli(){
		out=System.out;
	}

	public void startGame(){
		out.println("                                                                                                                      \n" +
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
				"                       																		°°*OO°...                  \n");

	}

}
