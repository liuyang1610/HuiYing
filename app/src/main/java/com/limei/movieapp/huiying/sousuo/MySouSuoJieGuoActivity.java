/**
 * 添加
 */
package com.limei.movieapp.huiying.sousuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.fenlei.MyContentAdapter;
import com.limei.movieapp.huiying.goupiao.MyShowMoveActivity;
import com.limei.movieapp.huiying.info.MovesInfo;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

public class MySouSuoJieGuoActivity
        extends AppCompatActivity {
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private MovesInfo movesInfo;
    @Bind({R.id.moveshow})
    RecyclerView moveshow;
    private MyContentAdapter myContentAdapter;
    private GridLayoutManager myContentmanager;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.title})
    TextView title;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_sou_suo_jie_guo);
        ButterKnife.bind(this);
        this.title.setText("电影");
        this.movesInfo = ((MovesInfo) getIntent().getSerializableExtra("movesInfo"));
        this.myContentmanager = new GridLayoutManager(this, 3);
        this.myContentAdapter = new MyContentAdapter(this, this.movesInfo);
        this.moveshow.setLayoutManager(this.myContentmanager);
        this.moveshow.setAdapter(this.myContentAdapter);
        this.myContentAdapter.setOnItemClickListener(new MyContentAdapter.OnItemClickListener() {
            public void onItemClick(View paramAnonymousView, int paramAnonymousInt) {
                MySouSuoJieGuoActivity.this.startActivity(new Intent(MySouSuoJieGuoActivity.this, MyShowMoveActivity.class).putExtra("flag", 0).putExtra("id", ((MovesInfo.DataEntity) MySouSuoJieGuoActivity.this.movesInfo.getData().get(paramAnonymousInt)).getId()));
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        if (paramView.getId() != R.id.fanhui) {
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\sousuo\MySouSuoJieGuoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */