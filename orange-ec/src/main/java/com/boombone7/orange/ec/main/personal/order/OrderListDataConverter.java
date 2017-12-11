package com.boombone7.orange.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.DataConverter;
import com.boombone7.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author Ting
 * @date 2017/12/11
 */

public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(I.OrderListItemType.ITEM_ORDER_LIST)
                    .setField(I.MultipleFields.ID, id)
                    .setField(I.MultipleFields.IMAGE_URL, thumb)
                    .setField(I.MultipleFields.TITLE, title)
                    .setField(I.OrderItemFields.PRICE, price)
                    .setField(I.OrderItemFields.TIME,time)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
