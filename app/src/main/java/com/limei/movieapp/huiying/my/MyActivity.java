package com.limei.movieapp.huiying.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.ActionSheetDialog;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.OkGoUtil;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


public class MyActivity
        extends AppCompatActivity {
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                String[] stras = MyActivity.this.pic.split("/");
                if ((!stras[0].equals("https:")) && (!stras[0].equals("http:"))) {
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(WebAdds.YUMING);
                    localStringBuilder.append(MyActivity.this.pic);
                    Picasso.with(MyActivity.this).load(localStringBuilder.toString()).into(MyActivity.this.icon);
                } else {
                    Picasso.with(MyActivity.this).load(MyActivity.this.pic).into(MyActivity.this.icon);
                }
                MyActivity.this.name.setText(MyActivity.this.names);
                MyActivity.this.iphone2.setText(MyActivity.this.mobile);
                return false;
            }
            if (paramAnonymousMessage.arg1 == 1) {
                Toast.makeText(MyActivity.this, "无数据", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 2) {
                Toast.makeText(MyActivity.this, "修改头像成功", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 3) {
                Toast.makeText(MyActivity.this, "修改头像失败", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    private Bitmap head;
    @Bind({R.id.icon})
    CircleTextImageView icon;
    @Bind({R.id.iphone2})
    TextView iphone2;
    @Bind({R.id.iphonenum})
    RelativeLayout iphonenum;
    @Bind({R.id.mima})
    RelativeLayout mima;
    private String mobile;
    @Bind({R.id.name})
    TextView name;
    private String names;
    private String namess;
    @Bind({R.id.nicheng})
    RelativeLayout nicheng;
    private String pic;
    @Bind({R.id.rightss})
    TextView rightss;

    StringCallback stringCallback = new StringCallback() {
        public void onError(Call paramAnonymousCall, Response paramAnonymousResponse, Exception paramAnonymousException) {
            super.onError(paramAnonymousCall, paramAnonymousResponse, paramAnonymousException);
            Log.i("sadsadasdas", "错误");
        }

        public void onSuccess(String paramAnonymousString, Call paramAnonymousCall, Response paramAnonymousResponse) {
            Log.i("sadsadasdas", paramAnonymousString);
            try {
                JSONObject jsonObject = new JSONObject(paramAnonymousString);
                if (jsonObject.getString("code").equals("000")) {

                    pic = jsonObject.getJSONObject("data").getString("pic");

                    postSetZiLiaoIcon(pic);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private String[] strs;
    public File tempFile;
    @Bind({R.id.title})
    TextView title;
    @Bind({R.id.touxiangdian})
    RelativeLayout touxiangdian;
    private Uri uritempFile;
    @Bind({R.id.you})
    ImageView you;
    @Bind({R.id.you1})
    ImageView you1;
    @Bind({R.id.you2})
    ImageView you2;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPMYINFO, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {

                        pic = jsonObject.getJSONObject("data").getString("pic");
                        names = jsonObject.getJSONObject("data").getString("name");
                        mobile = jsonObject.getJSONObject("data").getString("mobile");

                        Message message = new Message();
                        message.arg1 = 0;
                        handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void openCa() {
        this.tempFile = new File(Environment.getExternalStorageDirectory().getPath(), "bg.jpg");
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (Build.VERSION.SDK_INT >= 24) {
            localIntent.setFlags(2);
            Uri localUri = FileProvider.getUriForFile(this, "name", this.tempFile);
            localIntent.putExtra("output", localUri);
            Log.e("dasd", localUri.toString());
        } else {
            localIntent.putExtra("output", Uri.fromFile(this.tempFile));
        }
        startActivityForResult(localIntent, 5);
    }

    private void openXiang() {
        Intent localIntent = new Intent("android.intent.action.PICK", null);
        localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(localIntent, 4);
    }

    private void postSetZiLiaoIcon(String paramString) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("");
        Log.i("asdsadsfd", localStringBuilder.toString());

        FormBody formBody = new FormBody.Builder().add("uid", this.strs[2]).add("pic", paramString).build();
        HttpOk.GetData(WebAdds.HTTPXIUGAITOUXIANG, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("asdsadsfd", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    if (new JSONObject(s).getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg1 = 2;
                        handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg1 = 3;
                    handler.sendMessage(message);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestPermissionCa() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 100);
                return;
            }
            openCa();
            return;
        }
        openCa();
    }

    private void requestPermissionXiang() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 90);
                return;
            }
            openXiang();
            return;
        }
        openXiang();
    }

    private String setPicToView(Bitmap mBitmap, String imagenames) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = imagenames;
        File file = new File(appDir, fileName);

        try {

            FileOutputStream fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cropPhotoIcon(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //intent.putExtra("scale", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 550);
        intent.putExtra("outputY", 550);
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, 6);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if ((paramInt1 == 4) && (paramInt2 == -1)) {
            cropPhotoIcon(paramIntent.getData());
        }
        if ((paramInt1 == 5) && (paramInt2 == -1)) {
            if (Build.VERSION.SDK_INT >= 24) {
                cropPhotoIcon(FileProvider.getUriForFile(this, "name", this.tempFile));
            } else {
                cropPhotoIcon(Uri.fromFile(this.tempFile));
            }
        }
        if ((paramInt1 == 1) && (paramInt2 == 2)) {
            this.namess = paramIntent.getStringExtra("name");
            this.name.setText(this.namess);
        }
        if ((paramInt1 == 6) && (paramIntent != null)) {

            if (paramIntent != null) {
                try {
                    head = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                    Bundle extras = data.getExtras();
//                    head = extras.getParcelable("data");
                if (head != null) {
                    /**
                     * 上传服务器代码
                     */

                    String a = setPicToView(head, "head.jpg");// 保存在SD卡中
                    icon.setImageBitmap(head);// 用ImageView显示出来
                    File file = new File(a);
                    Log.i("filesss", file + "");
                    OkGoUtil.upiconloadFile(this, WebAdds.YUMING + "UploadHeads.html", "pic", file, stringCallback);
                }
            }

        }
    }

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.title.setText("我的");
        getData();
    }

    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {
        if (paramInt != 90) {
            if (paramInt != 100) {
                super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
                return;
            }
            if (paramArrayOfInt[0] == 0) {
                openCa();
                return;
            }
            Toast.makeText(this, "拒绝了相机权限", Toast.LENGTH_SHORT).show();
            return;
        }
        if (paramArrayOfInt[0] == 0) {
            openXiang();
            return;
        }
        Toast.makeText(this, "拒绝了相册权限", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.fanhui, R.id.touxiangdian, R.id.nicheng, R.id.mima, R.id.iphonenum})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.mima) {
                if (i != R.id.nicheng) {
                    if (i != R.id.touxiangdian) {
                        return;
                    }
                    new ActionSheetDialog(this).builder().setCancelable(false).setCanceledOnTouchOutside(false).addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                        public void onClick(int paramAnonymousInt) {
                            MyActivity.this.requestPermissionCa();
                        }
                    }).addSheetItem("从相册中选取", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                        public void onClick(int paramAnonymousInt) {
                            MyActivity.this.requestPermissionXiang();
                        }
                    }).show();
                    return;
                }
                startActivityForResult(new Intent(this, MyNiChengActivity.class), 1);
            }
        } else {
            finish();
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
