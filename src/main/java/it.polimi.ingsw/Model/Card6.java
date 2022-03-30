package it.polimi.ingsw.Model;


import java.util.ArrayList;

public class Card6 extends CharacterCard{
    private ArrayList<Color>StudentsOnCard;
    public Card6(){
        SetCardCost(1);
        StudentsOnCard= new ArrayList<Color>();
        for (int i=0;i<6;i++){
            AddCard6Student();
        }
    }

    public void PlayCard6(Player CardPlayer, int EntranceIndex, int StudentIndexOnCard){
        Color StudentColorCard;
        Color StudentColorEntrance;
        StudentColorEntrance=CardPlayer.getPlayersSchool().RemoveStudentFromEntrance(EntranceIndex);
        StudentColorCard=MoveStudentsFromCard6(StudentIndexOnCard);
        StudentsOnCard.add(StudentColorEntrance);
        CardPlayer.getPlayersSchool().AddEntranceStudents(StudentColorCard);
        this.IncreaseCardCost();
    }

    public void AddCard6Student () {
        Color StudentColor;
        StudentColor=getMatch().getBag().FillCard();
        StudentsOnCard.add(StudentColor);
    }
    public Color MoveStudentsFromCard6(int index) {
        Color StudentColor = StudentsOnCard.get(index);
        StudentsOnCard.remove(index);
        AddCard6Student();
        return StudentColor;
    }
}
