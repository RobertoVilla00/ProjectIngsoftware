package It.polimi.ingsw.Message;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Model.Color;
import org.junit.Test;
import static org.junit.Assert.*;
public class Card12MessageTest {
    @Test
    public void getBlueStudent() throws InvalidInputException {
        Card12Message card12Message=new Card12Message("blue");
        Color color=card12Message.getStudentColor();
        assertEquals(Color.BLUE,color);
    }

    @Test
    public void getRedStudent() throws InvalidInputException {
        Card12Message card12Message=new Card12Message("Red");
        Color color=card12Message.getStudentColor();
        assertEquals(Color.RED,color);
    }

    @Test
    public void getPinkStudent() throws InvalidInputException {
        Card12Message card12Message=new Card12Message("pinK");
        Color color=card12Message.getStudentColor();
        assertEquals(Color.PINK,color);
    }

    @Test
    public void getYellowStudent() throws InvalidInputException {
        Card12Message card12Message=new Card12Message("YELLOW");
        Color color=card12Message.getStudentColor();
        assertEquals(Color.YELLOW,color);
    }

    @Test
    public void getGreenStudent() throws InvalidInputException {
        Card12Message card12Message=new Card12Message("GREen");
        Color color=card12Message.getStudentColor();
        assertEquals(Color.GREEN,color);
    }
}
