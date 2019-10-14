package Until;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpUntil {
    public static HttpClient initHttpClient(){
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
        return httpClient;
    }

    public static String postRequest(String url, String json) throws IOException {
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
        Integer statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != HttpStatus.SC_OK) throw new IOException(statusCode.toString());
        return EntityUtils.toString(response.getEntity());
    }

    public static String getRequest(String url, String token) throws IOException{
        HttpClient httpClient = initHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("X-Auth-Token", token);
        HttpResponse response = httpClient.execute(get);
        Integer statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != HttpStatus.SC_OK) throw new IOException(statusCode.toString());
        return EntityUtils.toString(response.getEntity());
    }
}
