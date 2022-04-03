package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.Player;
import It.polimi.ingsw.Model.Teacher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test
    public void CheckTeacherControl(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        Teacher teacher=controller.getMatch().getTeacherByColor(Color.BLUE);
        Player controllingPlayer = teacher.getControllingPlayer();
        assertEquals(controller.getMatch().getPlayers()[0],controllingPlayer);
    }

    @Test
    public void CheckTeacherControlWithDraw(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        Teacher teacher=controller.getMatch().getTeacherByColor(Color.BLUE);
        Player controllingPlayer = teacher.getControllingPlayer();
        assertEquals(controller.getMatch().getPlayers()[0],controllingPlayer);
    }

    @Test
    public void CheckTeacherControlWithChange(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        Teacher teacher=controller.getMatch().getTeacherByColor(Color.BLUE);
        Player controllingPlayer = teacher.getControllingPlayer();
        assertEquals(controller.getMatch().getPlayers()[1],controllingPlayer);
    }

}
