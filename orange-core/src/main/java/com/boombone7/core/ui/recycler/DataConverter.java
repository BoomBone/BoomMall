package com.boombone7.core.ui.recycler;

import java.util.ArrayList;

/**
 *
 * @author Ting
 * @date 2017/12/5
 */

public abstract class DataConverter {

    private String mJsonData = null;
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }
    public String getJsonData(){
        if(mJsonData == null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public abstract ArrayList<MultipleItemEntity> convert();

    public void clearData(){
        ENTITIES.clear();
    }
}
