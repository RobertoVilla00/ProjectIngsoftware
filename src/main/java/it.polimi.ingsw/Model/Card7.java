package it.polimi.ingsw.Model;


public class Card7 extends CharacterCard{                           //Exchange students between entrance and dining room
    public Card7(){
        SetCardCost(1);
    }

    public void PlayCard7(Player CardPlayer, int EntranceIndex, Color DiningRoomColor){
        Color StudentColor;
        StudentColor=CardPlayer.getPlayersSchool().RemoveStudentFromEntrance(EntranceIndex);
        CardPlayer.getPlayersSchool().AddStudentToDiningRoom(StudentColor);
        CardPlayer.getPlayersSchool().RemoveStudentFromDiningRoom(DiningRoomColor);
        CardPlayer.getPlayersSchool().AddEntranceStudents(DiningRoomColor);
        this.IncreaseCardCost();
    }

}
