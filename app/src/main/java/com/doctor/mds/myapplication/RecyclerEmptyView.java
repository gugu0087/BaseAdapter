package com.doctor.mds.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;

/**
 * Create by Admin on 2018/12/24
 */
public class RecyclerEmptyView extends RecyclerView {
    private static final String TAG = "RecyclerViewEmptySupport";
    /**
     * 当数据为空时展示的View
     */
    private View mEmptyView;
    /**
     * 创建一个观察者
     * *为什么要在onChanged里面写？
     * * 因为每次notifyDataChanged的时候，系统都会调用这个观察者的onChange函数
     * * 我们大可以在这个观察者这里判断我们的逻辑，就是显示隐藏
     */
    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        @SuppressLint("LongLogTag")
        @Override
        public void onChanged() {

            Adapter<?> adapter = getAdapter(); //这种写发跟之前我们之前看到的ListView的是一样的，判断数据为空否，再进行显示或者隐藏
            if (adapter != null && mEmptyView != null) {
                if (adapter.getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    RecyclerEmptyView.this.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                    RecyclerEmptyView.this.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
           onChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            onChanged();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onChanged();
        }
    };

    public RecyclerEmptyView(Context context) {
        super(context);
    }

    public RecyclerEmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public RecyclerEmptyView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * * @param emptyView 展示的空view
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            //这里用了观察者模式，同时把这个观察者添加进去，
            // 至于这个模式怎么用，谷歌一下，不多讲了，因为这个涉及到了Adapter的一些原理，感兴趣可以点进去看看源码，还是受益匪浅的
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        //当setAdapter的时候也调一次（实际上，经我粗略验证，不添加貌似也可以。不行就给添上呗，多大事嘛）
        emptyObserver.onChanged();
    }

}
