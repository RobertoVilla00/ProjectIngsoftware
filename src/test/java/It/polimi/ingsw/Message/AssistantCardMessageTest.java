package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssistantCardMessageTest {
    @Test
    public void getCardIndex(){
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(5);
        int index=assistantCardMessage.getCardIndex();
        assertEquals(5,index);
    }

    @Test
    public void getMessageContent(){
        AssistantCardMessage assistantCardMessage=new AssistantCardMessage(5);
        MessageContent content=assistantCardMessage.getMessageContent();
        assertEquals(MessageContent.ASSISTANTCARD,content);
    }
}
