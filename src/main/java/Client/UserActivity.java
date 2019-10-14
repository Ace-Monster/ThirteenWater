package Client;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class UserActivity {

    private static String loginURL = "https://api.shisanshui.rtxux.xyz/auth/login";
    private static String registerURL = "https://api.shisanshui.rtxux.xyz/auth/register";

    private static HttpClient initHttpClient(){
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
        return httpClient;
    }

    private static String request(String url, String json) throws IOException{
        HttpPost post = null;
        HttpClient httpClient = initHttpClient();

        post = new HttpPost(url);
        // 构造消息头
        post.setHeader("Content-type", "application/json");
        // 构建消息实体
        StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);
        //读取服务器返回过来的json字符串数据
        return EntityUtils.toString(response.getEntity());
    }

    public static boolean login(User user){
        Gson gson = new Gson();
        try{
            String resJson = request(loginURL, gson.toJson(user));
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0){
                user = gson.fromJson(jsonElement.getAsJsonObject().get("data"), User.class);
                System.out.println("OK");
                return true;
            }else{
                System.err.println("登录失败");
                System.err.println(jsonElement.getAsJsonObject().get("data"));
                return false;
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean register(User user){
        Gson gson = new Gson();
        try{
            String resJson = request(registerURL, gson.toJson(user));
            JsonElement jsonElement = new JsonParser().parse(resJson);
            if(jsonElement.getAsJsonObject().get("status").getAsInt() == 0)
                return true;
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
}