/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class FenLeiInfo {
    private String code;
    private DataEntity data;
    private String message;

    public String getCode() {
        return this.code;
    }

    public DataEntity getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(String paramString) {
        this.code = paramString;
    }

    public void setData(DataEntity paramDataEntity) {
        this.data = paramDataEntity;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public static class DataEntity {
        private List<AddressEntity> address;
        private List<StarEntity> star;
        private List<StyleEntity> style;

        public List<AddressEntity> getAddress() {
            return this.address;
        }

        public List<StarEntity> getStar() {
            return this.star;
        }

        public List<StyleEntity> getStyle() {
            return this.style;
        }

        public void setAddress(List<AddressEntity> paramList) {
            this.address = paramList;
        }

        public void setStar(List<StarEntity> paramList) {
            this.star = paramList;
        }

        public void setStyle(List<StyleEntity> paramList) {
            this.style = paramList;
        }

        public static class AddressEntity {
            private String id;
            private boolean iskoAdd = true;
            private String px;
            private String title;

            public String getId() {
                return this.id;
            }

            public String getPx() {
                return this.px;
            }

            public String getTitle() {
                return this.title;
            }

            public boolean iskoAdd() {
                return this.iskoAdd;
            }

            public void setId(String paramString) {
                this.id = paramString;
            }

            public void setIskoAdd(boolean paramBoolean) {
                this.iskoAdd = paramBoolean;
            }

            public void setPx(String paramString) {
                this.px = paramString;
            }

            public void setTitle(String paramString) {
                this.title = paramString;
            }
        }

        public static class StarEntity {
            private String id;
            private boolean isIskoStar = true;
            private String px;
            private String title;

            public String getId() {
                return this.id;
            }

            public String getPx() {
                return this.px;
            }

            public String getTitle() {
                return this.title;
            }

            public boolean isIskoStar() {
                return this.isIskoStar;
            }

            public void setId(String paramString) {
                this.id = paramString;
            }

            public void setIsIskoStar(boolean paramBoolean) {
                this.isIskoStar = paramBoolean;
            }

            public void setPx(String paramString) {
                this.px = paramString;
            }

            public void setTitle(String paramString) {
                this.title = paramString;
            }
        }

        public static class StyleEntity {
            private String id;
            private boolean iskoStyle = true;
            private String px;
            private String title;

            public String getId() {
                return this.id;
            }

            public String getPx() {
                return this.px;
            }

            public String getTitle() {
                return this.title;
            }

            public boolean isIskoStyle() {
                return this.iskoStyle;
            }

            public void setId(String paramString) {
                this.id = paramString;
            }

            public void setIskoStyle(boolean paramBoolean) {
                this.iskoStyle = paramBoolean;
            }

            public void setPx(String paramString) {
                this.px = paramString;
            }

            public void setTitle(String paramString) {
                this.title = paramString;
            }
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\FenLeiInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */