package com.limei.movieapp.huiying.goupiao;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.yich.layout.picwatcherlib.PicWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */

/**
 * 有不同
 */


public class MyDianYingJuZhaoAdapter
        extends RecyclerView.Adapter<MyDianYingJuZhaoAdapter.ViewHolder>
        implements View.OnClickListener {
    private Activity context;
    private List<String> data;
    private OnItemClickListener mItemClickListener;
    private RecyclerView mRecycleView;

    public MyDianYingJuZhaoAdapter(Activity paramContext, List<String> paramList, RecyclerView mRecycleView) {
        this.context = paramContext;
        this.data = paramList;
        this.mRecycleView = mRecycleView;
    }

    public int getItemCount() {
        return this.data.size();
    }


    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        RequestOptions localRequestOptions = new RequestOptions().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate();

        Glide.with(this.context).load(data.get(paramInt)).apply(localRequestOptions).into(paramViewHolder.juzhao);

        paramViewHolder.juzhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicWatcher.showImages(context, (ImageView) v, paramInt, findRecelyVisiableImageviews(mRecycleView), data);
            }
        });

        paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));


    }

    public List<ImageView> findRecelyVisiableImageviews(RecyclerView list) {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        LinearLayoutManager layoutManager = (LinearLayoutManager) (list.getLayoutManager());
        int fisrtPos = layoutManager.findFirstVisibleItemPosition();
        int lastPos = layoutManager.findLastVisibleItemPosition();
        for (int i = fisrtPos; i < lastPos + 1; i++) {
            imageViews.add(layoutManager.findViewByPosition(i).findViewById(R.id.juzhao));
        }
        return imageViews;
    }

    public void onClick(View paramView) {
        if (this.mItemClickListener != null) {
            this.mItemClickListener.onItemClick(((Integer) paramView.getTag()).intValue(), paramView);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_juzhao, paramViewGroup, false);
        ViewHolder localViewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return localViewHolder;
    }

    public void setItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mItemClickListener = paramOnItemClickListener;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(int paramInt, View v);
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        private ImageView juzhao;

        public ViewHolder(View paramView) {
            super(paramView);
            this.juzhao = ((ImageView) paramView.findViewById(R.id.juzhao));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\goupiao\MyDianYingJuZhaoAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */


