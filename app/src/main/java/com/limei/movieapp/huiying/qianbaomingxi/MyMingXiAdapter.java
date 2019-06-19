package com.limei.movieapp.huiying.qianbaomingxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyMingXiInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/15.
 */


/**
 * 有不同
 */


public class MyMingXiAdapter
        extends RecyclerView.Adapter<MyMingXiAdapter.ViewHolder> {
    public Context context;
    private MyMingXiInfo myMingXiInfo;

    public MyMingXiAdapter(Context paramContext, MyMingXiInfo paramMyMingXiInfo) {
        this.context = paramContext;
        this.myMingXiInfo = paramMyMingXiInfo;
    }

    public int getItemCount() {
        return this.myMingXiInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.title.setText(((MyMingXiInfo.DataEntity) this.myMingXiInfo.getData().get(paramInt)).getType());
        Object localObject = new StringBuilder();
        ((StringBuilder) localObject).append(((MyMingXiInfo.DataEntity) this.myMingXiInfo.getData().get(paramInt)).getCtime());
        ((StringBuilder) localObject).append("000");
        localObject = new Date(Long.parseLong(((StringBuilder) localObject).toString()));
        localObject = new SimpleDateFormat("yyyy-MM-dd").format((Date) localObject);
        paramViewHolder.titme.setText((CharSequence) localObject);

        localObject = new StringBuilder();
        ((StringBuilder) localObject).append(((MyMingXiInfo.DataEntity) this.myMingXiInfo.getData().get(paramInt)).getPrice());
        ((StringBuilder) localObject).append("元");
        paramViewHolder.jiaqian.setText(((StringBuilder) localObject).toString());
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_mingxi, paramViewGroup, false));
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        @Bind({R.id.jiaqian})
        TextView jiaqian;
        @Bind({R.id.title})
        TextView title;
        @Bind({R.id.titme})
        TextView titme;

        public ViewHolder(View paramView) {
            super(paramView);
            ButterKnife.bind(this, paramView);
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\qianbaomingxi\MyMingXiAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

