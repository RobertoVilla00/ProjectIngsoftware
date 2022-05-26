package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.*;

public class Card3and5MessageTest {
	@Test
	public void getIslandIndex() {
		Card3and5Message card3and5Message = new Card3and5Message(7);
		int islandIndex = card3and5Message.getIslandIndex();
		assertEquals(6, islandIndex);
	}
}
