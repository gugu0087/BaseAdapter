package com.xyc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Create by Admin on 2019/1/2
 */
public abstract class BaseEmptyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private int mViewType;

    protected abstract RecyclerView.ViewHolder getContentView(ViewGroup parent, int viewType);

    protected abstract void updateUI(RecyclerView.ViewHolder holder, int position);

    protected abstract void setEmptyUI(ImageView emptyIcon, TextView emptyTip);

    public BaseEmptyAdapter(List<String> mList) {
        this.mList = mList;
    }

    public BaseEmptyAdapter(List<String> mList, int mViewType) {
        this.mList = mList;
        this.mViewType = mViewType;
    }

    /**
     * viewType--分别为item以及空view
     */
    public static final int VIEW_TYPE_EMPTY = -1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_EMPTY) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_layout, parent, false);
            return new EmptyViewHolder(emptyView);
        }
        //在这里根据不同的viewType进行引入不同的布局
        return getContentView(parent, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof EmptyViewHolder) {
            setEmptyUI(((EmptyViewHolder) holder).ivDefault, ((EmptyViewHolder) holder).tvEmptyTip);
        }
        updateUI(holder, position);
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmptyTip;
        ImageView ivDefault;

        private EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmptyTip = (TextView) itemView.findViewById(R.id.tvEmptyTip);
            ivDefault = (ImageView) itemView.findViewById(R.id.ivDefault);
        }
    }

    @Override
    public int getItemCount() {
        //同时这里也需要添加判断，如果mData.size()为0的话，只引入一个布局，就是emptyView
        // 那么，这个recyclerView的itemCount为1
        if (mList.size() == 0) {
            return 1;
        }
        //如果不为0，按正常的流程跑
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //在这里进行判断，如果我们的集合的长度为0时，我们就使用emptyView的布局
        if (mList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }
        //如果有数据，则使用ITEM的布局
        return mViewType;
    }
}
