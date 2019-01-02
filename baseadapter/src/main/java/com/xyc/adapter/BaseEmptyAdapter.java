package com.xyc.adapter;

import android.content.Context;
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
public abstract class BaseEmptyAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mList;
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected int mLayoutResId;

    private int mViewType;


    protected abstract void setEmptyUI(BaseViewHolder holder);

    /**
     * @param helper
     * @param item
     */
    protected abstract void updateUI(BaseViewHolder helper, T item);

    public BaseEmptyAdapter(Context context, List<T> mList,int layoutResId) {
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mList = mList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * viewType--分别为item以及空view
     */
    public static final int VIEW_TYPE_EMPTY = -1;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;

        if (viewType == VIEW_TYPE_EMPTY) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_layout, parent, false);
            return new BaseViewHolder(emptyView);
        }

        baseViewHolder = onBaseViewHolder(parent, viewType);

        //在这里根据不同的viewType进行引入不同的布局
        return baseViewHolder;
    }

    /**
     * @param parent
     */
    protected BaseViewHolder onBaseViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, mLayoutResId);
    }

    private View mContentView;

    public View getmContentView() {
        return mContentView;
    }

    /**
     * @param parent
     * @param layoutResId
     * @return
     */
    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        if (mContentView == null) {
            return new BaseViewHolder(getItemView(layoutResId, parent));
        }
        return new BaseViewHolder(mContentView);
    }

    /**
     * @param layoutResId
     * @param parent
     * @return
     */
    protected View getItemView(int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == VIEW_TYPE_EMPTY) {
            setEmptyUI(((BaseViewHolder) holder));
            return;
        }
        updateUI(((BaseViewHolder) holder), mList.get(position));
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
