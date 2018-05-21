package com.example.point.functionpoints.http;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 42822 on 2018/5/16.
 */

public class HttpUtil {


    public  static HttpURLConnection getHttpUrlConnection(String url)
    {
        HttpURLConnection mHttpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(15000);
            mHttpURLConnection.setReadTimeout(15000);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setRequestProperty("Connection","Keep-Alive");
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }

    public static void  postParams(OutputStream output, List<NameValuePair> paramsList) throws IOException
    {
        StringBuilder mStringBuilder = new StringBuilder();
        for(NameValuePair pair : paramsList)
        {
            if(!TextUtils.isEmpty(mStringBuilder))
            {
                mStringBuilder.append("&");
            }

            String name = URLEncoder.encode(pair.getName(),"UTF-8");
            String value = URLEncoder.encode(pair.getValue(),"UTF-8");
            mStringBuilder.append(name);
            mStringBuilder.append("=");
            mStringBuilder.append(value);

        }

        BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(output,"UTF-8"));
        writer.write(mStringBuilder.toString());
        writer.flush();
        writer.close();
    }

    private void useHttpUrlConnectionPost(String url)
    {
        InputStream mInputStream = null;
        HttpURLConnection mHttpURLConnection = HttpUtil.getHttpUrlConnection(url);

        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("username", "moon"));
        postParams.add(new BasicNameValuePair("password", "123"));

        try {
            HttpUtil.postParams(mHttpURLConnection.getOutputStream(),postParams);
            mHttpURLConnection.connect();

            mInputStream = mHttpURLConnection.getInputStream();
            int code = mHttpURLConnection.getResponseCode();
            String respose = converStreamToString(mInputStream);
            Log.i("HaiChao", "请求状态码:" + code + "\n请求结果:\n" + respose);
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String converStreamToString(InputStream inputStream) throws IOException {
        BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder mStringBuilder = new StringBuilder();
        String line =null;
        while ((line =mBufferedReader.readLine())!=null)
        {
            mStringBuilder.append(line+"\n");
        }
        String respose = mStringBuilder.toString();
        return  respose;
    }

    private HttpClient createHttpClient() {
        HttpParams mDefaultHttpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams, true);
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams, true);
        HttpClient mHttpClient = new DefaultHttpClient(mDefaultHttpParams);
        return mHttpClient;

    }

    private void useHttpClientGet(String url) {
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient mHttpClient = createHttpClient();
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.i("HaiChao", "请求状态码:" + code + "\n请求结果:\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void useHttpClientPost(String url) {
        HttpPost mHttpPost = new HttpPost(url);
        mHttpPost.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient mHttpClient = createHttpClient();
            List<NameValuePair> postParams = new ArrayList<>();
            //要传递的参数
            postParams.add(new BasicNameValuePair("username", "moon"));
            postParams.add(new BasicNameValuePair("password", "123"));
            mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.i("HaiChao", "请求状态码:" + code + "\n请求结果:\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
