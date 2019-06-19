/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

import java.util.List;

public class ZhiFuSelerInfo
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
    private String id;
    private List<ListEntity> list;
    
    public String getBalance()
    {
      return this.balance;
    }
    
    public String getId()
    {
      return this.id;
    }
    
    public List<ListEntity> getList()
    {
      return this.list;
    }
    
    public void setBalance(String paramString)
    {
      this.balance = paramString;
    }
    
    public void setId(String paramString)
    {
      this.id = paramString;
    }
    
    public void setList(List<ListEntity> paramList)
    {
      this.list = paramList;
    }
    
    public static class ListEntity
    {
      private String full;
      private String id;
      private String px;
      private String reduce;
      private String title;
      private boolean xuan = false;
      
      public String getFull()
      {
        return this.full;
      }
      
      public String getId()
      {
        return this.id;
      }
      
      public String getPx()
      {
        return this.px;
      }
      
      public String getReduce()
      {
        return this.reduce;
      }
      
      public String getTitle()
      {
        return this.title;
      }
      
      public boolean isXuan()
      {
        return this.xuan;
      }
      
      public void setFull(String paramString)
      {
        this.full = paramString;
      }
      
      public void setId(String paramString)
      {
        this.id = paramString;
      }
      
      public void setPx(String paramString)
      {
        this.px = paramString;
      }
      
      public void setReduce(String paramString)
      {
        this.reduce = paramString;
      }
      
      public void setTitle(String paramString)
      {
        this.title = paramString;
      }
      
      public void setXuan(boolean paramBoolean)
      {
        this.xuan = paramBoolean;
      }
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\ZhiFuSelerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */