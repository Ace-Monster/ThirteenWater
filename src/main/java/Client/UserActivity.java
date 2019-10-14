package Client;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static Until.HttpUntil.*;

public class UserActivity {

    private static Gson gson = new Gson();
    private static String loginURL = "https://api.shisanshui.rtxux.xyz/auth/login";
    private static String registerURL = "https://api.shisanshui.rtxux.xyz/auth/register";
    private static String logoutURL = "https://api.shisanshui.rtxux.xyz/auth/logout";

    public static User login(User user){
        try{
            String resJson = postRequest(loginURL, gson.toJson(user));
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
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean register(User user){
        try{
            String resJson = postRequest(registerURL, gson.toJson(user));
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0) {
                System.out.println("注册成功");
                return true;
            }
            else{
                System.err.println("注册失败");
                System.err.println(jsonElement.getAsJsonObject().get("data").getAsString());
                return false;
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean logout(User user){
        try{
            HttpClient httpClient = initHttpClient();
            HttpPost post = new HttpPost(logoutURL);
            post.setHeader("X-Auth-Token", user.getToken());
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                System.err.println("访问失败");
                System.err.println(response.getStatusLine().getStatusCode());
                return false;
            }
            JsonElement jsonElement = new JsonParser().parse(EntityUtils.toString(response.getEntity()));
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