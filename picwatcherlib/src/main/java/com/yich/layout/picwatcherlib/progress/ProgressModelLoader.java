package com.yich.layout.picwatcherlib.progress;

import android.os.Handler;

import com.bumptech.glide.load.data.DataFetcher;

import java.io.InputStream;

public class ProgressModelLoader  {

    private Handler handler;

    public ProgressModelLoader(Handler handler) {
        this.handler = handler;
    }

//    @Override
//    public DataFetcher<InputStream> getResourceFetcher(String model, int width, int height) {
//        return new ProgressDataFetcher(model, handler);
//    }

}
