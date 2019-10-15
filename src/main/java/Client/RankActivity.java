package Client;

import Model.RankList;
import Until.HttpUntil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class RankActivity extends HttpUntil {
    private static String rankListURL = "https://api.shisanshui.rtxux.xyz/rank";
    private static Gson gson = new Gson();
    public static ArrayList<RankList> getRankList(){
        ArrayList<RankList> res = null;
        try {
            res = gson.fromJson(getRequest(rankListURL), new TypeToken<ArrayList<RankList>>(){}.getType());
        }catch (IOException ex){
            System.out.println("访问失败");
            ex.printStackTrace();
            return null;
        }
        System.out.println("排行榜获取成功");
        return res;
    }
}
