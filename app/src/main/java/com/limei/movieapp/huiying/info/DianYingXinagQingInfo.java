/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.io.Serializable;
import java.util.List;

public class DianYingXinagQingInfo
  implements Serializable
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
    private AddressEntity address;
    private String content;
    private List<String> drama;
    private String dtime;
    private String id;
    private List<PalyEntity> paly;
    private String pic;
    private String preview;
    private String px;
    private String saddress;
    private String style;
    private String title;
    
    public AddressEntity getAddress()
    {
      return this.address;
    }
    
    public String getContent()
    {
      return this.content;
    }
    
    public List<String> getDrama()
    {
      return this.drama;
    }
    
    public String getDtime()
    {
      return this.dtime;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public List<PalyEntity> getPaly()
    {
      return this.paly;
    }
    
    public String getPic()
    {
      return this.pic;
    }
    
    public String getPreview()
    {
      return this.preview;
    }
    
    public String getPx()
    {
      return this.px;
    }
    
    public String getSaddress()
    {
      return this.saddress;
    }
    
    public String getStyle()
    {
      return this.style;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public void setAddress(AddressEntity paramAddressEntity)
    {
      this.address = paramAddressEntity;
    }
    
    public void setContent(String paramString)
    {
      this.content = paramString;
    }
    
    public void setDrama(List<String> paramList)
    {
      this.drama = paramList;
    }
    
    public void setDtime(String paramString)
    {
      this.dtime = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setPaly(List<PalyEntity> paramList)
    {
      this.paly = paramList;
    }
    
    public void setPic(String paramString)
    {
      this.pic = paramString;
    }
    
    public void setPreview(String paramString)
    {
      this.preview = paramString;
    }
    
    public void setPx(String paramString)
    {
      this.px = paramString;
    }
    
    public void setSaddress(String paramString)
    {
      this.saddress = paramString;
    }
    
    public void setStyle(String paramString)
    {
      this.style = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
    
    public static class AddressEntity
    {
      private String title;
      
      public String getTitle()
      {
        return this.title;
      }
      
      public void setTitle(String paramString)
      {
        this.title = paramString;
      }
    }
    
    public static class PalyEntity
    {
      private String id;
      private String pic;
      private String px;
      private String subtitle;
      private String title;
      
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
      
      public String getSubtitle()
      {
        return this.subtitle;
      }
      
      public String getTitle()
      {
        return this.title;
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
      
      public void setSubtitle(String paramString)
      {
        this.subtitle = paramString;
      }
      
      public void setTitle(String paramString)
      {
        this.title = paramString;
      }
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\DianYingXinagQingInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */