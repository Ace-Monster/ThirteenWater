package Client;

import Model.HistoryList;
import Model.User;
import Until.HttpUntil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class HistoryActivity {

    private static Gson gson = new Gson();
    private static String ListURL = "https://api.shisanshui.rtxux.xyz/history";
    private static String DetailURL = "https://api.shisanshui.rtxux.xyz/history";

    public static ArrayList<HistoryList> getHistoryList(User user){
        ArrayList<HistoryList> res = null;
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("player_id", Integer.toString(user.getUID())));
        params.add(new BasicNameValuePair("limit", "100"));
        params.add(new BasicNameValuePair("page", "0"));
        try {
            String resJson = HttpUntil.getRequest(ListURL + "?" +
                            EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)), user.getToken());
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0){
                res = gson.fromJson(jsonElement.getAsJsonObject().get("data").toString(),
                        new TypeToken<ArrayList<HistoryList>>(){}.getType());
            }else {
                System.out.println("获取失败");
                return null;
            }
        }catch (IOException ex){
            System.out.println("访问失败");
            ex.printStackTrace();
            return null;
        }
        System.out.println("历史战绩列表获取成功");
        return res;
    }
}
