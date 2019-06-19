package com.limei.movieapp.huiying.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 过
 */
public class MyTalkActivity extends AppCompatActivity {

    @Bind(R.id.fanhui)
    ImageView fanhui;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.rightss)
    TextView rightss;
    @Bind(R.id.talkshow)
    RecyclerView talkshow;

    private LinearLayoutManager manager;
    private MyTalkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_talk);
        ButterKnife.bind(this);

        title.setText("评论");

        manager = new LinearLayoutManager(this);
        adapter = new MyTalkAdapter(this);
        talkshow.setLayoutManager(manager);
        talkshow.setAdapter(adapter);
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.rightss:
                break;
        }
    }
}
