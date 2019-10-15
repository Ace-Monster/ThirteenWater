package Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GamePlay {
    @SerializedName("id")
    private int GID;
    private ArrayList<String> card;
    public GamePlay(int GID, String firstCard, String secondCard, String thirdCard){
        this.GID = GID;
        this.card.add(firstCard);
        this.card.add(secondCard);
        this.card.add(thirdCard);
    }
}
