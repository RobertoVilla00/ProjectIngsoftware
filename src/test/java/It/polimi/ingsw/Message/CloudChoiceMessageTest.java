package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CloudChoiceMessageTest {
    @Test
    public void getCloudIndex(){
        CloudChoiceMessage cloudChoiceMessage=new CloudChoiceMessage(2);
        int index=cloudChoiceMessage.getCloudIndex();
        assertEquals(2,index);
    }
}
