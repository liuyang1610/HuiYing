package com.limei.movieapp.huiying.info;

import java.util.List;

/**
 * 2018年12月17日 16:27:10
 *  scy修改
 */
public class JijiangshangyingInfo
{
    private String code;
    private DataEntity data;
    private String message;

    public String getCode()
    {
        return this.code;
    }

    public DataEntity getData()
    {
        return this.data;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setCode(String paramString)
    {
        this.code = paramString;
    }

    public void setData(DataEntity paramDataEntity)
    {
        this.data = paramDataEntity;
    }

    public void setMessage(String paramString)
    {
        this.message = paramString;
    }

    public static class DataEntity
    {
        private List<BannerEntity> banner;
        private List<ListEntity> list;

        public List<BannerEntity> getBanner()
        {
            return this.banner;
        }

        public List<ListEntity> getList()
        {
            return this.list;
        }

        public void setBanner(List<BannerEntity> paramList)
        {
            this.banner = paramList;
        }

        public void setList(List<ListEntity> paramList)
        {
            this.list = paramList;
        }

        public static class BannerEntity
        {
            private String did;
            private String id;
            private String pic;
            private String px;
            private String url;

            public String getDid()
            {
                return this.did;
            }

            public String getId()
            {
                return this.id;
            }

            public String getPic()
            {
                return this.pic;
            }

            public String getPx()
            {
                return this.px;
            }

            public String getUrl()
            {
                return this.url;
            }

            public void setDid(String paramString)
            {
                this.did = paramString;
            }

            public void setId(String paramString)
            {
                this.id = paramString;
            }

            public void setPic(String paramString)
            {
                this.pic = paramString;
            }

            public void setPx(String paramString)
            {
                this.px = paramString;
            }

            public void setUrl(String paramString)
            {
                this.url = paramString;
            }
        }

        public static class ListEntity
        {
            private String dtime;
            private String id;
            private String pic;
            private String px;
            private String saddress;
            private String stime;
            private String title;
            private List<TostarEntity> tostar;

            public String getDtime()
            {
                return this.dtime;
            }

            public String getId()
            {
                return this.id;
            }

            public String getPic()
            {
                return this.pic;
            }

            public String getPx()
            {
                return this.px;
            }

            public String getSaddress()
            {
                return this.saddress;
            }

            public String getStime()
            {
                return this.stime;
            }

            public String getTitle()
            {
                return this.title;
            }

            public List<TostarEntity> getTostar()
            {
                return this.tostar;
            }

            public void setDtime(String paramString)
            {
                this.dtime = paramString;
            }

            public void setId(String paramString)
            {
                this.id = paramString;
            }

            public void setPic(String paramString)
            {
                this.pic = paramString;
            }

            public void setPx(String paramString)
            {
                this.px = paramString;
            }

            public void setSaddress(String paramString)
            {
                this.saddress = paramString;
            }

            public void setStime(String paramString)
            {
                this.stime = paramString;
            }

            public void setTitle(String paramString)
            {
                this.title = paramString;
            }

            public void setTostar(List<TostarEntity> paramList)
            {
                this.tostar = paramList;
            }

            public static class TostarEntity
            {
                private String id;
                private String px;
                private String title;

                public String getId()
                {
                    return this.id;
                }

                public String getPx()
                {
                    return this.px;
                }

                public String getTitle()
                {
                    return this.title;
                }

                public void setId(String paramString)
                {
                    this.id = paramString;
                }

                public void setPx(String paramString)
                {
                    this.px = paramString;
                }

                public void setTitle(String paramString)
                {
                    this.title = paramString;
                }
            }
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\JijiangshangyingInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */