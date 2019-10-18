package Client;

import Model.GameData;
import Model.GamePlay;
import Model.User;
import Until.HttpUntil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends HttpUntil {

    private static Gson gson = new Gson();
    private static String joinURL = "https://api.shisanshui.rtxux.xyz/game/open";
    private static String playURL = "https://api.shisanshui.rtxux.xyz/game/submit";

    public static GameData gameStar(User user){
        try {
            ArrayList<Pair<String, String>> t = new ArrayList<Pair<String, String>>();
            t.add(new Pair<String, String>("X-Auth-Token", user.getToken()));
            JsonElement jsonElement = new JsonParser().parse(postRequest(joinURL, t));
            return gson.fromJson(jsonElement.getAsJsonObject().get("data"), GameData.class);
        }catch (IOException ex){
            System.err.println("访问失败");
            ex.printStackTrace();
            if(ex.getMessage().equals("403")) user.logout();
            return null;
        }
    }

    public static boolean submitCard(User user, GamePlay gamePlay){
        try {
            ArrayList<Pair<String, String>> t = new ArrayList<Pair<String, String>>();
            JsonElement jsonElement = gson.toJsonTree(gamePlay);
            t.add(new Pair<String, String>("json", jsonElement.toString()));
            t.add(new Pair<String, String>("X-Auth-Token", user.getToken()));
            postRequest(playURL, t);
            return true;
        }catch (IOException ex){
            System.err.println("访问失败");
            ex.printStackTrace();
            if(ex.getMessage().equals("403")) user.logout();
            return false;
        }
    }
}
