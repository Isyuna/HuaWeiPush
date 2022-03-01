package com.mcp.huaweipush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private final String TAG = "MainActivity";

    TextView text;

    private TextToSpeech tts;
    private TextView speechText;
    private Button speechButton;
    private Button mThreadPoolExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        //初始化TTS
//        tts = new TextToSpeech(this, this);
        speechText = findViewById(R.id.tv);
        speechButton = findViewById(R.id.btn);
        mThreadPoolExecute = findViewById(R.id.thread_pool_execute);
        findViewById(R.id.constraintLayout_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ConstraintLayoutViewActivity.class));
            }
        });
        //为button添加监听
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tts.speak("bob,we go play ping-pang,hello word！哈哈哈", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        findViewById(R.id.recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyRecyclerViewActivity.class));
            }
        });
        getToken();

        //创建基本线程池
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
        /**
         * 基本线程池使用
         */
        mThreadPoolExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0;i<30;i++){
                    final int finali = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                Log.d("Thread", "run: "+finali);
                                Log.d("当前线程：",Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    threadPoolExecutor.execute(runnable);
                }
            }
        });

    }

    private void getToken() {
        // 创建一个新线程
        new Thread() {
            @Override
            public void run() {
                try {
                    // 从agconnect-service.json文件中读取appId
                    String appId = "104668321";

                    // 输入token标识"HCM"
                    String tokenScope = "HCM12";
                    String token = HmsInstanceId.getInstance(MainActivity.this).getToken(appId, tokenScope);
                    Log.i(TAG, "get token: " + token);
                    // 判断token是否为空
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token);
                    }
                } catch (ApiException e) {
                    Log.e(TAG, "get token failed, " + e);
                }
            }
        }.start();
    }

    private void sendRegTokenToServer(String token) {
        Log.i(TAG, "sending token to server. token:" + token);
    }

    @Override
    public void onInit(int status) {
        // 判断是否转化成功
        if (status == TextToSpeech.SUCCESS) {
            //默认设定语言为中文，原生的android貌似不支持中文。
            int result = tts.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                Toast.makeText(MainActivity.this, R.string.notAvailable, Toast.LENGTH_SHORT).show();
            } else {
                //不支持中文就将语言设置为英文
                tts.setLanguage(Locale.CHINESE);
            }
        }
    }
}