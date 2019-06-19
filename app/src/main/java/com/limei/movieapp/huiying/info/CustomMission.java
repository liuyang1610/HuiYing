/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import org.jetbrains.annotations.NotNull;

import zlc.season.rxdownload3.core.Mission;

public class CustomMission
        extends Mission {
    private String img = "";
    private String introduce = "";

    public CustomMission(@NotNull String url, String introduce, String img) {
        super(url);
        this.introduce = introduce;
        this.img = img;
    }

    public CustomMission(@NotNull Mission mission, String img) {
        super(mission);
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\CustomMission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */