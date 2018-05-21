package com.example.point.functionpoints.okhttp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 42822 on 2018/4/12.
 */

public class OkHttpUitl {

    private Activity context;

    public OkHttpUitl(Activity context)
    {
        this.context = context;
    }

    //1.异步GET请求
    // 最简单的GET请求
    /*基本的步骤很简单，就是创建OkHttpClient、Request和Call，最后调用Call的enqueue()方法。
    但是每次这么写肯定是很麻烦，肯定是要进行封装的。需要注意的是onResponse回调并不是在UI线程。
    注：onResponse回调的参数是response，一般情况下，
    比如我们希望获得返回的字符串，可以通过response.body().string()获取；
    如果希望获得返回的二进制字节数组，则调用response.body().bytes()；
    如果你想拿到返回的inputStream，则调用response.body().byteStream()。*/
    public void getAsynHttp(String url) {
        OkHttpClient mokHttpClient =new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showlog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                showlog(str);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //2.同步GET请求
    //同步Get请求和异步调用区别就是调用了call的execute()方法。
    /*注意同步GET请求的调用必须放在子线程中执行，不然会报NetworkOnMainThreadException。*/
    public String getSyncHttp(String url) throws IOException{
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response mResponse=call.execute();
        if (mResponse.isSuccessful()) {
            return mResponse.body().string();
        } else {
            throw new IOException("Unexpected code " + mResponse);
        }
    }

    //3.异步POST请求
    //post与get不同的就是要创建RequestBody并传进Request中，同样onResponse回调不是在UI线程。
    private void postAsynHttp() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("topicId", "1002")
                .add("maxReply", "-1")
                .add("reqApp", "1")
                .build();

        Request request = new Request.Builder()
                .url("http://61.129.89.191/SoarAPI/api/SoarTopic")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showlog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //1.异步上传文件
    //上传文件本身也是一个POST请求，首先定义上传文件类型：
    public final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    //将sdcard根目录的demo.txt文件上传到服务器上：
    /*当然如果想要改为同步的上传文件只要调用 mOkHttpClient.newCall(request).execute()就可以了。
      在demo.txt文件中有一行字“测试OkHttp异步上传”我们运行程序点击发送文件按钮，最终请求网络返回的结果就是我们txt文件中的内容

        当然不要忘了添加如下权限：
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>*/
    private void postAsynFile() {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        File file = new File("/sdcard/demo.txt");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showlog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                showlog(response.body().string());
            }
        });
    }
    //2.异步下载图片
    //下载图片本身也是一个GET请求
    /*对于图片下载，文件下载其实是类似的；图片下载是通过回调的Response拿到byte[]然后decode成图片；
    文件下载则是拿到inputStream做写文件操作，我们这里就不赘述了。*/
    private void getAsynFile() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showlog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] data = response.body().bytes();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        //image.setImageBitmap(bmp);
                    }
                });
            }
        });
    }

    public void showlog(String log)
    {
        Log.i("OkhttpUtil",log);
    }

    private void tryOne()    {
        //getAsynHttp();
        /*try {
            getSyncHttp();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        postAsynHttp();
        postAsynFile();
    }
}
