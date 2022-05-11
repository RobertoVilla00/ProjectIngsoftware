package It.polimi.ingsw.View;

import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.Match;
import It.polimi.ingsw.View.Cli.Cli;
import It.polimi.ingsw.View.Cli.StrColor;
import org.junit.Test;

public class CliTest {


    @Test
    public void StartGame(){
        System.out.println(StrColor.ANSI_PURPLE+"                                                                                                                      \n" +
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

    @Test

    public void ShowMatchInfo(){
        Cli cli = new Cli();
        ShowMatchInfoMessage msg;
        Match match = new Match(2, 1);
        for(int i=0;i<2;i++){
            match.MoveStudentsBagToIsland(0);
        }
        for(int i=0;i<12;i++){
            match.MoveStudentsBagToIsland(1);
        }
        for(int i=0;i<2;i++){
            match.MoveStudentsBagToIsland(2);
        }
        for(int i=0;i<2;i++){
            match.MoveStudentsBagToIsland(3);
        }

        //match.MergeIslands(0,1);
        match.MergeIslands(0,1);

        msg = new ShowMatchInfoMessage(match);
        cli.setMsg(msg);
        cli.startGame();
        cli.showGameInformation();

    }
}
