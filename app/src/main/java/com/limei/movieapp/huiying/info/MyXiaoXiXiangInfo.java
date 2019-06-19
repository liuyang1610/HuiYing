/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

public class MyXiaoXiXiangInfo
{
  private String code;
  private NesContentInfo.DataEntity data;
  private String message;
  
  public String getCode()
  {
    return this.code;
  }
  
  public NesContentInfo.DataEntity getData()
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
  
  public void setData(NesContentInfo.DataEntity paramDataEntity)
  {
    this.data = paramDataEntity;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public static class DataEntity
  {
    private String content;
    private String ctime;
    private String title;
    
    public String getContent()
    {
      return this.content;
    }
    
    public String getCtime()
    {
      return this.ctime;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public void setContent(String paramString)
    {
      this.content = paramString;
    }
    
    public void setCtime(String paramString)
    {
      this.ctime = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyXiaoXiXiangInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */