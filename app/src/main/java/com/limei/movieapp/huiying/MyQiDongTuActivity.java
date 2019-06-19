package com.limei.movieapp.huiying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.limei.movieapp.huiying.shouye.MyShouYeActivity;
import com.limei.movieapp.huiying.unit.Unit;

/**
 * 相同
 */
public class MyQiDongTuActivity extends AppCompatActivity {

    private String[] strs;
    private boolean isYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qi_dong_tu);

        init();

        if (isYou == false) {
            startActivity(new Intent(this, MainActivity.class).putExtra("is", "istrue").putExtra("yanzheng", 1));
            finish();
        } else {
            startActivity(new Intent(this, MyShouYeActivity.class));
            finish();
        }
    }

    private void init() {
        try {
            String haha = Unit.load(this);
            strs = haha.split(",");
            String k = strs[1];
            Log.i("liu1145", k + "     asd");
            isYou = true;
        } catch (Exception e) {
            isYou = false;

        }
    }
}
