package com.doctor.mds.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyc.adapter.BaseEmptyAdapter;

import java.util.List;

/**
 * Create by Admin on 2019/1/2
 */
public class DataAdapter extends BaseEmptyAdapter {
    private List<String> mList;

    public DataAdapter(List<String> dataList) {
        super(dataList);
        mList = dataList;
    }

    @Override
    protected RecyclerView.ViewHolder getContentView(ViewGroup parent, int viewType) {
        //其他的引入正常的
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void updateUI(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder hd = (ItemViewHolder) holder;
            hd.tvItem.setText(mList.get(position));
        }
    }

    @Override
    protected void setEmptyUI(ImageView emptyIcon, TextView emptyTip) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        private ItemViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tvItem);
        }
    }

}
