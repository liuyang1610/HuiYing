package com.limei.movieapp.huiying;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.liyi.viewer.ImageLoader;
import com.liyi.viewer.widget.ImageViewer;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyImagePluhActivity extends AppCompatActivity {

    @Bind(R.id.imageviewer)
    ImageViewer imageViewer;

    private List<String> imageurl;
    private int posn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_pluh);
        ButterKnife.bind(this);

        imageurl = getIntent().getStringArrayListExtra("data");

        posn = getIntent().getIntExtra("posn", 0);

        Log.i("dasdasd",imageurl.size()+"");
        imageViewer.setStartPosition(posn);
        // 图片的数据源
        imageViewer.setImageData(imageurl);
        // 自定义图片的加载方式
        imageViewer.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(final int position, Object src, final ImageView view) {
                Glide.with(MyImagePluhActivity.this)
                        .load(src)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onLoadStarted(@Nullable Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                view.setImageDrawable(placeholder);
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                super.onLoadFailed(errorDrawable);
                                view.setImageDrawable(errorDrawable);
                            }

                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                view.setImageDrawable(resource);
//                                mViewDatas.get(position).setImageWidth(resource.getIntrinsicWidth());
//                                mViewDatas.get(position).setImageHeight(resource.getIntrinsicHeight());
                            }
                        });
            }
        });
        // 开启图片浏览

    }
}
