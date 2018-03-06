package com.repoimage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResponse {

@SerializedName("worldpopulation")
@Expose
    private List<DataItem> worldpopulation=null;
public  List<DataItem> getWorldpopulation(){
    return worldpopulation;
}

public  void setWorldpopulation(List<DataItem> worldpopulation){
    this.worldpopulation=worldpopulation;
}


}
