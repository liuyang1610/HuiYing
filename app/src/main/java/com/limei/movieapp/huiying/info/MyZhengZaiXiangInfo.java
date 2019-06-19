/**
 * 添加
 */
package com.limei.movieapp.huiying.info;

public class MyZhengZaiXiangInfo
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
    private String Playtime;
    private String dtime;
    private String iscache;
    private String isuse;
    private String movieaddress;
    private String num;
    private String pic;
    private String pic1;
    
    public String getDtime()
    {
      return this.dtime;
    }
    
    public String getIscache()
    {
      return this.iscache;
    }
    
    public String getIsuse()
    {
      return this.isuse;
    }
    
    public String getMovieaddress()
    {
      return this.movieaddress;
    }
    
    public String getNum()
    {
      return this.num;
    }
    
    public String getPic()
    {
      return this.pic;
    }
    
    public String getPic1()
    {
      return this.pic1;
    }
    
    public String getPlaytime()
    {
      return this.Playtime;
    }
    
    public void setDtime(String paramString)
    {
      this.dtime = paramString;
    }
    
    public void setIscache(String paramString)
    {
      this.iscache = paramString;
    }
    
    public void setIsuse(String paramString)
    {
      this.isuse = paramString;
    }
    
    public void setMovieaddress(String paramString)
    {
      this.movieaddress = paramString;
    }
    
    public void setNum(String paramString)
    {
      this.num = paramString;
    }
    
    public void setPic(String paramString)
    {
      this.pic = paramString;
    }
    
    public void setPic1(String paramString)
    {
      this.pic1 = paramString;
    }
    
    public void setPlaytime(String paramString)
    {
      this.Playtime = paramString;
    }
  }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\info\MyZhengZaiXiangInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */