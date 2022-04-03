package It.polimi.ingsw.Model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getPlayerColor(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        TowerColor color= player.getPlayerColor();
        assertEquals(TowerColor.BLACK,color);
    }

    @Test
    public void getName(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        String name= player.getName();
        assertEquals("Mario",name);
    }

    @Test
    public void getSchool(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        School school=player.getPlayersSchool();
        school.AddStudentToDiningRoom(Color.BLUE);
        school.AddStudentToDiningRoom(Color.BLUE);
        School playerSchool= player.getPlayersSchool();
        int BlueStudents=playerSchool.getStudentNumber(Color.BLUE);
        assertEquals(2,BlueStudents);
    }

    @Test
    public void getSchoolNotNull(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        School school=player.getPlayersSchool();
        assertNotNull(school);
    }

    @Test
    public void IncreaseNumberOfTowers(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.IncreaseTowersPlaced(4);
        assertEquals(4,player.getTowersPlaced());
    }

    @Test
    public void DecreaseNumberOfTowers(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.IncreaseTowersPlaced(4);
        player.DecreaseTowersPlaced(2);
        assertEquals(2,player.getTowersPlaced());
    }

    @Test
    public void AddAndReduceCoins(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.AddCoin(1);
        player.AddCoin(3);
        player.RemoveCoins(2);
        assertEquals(3,player.getCoins());
    }

    @Test
    public void GetPlayedMovements(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.PlayAssistantCard(6);
        int movements=player.GetPlayedMovements();
        assertEquals(4,movements);
    }

    @Test
    public void GetPlayedOrderValue(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.PlayAssistantCard(6);
        int orderValue=player.GetPlayedOrderValue();
        assertEquals(7,orderValue);
    }

    @Test
    public void IncreaseMovements(){
        Player player=new Player(TowerColor.BLACK,"Mario",0);
        player.PlayAssistantCard(6);
        player.IncreaseMovements(2);
        int movements=player.GetPlayedMovements();
        assertEquals(6,movements);
    }

    @Test
    public void OrderTest(){
        Player[] players=new Player[3];
        players[0]=new Player(TowerColor.BLACK,"Mario",0);
        players[1]=new Player(TowerColor.WHITE,"Sandro",1);
        players[2]=new Player(TowerColor.GREY,"Giulio",2);
        players[0].PlayAssistantCard(5);//ordervalue=6
        players[1].PlayAssistantCard(3);//ordervalue=4
        players[2].PlayAssistantCard(7);//ordervalue=8
        Arrays.sort(players);
        assertTrue(players[1].getPlayerColor()==TowerColor.BLACK);
    }

    @Test
    public void getPlayerId(){
        Player[] players=new Player[3];
        players[0]=new Player(TowerColor.BLACK,"Mario",0);
        players[1]=new Player(TowerColor.WHITE,"Sandro",1);
        players[2]=new Player(TowerColor.GREY,"Giulio",2);
        int playerId=players[1].getPlayerId();
        assertEquals(1,playerId);
    }
}
