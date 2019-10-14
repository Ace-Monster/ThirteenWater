package Client;

import Model.RankList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

import static Until.HttpUntil.initHttpClient;

public class RankActivity {
    private static String rankListURL = "https://api.shisanshui.rtxux.xyz/rank";
    private static Gson gson = new Gson();
    public static ArrayList<RankList> getRankList(){
        ArrayList<RankList> res = null;
        try {
            HttpClient httpClient = initHttpClient();
            HttpGet get = new HttpGet(rankListURL);
            HttpResponse httpResponse = httpClient.execute(get);
            res = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), new TypeToken<ArrayList<RankList>>(){}.getType());
        }catch (IOException ex){
            System.out.println("访问失败");
            ex.printStackTrace();
            return null;
        }
        System.out.println("排行榜获取成功");
        return res;
    }
}
