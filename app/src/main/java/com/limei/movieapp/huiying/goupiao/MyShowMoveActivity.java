package com.limei.movieapp.huiying.goupiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.DianYingXinagQingInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.view.MyJzvdStd;
import com.limei.movieapp.huiying.zhifu.MyYuEActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.carbs.android.expandabletextview.library.ExpandableTextView;
import cn.jzvd.JzvdStd;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 有不同
 */


public class MyShowMoveActivity
        extends Activity {
    private MyYanZhiRenYuanAdapter adapter;
    private MyDianYingJuZhaoAdapter adapter2;
    private DianYingXinagQingInfo dianYingXinagQingInfo;
    @Bind({R.id.etv})
    ExpandableTextView etv;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private int flag;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {

            if (paramAnonymousMessage.arg1 == 0) {
                RequestOptions requestOptions = new RequestOptions().centerCrop();
                Object localObject2 = new StringBuilder();
                ((StringBuilder) localObject2).append(WebAdds.YUMING);
                ((StringBuilder) localObject2).append(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getPic());
                Glide.with(MyShowMoveActivity.this).load(((StringBuilder) localObject2).toString()).apply(requestOptions).into(MyShowMoveActivity.this.moviebg);

                videoplayer = MyShowMoveActivity.this.findViewById(R.id.videoplayer);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(WebAdds.YUMING);
                stringBuilder.append(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getPreview());
                videoplayer.setUp(stringBuilder.toString(), "预告片", 0);

                RequestOptions requestOptions1 = new RequestOptions().centerCrop();
                localObject2 = Glide.with(MyShowMoveActivity.this);
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(WebAdds.YUMING);
                localStringBuilder.append((String) MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getDrama().get(0));
                ((RequestManager) localObject2).load(localStringBuilder.toString()).apply(requestOptions1).into(videoplayer.thumbImageView);
                //Jzvd.setJzUserAction(new MyUserActionStd());

                try {
                    StringBuilder sad = new StringBuilder();
                    sad.append(MyShowMoveActivity.this.stime);
                    sad.append("000");
                    Date date = new Date(Long.parseLong(sad.toString()));
                    String string = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    MyShowMoveActivity.this.movetime.setText(string);
                } catch (Exception e) {
                }

                MyShowMoveActivity.this.etv.setText(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getContent());
                MyShowMoveActivity.this.movename.setText(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getTitle());

                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getStyle());
                stringBuilder1.append(" ");
                stringBuilder1.append(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getDtime());
                stringBuilder1.append("分钟");
                movezongtime.setText(stringBuilder1.toString());

                moveshangyingdiqu.setText(MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getAddress().getTitle());

                StringBuffer stringBuffer = new StringBuffer();
                int i = 0;
                while (i < MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getPaly().size()) {
                    StringBuilder as = new StringBuilder();
                    as.append(dianYingXinagQingInfo.getData().getPaly().get(i).getTitle());
                    as.append(" ");
                    stringBuffer.append(as.toString());
                    i += 1;
                }
                movezhuyan.setText(stringBuffer.toString());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyShowMoveActivity.this);
                linearLayoutManager.setOrientation(0);

                adapter = new MyYanZhiRenYuanAdapter(MyShowMoveActivity.this, MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getPaly());

                yanzhirenshow.setLayoutManager(linearLayoutManager);
                yanzhirenshow.setAdapter(MyShowMoveActivity.this.adapter);

                List<String> urlda = new ArrayList<>();

                for (i = 0; i < dianYingXinagQingInfo.getData().getDrama().size(); i++) {
                    urlda.add(WebAdds.YUMING + dianYingXinagQingInfo.getData().getDrama().get(i));
                }

                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MyShowMoveActivity.this);
                linearLayoutManager1.setOrientation(0);

                adapter2 = new MyDianYingJuZhaoAdapter(MyShowMoveActivity.this, urlda, juzhaoshow);

                MyShowMoveActivity.this.juzhaoshow.setLayoutManager(linearLayoutManager1);
                MyShowMoveActivity.this.juzhaoshow.setAdapter(MyShowMoveActivity.this.adapter2);


//                MyShowMoveActivity.this.adapter2.setItemClickListener(new MyDianYingJuZhaoAdapter.OnItemClickListener() {
//                    public void onItemClick(int paramAnonymous2Int) {
////                            Intent localIntent = new Intent(MyShowMoveActivity.this, MyImagePluhActivity.class);
////                            localIntent.putExtra("data", (Serializable) MyShowMoveActivity.this.dianYingXinagQingInfo.getData().getDrama());
////                            localIntent.putExtra("posn", paramAnonymous2Int);
////                            MyShowMoveActivity.this.startActivity(localIntent);
//                    }
//                });


            }
            return false;
        }
    });
    private String id;
    @Bind({R.id.juzhaoshow})
    RecyclerView juzhaoshow;
    @Bind({R.id.movename})
    TextView movename;

    @Bind({R.id.moveshangyingdiqu})
    TextView moveshangyingdiqu;
    @Bind({R.id.movetime})
    TextView movetime;
    @Bind({R.id.movezhuyan})
    TextView movezhuyan;
    @Bind({R.id.movezongtime})
    TextView movezongtime;
    @Bind({R.id.moviebg})
    ImageView moviebg;
    @Bind({R.id.startgoupiao})
    TextView startgoupiao;
    private String stime;
    @Bind({R.id.title})
    TextView title;
    @Bind({R.id.titless})
    RelativeLayout titless;
    @Bind({R.id.videoplayer})
    MyJzvdStd videoplayer;
    @Bind({R.id.yanzhirenshow})
    RecyclerView yanzhirenshow;
    @Bind(R.id.releasd)
    RelativeLayout relativeLayout;

    private void getData(String paramString) {

        FormBody formBody = new FormBody.Builder().add("id", paramString).build();
        HttpOk.GetData(WebAdds.HTTPDIANYINGXIANG, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.d("kananan", "onFailure:" + s);
                Gson gson = new Gson();
                dianYingXinagQingInfo = gson.fromJson(s, DianYingXinagQingInfo.class);

                if (MyShowMoveActivity.this.dianYingXinagQingInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 1;
                handler.sendMessage(message);
            }
        });
    }

    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_my_show_move);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        relativeLayout.setFocusable(true);
        relativeLayout.setFocusableInTouchMode(true);

        relativeLayout.requestFocus();
        this.flag = getIntent().getIntExtra("flag", 3);
        this.id = getIntent().getStringExtra("id");
        this.stime = getIntent().getStringExtra("stime");
        if (this.flag == 0) {
            this.startgoupiao.setVisibility(View.VISIBLE);
        } else if (this.flag == 1) {
            this.startgoupiao.setVisibility(View.GONE);
        }
        getData(this.id);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @OnClick({R.id.fanhui, R.id.startgoupiao})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.startgoupiao) {
                return;
            }
            startActivity(new Intent(this, MyYuEActivity.class).putExtra("id", this.id));
            return;
        }
        finish();
    }

}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\goupiao\MyShowMoveActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

