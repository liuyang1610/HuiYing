package com.limei.movieapp.huiying.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.WebAdds;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;


/**
 * 有不同
 */

public class MyGuanYuActivity
        extends AppCompatActivity {

    @Bind({R.id.fanhui})
    ImageView fanhui;
    @Bind({R.id.kefudianhua})
    RelativeLayout kefudianhua;
    @Bind({R.id.rightss})
    TextView rightss;
    private String tel;
    @Bind({R.id.title})
    TextView title;
    private String url = "http://hy.shiduweb.com/index.php/html/agreement";
    @Bind({R.id.xieyi})
    RelativeLayout xieyi;

    private void callPhone() {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.CALL");
        localIntent.setData(Uri.parse("tel:17600581610"));
        startActivity(localIntent);
    }

    private void getDataGuanYu() {
        FormBody localFormBody = new FormBody.Builder().build();
        HttpOk.GetData(WebAdds.HTTPGUANYUWE, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {
                        url = jsonObject.getJSONObject("data").getString("url");
                        tel = jsonObject.getJSONObject("data").getString("tel");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.CALL_PHONE"}, 100);
                return;
            }
            callPhone();
            return;
        }
        callPhone();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_guan_yu);
        ButterKnife.bind(this);
        this.title.setText("关于我们");
        getDataGuanYu();
    }

    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {
        if (paramInt != 100) {
            super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
            return;
        }
        if (paramArrayOfInt[0] == 0) {
            callPhone();
            return;
        }
        Toast.makeText(this, "拒绝了电话权限", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.fanhui, R.id.kefudianhua, R.id.xieyi})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.kefudianhua) {
                if (i != R.id.xieyi) {
                    return;
                }
                Log.i("fdfdf", this.url);
                startActivity(new Intent(this, MyYongHuXIeYiActivity.class).putExtra("url", this.url));
                return;
            }
            requestPermission();
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyGuanYuActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
