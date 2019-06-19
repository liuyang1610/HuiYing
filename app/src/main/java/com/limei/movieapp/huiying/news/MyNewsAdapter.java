package com.limei.movieapp.huiying.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.NewInfo;
import com.limei.movieapp.huiying.unit.WebAdds;

/**
 * Created by Administrator on 2018/8/17.
 */

/**
 * 有不同
 */


public class MyNewsAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {
    private Context context;
    private OnItemClickListener mItemClickListener;
    private NewInfo newInfo;

    public MyNewsAdapter(Context paramContext, NewInfo paramNewInfo) {
        this.context = paramContext;
        this.newInfo = paramNewInfo;
    }

    private void setFloorFoor(ThreeHolder paramThreeHolder, int paramInt) {
        RequestOptions localRequestOptions = new RequestOptions().centerCrop();
        RequestManager localRequestManager = Glide.with(this.context);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append((String) ((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getImg().get(0));
        localRequestManager.load(localStringBuilder.toString()).apply(localRequestOptions).into(paramThreeHolder.newsone);
        localRequestOptions = new RequestOptions().centerCrop();
        localRequestManager = Glide.with(this.context);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append((String) ((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getImg().get(1));
        localRequestManager.load(localStringBuilder.toString()).apply(localRequestOptions).into(paramThreeHolder.newstwo);
        localRequestOptions = new RequestOptions().centerCrop();
        localRequestManager = Glide.with(this.context);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append((String) ((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getImg().get(2));
        localRequestManager.load(localStringBuilder.toString()).apply(localRequestOptions).into(paramThreeHolder.newthree);
        paramThreeHolder.title.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getTitle());
        paramThreeHolder.plnum.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getFabulous());
        paramThreeHolder.times.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getHour());
    }

    private void setFloorThree(OneRightHolder paramOneRightHolder, int paramInt) {
        RequestOptions localRequestOptions = new RequestOptions().centerCrop();
        RequestManager localRequestManager = Glide.with(this.context);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append((String) ((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getImg().get(0));
        localRequestManager.load(localStringBuilder.toString()).apply(localRequestOptions).into(paramOneRightHolder.nesbg);
        paramOneRightHolder.title.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getTitle());
        paramOneRightHolder.plnum.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getFabulous());
        paramOneRightHolder.times.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getHour());
    }

    private void setFloorTwo(OneButtonHolder paramOneButtonHolder, int paramInt) {
        RequestOptions localRequestOptions = new RequestOptions().centerCrop();
        RequestManager localRequestManager = Glide.with(this.context);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(WebAdds.YUMING);
        localStringBuilder.append((String) ((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getImg().get(0));
        localRequestManager.load(localStringBuilder.toString()).apply(localRequestOptions).into(paramOneButtonHolder.nesbg);
        paramOneButtonHolder.title.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getTitle());
        paramOneButtonHolder.plnum.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getFabulous());
        paramOneButtonHolder.times.setText(((NewInfo.DataEntity) this.newInfo.getData().get(paramInt)).getHour());
    }

    public int getItemCount() {
        return this.newInfo.getData().size();
    }

    public int getItemViewType(int paramInt) {
        if (newInfo.getData().get(paramInt).getType().equals("1")) {
            return 2;
        }
        if (newInfo.getData().get(paramInt).getType().equals("2")) {
            return 3;
        }
        if (newInfo.getData().get(paramInt).getType().equals("3")) {
            return 4;
        }
        return 0;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        if ((paramViewHolder instanceof OneButtonHolder)) {
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
            setFloorTwo((OneButtonHolder) paramViewHolder, paramInt);
            return;
        }
        if ((paramViewHolder instanceof OneRightHolder)) {
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
            setFloorThree((OneRightHolder) paramViewHolder, paramInt);
            return;
        }
        if ((paramViewHolder instanceof ThreeHolder)) {
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
        }
        try {
            setFloorFoor((ThreeHolder) paramViewHolder, paramInt);
            return;
        } catch (Exception e) {
        }
    }

    public void onClick(View paramView) {
        if (this.mItemClickListener != null) {
            this.mItemClickListener.onItemClick(((Integer) paramView.getTag()).intValue());
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        if (paramInt == 2) {
            View view = LayoutInflater.from(this.context).inflate(R.layout.item_newsonebuttom, paramViewGroup, false);
            OneButtonHolder oneButtonHolder = new OneButtonHolder(view);
            view.setOnClickListener(this);
            return oneButtonHolder;
        }
        if (paramInt == 3) {
            View view = LayoutInflater.from(this.context).inflate(R.layout.item_newsoneright, paramViewGroup, false);
            OneRightHolder oneRightHolder = new OneRightHolder(view);
            view.setOnClickListener(this);
            return oneRightHolder;
        }
        if (paramInt == 4) {
            View view = LayoutInflater.from(this.context).inflate(R.layout.item_newsthree, paramViewGroup, false);
            ThreeHolder threeHolder = new ThreeHolder(view);
            view.setOnClickListener(this);
            return threeHolder;
        }
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_newsonebuttom, paramViewGroup, false);
        OneButtonHolder localObject = new OneButtonHolder(view);
        view.setOnClickListener(this);
        return localObject;
    }

    public void setItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mItemClickListener = paramOnItemClickListener;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(int paramInt);
    }

    public class OneButtonHolder
            extends RecyclerView.ViewHolder {
        private ImageView nesbg;
        private TextView plnum;
        private TextView times;
        private TextView title;
        private ImageView tubiao;

        public OneButtonHolder(View paramView) {
            super(paramView);
            this.title = ((TextView) paramView.findViewById(R.id.title));
            this.nesbg = ((ImageView) paramView.findViewById(R.id.nesbg));
            this.tubiao = ((ImageView) paramView.findViewById(R.id.tubiao));
            this.plnum = ((TextView) paramView.findViewById(R.id.plnum));
            this.times = ((TextView) paramView.findViewById(R.id.times));
        }
    }

    public class OneRightHolder
            extends RecyclerView.ViewHolder {
        private ImageView nesbg;
        private TextView plnum;
        private TextView times;
        private TextView title;
        private ImageView tubiao;

        public OneRightHolder(View paramView) {
            super(paramView);
            this.title = ((TextView) paramView.findViewById(R.id.title));
            this.nesbg = ((ImageView) paramView.findViewById(R.id.nesbg));
            this.tubiao = ((ImageView) paramView.findViewById(R.id.tubiao));
            this.plnum = ((TextView) paramView.findViewById(R.id.plnum));
            this.times = ((TextView) paramView.findViewById(R.id.times));
        }
    }

    public class ThreeHolder
            extends RecyclerView.ViewHolder {
        private ImageView newsone;
        private ImageView newstwo;
        private ImageView newthree;
        private TextView plnum;
        private TextView times;
        private TextView title;
        private ImageView tubiao;

        public ThreeHolder(View paramView) {
            super(paramView);
            this.title = ((TextView) paramView.findViewById(R.id.title));
            this.newsone = ((ImageView) paramView.findViewById(R.id.newsone));
            this.newstwo = ((ImageView) paramView.findViewById(R.id.newstwo));
            this.newthree = ((ImageView) paramView.findViewById(R.id.newthree));
            this.tubiao = ((ImageView) paramView.findViewById(R.id.tubiao));
            this.plnum = ((TextView) paramView.findViewById(R.id.plnum));
            this.times = ((TextView) paramView.findViewById(R.id.times));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\news\MyNewsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
