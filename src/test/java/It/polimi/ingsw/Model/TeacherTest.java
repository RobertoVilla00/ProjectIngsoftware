package It.polimi.ingsw.Model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeacherTest {

    @Test
    public void getControllingPlayerColor(){
        Player player = new Player(TowerColor.BLACK, "Tullio");
        Teacher teacher = new Teacher(Color.BLUE);
        teacher.ChangeController(player);
        TowerColor towerColor = teacher.getControllingPlayerColor();
        assertEquals(towerColor, TowerColor.BLACK);
    }

    @Test
    public void IncreaseHighestNumberOfStudents(){
        Teacher teacher = new Teacher(Color.BLUE);
        for(int i=0;i<3;i++) teacher.IncreaseHighestNumberOfStudents();
        int Number = teacher.getHighestNumberOfStudents();
        assertEquals(Number, 3);
    }
}
