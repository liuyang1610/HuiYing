package com.limei.movieapp.huiying.goupiao;

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
import com.limei.movieapp.huiying.info.DianYingXinagQingInfo;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */


/**
 * 有不同
 */


public class MyYanZhiRenYuanAdapter
        extends RecyclerView.Adapter<MyYanZhiRenYuanAdapter.ViewHolder> {
    private Context context;
    private List<DianYingXinagQingInfo.DataEntity.PalyEntity> data;

    public MyYanZhiRenYuanAdapter(Context paramContext, List<DianYingXinagQingInfo.DataEntity.PalyEntity> paramList) {
        this.context = paramContext;
        this.data = paramList;
    }

    public int getItemCount() {
        try {
            int i = this.data.size();
            return i;
        } catch (Exception localException) {
            return 0;
        }

    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        try {
            RequestOptions localObject = new RequestOptions().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate();
            RequestManager localRequestManager = Glide.with(this.context);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(WebAdds.YUMING);
            localStringBuilder.append(((DianYingXinagQingInfo.DataEntity.PalyEntity) this.data.get(paramInt)).getPic());
            localRequestManager.load(localStringBuilder.toString()).apply((RequestOptions) localObject).into(paramViewHolder.yanyuanim);
            paramViewHolder.name.setText(((DianYingXinagQingInfo.DataEntity.PalyEntity) this.data.get(paramInt)).getTitle());
            if (((DianYingXinagQingInfo.DataEntity.PalyEntity) this.data.get(paramInt)).getSubtitle().equals("导演")) {
                paramViewHolder.nameyan.setText(((DianYingXinagQingInfo.DataEntity.PalyEntity) this.data.get(paramInt)).getSubtitle());

            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("饰：");
            stringBuilder.append(((DianYingXinagQingInfo.DataEntity.PalyEntity) this.data.get(paramInt)).getSubtitle());
            paramViewHolder.nameyan.setText(stringBuilder.toString());
            return;
        } catch (Exception e) {
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_yanzhirenyuan, paramViewGroup, false));
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView nameyan;
        private ImageView yanyuanim;

        public ViewHolder(View paramView) {
            super(paramView);
            this.yanyuanim = ((ImageView) paramView.findViewById(R.id.yanyuanim));
            this.name = ((TextView) paramView.findViewById(R.id.name));
            this.nameyan = ((TextView) paramView.findViewById(R.id.nameyan));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\goupiao\MyYanZhiRenYuanAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

