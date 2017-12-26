package com.boombone7.orange.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.DataConverter;
import com.boombone7.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Ting on 2017/12/26.
 */

public class AddressDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(I.AddressItemType.ITEM_ADDRESS)
                    .setField(I.MultipleFields.ID, id)
                    .setField(I.MultipleFields.NAME, name)
                    .setField(I.MultipleFields.TAG, isDefault)
                    .setField(I.AddressItemFields.ADDRESS, address)
                    .setField(I.AddressItemFields.PHONE, phone)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
