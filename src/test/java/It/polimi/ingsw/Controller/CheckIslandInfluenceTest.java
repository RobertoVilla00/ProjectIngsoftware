package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.TowerColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CheckIslandInfluenceTest {

    @Test
    public void CheckIslandInfluence(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.BLACK,towerColor);
    }

    @Test
    public void CheckIslandInfluenceWithTower(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.CheckIslandInfluence(0);
        for (int i = 0; i <3 ; i++) {
            controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
            controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        }
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.WHITE,towerColor);
    }

    @Test
    public void CheckIslandInfluenceMultiStudents(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.CheckIslandInfluence(0);
        for (int i = 0; i <3 ; i++) {
            controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.RED);
            controller.CheckTeacherControl(Color.RED,controller.getMatch().getPlayers()[0]);
        }
        controller.getMatch().getTable().get(0).AddStudent(Color.RED);
        controller.getMatch().getTable().get(0).AddStudent(Color.RED);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.BLACK,towerColor);
    }

    @Test
    public void CheckIslandInfluenceMultiTowers(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.CheckIslandInfluence(0);
        for (int i = 0; i <3 ; i++) {
            controller.getMatch().getTable().get(0).AddStudent(Color.RED);
        }
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.RED);
        controller.CheckTeacherControl(Color.RED,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getTable().get(0).IncreaseTower();
        controller.getMatch().getTable().get(0).IncreaseTower();
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.BLACK,towerColor);
    }

    @Test
    public void CheckIslandInfluenceDraw(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.RED);
        controller.CheckTeacherControl(Color.RED,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).AddStudent(Color.RED);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertNull(towerColor);
    }

    @Test
    public void CheckIslandInfluenceWithTowersAndDraw(){
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[1]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.CheckIslandInfluence(0);
        for (int i = 0; i <3 ; i++) {
            controller.getMatch().getTable().get(0).AddStudent(Color.RED);
        }
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.RED);
        controller.CheckTeacherControl(Color.RED,controller.getMatch().getPlayers()[0]);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.BLACK,towerColor);
    }
}
