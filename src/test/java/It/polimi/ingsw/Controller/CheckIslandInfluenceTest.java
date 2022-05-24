package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.TowerColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckIslandInfluenceTest {

    @Test
    public void CheckIslandInfluence() throws InvalidInputException {
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
    public void CheckIslandInfluenceWithTower() throws InvalidInputException {
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
    public void CheckIslandInfluenceMultiStudents() throws InvalidInputException {
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
    public void CheckIslandInfluenceMultiTowers() throws InvalidInputException {
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
    public void CheckIslandInfluenceDraw() throws InvalidInputException {
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
    public void CheckIslandInfluenceWithTowersAndDraw() throws InvalidInputException {
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

    @Test
    public void CheckIslandInfluenceWithAdditionalPoints() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getPlayers()[1].setAdditionalPoints(true);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.BLACK,towerColor);
    }

    @Test
    public void CheckIslandWithCard6Played() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(0).IncreaseTower();
        controller.getMatch().setPlaysCard6(true);
        controller.CheckIslandInfluence(0);
        TowerColor towerColor=controller.getMatch().getTable().get(0).getTowersColor();
        assertEquals(TowerColor.WHITE,towerColor);
    }

    @Test public void CheckIslandNoEntryTile() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        while(controller.getMatch().getCharacterCardsOnTable()[0].getIdCharacterCard()!=5){
            controller.InitializeGame(startMessage);
        }
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getTable().get(0).AddStudent(Color.BLUE);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(0).IncreaseTower();
        controller.getMatch().getTable().get(0).setNoEntryTile();
        controller.CheckIslandInfluence(0);
        assertFalse(controller.getMatch().getTable().get(0).GetNoEntryTile());
    }

    @Test public void CheckIslandNoEntryTile2() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        controller.getMatch().getTable().get(0).setNoEntryTile();
        controller.CheckIslandInfluence(0);
        assertNull(controller.getMatch().getTable().get(0).getTowersColor());
    }
}
