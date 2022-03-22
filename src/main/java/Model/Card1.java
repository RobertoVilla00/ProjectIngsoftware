package Model;

import java.util.ArrayList;

public class Card1 extends CharacterCard{
    private ArrayList<Color> StudentsOnCard;
    public Card1(){
        SetCardCost(1);
        StudentsOnCard= new ArrayList<Color>();
        for (int i=0;i<4;i++){
            AddCard1Student();
        }
    }

    public void PlayCard1(int StudentIndex, int IslandIndex){
        Color StudentColor;
        StudentColor=StudentsOnCard.get(StudentIndex);
        getMatch().getTable().get(IslandIndex).AddStudent(StudentColor);
        AddCard1Student();
        this.IncreaseCardCost();
    }

    public void AddCard1Student () {
        Color StudentColor;
        StudentColor=getMatch().getBag().FillCard();
        StudentsOnCard.add(StudentColor);
    }
}
