package Until;

import javafx.util.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class HttpUntil {
    private static HttpClient initHttpClient(){
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
        return httpClient;
    }

    private static HttpResponse Request(HttpUriRequest request) throws IOException{
        HttpClient httpClient = initHttpClient();
        HttpResponse response = httpClient.execute(request);
        Integer statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != HttpStatus.SC_OK) {
            System.err.println("访问失败");
            throw new IOException(statusCode.toString());
        }
        return response;
    }

    protected static String postRequest(String url, ArrayList<Pair<String, String>> params)throws IOException{
        HttpPost post = new HttpPost(url);
        for(int i = 0;i < params.size();i++){
            Pair t = params.get(i);
            if(t.getKey() == "json"){
                post.setHeader("Content-type", "application/json");
                StringEntity entity = new StringEntity(t.getValue().toString(), Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
            }else post.setHeader(t.getKey().toString(), t.getValue().toString());
        }
        return EntityUtils.toString(Request(post).getEntity());
    }

    protected static String getRequest(String url)throws IOException{
        HttpGet get = new HttpGet(url);
        return EntityUtils.toString(Request(get).getEntity());
    }

    protected static String getRequest(String url, String token)throws IOException{
        HttpGet get = new HttpGet(url);
        get.setHeader("X-Auth-Token", token);
        return EntityUtils.toString(Request(get).getEntity());
    }
}