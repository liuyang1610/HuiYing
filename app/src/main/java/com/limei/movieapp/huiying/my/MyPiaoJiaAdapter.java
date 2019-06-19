package com.limei.movieapp.huiying.my;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyZengPiaoInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/16.
 */

/**
 * 有不同
 */


public class MyPiaoJiaAdapter
        extends RecyclerView.Adapter<MyPiaoJiaAdapter.ViewHolder> {
    private Context context;
    private MyZengPiaoInfo myZengPiaoInfo;

    public MyPiaoJiaAdapter(Context paramContext, MyZengPiaoInfo paramMyZengPiaoInfo) {
        this.context = paramContext;
        this.myZengPiaoInfo = paramMyZengPiaoInfo;
    }

    public int getItemCount() {
        return this.myZengPiaoInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.movename.setText(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getTitle());
        Object localObject1 = paramViewHolder.nums;
        Object localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append("总数：");
        ((StringBuilder) localObject2).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getNum());
        ((StringBuilder) localObject2).append("张");
        ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());
        localObject1 = paramViewHolder.movejiage;
        localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append("总价：￥");
        ((StringBuilder) localObject2).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getPrice());
        ((StringBuilder) localObject2).append("");
        ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());

        localObject1 = new StringBuilder();
        ((StringBuilder) localObject1).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getCtime());
        ((StringBuilder) localObject1).append("000");
        localObject1 = new Date(Long.parseLong(((StringBuilder) localObject1).toString()));
        localObject1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) localObject1);

        localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getDtime());
        ((StringBuilder) localObject2).append("000");
        localObject2 = new Date(Long.parseLong(((StringBuilder) localObject2).toString()));
        localObject2 = new SimpleDateFormat("yyyy-MM-dd").format((Date) localObject2);
        Object localObject3 = paramViewHolder.youxiaoqi;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("有效日期：");
        localStringBuilder.append((String) localObject1);
        localStringBuilder.append("至");
        localStringBuilder.append((String) localObject2);

        ((TextView) localObject3).setText(localStringBuilder.toString());
        localObject1 = new StringBuilder();
        ((StringBuilder) localObject1).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getDtime());
        ((StringBuilder) localObject1).append("000");
        localObject1 = new Date(Long.parseLong(((StringBuilder) localObject1).toString()));
        localObject1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) localObject1);
        localObject2 = paramViewHolder.goupaioriqi;
        localObject3 = new StringBuilder();
        ((StringBuilder) localObject3).append("购票日期：");
        ((StringBuilder) localObject3).append((String) localObject1);
        ((TextView) localObject2).setText(((StringBuilder) localObject3).toString());

        localObject1 = paramViewHolder.piaohao;
        localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append("票号：");
        ((StringBuilder) localObject2).append(((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getTicketnumber());
        ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());
        if (((MyZengPiaoInfo.DataEntity) this.myZengPiaoInfo.getData().get(paramInt)).getReceive().equals("1")) {
            paramViewHolder.zhuangtai.setText("已领取");
        }else{
            paramViewHolder.zhuangtai.setText("未领取");
        }

    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_piaojia, paramViewGroup, false));
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        @Bind({R.id.goupaioriqi})
        TextView goupaioriqi;
        @Bind({R.id.movejiage})
        TextView movejiage;
        @Bind({R.id.movename})
        TextView movename;
        @Bind({R.id.nums})
        TextView nums;
        @Bind({R.id.piaohao})
        TextView piaohao;
        @Bind({R.id.youxiaoqi})
        TextView youxiaoqi;
        @Bind({R.id.zengsongtime})
        TextView zengsongtime;
        @Bind({R.id.zhengpiao})
        TextView zhengpiao;
        @Bind({R.id.zhuangtai})
        TextView zhuangtai;

        public ViewHolder(View paramView) {
            super(paramView);
            ButterKnife.bind(this, paramView);
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyPiaoJiaAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

