package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hnshangyu.testgreendao.greendao.UserDao;

public class MainActivity extends AppCompatActivity {

    /**
     * Hello World!
     */
    private TextView mTv;
    private EditText mEt;
    private String string2;
    private String q;
    /**
     * 张三
     */
    private TextView mTvName;
    /**
     * 21
     */
    private TextView mTvAge;
    private UserDao userDao;
    private List<User> list = new ArrayList<>();
    private StudentDaoUtil studentDaoUtil;
    /**
     * 21
     */
    private TextView mTvId;
    /**
     * 21
     */
    private TextView mTvPai;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDaoUtil = new StudentDaoUtil(this);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cn.boc.mtms.CHECK_VERSION_COMPLETE");//要和发送广播Action一致
        registerReceiver(receiver, filter);
        // DaoSession daoSession = MyApp.getDaoSession();
        // userDao = daoSession.getUserDao();
        initView();

    }
   /* public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("cn.boc.mtms.CHECK_VERSION_COMPLETE")){
                boolean check_result = intent.getBooleanExtra("CHECK_RESULT", false);
                if (check_result) {
                    Log.i("MyTag", "onReceive: 获取到YangLiWei!");
                    mTv.setText("获取到YangLiWei!");
                }else {
                    Log.i("MyTag", "onReceive: 没有获取到");
                }
            }

        }




    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
      /* // mEt = (EditText) findViewById(R.id.et);
        String rule = "^[";
        String s = "1-7";
        String substring = s.substring(s.indexOf("-") + 1);
        int i = Integer.parseInt(substring);
        Log.e("aaaa", substring);
        Log.e("aaaaaaaa", i + "");
        //Integer text_type = 7;
        StringBuilder str = new StringBuilder();
        Map<Integer, String> resMap = new HashMap<>();
        resMap.put(1, "0-9");
        resMap.put(2, "a-zA-Z");
        resMap.put(4, "\\\\u4e00-\\\\u9fa5");
        resMap.put(8, "-+*;/\\\\.\\–\\①\\②\\③\\④\\⑤\\⑥\\⑦\\⑧\\⑨\\⑩\\⑪\\⑫\\⑬\\\"\\<\\>\\~\\．\\·\\,\\:\\!\\\\[\\\\]\\@\\#\\$\\%\\^\\&\\_\\=\\?\\(\\)\\{\\}\\\\s");
        resMap.put(16, "～———－/\\\\（\\）\\【\\】\\《\\》\\*\\%\\￥\\#\\@\\？\\〈\\〉\\•\\！\\。\\，\\；\\：\\“\\”\\‘\\’\\、");
        resMap.put(32, "[0-9]{1,5}(\\\\.[0-9]{1,2})");

        for (Integer key1 : resMap.keySet()) {
            Integer keyNum = key1;
            if (keyNum <= i) {
                String res = resMap.get(key1);
                str.append(res);
            }
        }
        string2 = rule + str.toString() + "]$";
        mTv.setText("正则表达式         " + string2);
        //String string1 = mEt.getText().toString();
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isEmote(s.toString())) {
                    //删除输入的表情，开始位置和结束位置，表情符号长度为2个，所以减2
                    // s.delete(s.length() - 2, s.length());
                    Toast.makeText(MainActivity.this,"格式正确",Toast.LENGTH_LONG);
                } else {
                    //textView.setText("(" + s.length() + "/" + "200" + ")");
                    Toast.makeText(MainActivity.this,"输入格式不正确",Toast.LENGTH_LONG);

                }
            }    //可以在这个方法里面操作完成

            @Override
            public void afterTextChanged(Editable s) {
                //如果为true

            }
        });*/
        mTvPai = (TextView) findViewById(R.id.tv_pai);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvAge = (TextView) findViewById(R.id.tv_age);
        mTvId = (TextView) findViewById(R.id.tv_id);
        int a = 2;
        int a1 = 1;
        long b = (int) a;
        long b1 = (int) a1;
        User user = new User(b, "张三", 21);
        User user1 = new User(b1, "李四", 22);
        list.add(user);
        list.add(user1);
        //userDao.insert(user);
        studentDaoUtil.insertMultStudent(list);
        List<User> users = studentDaoUtil.queryAll();
        mTv.setText(users.get(0).getAge() + "");
        mTvName.setText(users.get(0).getName());
        mTvAge.setText(users.get(1).getAge()+"");
        mTvId.setText(users.get(1).getName());

        Collections.sort(users, new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                long i = o1.getId() - o2.getId();
                if (i == 0) {
                    return o1.getAge() - o2.getAge();
                }

                return (int) i;
            }
        });

        for (User use : users) {
            System.out.println("score:" + use.getName() + ":age" + use.getAge());

        }
        studentDaoUtil.deleteAll(User.class);
        studentDaoUtil.insertMultStudent(users);
        List<User> users1 = studentDaoUtil.queryAll();
        mTvPai.setText(users1.get(0).getName()+"  "+users1.get(0).getAge()+"  "+users1.get(1).getName()+"  "+users1.get(1).getAge());


        //mEt = (EditText) findViewById(R.id.et);
    }

    public boolean isEmote(String content) {
        //String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(string2);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();

    }


}
