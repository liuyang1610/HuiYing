/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class MyFangYingZhengInfo
{
  private String code;
  private List<DataEntity> data;
  private String message;
  
  public String getCode()
  {
    return this.code;
  }
  
  public List<DataEntity> getData()
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
  
  public void setData(List<DataEntity> paramList)
  {
    this.data = paramList;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public static class DataEntity
  {
    private String Lengthtime;
    private String Playtime;
    private String Surplus;
    private String ctime;
    private String did;
    private String dtime;
    private String id;
    private String pic;
    private String saddress;
    private String stime;
    private String ticketnumber;
    private String title;
    private List<TostarEntity> tostar;
    
    public String getCtime()
    {
      return this.ctime;
    }
    
    public String getDid()
    {
      return this.did;
    }
    
    public String getDtime()
    {
      return this.dtime;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public String getLengthtime()
    {
      return this.Lengthtime;
    }
    
    public String getPic()
    {
      return this.pic;
    }
    
    public String getPlaytime()
    {
      return this.Playtime;
    }
    
    public String getSaddress()
    {
      return this.saddress;
    }
    
    public String getStime()
    {
      return this.stime;
    }
    
    public String getSurplus()
    {
      return this.Surplus;
    }
    
    public String getTicketnumber()
    {
      return this.ticketnumber;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public List<TostarEntity> getTostar()
    {
      return this.tostar;
    }
    
    public void setCtime(String paramString)
    {
      this.ctime = paramString;
    }
    
    public void setDid(String paramString)
    {
      this.did = paramString;
    }
    
    public void setDtime(String paramString)
    {
      this.dtime = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setLengthtime(String paramString)
    {
      this.Lengthtime = paramString;
    }
    
    public void setPic(String paramString)
    {
      this.pic = paramString;
    }
    
    public void setPlaytime(String paramString)
    {
      this.Playtime = paramString;
    }
    
    public void setSaddress(String paramString)
    {
      this.saddress = paramString;
    }
    
    public void setStime(String paramString)
    {
      this.stime = paramString;
    }
    
    public void setSurplus(String paramString)
    {
      this.Surplus = paramString;
    }
    
    public void setTicketnumber(String paramString)
    {
      this.ticketnumber = paramString;
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


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyFangYingZhengInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */