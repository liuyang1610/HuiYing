/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class MyZengPiaoInfo
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
    private String Raccount;
    private String Receive;
    private String gtime;
    private String dtime;
    private String id;
    private String num;
    private String price;
    private String ticketnumber;
    private String title;
    
    public String getCtime()
    {
      return this.gtime;
    }
    
    public String getDtime()
    {
      return this.dtime;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public String getNum()
    {
      return this.num;
    }
    
    public String getPrice()
    {
      return this.price;
    }
    
    public String getRaccount()
    {
      return this.Raccount;
    }
    
    public String getReceive()
    {
      return this.Receive;
    }
    
    public String getTicketnumber()
    {
      return this.ticketnumber;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public void setCtime(String paramString)
    {
      this.gtime = paramString;
    }
    
    public void setDtime(String paramString)
    {
      this.dtime = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setNum(String paramString)
    {
      this.num = paramString;
    }
    
    public void setPrice(String paramString)
    {
      this.price = paramString;
    }
    
    public void setRaccount(String paramString)
    {
      this.Raccount = paramString;
    }
    
    public void setReceive(String paramString)
    {
      this.Receive = paramString;
    }
    
    public void setTicketnumber(String paramString)
    {
      this.ticketnumber = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyZengPiaoInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */