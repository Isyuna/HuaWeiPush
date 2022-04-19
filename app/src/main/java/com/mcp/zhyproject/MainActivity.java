package com.mcp.zhyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.common.utils.QRCodeUtil;
import com.zhy.login.LoginMainActivity;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    TextView text;

    private TextView speechText;
    private Button speechButton;
    private Button mThreadPoolExecute;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        speechText = findViewById(R.id.tv);
        speechButton = findViewById(R.id.btn);
        mThreadPoolExecute = findViewById(R.id.thread_pool_execute);
        imageView = findViewById(R.id.image_view);
        findViewById(R.id.constraintLayout_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ConstraintLayoutViewActivity.class));

                imageView.setImageBitmap(QRCodeUtil.
                        createQRCodeBitmap("111", 200, "冀AR8757", 0.2F));
            }
        });
        //为button添加监听
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginMainActivity.class));
            }
        });
        findViewById(R.id.recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyRecyclerViewActivity.class));
            }
        });


//        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });


        //创建基本线程池
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
        /**
         * 基本线程池使用
         */
        mThreadPoolExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 30; i++) {
                    final int finali = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                Log.d("Thread", "run: " + finali);
                                Log.d("当前线程：", Thread.currentThread().getName());
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


}