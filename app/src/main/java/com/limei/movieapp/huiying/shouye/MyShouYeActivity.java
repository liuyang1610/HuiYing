package com.limei.movieapp.huiying.shouye;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.fangying.MyFangYingFragment;
import com.limei.movieapp.huiying.goupiao.MyGoupiaoFragment;
import com.limei.movieapp.huiying.my.MySelfAFragment;
import com.limei.movieapp.huiying.news.MyNewFragment;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 有不同
 */

public class MyShouYeActivity
        extends AppCompatActivity {
    @Bind({R.id.cehis})
    RelativeLayout cehis;
    @Bind({R.id.cehisfind})
    RelativeLayout cehisfind;
    @Bind({R.id.cehisxiao})
    RelativeLayout cehisxiao;
    @Bind({R.id.cehisyue})
    RelativeLayout cehisyue;
    private FragmentManager f;
    @Bind({R.id.fangying})
    RelativeLayout fangying;
    @Bind({R.id.findimage})
    ImageView findimage;
    @Bind({R.id.findtext})
    TextView findtext;
    @Bind({R.id.footall})
    LinearLayout footall;
    @Bind({R.id.footimage})
    ImageView footimage;
    @Bind({R.id.foottext})
    TextView foottext;
    @Bind({R.id.fragmetn})
    FrameLayout fragmetn;
    private FragmentTransaction ft;
    @Bind({R.id.goupiao})
    RelativeLayout goupiao;
    private MyFangYingFragment myFangYingFragment;
    private MyGoupiaoFragment myGoupiaoFragment;
    private MyNewFragment myNewFragment;
    private MySelfAFragment mySelfAFragment;
    @Bind({R.id.shouyeimage})
    ImageView shouyeimage;
    @Bind({R.id.shouyetext})
    TextView shouyetext;
    @Bind({R.id.talkiamge})
    ImageView talkiamge;
    @Bind({R.id.talktext})
    TextView talktext;
    @Bind({R.id.wode})
    RelativeLayout wode;
    @Bind({R.id.xinwen})
    RelativeLayout xinwen;

    private void hideFragments(FragmentTransaction paramFragmentTransaction) {
        if (this.myGoupiaoFragment != null) {
            paramFragmentTransaction.hide(this.myGoupiaoFragment);
        }
        if (this.myFangYingFragment != null) {
            paramFragmentTransaction.hide(this.myFangYingFragment);
        }
        if (this.myNewFragment != null) {
            paramFragmentTransaction.hide(this.myNewFragment);
        }
        if (this.mySelfAFragment != null) {
            paramFragmentTransaction.hide(this.mySelfAFragment);
        }
    }

    private void setTabSelection(int paramInt) {
        FragmentTransaction localFragmentTransaction = this.f.beginTransaction();
        localFragmentTransaction.hide(this.myGoupiaoFragment);
        hideFragments(localFragmentTransaction);
        switch (paramInt) {
            default:
                break;
            case 3:
                if (this.mySelfAFragment == null) {
                    this.mySelfAFragment = new MySelfAFragment();
                    localFragmentTransaction.add(R.id.fragmetn, this.mySelfAFragment);
                } else {
                    localFragmentTransaction.show(this.mySelfAFragment);
                }
                break;
            case 2:
                if (this.myNewFragment == null) {
                    this.myNewFragment = new MyNewFragment();
                    localFragmentTransaction.add(R.id.fragmetn, this.myNewFragment);
                } else {
                    localFragmentTransaction.show(this.myNewFragment);
                }
                break;
            case 1:
                if (this.myFangYingFragment == null) {
                    this.myFangYingFragment = new MyFangYingFragment();
                    localFragmentTransaction.add(R.id.fragmetn, this.myFangYingFragment);
                } else {
                    localFragmentTransaction.show(this.myFangYingFragment);
                }
                break;
            case 0:
                if (this.myGoupiaoFragment == null) {
                    this.myGoupiaoFragment = new MyGoupiaoFragment();
                    localFragmentTransaction.add(R.id.fragmetn, this.myGoupiaoFragment);
                } else {
                    localFragmentTransaction.show(this.myGoupiaoFragment);
                }
                break;
        }
        localFragmentTransaction.commitAllowingStateLoss();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_my_shou_ye);
        ButterKnife.bind(this);
        set();

        this.f = getSupportFragmentManager();
        this.ft = this.f.beginTransaction();
        this.myGoupiaoFragment = new MyGoupiaoFragment();
        this.ft.add(R.id.fragmetn, this.myGoupiaoFragment);
        this.ft.commit();
    }

    @OnClick({R.id.goupiao, R.id.fangying, R.id.xinwen, R.id.wode})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fangying) {
            if (i != R.id.goupiao) {
                if (i != R.id.wode) {
                    if (i != R.id.xinwen) {
                        return;
                    }

                    StatusBarUtil.setStatusBarColor(this, R.color.black);
                    this.footimage.setBackgroundResource(R.drawable.goupiao);
                    this.shouyeimage.setBackgroundResource(R.drawable.fangyin);
                    this.talkiamge.setBackgroundResource(R.drawable.xinwen1);
                    this.findimage.setBackgroundResource(R.drawable.wd);
                    setTabSelection(2);
                    return;
                }

                StatusBarUtil.setStatusBarColor(this, R.color.black);
                this.footimage.setBackgroundResource(R.drawable.goupiao);
                this.shouyeimage.setBackgroundResource(R.drawable.fangyin);
                this.talkiamge.setBackgroundResource(R.drawable.xinwen);
                this.findimage.setBackgroundResource(R.drawable.wd1);
                setTabSelection(3);
                return;
            }

            StatusBarUtil.setStatusBarColor(this, R.color.wight);
            this.footimage.setBackgroundResource(R.drawable.goupiao1);
            this.shouyeimage.setBackgroundResource(R.drawable.fangyin);
            this.talkiamge.setBackgroundResource(R.drawable.xinwen);
            this.findimage.setBackgroundResource(R.drawable.wd);
            setTabSelection(0);
            return;
        }

        StatusBarUtil.setStatusBarColor(this, R.color.wight);
        this.footimage.setBackgroundResource(R.drawable.goupiao);
        this.shouyeimage.setBackgroundResource(R.drawable.fangyin1);
        this.talkiamge.setBackgroundResource(R.drawable.xinwen);
        this.findimage.setBackgroundResource(R.drawable.wd);
        setTabSelection(1);
    }

    public void set() {
        this.cehis.setClickable(false);
        this.cehis.setPressed(false);
        this.cehis.setEnabled(false);
        this.footimage.setClickable(false);
        this.footimage.setPressed(false);
        this.footimage.setEnabled(false);
        this.foottext.setClickable(false);
        this.foottext.setPressed(false);
        this.cehisxiao.setClickable(false);
        this.cehisxiao.setPressed(false);
        this.cehisxiao.setEnabled(false);
        this.shouyeimage.setClickable(false);
        this.shouyeimage.setPressed(false);
        this.shouyeimage.setEnabled(false);
        this.shouyetext.setClickable(false);
        this.shouyetext.setPressed(false);
        this.cehisfind.setClickable(false);
        this.cehisfind.setPressed(false);
        this.cehisfind.setEnabled(false);
        this.talkiamge.setClickable(false);
        this.talkiamge.setPressed(false);
        this.talkiamge.setEnabled(false);
        this.talktext.setClickable(false);
        this.talktext.setPressed(false);
        this.cehisyue.setClickable(false);
        this.cehisyue.setPressed(false);
        this.cehisyue.setEnabled(false);
        this.findimage.setClickable(false);
        this.findimage.setPressed(false);
        this.findimage.setEnabled(false);
        this.findtext.setClickable(false);
        this.findtext.setPressed(false);
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\shouye\MyShouYeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */


