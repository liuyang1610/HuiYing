package com.limei.movieapp.huiying.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.goupiao.MyShowMoveActivity;
import com.limei.movieapp.huiying.info.FenLeiInfo;
import com.limei.movieapp.huiying.info.MovesInfo;
import com.limei.movieapp.huiying.sousuo.MySouSuoActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.io.IOException;

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


public class MyFenLeiActivity
        extends AppCompatActivity {
    private MyFenLeiAdapter adapter;
    private MyFenLeiAdapter adapter1;
    private MyFenLeiAdapter adapter2;
    private String add = "0";
    @Bind({R.id.diqushow})
    RecyclerView diqushow;
    private FenLeiInfo fenLeiInfo;
    @Bind({R.id.fenggeshow})
    RecyclerView fenggeshow;
    @Bind({R.id.fenlei})
    ImageView fenlei;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg2 == 0) {
                FenLeiInfo.DataEntity.AddressEntity localObject = new FenLeiInfo.DataEntity.AddressEntity();
                FenLeiInfo.DataEntity.StyleEntity localStyleEntity = new FenLeiInfo.DataEntity.StyleEntity();
                FenLeiInfo.DataEntity.StarEntity localStarEntity = new FenLeiInfo.DataEntity.StarEntity();
                ((FenLeiInfo.DataEntity.AddressEntity) localObject).setId("0");
                ((FenLeiInfo.DataEntity.AddressEntity) localObject).setPx("");
                ((FenLeiInfo.DataEntity.AddressEntity) localObject).setTitle("全部");
                localStyleEntity.setId("0");
                localStyleEntity.setPx("");
                localStyleEntity.setTitle("全部");
                localStarEntity.setId("0");
                localStarEntity.setPx("");
                localStarEntity.setTitle("全部");
                MyFenLeiActivity.this.fenLeiInfo.getData().getAddress().add(0, localObject);
                MyFenLeiActivity.this.fenLeiInfo.getData().getStyle().add(0, localStyleEntity);
                MyFenLeiActivity.this.fenLeiInfo.getData().getStar().add(0, localStarEntity);

                LinearLayoutManager manager = new LinearLayoutManager(MyFenLeiActivity.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                adapter = new MyFenLeiAdapter(MyFenLeiActivity.this, MyFenLeiActivity.this.fenLeiInfo.getData().getAddress(), MyFenLeiActivity.this.fenLeiInfo.getData().getStyle(), MyFenLeiActivity.this.fenLeiInfo.getData().getStar(), 0);
                MyFenLeiActivity.this.diqushow.setLayoutManager(manager);
                MyFenLeiActivity.this.diqushow.setAdapter(MyFenLeiActivity.this.adapter);
                MyFenLeiActivity.this.adapter.setOnItemClickListenerAdd(new MyFenLeiAdapter.OnItemClickListenerAdd() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        add = fenLeiInfo.getData().getAddress().get(paramAnonymous2Int).getId();
                        MyFenLeiActivity.this.getDataAll(MyFenLeiActivity.this.add, MyFenLeiActivity.this.sty, MyFenLeiActivity.this.star);
                        int i = 0;
                        while (i < MyFenLeiActivity.this.fenLeiInfo.getData().getAddress().size()) {
                            ((FenLeiInfo.DataEntity.AddressEntity) MyFenLeiActivity.this.fenLeiInfo.getData().getAddress().get(i)).setIskoAdd(true);
                            i += 1;
                        }
                        ((FenLeiInfo.DataEntity.AddressEntity) MyFenLeiActivity.this.fenLeiInfo.getData().getAddress().get(paramAnonymous2Int)).setIskoAdd(false);
                        MyFenLeiActivity.this.adapter.notifyDataSetChanged();
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyFenLeiActivity.this);
                linearLayoutManager.setOrientation(0);
                adapter1 = new MyFenLeiAdapter(MyFenLeiActivity.this, MyFenLeiActivity.this.fenLeiInfo.getData().getAddress(), MyFenLeiActivity.this.fenLeiInfo.getData().getStyle(), MyFenLeiActivity.this.fenLeiInfo.getData().getStar(), 1);
                MyFenLeiActivity.this.fenggeshow.setLayoutManager(linearLayoutManager);
                MyFenLeiActivity.this.fenggeshow.setAdapter(MyFenLeiActivity.this.adapter1);
                MyFenLeiActivity.this.adapter1.setmOnItemClickListenerSty(new MyFenLeiAdapter.OnItemClickListenerSty() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        sty = fenLeiInfo.getData().getStyle().get(paramAnonymous2Int).getId();
                        MyFenLeiActivity.this.getDataAll(MyFenLeiActivity.this.add, MyFenLeiActivity.this.sty, MyFenLeiActivity.this.star);
                        int i = 0;
                        while (i < MyFenLeiActivity.this.fenLeiInfo.getData().getStyle().size()) {
                            ((FenLeiInfo.DataEntity.StyleEntity) MyFenLeiActivity.this.fenLeiInfo.getData().getStyle().get(i)).setIskoStyle(true);
                            i += 1;
                        }
                        ((FenLeiInfo.DataEntity.StyleEntity) MyFenLeiActivity.this.fenLeiInfo.getData().getStyle().get(paramAnonymous2Int)).setIskoStyle(false);
                        MyFenLeiActivity.this.adapter1.notifyDataSetChanged();
                    }
                });
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MyFenLeiActivity.this);
                linearLayoutManager1.setOrientation(0);
                adapter2 = new MyFenLeiAdapter(MyFenLeiActivity.this, MyFenLeiActivity.this.fenLeiInfo.getData().getAddress(), MyFenLeiActivity.this.fenLeiInfo.getData().getStyle(), MyFenLeiActivity.this.fenLeiInfo.getData().getStar(), 2);
                MyFenLeiActivity.this.mingxingshow.setLayoutManager(linearLayoutManager1);
                MyFenLeiActivity.this.mingxingshow.setAdapter(MyFenLeiActivity.this.adapter2);
                MyFenLeiActivity.this.adapter2.setmOnItemClickListenerStar(new MyFenLeiAdapter.OnItemClickListenerStar() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        star = fenLeiInfo.getData().getStar().get(paramAnonymous2Int).getId();
                        MyFenLeiActivity.this.getDataAll(MyFenLeiActivity.this.add, MyFenLeiActivity.this.sty, MyFenLeiActivity.this.star);
                        int i = 0;
                        while (i < MyFenLeiActivity.this.fenLeiInfo.getData().getStar().size()) {
                            fenLeiInfo.getData().getStar().get(i).setIsIskoStar(true);
                            i += 1;
                        }
                        fenLeiInfo.getData().getStar().get(paramAnonymous2Int).setIsIskoStar(false);
                        MyFenLeiActivity.this.adapter2.notifyDataSetChanged();
                    }
                });
            }

            if (paramAnonymousMessage.arg2 == 2) {
                MyFenLeiActivity.this.shouyeshow.setVisibility(View.VISIBLE);
                myContentmanager = new GridLayoutManager(MyFenLeiActivity.this, 3);
                myContentAdapter = new MyContentAdapter(MyFenLeiActivity.this, MyFenLeiActivity.this.movesInfo);
                MyFenLeiActivity.this.shouyeshow.setLayoutManager(MyFenLeiActivity.this.myContentmanager);
                MyFenLeiActivity.this.shouyeshow.setAdapter(MyFenLeiActivity.this.myContentAdapter);
                MyFenLeiActivity.this.myContentAdapter.setOnItemClickListener(new MyContentAdapter.OnItemClickListener() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        MyFenLeiActivity.this.startActivity(new Intent(MyFenLeiActivity.this, MyShowMoveActivity.class).putExtra("flag", 0).putExtra("id", ((MovesInfo.DataEntity) MyFenLeiActivity.this.movesInfo.getData().get(paramAnonymous2Int)).getId()));
                    }
                });
            }
            if (paramAnonymousMessage.arg2 == 4) {
                MyFenLeiActivity.this.shouyeshow.setVisibility(View.GONE);
                Toast.makeText(MyFenLeiActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    @Bind({R.id.mingxingshow})
    RecyclerView mingxingshow;
    private MovesInfo movesInfo;
    private MyContentAdapter myContentAdapter;
    private GridLayoutManager myContentmanager;
    @Bind({R.id.shouyeshow})
    RecyclerView shouyeshow;
    @Bind({R.id.sousuo})
    ImageView sousuo;
    private String star = "0";
    private String sty = "0";

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().build();
        HttpOk.GetData(WebAdds.HTTPFENLEI, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                fenLeiInfo = gson.fromJson(s, FenLeiInfo.class);

                if (MyFenLeiActivity.this.fenLeiInfo.getCode().equals("000")) {
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

    private void getDataAll(String paramString1, String paramString2, String paramString3) {
        FormBody formBody = new FormBody.Builder().add("address", paramString1).add("style", paramString2).add("star", paramString3).build();
        HttpOk.GetData(WebAdds.HTTPALLMOVES, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                movesInfo = gson.fromJson(s, MovesInfo.class);

                if (MyFenLeiActivity.this.movesInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg2 = 2;
                    handler.sendMessage(message);
                    return;
                }
                if (MyFenLeiActivity.this.movesInfo.getCode().equals("002")) {
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
        setContentView(R.layout.activity_my_fen_lei);
        ButterKnife.bind(this);
        getData();
        getDataAll(this.add, this.sty, this.star);
    }

    @OnClick({R.id.fenlei, R.id.sousuo})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();

        if (i != R.id.fenlei) {
            if (i != R.id.sousuo) {
                return;
            }
            startActivity(new Intent(this, MySouSuoActivity.class));
            return;
        }
        finish();

    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\fenlei\MyFenLeiActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
