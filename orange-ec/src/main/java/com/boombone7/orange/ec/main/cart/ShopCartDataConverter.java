package com.boombone7.orange.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.DataConverter;
import com.boombone7.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author Ting
 * @date 2017/12/8
 */

public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(I.MultipleFields.ITEM_TYPE, I.ShopCart.SHOP_CART_ITEM)
                    .setField(I.MultipleFields.ID, id)
                    .setField(I.MultipleFields.IMAGE_URL, thumb)
                    .setField(I.ShopCart.TITLE, title)
                    .setField(I.ShopCart.DESC, desc)
                    .setField(I.ShopCart.COUNT, count)
                    .setField(I.ShopCart.PRICE, price)
                    .setField(I.ShopCart.IS_SELECTED, false)
                    .setField(I.ShopCart.POSITION, i)
                    .build();

            dataList.add(entity);
        }
        return dataList;
    }
}
