package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("cn.boc.mtms.CHECK_VERSION_COMPLETE")){
            boolean check_result = intent.getBooleanExtra("CHECK_RESULT", false);
            if (check_result) {
                Log.i("MyTag", "onReceive: 获取到YangLiWei!");
            }else {
                Log.i("MyTag", "onReceive: 没有获取到");
            }
        }

    }




}
