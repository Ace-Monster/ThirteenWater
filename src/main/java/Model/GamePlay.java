package Model;

import AI.Type;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GamePlay {
    @SerializedName("id")
    private int GID;
    private transient ArrayList<Poker> card;
    @SerializedName("card")
    private ArrayList<String> ans;

    public GamePlay(int GID){
        this.GID = GID;
        card = new ArrayList<Poker>();
        ans = new ArrayList<String>();
    }

    public GamePlay(int GID, Poker front, Poker middle, Poker back){
        this.GID = GID;
        card = new ArrayList<Poker>();
        ans = new ArrayList<String>();
        this.card.add(front);
        this.card.add(middle);
        this.card.add(back);
    }


    public void add(Poker card){
        this.card.add(card);
    }

    public void add(String card){
        this.ans.add(card);
    }

    public ArrayList<Poker> getCard() {
        return card;
    }
}
