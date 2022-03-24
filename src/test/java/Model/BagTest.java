package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BagTest {

    @Test
    public void getFirstElement(){
        Bag b=new Bag();
        Color c=b.getFirstElement();
        assertEquals(c,b.getFirstElement());
    }
    @Test
    public void RemoveFirstElement() {
        Bag b=new Bag();
        b.RemoveFirstElement();
        System.out.println(b.getFirstElement());
    }
    @Test
    public void FillClouds(){
        Bag b=new Bag();
        Color StudentColor=b.getFirstElement();
        System.out.println(StudentColor);
    }
    @Test
    public void FillIsland(){
        Bag b=new Bag();
        Color StudentColor=b.getFirstElement();
        System.out.println(StudentColor);
    }
    @Test
    public void FillCard(){
        Bag b=new Bag();
        Color StudentColor=b.getFirstElement();
        System.out.println(StudentColor);
    }
    @Test
    public void AddStudent(){
        Bag b=new Bag();
        b.AddStudent(Color.BLUE);

    }

    }






