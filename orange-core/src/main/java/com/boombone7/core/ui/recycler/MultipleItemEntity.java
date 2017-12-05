package com.boombone7.core.ui.recycler;

import com.boombone7.core.I;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author Ting
 * @date 2017/12/5
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> field) {
        FIELDS_REFERENCE.get().putAll(field);
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(I.MultipleFields.ITEM_TYPE);
    }

    public final MultipleItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }


}
