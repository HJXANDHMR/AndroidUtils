package com.example.hjx.androidutils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import utils.NetUtil;
import utils.view.CheckBoxView;


public class MainActivity extends Activity {
    private Context mContext = MainActivity.this;
    private CheckBoxView checkBoxView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.bt_cutscreen);
        checkBoxView = (CheckBoxView) findViewById(R.id.check1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxView.isChecked()){
                    button.setText("选中了checkBox");
                }else{
                    button.setText("没有选中checkBox");
                }
            }
        });


        checkBoxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxView.toggle();
            }
        });

    }

}
