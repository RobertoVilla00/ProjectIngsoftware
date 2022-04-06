package It.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class Cards1and10Test {
    @Test
    public void AddStudents(){
        Match match = new Match(3,1);
        Cards1and10 cards1and10= new Cards1and10(1,1,match);
        cards1and10.AddStudent();
        Color color=cards1and10.GetStudentColor(0);
        assertNotNull(color);
    }

    @Test
    public void RemoveStudents(){
        Match match = new Match(3,1);
        Cards1and10 cards1and10= new Cards1and10(1,1,match);
        cards1and10.AddStudent();
        Color studentColor=cards1and10.GetStudentColor(0);
        Color color =cards1and10.RemoveStudent(0);
        assertEquals(studentColor,color);
    }

}
