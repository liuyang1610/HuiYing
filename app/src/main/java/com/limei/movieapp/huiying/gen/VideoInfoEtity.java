/**
 * 添加
 */
package com.limei.movieapp.huiying.gen;

public class VideoInfoEtity {
    private Long id;
    public String moveImgUrl = "";
    public String movedowid = "";
    public String movefilepath = "";
    public String movename = "";
    public String zhuangtai = "";

    public VideoInfoEtity() {
    }

    public VideoInfoEtity(Long paramLong, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
        this.id = paramLong;
        this.movename = paramString1;
        this.movefilepath = paramString2;
        this.movedowid = paramString3;
        this.moveImgUrl = paramString4;
        this.zhuangtai = paramString5;
    }

    public Long getId() {
        return this.id;
    }

    public String getMoveImgUrl() {
        return this.moveImgUrl;
    }

    public String getMovedowid() {
        return this.movedowid;
    }

    public String getMovefilepath() {
        return this.movefilepath;
    }

    public String getMovename() {
        return this.movename;
    }

    public String getZhuangtai() {
        return this.zhuangtai;
    }

    public void setId(Long paramLong) {
        this.id = paramLong;
    }

    public void setMoveImgUrl(String paramString) {
        this.moveImgUrl = paramString;
    }

    public void setMovedowid(String paramString) {
        this.movedowid = paramString;
    }

    public void setMovefilepath(String paramString) {
        this.movefilepath = paramString;
    }

    public void setMovename(String paramString) {
        this.movename = paramString;
    }

    public void setZhuangtai(String paramString) {
        this.zhuangtai = paramString;
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\gen\VideoInfoEtity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */