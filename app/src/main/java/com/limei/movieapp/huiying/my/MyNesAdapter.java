package com.limei.movieapp.huiying.my;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyXiaoXiInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/16.
 */

/**
 * 有不同
 */

public class MyNesAdapter
        extends RecyclerView.Adapter<MyNesAdapter.ViewHolder>
        implements View.OnClickListener {
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private MyXiaoXiInfo myXiaoXiInfo;

    public MyNesAdapter(Context paramContext, MyXiaoXiInfo paramMyXiaoXiInfo) {
        this.context = paramContext;
        this.myXiaoXiInfo = paramMyXiaoXiInfo;
    }

    public int getItemCount() {
        return this.myXiaoXiInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.nestitle.setText(((MyXiaoXiInfo.DataEntity) this.myXiaoXiInfo.getData().get(paramInt)).getTitle().trim());
        paramViewHolder.nescontent.setText(((MyXiaoXiInfo.DataEntity) this.myXiaoXiInfo.getData().get(paramInt)).getDescription());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((MyXiaoXiInfo.DataEntity) this.myXiaoXiInfo.getData().get(paramInt)).getCtime());
        stringBuilder.append("000");

        Date date = new Date(Long.parseLong(stringBuilder.toString()));

        String s = new SimpleDateFormat("yyyy-MM-dd").format(date);
        paramViewHolder.times.setText(s);

        paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
    }

    public void onClick(View paramView) {
        if (this.mOnItemClickListener != null) {
            this.mOnItemClickListener.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_nes, paramViewGroup, false);
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
        private TextView nescontent;
        private TextView nestitle;
        private TextView times;

        public ViewHolder(View paramView) {
            super(paramView);
            this.nestitle = ((TextView) paramView.findViewById(R.id.nestitle));
            this.nescontent = ((TextView) paramView.findViewById(R.id.nescontent));
            this.times = ((TextView) paramView.findViewById(R.id.times));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyNesAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */


