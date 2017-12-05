package com.boombone7.core.ui.recycler;

import java.util.ArrayList;

/**
 *
 * @author Ting
 * @date 2017/12/5
 */

public abstract class DataConver {

    private String mJsonData = null;
    public DataConver setJsonData(String json){
        this.mJsonData = json;
        return this;
    }
    public String getmJsonData(){
        if(mJsonData == null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
