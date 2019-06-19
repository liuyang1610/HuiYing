/**
 * 添加
 */
package com.limei.movieapp.huiying.unit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class OkGoUtil {
    public static void downLoad(Context paramContext, String paramString, FileCallback paramFileCallback) {
        Log.i("sdfdsf", paramString);
        ((GetRequest) OkGo.get(paramString).tag(paramContext)).execute(paramFileCallback);
    }

    public static void getBitmap(Context paramContext, String paramString, BitmapCallback paramBitmapCallback) {
        ((GetRequest) OkGo.get(paramString).tag(paramContext)).execute(paramBitmapCallback);
    }

    public static void saveBitmapFile(Bitmap paramBitmap) {
        Object localObject = new File("/mnt/sdcard/pic/touxiang.jpg");
        try {
            localObject = new BufferedOutputStream(new FileOutputStream((File) localObject));
            paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream) localObject);
            ((BufferedOutputStream) localObject).flush();
            ((BufferedOutputStream) localObject).close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upiconloadFile(Context paramContext, String paramString1, String paramString2, File paramFile, StringCallback paramStringCallback) {
        Log.i("padddddd", paramString2);
        ((PostRequest) ((PostRequest) OkGo.post(paramString1).tag(paramContext)).params(paramString2, paramFile)).execute(paramStringCallback);
    }

    public static void uploadFiles(Context paramContext, String paramString1, String paramString2, List<File> paramList, StringCallback paramStringCallback) {
        ((PostRequest) ((PostRequest) OkGo.post(paramString1).tag(paramContext)).addFileParams(paramString2, paramList)).execute(paramStringCallback);
    }
}

