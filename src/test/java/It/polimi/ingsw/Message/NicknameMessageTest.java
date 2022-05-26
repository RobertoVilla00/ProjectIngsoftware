package It.polimi.ingsw.Message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NicknameMessageTest {
	@Test
	public void getNickname() {
		NicknameMessage nicknameMessage = new NicknameMessage("Piero");
		String nickname = nicknameMessage.getNickname();
		assertEquals("Piero", nickname);
	}
}
