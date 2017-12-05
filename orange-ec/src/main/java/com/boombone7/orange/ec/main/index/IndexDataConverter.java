package com.boombone7.orange.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boombone7.core.I;
import com.boombone7.core.ui.recycler.DataConverter;
import com.boombone7.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 *
 * @author Ting
 * @date 2017/12/5
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = I.ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = I.ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = I.ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = I.ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(I.MultipleFields.ITEM_TYPE,type)
                    .setField(I.MultipleFields.SPAN_SIZE,spanSize)
                    .setField(I.MultipleFields.ID,id)
                    .setField(I.MultipleFields.TEXT,text)
                    .setField(I.MultipleFields.IMAGE_URL,imageUrl)
                    .setField(I.MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
