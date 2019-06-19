package com.limei.movieapp.huiying.sousuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MovesInfo;
import com.limei.movieapp.huiying.info.ReMenSouInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;


/**
 * 有不同
 */


public class MySouSuoActivity
        extends AppCompatActivity {
    private List<String> contentdata = new ArrayList();
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg2 == 0) {
                Log.i("dfdf", "fsadf");
                int i = 0;
                while (i < MySouSuoActivity.this.reMenSouInfo.getData().size()) {
                    contentdata.add(reMenSouInfo.getData().get(i).getTitle());
                    i += 1;
                }

                searchPage.setAdapter(new TagAdapter(contentdata) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView textView = (TextView) MySouSuoActivity.this.mInflater.inflate(R.layout.search_label_tv, MySouSuoActivity.this.searchPage, false);
                        textView.setText((String) o);
                        return textView;
                    }
                });

            }
            int i = paramAnonymousMessage.arg2;
            if (paramAnonymousMessage.arg2 == 2) {
                MySouSuoActivity.this.startActivity(new Intent(MySouSuoActivity.this, MySouSuoJieGuoActivity.class).putExtra("movesInfo", MySouSuoActivity.this.movesInfo));
            }
            if (paramAnonymousMessage.arg2 == 4) {
                Toast.makeText(MySouSuoActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            }
            i = paramAnonymousMessage.arg2;
            return false;
        }
    });
    private LayoutInflater mInflater;
    private MovesInfo movesInfo;
    private ReMenSouInfo reMenSouInfo;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.search_page})
    TagFlowLayout searchPage;
    @Bind({R.id.sou})
    ImageView sou;
    @Bind({R.id.sousuocon})
    EditText sousuocon;
    @Bind({R.id.title})
    RelativeLayout title;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().build();
        HttpOk.GetData(WebAdds.HTTPREMENSOU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                reMenSouInfo = gson.fromJson(s, ReMenSouInfo.class);

                if (MySouSuoActivity.this.reMenSouInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg2 = 0;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg2 = 1;
                handler.sendMessage(message);
            }
        });
    }

    private void getDataMoves(String paramString) {
        FormBody formBody = new FormBody.Builder().add("title", paramString).build();
        HttpOk.GetData(WebAdds.HTTPSOUSUO, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                movesInfo = gson.fromJson(s, MovesInfo.class);
                if (MySouSuoActivity.this.movesInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg2 = 2;
                    handler.sendMessage(message);
                    return;
                }
                if (MySouSuoActivity.this.movesInfo.getCode().equals("002")) {
                    Message message = new Message();
                    message.arg2 = 4;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg2 = 3;
                handler.sendMessage(message);
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_sou_suo);
        ButterKnife.bind(this);
        this.sousuocon.setImeOptions(3);
        this.sousuocon.setInputType(1);
        this.mInflater = LayoutInflater.from(this);
        this.sousuocon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent) {
                if ((paramAnonymousInt != 4) && ((paramAnonymousKeyEvent == null) || (paramAnonymousKeyEvent.getKeyCode() != 66))) {
                    return false;
                }
                if (paramAnonymousKeyEvent.getAction() == 1) {
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(paramAnonymousKeyEvent.getAction());
                    localStringBuilder.append("   dsf");
                    Log.i("fdf", localStringBuilder.toString());
                    String content = paramAnonymousTextView.getText().toString();

                    Log.i("fdf", paramAnonymousKeyEvent.toString());
                    MySouSuoActivity.this.getDataMoves(content);
                }
                return true;
            }
        });
        getData();
        this.searchPage.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            public boolean onTagClick(View paramAnonymousView, int paramAnonymousInt, FlowLayout paramAnonymousFlowLayout) {
                MySouSuoActivity.this.getDataMoves((String) MySouSuoActivity.this.contentdata.get(paramAnonymousInt));
                return true;
            }
        });
        this.searchPage.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            public void onSelected(Set<Integer> paramAnonymousSet) {
                MySouSuoActivity localMySouSuoActivity = MySouSuoActivity.this;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("choose:");
                localStringBuilder.append(paramAnonymousSet.toString());
                localMySouSuoActivity.setTitle(localStringBuilder.toString());
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.rightss) {
                return;
            }
            String con = this.sousuocon.getText().toString();

            getDataMoves(con);
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\sousuo\MySouSuoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */