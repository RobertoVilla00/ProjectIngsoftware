package It.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BagTest {

    @Test
    public void NotNull() {
        Bag b = new Bag(24);
        Color c = b.getFirstElement();
        assertNotNull(c);
    }

    @Test
    public void FillClouds() {
        Bag b = new Bag(24);
        Color StudentColor = b.getFirstElement();
        assertEquals(StudentColor,b.FillClouds());
    }

    @Test
    public void FillIsland() {
        Bag b = new Bag(24);
        Color StudentColor = b.getFirstElement();
        assertEquals(StudentColor,b.FillIsland());
    }

    @Test
    public void FillCard() {
        Bag b = new Bag(24);
        Color StudentColor = b.getFirstElement();
        assertEquals(StudentColor,b.FillCard());
    }

    @Test
    public void RemoveStudent(){
        Bag bag = new Bag(24);
        int GreenStudents=0;
        int RedStudents=0;
        int BlueStudents=0;
        int YellowStudents=0;
        int PinkStudents=0;
        for(int i=0 ; i<120; i++){
            Color studentColor;
            studentColor=bag.getFirstElement();
            bag.RemoveFirstElement();
            switch (studentColor){
                case GREEN:GreenStudents++;
                    break;
                case RED:RedStudents++;
                    break;
                case BLUE:BlueStudents++;
                    break;
                case PINK:PinkStudents++;
                    break;
                case YELLOW:YellowStudents++;
                    break;
            }
        }
        assertEquals(24,BlueStudents);
    }

    @Test
    public void AddStudent() {
        Bag bag = new Bag(24);
        bag.AddStudent(Color.RED);
        int GreenStudents=0;
        int RedStudents=0;
        int BlueStudents=0;
        int YellowStudents=0;
        int PinkStudents=0;
        for(int i=0 ; i<121; i++){
            Color studentColor;
            studentColor=bag.getFirstElement();
            bag.RemoveFirstElement();
            switch (studentColor){
                case GREEN:GreenStudents++;
                    break;
                case RED:RedStudents++;
                    break;
                case BLUE:BlueStudents++;
                    break;
                case PINK:PinkStudents++;
                    break;
                case YELLOW:YellowStudents++;
                    break;
            }
        }
        assertEquals(25,RedStudents);
    }
    @Test
    public void bagSize(){
        Bag bag = new Bag(24);
        assertEquals(120,bag.BagSize());
    }
}