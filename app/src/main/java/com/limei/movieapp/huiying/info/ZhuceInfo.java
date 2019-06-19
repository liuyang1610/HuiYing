package com.limei.movieapp.huiying.info;

/**
 * Created by Administrator on 2018/8/27.
 */

public class ZhuceInfo {

    /**
     * code : 000
     * message :
     * data : {"id":null,"appid":"Hy5b83a96b1af60","appsecret":"0752f2e58a0e681ab13e105b166dd6a6"}
     */

    private String code;
    private String message;
    /**
     * id : null
     * appid : Hy5b83a96b1af60
     * appsecret : 0752f2e58a0e681ab13e105b166dd6a6
     */

    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private Object id;
        private String appid;
        private String appsecret;

        public void setId(Object id) {
            this.id = id;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public Object getId() {
            return id;
        }

        public String getAppid() {
            return appid;
        }

        public String getAppsecret() {
            return appsecret;
        }
    }
}
