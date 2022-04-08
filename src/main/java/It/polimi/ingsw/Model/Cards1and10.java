package It.polimi.ingsw.Model;

import java.util.ArrayList;

public class Cards1and10 extends CharacterCard{

    private ArrayList<Color> StudentsOnCard;

    public Cards1and10(int idCharacterCard, int cardCost, Match currentMatch){
        super(idCharacterCard,cardCost,currentMatch);
        StudentsOnCard =new ArrayList<Color>();
    }

    public void AddStudent(){
        Color StudentColor=getMatch().getBag().FillCard();
        StudentsOnCard.add(StudentColor);
    }

    public int getNumberOfStudents(){
        return StudentsOnCard.size();
    }

    public Color RemoveStudent(int index){
        Color StudentColor=StudentsOnCard.get(index);
        StudentsOnCard.remove(index);
        return StudentColor;
    }

    public Color GetStudentColor(int index){
        Color StudentColor=StudentsOnCard.get(index);
        return StudentColor;
    }
}
