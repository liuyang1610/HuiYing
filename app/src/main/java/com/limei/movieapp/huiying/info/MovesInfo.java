/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.io.Serializable;
import java.util.List;

public class MovesInfo
  implements Serializable
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
    implements Serializable
  {
    private String id;
    private String isshow;
    private String pic;
    private String px;
    private String title;
    
    public String getId()
    {
      return this.id;
    }
    
    public String getIsshow()
    {
      return this.isshow;
    }
    
    public String getPic()
    {
      return this.pic;
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
    
    public void setIsshow(String paramString)
    {
      this.isshow = paramString;
    }
    
    public void setPic(String paramString)
    {
      this.pic = paramString;
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


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MovesInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */