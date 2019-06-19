package com.limei.movieapp.huiying.fangying;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyFangYingZhengInfo;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/15.
 */

/**
 * 有不同
 */

public class MyFangYingAdapter
        extends RecyclerView.Adapter<MyFangYingAdapter.ViewHolder>
        implements View.OnClickListener {
    private Context context;
    private OnItemClickListener mItemClickListener;
    private MyFangYingZhengInfo myFangYingZhengInfo;

    public MyFangYingAdapter(Context paramContext, MyFangYingZhengInfo paramMyFangYingZhengInfo) {
        this.context = paramContext;
        this.myFangYingZhengInfo = paramMyFangYingZhengInfo;
    }

    public int getItemCount() {
        return this.myFangYingZhengInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        Object localObject1 = new RequestOptions().centerCrop();
        Object localObject2 = Glide.with(this.context);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getPic());
        ((RequestManager) localObject2).load(localStringBuilder.toString()).apply((RequestOptions) localObject1).into(paramViewHolder.moviebg);
        paramViewHolder.movename.setText(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getTitle());
        localObject1 = new StringBuilder();
        ((StringBuilder) localObject1).append(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getStime());
        ((StringBuilder) localObject1).append("000");
        localObject1 = new Date(Long.parseLong(((StringBuilder) localObject1).toString()));
        localObject1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) localObject1);
        paramViewHolder.movetime.setText((CharSequence) localObject1);
        paramViewHolder.moveshangyingdiqu.setText(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getSaddress());
        try {
            localObject1 = new StringBuffer();
            int i = 0;
            while (i < ((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getTostar().size()) {
                localObject2 = new StringBuilder();
                ((StringBuilder) localObject2).append(((MyFangYingZhengInfo.DataEntity.TostarEntity) ((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getTostar().get(i)).getTitle());
                ((StringBuilder) localObject2).append(" ");
                ((StringBuffer) localObject1).append(((StringBuilder) localObject2).toString());
                i += 1;
            }
            paramViewHolder.movezhuyan.setText(((StringBuffer) localObject1).toString());
        } catch (Exception localException1) {

        }

        try {
            paramViewHolder.bottomProgress.setProgress(Integer.parseInt(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getPlaytime()));
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));

        } catch (Exception localException2) {

        }

        localObject1 = paramViewHolder.movezongtime;
        localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getLengthtime());
        ((StringBuilder) localObject2).append("分钟");
        ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder) localObject1).append(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getSurplus());
        ((StringBuilder) localObject1).append("");
        if (((StringBuilder) localObject1).toString().equals("null")) {
            paramViewHolder.goupiao.setText("已完成");
        } else {
            localObject1 = paramViewHolder.goupiao;
            localObject2 = new StringBuilder();
            ((StringBuilder) localObject2).append("还剩");
            ((StringBuilder) localObject2).append(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getSurplus());
            ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());
        }
        localObject1 = Pattern.compile("\\d+").matcher(((MyFangYingZhengInfo.DataEntity) this.myFangYingZhengInfo.getData().get(paramInt)).getLengthtime());
        ((Matcher) localObject1).find();
        paramViewHolder.bottomProgress.setMax(Integer.parseInt(((Matcher) localObject1).group()) * 60);
    }

    public void onClick(View paramView) {
        if (this.mItemClickListener != null) {
            this.mItemClickListener.onItemClick(((Integer) paramView.getTag()).intValue());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_fangying, paramViewGroup, false);
        ViewHolder localViewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return localViewHolder;
    }

    public void setItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mItemClickListener = paramOnItemClickListener;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(int paramInt);
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        @Bind({R.id.bottom_progress})
        ProgressBar bottomProgress;
        @Bind({R.id.goupiao})
        TextView goupiao;
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

        public ViewHolder(View paramView) {
            super(paramView);
            ButterKnife.bind(this, paramView);
        }
    }
}
