/**
 * 添加
 */
package com.limei.movieapp.huiying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.limei.movieapp.huiying.R;

import cn.jzvd.JzvdStd;

public class MyJzvdStd
        extends JzvdStd {
    public MyJzvdStd(Context paramContext) {
        super(paramContext);
    }

    public MyJzvdStd(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public void changeUiToComplete() {
        super.changeUiToComplete();
    }

    public void changeUiToError() {
        super.changeUiToError();
    }

    public void changeUiToNormal() {
        super.changeUiToNormal();
    }

    public void changeUiToPauseClear() {
        super.changeUiToPauseClear();
    }

    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
    }

    public void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
    }

    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
    }

    public void changeUiToPreparing() {
        super.changeUiToPreparing();
    }

    public int getLayoutId() {
        return R.layout.jz_layout_standard;
    }

    public void init(Context paramContext) {
        super.init(paramContext);
    }

    public void onClick(View paramView) {
        super.onClick(paramView);
        if (paramView.getId() == R.id.fullscreen) {
            int i = this.currentScreen;
        }
    }

    public void onError(int paramInt1, int paramInt2) {
        super.onError(paramInt1, paramInt2);
    }

    public void onInfo(int paramInt1, int paramInt2) {
        super.onInfo(paramInt1, paramInt2);
    }

    public void onStateAutoComplete() {
        super.onStateAutoComplete();
    }

    public void onStateError() {
        super.onStateError();
    }

    public void onStateNormal() {
        super.onStateNormal();
    }

    public void onStatePause() {
        super.onStatePause();
    }

    public void onStatePlaying() {
        super.onStatePlaying();
    }

    public void onStatePreparing() {
        super.onStatePreparing();
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
        return super.onTouch(paramView, paramMotionEvent);
    }

    public void startVideo() {
        super.startVideo();
    }

    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }

    public void startWindowTiny() {
        super.startWindowTiny();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\view\MyJzvdStd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */