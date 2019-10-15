package Model;

import com.google.gson.annotations.SerializedName;

public class GameData {
    @SerializedName("id")
    private int GID;
    @SerializedName("card")
    private String strCard;
    private int[][] cards = new int[4][15];//黑桃、梅花、红桃、方块

    public int getGID() {
        return GID;
    }

    public void getCard() {
        for(int i = 0;i < 13;i++){
            String t = strCard.substring(i*3, i*3+2);
            //if(t.)
        }
        return;
    }
}
