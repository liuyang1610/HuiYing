package com.limei.movieapp.huiying.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.limei.movieapp.huiying.info.CustomMission;
import com.limei.movieapp.huiying.info.CustomSqliteActor;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;


/**
 * Created by Administrator on 2018/8/14.
 */

/**
 * 有不同
 */
public class MyApplication
        extends Application {
    public static CustomMission customMission;

    public static String id;
    public static IWXAPI mWxApi;
    public static String ticketnumber;
    public static String uid;


    public static void initImageLoader(Context paramContext) {
        int i = (int) (Runtime.getRuntime().maxMemory() / 10L);
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(paramContext)
                .memoryCache(new LRULimitedMemoryCache(i))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    private void registToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, "wxebe5fd0313de8f4d", true);
        mWxApi.registerApp("wxebe5fd0313de8f4d");
    }


    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
        //MultiDex.install(this);
    }

    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
        registToWX();

        DownloadConfig.Builder localBuilder = DownloadConfig.Builder.Companion.create(this).enableDb(true).setDebug(true);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(Environment.getExternalStorageDirectory().getPath());
        localStringBuilder.append("/huiying/movie/abc/huacun");
        localBuilder = localBuilder.setDefaultPath(localStringBuilder.toString()).setMaxMission(3).setMaxRange(10).enableService(true).setDbActor(new CustomSqliteActor(this)).enableNotification(true).addExtension(ApkInstallExtension.class).addExtension(ApkOpenExtension.class);
        DownloadConfig.INSTANCE.init(localBuilder);
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\application\MyApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */