package com.limei.movieapp.huiying.goupiao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.JijiangshangyingInfo;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.zhifu.MyYuEActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */

/**
 * 有不同
 */


public class MyGouPiaoAdapter
        extends RecyclerView.Adapter<MyGouPiaoAdapter.ViewHolder>
        implements View.OnClickListener {
    private View VIEW_HEADER;
    private int a;
    private Context context;
    private JijiangshangyingInfo jijiangshangyingInfo;
    private OnItemClickListener mItemClickListener;

    public MyGouPiaoAdapter(Context paramContext, JijiangshangyingInfo paramJijiangshangyingInfo, int paramInt) {
        this.context = paramContext;
        this.a = paramInt;
        this.jijiangshangyingInfo = paramJijiangshangyingInfo;
    }

    public void addHeaderView(View paramView) {
        this.VIEW_HEADER = paramView;
    }

    public int getItemCount() {
        return this.jijiangshangyingInfo.getData().getList().size() + 1;
    }

    public int getItemViewType(int paramInt) {
        if (paramInt == 0) {
            return 1;
        }
        return 2;
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, final int paramInt) {
        int i;
        int j;
        if (paramInt > 0) {
            if (this.a == 0) {
                paramViewHolder.goupiao.setText("购票");
                paramViewHolder.goupiao.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        MyGouPiaoAdapter.this.context.startActivity(new Intent(MyGouPiaoAdapter.this.context, MyYuEActivity.class).putExtra("flag", 0).putExtra("id", ((JijiangshangyingInfo.DataEntity.ListEntity) MyGouPiaoAdapter.this.jijiangshangyingInfo.getData().getList().get(paramInt - 1)).getId()));
                    }
                });
            } else {
                paramViewHolder.goupiao.setText("查看");
                paramViewHolder.goupiao.setBackgroundResource(R.drawable.lineblue);
                i = this.context.getResources().getColor(R.color.lingblue);
                paramViewHolder.goupiao.setTextColor(i);
                paramViewHolder.goupiao.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {

                        MyGouPiaoAdapter.this.context.startActivity(new Intent
                                (MyGouPiaoAdapter.this.context, MyShowMoveActivity.class)
                                .putExtra("flag", 1).putExtra("id", jijiangshangyingInfo.getData().getList().get(paramInt - 1).getId()));
                    }
                });
            }
            RequestOptions localObject1 = new RequestOptions().centerCrop();

            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(WebAdds.YUMING);
            List localList = this.jijiangshangyingInfo.getData().getList();
            j = paramInt - 1;
            localStringBuilder.append(((JijiangshangyingInfo.DataEntity.ListEntity) localList.get(j)).getPic());
            Glide.with(this.context).load(localStringBuilder.toString()).apply((RequestOptions) localObject1).into(paramViewHolder.moviebg);
            paramViewHolder.movename.setText(((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getTitle());
            paramViewHolder.moveshangyingdiqu.setText(((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getSaddress());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getStime());
            stringBuilder.append("000");
            Date date = new Date(Long.parseLong(stringBuilder.toString()));
            String s = new SimpleDateFormat("yyyy-MM-dd").format(date);
            paramViewHolder.movetime.setText(s);

            try {
                StringBuffer stringBuffer = new StringBuffer();
                i = 0;
                while (i < ((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getTostar().size()) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(((JijiangshangyingInfo.DataEntity.ListEntity.TostarEntity) ((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getTostar().get(i)).getTitle());
                    stringBuilder1.append(" ");
                    stringBuffer.append(stringBuilder1.toString());
                    i += 1;
                }
                paramViewHolder.movezhuyan.setText(stringBuffer.toString());
            } catch (Exception localException) {

            }

            Object localObject2 = new StringBuilder();
            ((StringBuilder) localObject2).append(((JijiangshangyingInfo.DataEntity.ListEntity) this.jijiangshangyingInfo.getData().getList().get(j)).getDtime());
            ((StringBuilder) localObject2).append("分钟");
            paramViewHolder.movezongtime.setText(((StringBuilder) localObject2).toString());
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
        }
    }

    public void onClick(View paramView) {
        if (this.mItemClickListener != null) {
            this.mItemClickListener.onItemClick(((Integer) paramView.getTag()).intValue());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        if (paramInt == 1) {
            return new ViewHolder(this.VIEW_HEADER);
        }
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_moviezhanshi, paramViewGroup, false);
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
        private TextView goupiao;
        private TextView movename;
        private TextView moveshangyingdiqu;
        private TextView movetime;
        private TextView movezhuyan;
        private TextView movezongtime;
        private ImageView moviebg;

        public ViewHolder(View paramView) {
            super(paramView);
            this.moviebg = ((ImageView) paramView.findViewById(R.id.moviebg));
            this.movename = ((TextView) paramView.findViewById(R.id.movename));
            this.movetime = ((TextView) paramView.findViewById(R.id.movetime));
            this.moveshangyingdiqu = ((TextView) paramView.findViewById(R.id.moveshangyingdiqu));
            this.movezhuyan = ((TextView) paramView.findViewById(R.id.movezhuyan));
            this.movezongtime = ((TextView) paramView.findViewById(R.id.movezongtime));
            this.goupiao = ((TextView) paramView.findViewById(R.id.goupiao));
        }
    }
}



