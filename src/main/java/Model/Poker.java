package Model;

import javafx.util.Pair;

import java.util.ArrayList;

public class Poker {

    private long rank;
    private ArrayList<Pair<Integer, Integer>> pk;

    private String regColor(int s){
        if(s == 0) return "$";
        if(s == 1) return "*";
        if(s == 2) return "&";
        return "#";
    }

    private String regDig(int s){
        if(s == 9) return "10";
        if(s == 10) return "J";
        if(s == 11) return "Q";
        if(s == 12) return "K";
        if(s == 13) return "A";
        return Integer.toString(s+1);
    }

    public Poker(long rank){
        this.rank = rank;
        pk = new ArrayList<Pair<Integer, Integer>>();
    }

    public void add(int color, int digital){
        pk.add(new Pair<Integer, Integer>(color, digital));
    }

    public long getRank() {
        return rank;
    }

    public void addRank(long rank) {
        this.rank += rank;
    }

    public ArrayList<Pair<Integer, Integer>> getPk() {
        return pk;
    }

    @Override
    public String toString() {
        String res = "";
        for(int k = 0;k < pk.size();k++) {
            res += regColor(pk.get(k).getKey())+regDig(pk.get(k).getValue());
            if(k != pk.size()-1) res += " ";
        }
        return res;
    }

    public Poker clone(){
        Poker res = new Poker(rank);
        for(int i = 0;i < pk.size();i++){
            res.add(pk.get(i).getKey(), pk.get(i).getValue());
        }
        return res;
    }
}
