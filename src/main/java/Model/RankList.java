package Model;

import com.google.gson.annotations.SerializedName;

public class RankList {
    @SerializedName("player_id")
    private int UID;
    private String name;
    private int score;

    public int getUID() {
        return UID;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
