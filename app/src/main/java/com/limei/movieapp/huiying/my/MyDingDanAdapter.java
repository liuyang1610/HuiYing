package com.limei.movieapp.huiying.my;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyDingDanInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/17.
 */

/**
 * 有不同
 */

public class MyDingDanAdapter
        extends RecyclerView.Adapter<MyDingDanAdapter.ViewHolder>
{
    private Context context;
    private MyDingDanInfo myDingDanInfo;

    public MyDingDanAdapter(Context paramContext, MyDingDanInfo paramMyDingDanInfo)
    {
        this.context = paramContext;
        this.myDingDanInfo = paramMyDingDanInfo;
    }

    public int getItemCount()
    {
        return this.myDingDanInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt)
    {

        StringBuilder localObject2 = new StringBuilder();
        localObject2.append("订单号：");
        localObject2.append(myDingDanInfo.getData().get(paramInt).getCode());
        paramViewHolder.dingdanhao.setText(localObject2.toString());
        paramViewHolder.movename.setText(((MyDingDanInfo.DataEntity)this.myDingDanInfo.getData().get(paramInt)).getTitle());

        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("总数：");
        ((StringBuilder)localObject2).append(((MyDingDanInfo.DataEntity)this.myDingDanInfo.getData().get(paramInt)).getNum());
        ((StringBuilder)localObject2).append("张");
        paramViewHolder.nums.setText(((StringBuilder)localObject2).toString());

        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("总价：￥");
        ((StringBuilder)localObject2).append(((MyDingDanInfo.DataEntity)this.myDingDanInfo.getData().get(paramInt)).getPrice());
        ((StringBuilder)localObject2).append("");
        paramViewHolder.movejiage.setText(((StringBuilder)localObject2).toString());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((MyDingDanInfo.DataEntity)this.myDingDanInfo.getData().get(paramInt)).getCtime());
        stringBuilder.append("000");
        Date date = new Date(Long.parseLong(stringBuilder.toString()));
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);

        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((MyDingDanInfo.DataEntity)this.myDingDanInfo.getData().get(paramInt)).getDtime());
        ((StringBuilder)localObject2).append("000");
        Date date1 = new Date(Long.parseLong(((StringBuilder)localObject2).toString()));
        String s = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("有效日期：");
        localStringBuilder.append(simpleDateFormat);
        localStringBuilder.append("至");
        localStringBuilder.append(s);
        paramViewHolder.youxiaoqi.setText(localStringBuilder.toString());

        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(myDingDanInfo.getData().get(paramInt).getCtime());
        stringBuilder1.append("000");
        Date date2 = new Date(Long.parseLong(stringBuilder1.toString()));
        String s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date2);

        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("购票日期：");
        stringBuilder2.append(s1);
        paramViewHolder.goupaioriqi.setText(stringBuilder2.toString());

        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("票号：");
        stringBuilder3.append((myDingDanInfo.getData().get(paramInt)).getTicketnumber());
        paramViewHolder.piaohao.setText(stringBuilder3.toString());
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_dingdan, paramViewGroup, false));
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
    {
        @Bind({R.id.dingdanhao})
        TextView dingdanhao;
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
        @Bind({R.id.zhengpiao})
        TextView zhengpiao;

        public ViewHolder(View paramView)
        {
            super(paramView);
            ButterKnife.bind(this, paramView);
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyDingDanAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */