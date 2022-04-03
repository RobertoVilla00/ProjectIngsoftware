package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class MoveStudentMessageTest {
    @Test
    public void getEntrancePosition(){
        MoveStudentMessage moveStudentMessage=new MoveStudentMessage(3,1);
        int entranceIndex=moveStudentMessage.getEntrancePosition();
        assertEquals(3,entranceIndex);
    }

    @Test
    public void getDestination(){
        MoveStudentMessage moveStudentMessage=new MoveStudentMessage(3,1);
        int destination=moveStudentMessage.getDestination();
        assertEquals(0,destination);            //destination value is reduced by 1 in constructor
    }
}
