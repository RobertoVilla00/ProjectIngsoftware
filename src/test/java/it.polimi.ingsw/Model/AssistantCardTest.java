package it.polimi.ingsw.Model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssistantCardTest {

    @Test
    public void getOrderValue(){
        AssistantCard card = new AssistantCard(5,5);
        int OrderValue=card.getOrderValue();
        assertEquals(5,OrderValue);
    }

    @Test
    public void getMovementValue(){
        AssistantCard card = new AssistantCard(3,6);
        int MovementValue=card.getMovement();
        assertEquals(6,MovementValue);
    }
}
