package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.*;

public class Card10MessageTest {
	@Test
	public void getStudentIndex() {
		Card10Message card10Message = new Card10Message(4);
		int studentIndex = card10Message.getStudentIndex();
		assertEquals(3, studentIndex);
	}
}
