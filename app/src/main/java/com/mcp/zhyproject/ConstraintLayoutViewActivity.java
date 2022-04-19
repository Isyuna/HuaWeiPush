package com.mcp.zhyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConstraintLayoutViewActivity extends AppCompatActivity {


    Placeholder placeholder;
    TextView  textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_view);
        placeholder = findViewById(R.id.place);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder.setContentId(R.id.A);
            }
        });
    }
}