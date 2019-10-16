package Model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class GameData {
    @SerializedName("id")
    private int GID;
    @SerializedName("card")
    private String strCard;
    private int colorcounst;
    private int[] color = new int[4];
    private int[][] cards = new int[4][15];//黑桃、梅花、红桃、方块

    public int getGID() {
        return GID;
    }

    public void getCard() {
        Map<Character, Integer> hash = new HashMap<Character, Integer>();
        hash.put('$', 0);hash.put('*', 1);
        hash.put('&', 2);hash.put('#', 3);
        for(int i = 0;i < 13;i++){
            String t = strCard.substring(i*3, i*3+2);
            int c = hash.get(t.charAt(0));
            cards[c][t.charAt(1)-'0']++;
            if(color[c] == 0) colorcounst++;
            color[c]++;
        }
        return;
    }

    public int[][] cards(){
        return cards;
    }
}
