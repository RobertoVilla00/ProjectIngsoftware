package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class StartMessageTest {
    @Test
    public void getNumberOfPlayers(){
        StartMessage startMessage=new StartMessage(3,1);
        int numberOfPlayers=startMessage.getNumberOfPlayers();
        assertEquals(3,numberOfPlayers);
    }

    @Test
    public void getGameMode(){
        StartMessage startMessage=new StartMessage(3,1);
        int gameMode=startMessage.getGameMode();
        assertEquals(1,gameMode);
    }
}
