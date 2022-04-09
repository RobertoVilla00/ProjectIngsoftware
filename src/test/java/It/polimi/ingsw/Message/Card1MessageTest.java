package It.polimi.ingsw.Message;

import org.junit.Test;
import static org.junit.Assert.*;
public class Card1MessageTest {
    @Test
    public void getStudentOnCardIndex(){
        Card1Message card1Message=new Card1Message(5,7);
        int studentOnCardIndex=card1Message.getStudentOnCardIndex();
        assertEquals(4,studentOnCardIndex);
    }

    @Test
    public void getIslandIndex(){
        Card1Message card1Message=new Card1Message(5,7);
        int studentOnCardIndex=card1Message.getIslandIndex();
        assertEquals(6,studentOnCardIndex);
    }
}
