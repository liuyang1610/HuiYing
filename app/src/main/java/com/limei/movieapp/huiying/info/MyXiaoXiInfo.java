/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class MyXiaoXiInfo
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
    private String ctime;
    private String description;
    private String id;
    private String state;
    private String title;
    
    public String getCtime()
    {
      return this.ctime;
    }
    
    public String getDescription()
    {
      return this.description;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public String getState()
    {
      return this.state;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public void setCtime(String paramString)
    {
      this.ctime = paramString;
    }
    
    public void setDescription(String paramString)
    {
      this.description = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setState(String paramString)
    {
      this.state = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyXiaoXiInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */