package com.limei.movieapp.huiying.unit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;

import java.util.List;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

/**
 * Created by Administrator on 2017/9/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isShow;
    private int menuid;
    private List<String> list;
    //private SwipeBackLayout mSwipeBackLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initWindows();
    }

    public void setTitle(Toolbar toolbar, String title, boolean a, int s) {
        this.isShow = a;
        this.menuid = s;
        toolbar.setTitle("");
        TextView titleTV = (TextView) toolbar.findViewById(R.id.title);
        titleTV.setText(title);
        titleTV.setTextSize(16);
    }

    public void intent(Class da) {

        Intent intent = new Intent();
        intent.setClass(this, da);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isShow == true) {
            getMenuInflater().inflate(menuid, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initWindows() {
        Window window = getWindow();
        //这里是自己定义状态栏的颜色
        int color = getResources().getColor(R.color.transparent);
        ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
        View childAt = contentView.getChildAt(0);
        if (childAt != null) {
            // 设置内容布局充满屏幕
            childAt.setFitsSystemWindows(true);
        }
        // 6.0及以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i("sdfdsfd","111");
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            StatusBarUtil.FlymeSetStatusBarLightMode(window,true);
            StatusBarUtil.MIUISetStatusBarLightMode(window,true);
            // 4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
            view.setBackgroundColor(color);
            contentView.addView(view);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public void setList(List<String> list){
        this.list = list;
    }

    public List<String> getList(){
        return  list;
    }

}


