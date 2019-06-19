/**
 * 添加
 */
package com.limei.movieapp.huiying.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.application.MyApplication;
import com.limei.movieapp.huiying.info.CustomMission;
import com.limei.movieapp.huiying.playvideo.MyBenDiPlayVideoActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;
import zlc.season.rxdownload3.core.Suspend;
import zlc.season.rxdownload3.core.Waiting;
import zlc.season.rxdownload3.helper.UtilsKt;

public class MyHuanCunActivity
        extends AppCompatActivity {
    private Adapter adapter;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    @Bind({R.id.replay_text})
    RecyclerView replayText;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.shanchu})
    Button shanchu;
    @Bind({R.id.title})
    TextView title;

    private void getDataZhengZaiXiang(String paramString1, String paramString2) {
        FormBody formBody = new FormBody.Builder().add("uid", paramString1).add("ticketnumber", paramString2).build();
        HttpOk.GetData(WebAdds.HTTPHUANCUNWAN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {

                String s = paramAnonymousResponse.body().string();

            }
        });
    }

    private void loadData() {

        RxDownload.INSTANCE.getAllMission()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Mission>>() {
                    @Override
                    public void accept(List<Mission> missions) throws Exception {
                        adapter.addData(MyHuanCunActivity.this, missions);
                    }
                });

    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_huan_cun);
        ButterKnife.bind(this);
        this.title.setText("缓存管理");
        this.adapter = new Adapter();
        this.replayText.setLayoutManager(new LinearLayoutManager(this));
        this.replayText.setAdapter(this.adapter);
        loadData();
    }

    protected void onResume() {
        Log.i("fdf", "onResume");
        super.onResume();
        loadData();
    }

    @OnClick({R.id.shanchu})
    public void onViewClicked() {
        RxDownload.INSTANCE.deleteAll(false).subscribe(new Consumer() {
            public void accept(Object paramAnonymousObject)
                    throws Exception {
                MyHuanCunActivity.this.loadData();
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        if (paramView.getId() == R.id.fanhui) {
            finish();
        }

    }

    class Adapter
            extends RecyclerView.Adapter<MyHuanCunActivity.ViewHolder> {
        private Context context;
        private List<Mission> data = new ArrayList();

        Adapter() {
        }

        public void addData(Context paramContext, List<Mission> paramList) {
            this.context = paramContext;
            this.data.clear();
            this.data.addAll(paramList);
            notifyDataSetChanged();
        }

        public int getItemCount() {
            return this.data.size();
        }

        public void onBindViewHolder(MyHuanCunActivity.ViewHolder paramViewHolder, int paramInt) {
            paramViewHolder.setData((CustomMission) this.data.get(paramInt));
        }

        public MyHuanCunActivity.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
            LayoutInflater.from(paramViewGroup.getContext());
            View view = LayoutInflater.from(this.context).inflate(R.layout.view_holder_download_item, paramViewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        public void onViewAttachedToWindow(MyHuanCunActivity.ViewHolder paramViewHolder) {
            super.onViewAttachedToWindow(paramViewHolder);
            paramViewHolder.onAttach();
        }

        public void onViewDetachedFromWindow(MyHuanCunActivity.ViewHolder paramViewHolder) {
            super.onViewDetachedFromWindow(paramViewHolder);
            paramViewHolder.onDetach();
        }
    }

    class ViewHolder
            extends RecyclerView.ViewHolder {
        @Bind({R.id.action})
        Button action;
        private Status currentStatus;
        private CustomMission customMission;
        private Disposable disposable;
        @Bind({R.id.icon})
        ImageView icon;
        @Bind({R.id.name})
        TextView name;
        @Bind({R.id.percent})
        TextView percent;
        @Bind({R.id.progressBars})
        ProgressBar progressBar;
        @Bind({R.id.size})
        TextView size;
        private String[] str;
        private String[] strings;

        public ViewHolder(View paramView) {
            super(paramView);
            ButterKnife.bind(this, paramView);
            this.str = Unit.load(paramView.getContext()).split(",");
            this.action.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    MyHuanCunActivity.ViewHolder.this.dispatchClick();
                }
            });
        }

        private void dispatchClick() {
            if ((this.currentStatus instanceof Normal)) {
                start();
                return;
            }
            if ((this.currentStatus instanceof Suspend)) {
                start();
                return;
            }
            if ((this.currentStatus instanceof Failed)) {
                start();
                return;
            }
            if ((this.currentStatus instanceof Downloading)) {
                stop();
                return;
            }
            if ((this.currentStatus instanceof Succeed)) {
                install();
            }
        }

        private void install() {
            Log.i("missions", this.customMission.getUrl());
            MyApplication.customMission = this.customMission;

            strings = this.customMission.getUrl().split("/");
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(this.customMission.getSavePath());
            localStringBuilder.append("/");
            localStringBuilder.append(strings[(strings.length - 1)]);

            this.itemView.getContext().startActivity(new Intent(this.itemView.getContext(), MyBenDiPlayVideoActivity.class).putExtra("path", localStringBuilder.toString()).putExtra("img", this.customMission.getImg()));
        }

        private void setActionText(Status paramStatus) {
            String str1 = "";
            if ((paramStatus instanceof Normal)) {
                str1 = "开始";
            } else if ((paramStatus instanceof Suspend)) {
                str1 = "已暂停";
            } else if ((paramStatus instanceof Waiting)) {
                str1 = "等待中";
            } else if ((paramStatus instanceof Downloading)) {
                str1 = "暂停";
            } else if ((paramStatus instanceof Failed)) {
                str1 = "失败";
            } else if ((paramStatus instanceof Succeed)) {
                str1 = "播放";
                MyHuanCunActivity.this.getDataZhengZaiXiang(this.str[2], this.strings[2]);
            }
            this.action.setText(str1);
        }

        private void setProgress(Status paramStatus) {
            Log.i("kanyikansad",paramStatus.getDownloadSize()+"");
            this.progressBar.setMax((int) paramStatus.getTotalSize());
            this.progressBar.setProgress((int) paramStatus.getDownloadSize());
            this.percent.setText(paramStatus.percent());
            this.size.setText(paramStatus.formatString());
        }

        private void start() {
            RxDownload.INSTANCE.start(this.customMission).subscribe();
        }

        private void stop() {
            RxDownload.INSTANCE.stop(this.customMission).subscribe();
        }

        public void onAttach() {
            this.disposable = RxDownload.INSTANCE.create(this.customMission).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Status>() {
                public void accept(Status paramAnonymousStatus)
                        throws Exception {

                    currentStatus = paramAnonymousStatus;
                    Log.i("kanyikansad",currentStatus.getDownloadSize()+"   333333333");
                    MyHuanCunActivity.ViewHolder.this.setProgress(paramAnonymousStatus);
                    MyHuanCunActivity.ViewHolder.this.setActionText(paramAnonymousStatus);
                }
            });
        }

        public void onDetach() {
            Log.i("missions", "sfdf444444443");
            UtilsKt.dispose(this.disposable);
        }

        public void setData(CustomMission paramCustomMission) {
            this.customMission = paramCustomMission;
            this.strings = paramCustomMission.getImg().split("￥");
            RequestOptions tr = new RequestOptions().centerCrop();
            Glide.with(this.itemView.getContext()).load(this.strings[0]).apply(tr).into(this.icon);
            this.name.setText(this.strings[1]);
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyHuanCunActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */