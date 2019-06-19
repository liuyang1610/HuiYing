package com.limei.movieapp.huiying.fenlei;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.FenLeiInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/8/15.
 */


/**
 * 有不同
 */

public class MyFenLeiAdapter
        extends RecyclerView.Adapter<MyFenLeiAdapter.ViewHolder>
        implements View.OnClickListener {
    private int a;
    private List<FenLeiInfo.DataEntity.AddressEntity> addressEntities;
    private Context context;
    private OnItemClickListenerAdd mOnItemClickListeneradd = null;
    private OnItemClickListenerStar mOnItemClickListenerstar = null;
    private OnItemClickListenerSty mOnItemClickListenersty = null;
    private List<FenLeiInfo.DataEntity.StarEntity> starEntities;
    private List<FenLeiInfo.DataEntity.StyleEntity> styleEntities;

    public MyFenLeiAdapter(Context paramContext, List<FenLeiInfo.DataEntity.AddressEntity> paramList, List<FenLeiInfo.DataEntity.StyleEntity> paramList1, List<FenLeiInfo.DataEntity.StarEntity> paramList2, int paramInt) {
        this.context = paramContext;
        this.addressEntities = paramList;
        ((FenLeiInfo.DataEntity.AddressEntity) paramList.get(0)).setIskoAdd(false);
        this.styleEntities = paramList1;
        ((FenLeiInfo.DataEntity.StyleEntity) paramList1.get(0)).setIskoStyle(false);
        this.starEntities = paramList2;
        ((FenLeiInfo.DataEntity.StarEntity) paramList2.get(0)).setIsIskoStar(false);
        this.a = paramInt;
    }

    public int getItemCount() {
        if (this.a == 0) {
            return this.addressEntities.size();
        }
        if (this.a == 1) {
            return this.styleEntities.size();
        }
        return this.starEntities.size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        if (this.a == 0) {
            paramViewHolder.contetn.setText(((FenLeiInfo.DataEntity.AddressEntity) this.addressEntities.get(paramInt)).getTitle());
            if (!((FenLeiInfo.DataEntity.AddressEntity) this.addressEntities.get(paramInt)).iskoAdd()) {
                paramViewHolder.contetn.setBackgroundResource(R.drawable.feileibg);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.wight));
            } else {
                paramViewHolder.contetn.setBackgroundResource(R.color.wight);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.black));
            }
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
            return;
        }
        if (this.a == 1) {
            paramViewHolder.contetn.setText(((FenLeiInfo.DataEntity.StyleEntity) this.styleEntities.get(paramInt)).getTitle());
            if (!((FenLeiInfo.DataEntity.StyleEntity) this.styleEntities.get(paramInt)).isIskoStyle()) {
                paramViewHolder.contetn.setBackgroundResource(R.drawable.feileibg);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.wight));
            } else {
                paramViewHolder.contetn.setBackgroundResource(R.color.wight);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.black));
            }
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
            return;
        }
        if (this.a == 2) {
            paramViewHolder.contetn.setText(((FenLeiInfo.DataEntity.StarEntity) this.starEntities.get(paramInt)).getTitle());
            if (!((FenLeiInfo.DataEntity.StarEntity) this.starEntities.get(paramInt)).isIskoStar()) {
                paramViewHolder.contetn.setBackgroundResource(R.drawable.feileibg);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.wight));
            } else {
                paramViewHolder.contetn.setBackgroundResource(R.color.wight);
                paramViewHolder.contetn.setTextColor(this.context.getResources().getColorStateList(R.color.black));
            }
            paramViewHolder.itemView.setTag(Integer.valueOf(paramInt));
        }
    }

    public void onClick(View paramView) {
        if (this.mOnItemClickListeneradd != null) {
            this.mOnItemClickListeneradd.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
        if (this.mOnItemClickListenersty != null) {
            this.mOnItemClickListenersty.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
        if (this.mOnItemClickListenerstar != null) {
            this.mOnItemClickListenerstar.onItemClick(paramView, ((Integer) paramView.getTag()).intValue());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_fenlei, paramViewGroup, false);
        ViewHolder localViewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return localViewHolder;
    }

    public void setOnItemClickListenerAdd(OnItemClickListenerAdd paramOnItemClickListenerAdd) {
        this.mOnItemClickListeneradd = paramOnItemClickListenerAdd;
    }

    public void setmOnItemClickListenerStar(OnItemClickListenerStar paramOnItemClickListenerStar) {
        this.mOnItemClickListenerstar = paramOnItemClickListenerStar;
    }

    public void setmOnItemClickListenerSty(OnItemClickListenerSty paramOnItemClickListenerSty) {
        this.mOnItemClickListenersty = paramOnItemClickListenerSty;
    }

    public static abstract interface OnItemClickListenerAdd {
        public abstract void onItemClick(View paramView, int paramInt);
    }

    public static abstract interface OnItemClickListenerStar {
        public abstract void onItemClick(View paramView, int paramInt);
    }

    public static abstract interface OnItemClickListenerSty {
        public abstract void onItemClick(View paramView, int paramInt);
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        private TextView contetn;

        public ViewHolder(View paramView) {
            super(paramView);
            this.contetn = ((TextView) paramView.findViewById(R.id.contetn));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\fenlei\MyFenLeiAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
