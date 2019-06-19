/**
 * 添加
 */
package com.limei.movieapp.huiying.playvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.view.MyJzvdStd;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class MyBenDiPlayVideoActivity
        extends AppCompatActivity {
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private String img;
    private String path;
    private String[] str;
    private String[] strs;
    @Bind({R.id.videoplayer})
    MyJzvdStd videoplayer;

    private void getDataZhengZaiXiang(String paramString1, String paramString2) {
        FormBody formBody = new FormBody.Builder().add("uid", paramString1).add("ticketnumber", paramString2).build();
        HttpOk.GetData(WebAdds.HTTPDELEHUANCUN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_my_ben_di_play_video);
        ButterKnife.bind(this);
        this.path = getIntent().getStringExtra("path");
        this.img = getIntent().getStringExtra("img");
        this.str = Unit.load(this).split(",");
        Log.i("fdfdf", this.path);
        this.strs = this.path.split("/");
        this.videoplayer.setUp(this.path, "", 0);
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(this).load(this.img).apply(requestOptions).into(this.videoplayer.thumbImageView);

        this.videoplayer.startVideo();
        this.fanhui.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                long l = MyBenDiPlayVideoActivity.this.videoplayer.getCurrentPositionWhenPlaying() / 1000L;
                MyBenDiPlayVideoActivity.this.finish();
            }
        });
    }

}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\playvideo\MyBenDiPlayVideoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */