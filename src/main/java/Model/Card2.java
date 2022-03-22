package Model;

import java.util.ArrayList;

public class Card2 extends CharacterCard{                           //Move student from card to dining room
    private ArrayList<Color> StudentOnCard;
    public Card2(){
        SetCardCost(2);
        StudentOnCard= new ArrayList<Color>();
        for (int i=0;i<4;i++){
            AddCard2Student();
        }
    }

    public void PlayCard2(Player CardPlayer, int index){
       Color StudentColor=MoveStudentsFromCard2(index);
       CardPlayer.getPlayersSchool().AddStudentToDiningRoom(StudentColor);
        this.IncreaseCardCost();

    }

    public void AddCard2Student () {
        Color StudentColor;
        StudentColor=getMatch().getBag().FillCard();
        StudentOnCard.add(StudentColor);
    }

    public Color MoveStudentsFromCard2(int index) {
        Color StudentColor = StudentOnCard.get(index);
        StudentOnCard.remove(index);
        AddCard2Student();
        return StudentColor;
    }
}
