package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotherNatureMessageTest {
	@Test
	public void getSteps() {
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(4);
		int steps = motherNatureMessage.getSteps();
		assertEquals(4, steps);
	}
}
