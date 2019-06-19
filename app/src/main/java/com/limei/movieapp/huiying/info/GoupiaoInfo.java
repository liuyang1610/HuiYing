/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

public class GoupiaoInfo
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
    private String balance;
    private String description;
    private String id;
    private String price;
    private String sub;
    private String title;
    private String xtime;
    
    public String getBalance()
    {
      return this.balance;
    }
    
    public String getDescription()
    {
      return this.description;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public String getPrice()
    {
      return this.price;
    }
    
    public String getSub()
    {
      return this.sub;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public String getXtime()
    {
      return this.xtime;
    }
    
    public void setBalance(String paramString)
    {
      this.balance = paramString;
    }
    
    public void setDescription(String paramString)
    {
      this.description = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setPrice(String paramString)
    {
      this.price = paramString;
    }
    
    public void setSub(String paramString)
    {
      this.sub = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
    
    public void setXtime(String paramString)
    {
      this.xtime = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\GoupiaoInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */