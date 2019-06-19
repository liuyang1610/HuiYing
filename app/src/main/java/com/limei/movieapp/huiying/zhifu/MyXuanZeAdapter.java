package com.limei.movieapp.huiying.zhifu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.ZhiFuSelerInfo;

/**
 * Created by Administrator on 2018/8/15.
 */

/**
 * 有不同
 */

public class MyXuanZeAdapter
        extends RecyclerView.Adapter<MyXuanZeAdapter.ViewHolder>
        implements View.OnClickListener {
    public Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private ZhiFuSelerInfo zhiFuSelerInfo;

    public MyXuanZeAdapter(Context paramContext, ZhiFuSelerInfo paramZhiFuSelerInfo) {
        this.context = paramContext;
        this.zhiFuSelerInfo = paramZhiFuSelerInfo;
    }

    public int getItemCount() {
        return this.zhiFuSelerInfo.getData().getList().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        TextView localTextView = paramViewHolder.chongnum;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("充");
        localStringBuilder.append(((ZhiFuSelerInfo.DataEntity.ListEntity) this.zhiFuSelerInfo.getData().getList().get(paramInt)).getFull());
        localStringBuilder.append("元");
        localTextView.setText(localStringBuilder.toString());
        localTextView = paramViewHolder.zeng;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("赠送");
        localStringBuilder.append(((ZhiFuSelerInfo.DataEntity.ListEntity) this.zhiFuSelerInfo.getData().getList().get(paramInt)).getReduce());
        localStringBuilder.append("元");
        localTextView.setText(localStringBuilder.toString());
        if (((ZhiFuSelerInfo.DataEntity.ListEntity) this.zhiFuSelerInfo.getData().getList().get(paramInt)).isXuan() == true) {
            paramViewHolder.gga.setBackgroundResource(R.drawable.lineblack);
            paramViewHolder.chongnum.setTextColor(this.context.getResources().getColor(R.color.black));
            paramViewHolder.zeng.setTextColor(this.context.getResources().getColor(R.color.black));
        } else {
            paramViewHolder.gga.setBackgroundResource(R.drawable.line);
            paramViewHolder.chongnum.setTextColor(this.context.getResources().getColor(R.color.lingwei));
            paramViewHolder.zeng.setTextColor(this.context.getResources().getColor(R.color.lingwei));
        }
        paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
    }

    public void onClick(View paramView) {
        if (this.mOnItemClickListener != null) {
            this.mOnItemClickListener.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_xuanze, paramViewGroup, false);
        ViewHolder localViewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return localViewHolder;
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(View paramView, int paramInt);
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        private TextView chongnum;
        private LinearLayout gga;
        private TextView zeng;

        public ViewHolder(View paramView) {
            super(paramView);
            this.zeng = ((TextView) paramView.findViewById(R.id.zeng));
            this.chongnum = ((TextView) paramView.findViewById(R.id.chongnum));
            this.gga = ((LinearLayout) paramView.findViewById(R.id.gga));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\zhifu\MyXuanZeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

