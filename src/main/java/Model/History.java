package Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class History {
    @SerializedName("id")
    private int HID;
    private ArrayList<String> card;
    private int score;
    private int timeStamp;
    private class Detail{
        public String name;
        public ArrayList<String> card;
        int score;
        @SerializedName("player_id")
        int UID;
    }
    private ArrayList<Detail> details;

    public int getHID() {
        return HID;
    }

    public ArrayList<String> getCard() {
        return card;
    }

    public int getScore() {
        return score;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }
}
