package com.limei.movieapp.huiying.fenlei;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MovesInfo;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.view.GlideRoundTransform;

/**
 * Created by Administrator on 2018/8/15.
 */

/**
 * 有不同
 */


public class MyContentAdapter
        extends RecyclerView.Adapter<MyContentAdapter.ViewHolder>
        implements View.OnClickListener {
    public Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private MovesInfo movesInfo;

    public MyContentAdapter(Context paramContext, MovesInfo paramMovesInfo) {
        this.context = paramContext;
        this.movesInfo = paramMovesInfo;
    }

    public int getItemCount() {
        return this.movesInfo.getData().size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        RequestOptions localRequestOptions = new RequestOptions()
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop()
                .fitCenter()
                .transform(new GlideRoundTransform(context,dip2px(context,5)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();


        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append(movesInfo.getData().get(paramInt).getPic());
        Glide.with(this.context)
                .load(localStringBuilder.toString())
                .apply(localRequestOptions)
                .into(paramViewHolder.image);

        paramViewHolder.movenamer.setText((movesInfo.getData().get(paramInt)).getTitle());

        if ((movesInfo.getData().get(paramInt)).getIsshow().equals("1")) {
            paramViewHolder.zhaungtai.setText("已上映");
            paramViewHolder.zhaungtai.setBackgroundResource(R.drawable.icon_cro_red);
        } else {
            paramViewHolder.zhaungtai.setText("即将上映");
            paramViewHolder.zhaungtai.setBackgroundResource(R.drawable.icon_cro);
        }
        paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
    }

    public void onClick(View paramView) {
        if (this.mOnItemClickListener != null) {
            this.mOnItemClickListener.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_content, paramViewGroup, false);
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
        private ImageView image;
        private TextView movenamer;
        private TextView zhaungtai;

        public ViewHolder(View paramView) {
            super(paramView);
            this.image = ((ImageView) paramView.findViewById(R.id.image));
            this.zhaungtai = ((TextView) paramView.findViewById(R.id.zhaungtai));
            this.movenamer = ((TextView) paramView.findViewById(R.id.movenamer));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\fenlei\MyContentAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
