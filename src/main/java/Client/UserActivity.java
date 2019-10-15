package Client;

import Model.User;
import Until.HttpUntil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class UserActivity extends HttpUntil {

    private static Gson gson = new Gson();
    private static String loginURL = "https://api.shisanshui.rtxux.xyz/auth/login";
    private static String registerURL = "https://api.shisanshui.rtxux.xyz/auth/register";
    private static String logoutURL = "https://api.shisanshui.rtxux.xyz/auth/logout";

    public static User login(User user){
        try{
            ArrayList<Pair<String, String>> t = new ArrayList<Pair<String, String>>();
            t.add(new Pair<String, String>("json", gson.toJson(user)));
            String resJson = postRequest(loginURL, t);
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0){
                user = gson.fromJson(jsonElement.getAsJsonObject().get("data"), User.class);
                System.out.println("登录成功");
                return user;
            }else{
                System.err.println("登录失败");
                System.err.println(jsonElement.getAsJsonObject().get("data"));
                return null;
            }
        }catch (IOException ex){
            System.out.println("访问失败");
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean register(User user){
        try{
            ArrayList<Pair<String, String>> t = new ArrayList<Pair<String, String>>();
            t.add(new Pair<String, String>("json", gson.toJson(user)));
            String resJson = postRequest(registerURL, t);
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0) {
                System.out.println("注册成功");
                return true;
            }
            else{
                System.err.println("注册失败");
                return false;
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean logout(User user){
        try{
            ArrayList<Pair<String, String>> t = new ArrayList<Pair<String, String>>();
            t.add(new Pair<String, String>("X-Auth-Token", user.getToken()));
            JsonElement jsonElement = new JsonParser().parse(postRequest(logoutURL, t));
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0){
                System.out.println("注销成功");
                return true;
            }
            System.err.println("注销失败");
            System.err.println(jsonElement.getAsJsonObject().get("msg").getAsString());
            return false;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}