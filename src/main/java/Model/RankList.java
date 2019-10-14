package Model;

import com.google.gson.annotations.SerializedName;

public class RankList {
    @SerializedName("player_id")
    int UID;
    String name;
    int score;

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
