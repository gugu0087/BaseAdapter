package com.doctor.mds.myapplication;

import android.content.Context;

import com.xyc.adapter.BaseEmptyAdapter;
import com.xyc.adapter.BaseViewHolder;

import java.util.List;

/**
 * Create by Admin on 2019/1/2
 */
public class DataAdapter extends BaseEmptyAdapter<DataModel> {


    public DataAdapter(Context context, List<DataModel> mList) {
        super(context, mList, R.layout.item_layout);
    }


    @Override
    protected void setEmptyUI(BaseViewHolder holder) {
        holder.setText(R.id.tvEmptyTip, "dadasd");
    }

    @Override
    protected void updateUI(BaseViewHolder helper, DataModel item) {

        helper.setText(R.id.tvItem, item.getName());
    }

}
