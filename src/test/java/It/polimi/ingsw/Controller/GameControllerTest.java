package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Message.*;
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
    @Test(expected = InvalidInputException.class)
    public void MoveStudentWithInvalidDestination() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2,-2);
        controller.MoveStudent(moveStudentMessage);
    }

    @Test (expected = InvalidInputException.class)
    public void MoveStudentToDiningRoomFull() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddEntranceStudents(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(10,0);
        controller.MoveStudent(moveStudentMessage);
    }

    @Test (expected = InvalidInputException.class)
    public void MoveStudentWithInvalidStudentIndex() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(-1,0);
        controller.MoveStudent(moveStudentMessage);
    }

    @Test
    public void MoveStudent() throws InvalidInputException, NoActivePlayerException {
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
    public void MoveStudentToDiningRoom() throws InvalidInputException, NoActivePlayerException {
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
    public void MoveStudentToIsland() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        Color studentColor=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
        MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2,7);
        controller.MoveStudent(moveStudentMessage);
        int numberOfStudents=controller.getMatch().getTable().get(6).CountStudents(studentColor);
        assertEquals(1,numberOfStudents);
    }

    @Test(expected = InvalidInputException.class)
    public void MoveMotherNatureWith0Steps() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        MotherNatureMessage motherNatureMessage=new MotherNatureMessage(0);
        controller.MoveMotherNature(motherNatureMessage);
    }
    @Test(expected = InvalidInputException.class)
    public void MoveMotherNatureWithNegativeSteps() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        MotherNatureMessage motherNatureMessage=new MotherNatureMessage(-1);
        controller.MoveMotherNature(motherNatureMessage);
    }

    @Test(expected = InvalidInputException.class)
    public void MoveMotherNatureWithTooManySteps() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(1);
        MotherNatureMessage motherNatureMessage=new MotherNatureMessage(4);
        controller.MoveMotherNature(motherNatureMessage);
    }
    @Test
    public void MoveMotherNatureWithCorrectSteps() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(6); // 4 movements
        MotherNatureMessage motherNatureMessage=new MotherNatureMessage(4);
        controller.MoveMotherNature(motherNatureMessage);
        assertEquals(4,controller.getMatch().getMotherNaturePosition());
    }
    @Test(expected = InvalidInputException.class)
    public void ChooseCloudInvalidCloudIndex() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(4);
        controller.ChooseCloud(cloudChoiceMessage);
    }

    @Test
    public void ChooseCloud() throws InvalidInputException, NoActivePlayerException {
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
    public void ChooseCloud2() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().PlanningPhase();
        CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(3);
        controller.ChooseCloud(cloudChoiceMessage);
        int entranceStudents=controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber();
        assertEquals(13,entranceStudents);
    }
    @Test(expected = InvalidInputException.class)
    public void PlayAssistantCardNegativeIndex() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(-1);
        controller.PlayAssistantCard(assistantCardMessage);

    }
    @Test(expected = InvalidInputException.class)
    public void PlayAssistantCardIndexGreaterThenCardCount() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(11);
        controller.PlayAssistantCard(assistantCardMessage);
    }
    @Test(expected = InvalidInputException.class)
    public void PlayAssistantCardTwoPlayerWithSameValue() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(6);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(6);
        controller.PlayAssistantCard(assistantCardMessage);
    }
    @Test
    public void PlayAssistantCardTwoPlayerWithDifferentValue() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(6);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(7);
        controller.PlayAssistantCard(assistantCardMessage);
    }
    @Test(expected = InvalidInputException.class)
    public void PlayAssistantCardThreePlayerSameValue() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(6);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(7);
        controller.PlayAssistantCard(assistantCardMessage);
        controller.setActivePlayer(2);
        AssistantCardMessage assistantCardMessage1=new AssistantCardMessage(6);
        controller.PlayAssistantCard(assistantCardMessage1);
    }

    @Test
    public void PlayAssistantCardTwoPlayerWithValue() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(2,1);
        controller.InitializeGame(startMessage);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(1);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(2);
        controller.PlayAssistantCard(assistantCardMessage);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(2);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage1=new AssistantCardMessage(3);
        controller.PlayAssistantCard(assistantCardMessage1);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(3);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage2=new AssistantCardMessage(4);
        controller.PlayAssistantCard(assistantCardMessage2);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(4);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage3=new AssistantCardMessage(5);
        controller.PlayAssistantCard(assistantCardMessage3);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(4);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage4=new AssistantCardMessage(3);
        controller.PlayAssistantCard(assistantCardMessage4);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(3);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage5=new AssistantCardMessage(2);
        controller.PlayAssistantCard(assistantCardMessage5);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(2);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage6=new AssistantCardMessage(1);
        controller.PlayAssistantCard(assistantCardMessage6);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(0);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage7=new AssistantCardMessage(1);
        controller.PlayAssistantCard(assistantCardMessage7);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(0);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage8=new AssistantCardMessage(0);
        controller.PlayAssistantCard(assistantCardMessage8);
        controller.setActivePlayer(0);
        controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(0);
        controller.setActivePlayer(1);
        AssistantCardMessage assistantCardMessage9=new AssistantCardMessage(0);
        controller.PlayAssistantCard(assistantCardMessage9);

    }
    @Test
    public void setActivePlayer() throws InvalidInputException, NoActivePlayerException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(2,1);
        controller.InitializeGame(startMessage);
        controller.setActivePlayer(1);
        assertEquals(1,controller.getActivePlayer());
    }



}
