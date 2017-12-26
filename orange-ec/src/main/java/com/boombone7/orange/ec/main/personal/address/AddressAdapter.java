package com.boombone7.orange.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.boombone7.core.I;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.ui.recycler.MultipleItemEntity;
import com.boombone7.core.ui.recycler.MultipleRecyclerAdapter;
import com.boombone7.core.ui.recycler.MultipleViewHolder;
import com.boombone7.orange.ec.R;

import java.util.List;

/**
 *
 * @author Ting
 * @date 2017/12/26
 */

public class AddressAdapter extends MultipleRecyclerAdapter {

    public AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(I.AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, MultipleItemEntity entity) {
        super.convert(helper, entity);
        switch (helper.getItemViewType()) {
            case I.AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(I.MultipleFields.NAME);
                final String phone = entity.getField(I.AddressItemFields.PHONE);
                final String address = entity.getField(I.AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(I.MultipleFields.TAG);
                final int id = entity.getField(I.MultipleFields.ID);

                final AppCompatTextView nameText = helper.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = helper.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = helper.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = helper.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("address.php")
                                .params("id", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(helper.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
