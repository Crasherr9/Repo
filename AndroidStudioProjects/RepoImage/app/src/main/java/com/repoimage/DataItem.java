package com.repoimage;

import com.google.gson.annotations.SerializedName;

public class DataItem {
  @SerializedName(value = "country")
  private String country;
  @SerializedName(value="flag")
  private String flag;
    @SerializedName(value="population")
    private String population;
     private Integer rank;

    public DataItem(String Country,String Flag,String Population,int Rank)
   {
       country=Country;
       flag=Flag;
       population=Population;
       rank=Rank;
   }
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCountry()
    {
        return country;
    }


    public String getFlag() {

        return flag;
    }


    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
