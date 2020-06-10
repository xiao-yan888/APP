package com.example.guangbo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 发送
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv:

                Intent intent = new Intent("cn.boc.mtms.CHECK_VERSION_COMPLETE");
                intent.putExtra("CHECK_RESULT",true);
                intent.setComponent(new ComponentName("com.example.myapplication", "com.example.myapplication.MyReceiver"));
                sendBroadcast(intent);

                break;
        }
    }
}
