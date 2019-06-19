package com.limei.movieapp.huiying.my;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.limei.movieapp.huiying.MainActivity;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 有不同
 */

public class MyYongHuXIeYiActivity
        extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private WebSettings mWebSettings;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.title})
    TextView title;
    private String url;
    @Bind({R.id.webviews})
    WebView webviews;

    public void clearWebViewCache() {
        deleteFile(getApplicationContext().getCacheDir().getAbsoluteFile());
    }

    public void deleteFile(File paramFile) {
        Object localObject = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("delete file path=");
        localStringBuilder.append(paramFile.getAbsolutePath());
        Log.i((String) localObject, localStringBuilder.toString());
        if (paramFile.exists()) {
            if (paramFile.isFile()) {
                paramFile.delete();
            } else if (paramFile.isDirectory()) {
                File[] files = paramFile.listFiles();
                int i = 0;
                while (i < files.length) {
                    deleteFile(files[i]);
                    i += 1;
                }
            }
            paramFile.delete();
            return;
        }
        localObject = TAG;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("delete file no exists ");
        localStringBuilder.append(paramFile.getAbsolutePath());
        Log.e((String) localObject, localStringBuilder.toString());
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_yong_hu_xie_yi);
        ButterKnife.bind(this);
        this.url = getIntent().getStringExtra("url");
        Log.i("fdfdf", this.url);
        this.title.setText("用户及服务使用协议");
        this.mWebSettings = this.webviews.getSettings();
        this.mWebSettings.setUseWideViewPort(true);
        this.mWebSettings.setLoadWithOverviewMode(true);
        this.mWebSettings.setSupportZoom(true);
        this.mWebSettings.setBuiltInZoomControls(true);
        this.mWebSettings.setDisplayZoomControls(false);
        this.mWebSettings.setCacheMode(1);
        this.mWebSettings.setAllowFileAccess(true);
        this.mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebSettings.setLoadsImagesAutomatically(true);
        this.mWebSettings.setDefaultTextEncodingName("utf-8");
        this.mWebSettings.setJavaScriptEnabled(true);
        this.mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        Log.i("sdffds", this.url);
        this.webviews.loadUrl("http://hy.shiduweb.com/index.php/html/agreement");
        this.webviews.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString) {
                paramAnonymousWebView.loadUrl(paramAnonymousString);
                return true;
            }
        });
        this.webviews.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt) {
                if (paramAnonymousInt < 100) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(paramAnonymousInt);
                    stringBuilder.append("%");
                    stringBuilder.toString();
                }
            }

            public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString) {
                System.out.println("标题在这里");
            }
        });
    }

    protected void onDestroy() {
        if (this.webviews != null) {
            this.webviews.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            this.webviews.clearHistory();
            ((ViewGroup) this.webviews.getParent()).removeView(this.webviews);
            this.webviews.destroy();
            this.webviews = null;
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramInt == 4) && (this.webviews.canGoBack())) {
            this.webviews.goBack();
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        if (paramView.getId() != R.id.fanhui) {
            return;
        }
        clearWebViewCache();
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyYongHuXIeYiActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
