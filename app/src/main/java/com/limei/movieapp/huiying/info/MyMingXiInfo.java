/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class MyMingXiInfo
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
    private String price;
    private String type;
    
    public String getCtime()
    {
      return this.ctime;
    }
    
    public String getPrice()
    {
      return this.price;
    }
    
    public String getType()
    {
      return this.type;
    }
    
    public void setCtime(String paramString)
    {
      this.ctime = paramString;
    }
    
    public void setPrice(String paramString)
    {
      this.price = paramString;
    }
    
    public void setType(String paramString)
    {
      this.type = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyMingXiInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */