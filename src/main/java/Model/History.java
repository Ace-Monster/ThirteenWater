package Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class History {
    @SerializedName("id")
    private int HID;
    private ArrayList<String> card = new ArrayList<>();
    private int score;
    private int timeStamp;
    public class Detail{
        public String name;
        public ArrayList<String> card = new ArrayList<>();
        public int score;
        @SerializedName("player_id")
        public int UID;
    }
    @SerializedName("detail")
    private ArrayList<Detail> details = new ArrayList<>();

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
