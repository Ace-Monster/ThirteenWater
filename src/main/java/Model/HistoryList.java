package Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HistoryList {
    @SerializedName("id")
    int HID;
    ArrayList<String> card;
    int score;
    int timeStamp;

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
}
