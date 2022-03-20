package Model;

import java.util.ArrayList;

public class Card2 extends CharacterCard{
    private ArrayList<Color> StudentOnCard;
    public Card2(){
        SetCardCost(2);
        StudentOnCard= new ArrayList<Color>();

    }
    public void AddCard2Student (Color StudentColor) {
        StudentOnCard.add(StudentColor);
    }

    public Color MoveStudentsFromCard2(int index) {
        Color StudentColor = StudentOnCard.get(index);
        StudentOnCard.remove(index);
        return StudentColor;
    }
}
