package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Message.CloudChoiceMessage;
import It.polimi.ingsw.Message.MotherNatureMessage;
import It.polimi.ingsw.Message.MoveStudentMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test(expected = InvalidInputException.class)
    public void InitializeGame() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(1,1);
        controller.InitializeGame(startMessage);
    }
    @Test(expected = InvalidInputException.class)
    public void InitializeGame1() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,4);
        controller.InitializeGame(startMessage);
    }

    @Test
    public void CheckIslandMergeWithNoTower() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.CheckIslandMerge(1);
        assertEquals(controller.getMatch().getTable().size(), 12);
    }
    @Test
    public void CheckIslandMergeWithAdjacentIndexAndSameTowerColor() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(4).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(5).BuildTower(TowerColor.BLACK);
        controller.CheckIslandMerge(5);
        assertEquals(controller.getMatch().getTable().size(), 11);
    }
    @Test
    public void CheckIslandMergeWithAdjacentIndexAndNotSameTowerColor() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(4).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(5).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(4);
        assertEquals(controller.getMatch().getTable().size(), 12);
    }
    @Test
    public void CheckIslandMergeWithNotAdjacentIndexAndSameTowerColor() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(2).BuildTower(TowerColor.GREY);
        controller.getMatch().getTable().get(8).BuildTower(TowerColor.GREY);
        controller.CheckIslandMerge(2);
        assertEquals(controller.getMatch().getTable().size(), 12);
    }
    @Test
    public void CheckIslandMergeWithFirstAndLastIndexAndSameTowerColor() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.GREY);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
        controller.CheckIslandMerge(0);
        assertEquals(controller.getMatch().getTable().size(), 11);
    }
    @Test
    public void CheckIslandMergeWithFirstAndLastIndexAndSameTowerColor1() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.GREY);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
        controller.CheckIslandMerge(11);
        assertEquals(controller.getMatch().getTable().size(), 11);
    }
    @Test
    public void CheckIslandMergeWithNotFirstAndLastIndexAndSameTowerColor2() throws InvalidInputException {
        GameController controller = new GameController();
        StartMessage startMessage = new StartMessage(3, 1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(10).BuildTower(TowerColor.GREY);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
        controller.CheckIslandMerge(11);
        assertEquals(controller.getMatch().getTable().size(), 11);
    }


    @Test
    public void CheckTeacherControl() throws InvalidInputException {
        GameController controller=new GameController();StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
        controller.CheckTeacherControl(Color.BLUE,controller.getMatch().getPlayers()[0]);
        Teacher teacher=controller.getMatch().getTeacherByColor(Color.BLUE);
        Player controllingPlayer = teacher.getControllingPlayer();
        assertEquals(controller.getMatch().getPlayers()[0],controllingPlayer);
    }

    @Test
    public void CheckTeacherControlWithDraw() throws InvalidInputException {
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
    public void CheckTeacherControlWithChange() throws InvalidInputException {
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

    @Test
    public void MoveStudent() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        Color studentColor=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2,0);
        controller.MoveStudent(moveStudentMessage);
        int numberOfStudents=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber();
        assertEquals(8,numberOfStudents);
    }

    @Test
    public void MoveStudentToDiningRoom() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        Color studentColor=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2,0);
        controller.MoveStudent(moveStudentMessage);
        int numberOfStudents=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getStudentNumber(studentColor);
        assertEquals(1,numberOfStudents);
    }

    @Test
    public void MoveStudentToIsland() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        Color studentColor=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2,7);
        controller.MoveStudent(moveStudentMessage);
        int numberOfStudents=controller.getMatch().getTable().get(6).CountStudents(studentColor);
        assertEquals(1,numberOfStudents);
    }

    @Test
    public void MoveMotherNature() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        MotherNatureMessage motherNatureMessage=new MotherNatureMessage(4);
        controller.MoveMotherNature(motherNatureMessage);
        int position=controller.getMatch().getMotherNaturePosition();
        assertEquals(4,position);
    }

    @Test
    public void ChooseCloud() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().PlanningPhase();
        CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(3);
        controller.ChooseCloud(cloudChoiceMessage);
        int studentsOnCloud=controller.getMatch().getClouds().get(2).CloudSize();
        assertEquals(0,studentsOnCloud);
    }

    @Test
    public void ChooseCloud2() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().PlanningPhase();
        CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(3);
        controller.ChooseCloud(cloudChoiceMessage);
        int entranceStudents=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber();
        assertEquals(13,entranceStudents);
    }
}
