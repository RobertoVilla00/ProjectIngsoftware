package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.Color;

public class Card12Message extends Message{

    private Color StudentColor;

    public Card12Message(String studentColor) {
        super(MessageContent.CARD12);

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
        }
    }
}
