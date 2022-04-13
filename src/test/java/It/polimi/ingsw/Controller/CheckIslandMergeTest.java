package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Message.StartMessage;
import It.polimi.ingsw.Model.TowerColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CheckIslandMergeTest {

    @Test
    public void checkIslandMerge() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(5).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(4).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(6).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(5);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(10,TableSize);
    }

    @Test
    public void checkIslandMerge2() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(5).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(4).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(6).BuildTower(TowerColor.BLACK);
        controller.CheckIslandMerge(5);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }

    @Test
    public void checkIslandMergeLimit() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(1).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(0);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(10,TableSize);
    }

    @Test
    public void checkIslandMergeLimit2() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(10).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(11);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(10,TableSize);
    }

    @Test
    public void checkIslandMergeLimit3() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(10).BuildTower(TowerColor.BLACK);
        controller.CheckIslandMerge(11);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }

    @Test
    public void checkIslandMergeLimit4() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(10).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(11);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }

    @Test
    public void checkIslandMergeLimit5() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(1).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(0);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }

    @Test
    public void checkIslandMergeLimit6() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(1).BuildTower(TowerColor.BLACK);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.WHITE);
        controller.CheckIslandMerge(0);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }

    @Test
    public void checkIslandMergeLimit7() throws InvalidInputException {
        GameController controller=new GameController();
        StartMessage startMessage=new StartMessage(3,1);
        controller.InitializeGame(startMessage);
        controller.getMatch().getTable().get(0).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(1).BuildTower(TowerColor.WHITE);
        controller.getMatch().getTable().get(11).BuildTower(TowerColor.BLACK);
        controller.CheckIslandMerge(0);
        int TableSize=controller.getMatch().getTable().size();
        assertEquals(11,TableSize);
    }
}
