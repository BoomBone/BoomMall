package com.boombone7.orange.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.DataConverter;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 *
 * @author Ting
 * @date 2017/12/6
 */

public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i <size ; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(I.MultipleFields.ITEM_TYPE,I.ItemType.VERTICAL_MENU_LIST)
                    .setField(I.MultipleFields.ID, id)
                    .setField(I.MultipleFields.TEXT, name)
                    .setField(I.MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(I.MultipleFields.TAG, true);
        }
        return dataList;
    }
}
