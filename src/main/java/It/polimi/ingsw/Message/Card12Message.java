package It.polimi.ingsw.Message;
import Exceptions.InvalidInputException;
import It.polimi.ingsw.Model.Color;

public class Card12Message extends Message{

    private Color StudentColor;

    public Card12Message(String studentColor) throws InvalidInputException {
        super(MessageContent.CARD12);

        studentColor.toUpperCase();
        switch (studentColor){
            case("GREEN"):
                this.StudentColor=Color.GREEN;
                break;
            case("YELLOW"):
                this.StudentColor=Color.YELLOW;
                break;
            case("RED"):
                this.StudentColor=Color.RED;
                break;
            case("BLUE"):
                this.StudentColor=Color.BLUE;
                break;
            case("PINK"):
                this.StudentColor=Color.PINK;
                break;
            default:
                throw new InvalidInputException("invalid input, please insert a valid Color");
        }
    }

    public Color getStudentColor() {
        return StudentColor;
    }
}
