package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Message.CloudChoiceMessage;
import It.polimi.ingsw.Message.MotherNatureMessage;
import It.polimi.ingsw.Message.MoveStudentMessage;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.Player;
import It.polimi.ingsw.Model.Teacher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test
    public void CheckTeacherControl() throws InvalidInputException {
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
