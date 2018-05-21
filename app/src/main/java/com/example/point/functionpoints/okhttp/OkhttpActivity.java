package com.example.point.functionpoints.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.point.functionpoints.R;

import java.io.IOException;

/**
 * Created by 42822 on 2018/4/12.
 */

public class OkhttpActivity extends Activity implements View.OnClickListener{

    private Button BTN_connect;
    private TextView TV_showStatus;

    private OkHttpUitl okHttpUitl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
    }

    public void initView()
    {
        okHttpUitl = new OkHttpUitl(this);

        BTN_connect =findViewById(R.id.BTN_connect);
        BTN_connect.setOnClickListener(this);
        TV_showStatus = findViewById(R.id.TV_showStatus);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.BTN_connect:
                //okHttpUitl.getAsynHttp("http://10.0.2.2:8080/WebServlet/TestServlet");
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           String str = okHttpUitl.getSyncHttp("http://10.0.2.2:8080/WebServlet/TestServlet");
                           showlog(str);
                           //Toast.makeText(OkhttpActivity.this,str,Toast.LENGTH_LONG).show();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }).start();
                break;
        }
    }

    public void showlog(String log)
    {
        Log.i("OkhttpActivity",log);
    }
}
