package Model;

import com.google.gson.annotations.SerializedName;

public class GameData {
    @SerializedName("id")
    private int GID;
    @SerializedName("card")
    private String strCard;

    public int getGID() {
        return GID;
    }

    public String getStrCard() {
        return strCard;
    }

    public void setGID(int GID) {
        this.GID = GID;
    }

    public void setStrCard(String strCard) {
        this.strCard = strCard;
    }
}
