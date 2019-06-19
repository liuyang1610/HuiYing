/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class NewInfo
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
    private String fabulous;
    private String hour;
    private String id;
    private List<String> img;
    private String px;
    private String title;
    private String type;
    
    public String getFabulous()
    {
      return this.fabulous;
    }
    
    public String getHour()
    {
      return this.hour;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public List<String> getImg()
    {
      return this.img;
    }
    
    public String getPx()
    {
      return this.px;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public String getType()
    {
      return this.type;
    }
    
    public void setFabulous(String paramString)
    {
      this.fabulous = paramString;
    }
    
    public void setHour(String paramString)
    {
      this.hour = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setImg(List<String> paramList)
    {
      this.img = paramList;
    }
    
    public void setPx(String paramString)
    {
      this.px = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
    
    public void setType(String paramString)
    {
      this.type = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\NewInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */