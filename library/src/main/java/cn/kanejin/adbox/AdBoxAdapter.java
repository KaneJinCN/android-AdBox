package cn.kanejin.adbox;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kane on 8/3/16.
 */
public abstract class AdBoxAdapter {
    private static final String TAG = AdBoxAdapter.class.getSimpleName();

    private List<AdItem> mAdItems = new ArrayList<>();
    private final Object mLock = new Object();

    private LayoutInflater mLayoutInflate;
    private Context mContext;

    public AdBoxAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflate = LayoutInflater.from(context);
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflate;
    }

    protected Context getContext() {
        return mContext;
    }

    public int getCount() {
        return mAdItems.size();
    }

    protected void setItems(List<AdItem> items) {
        synchronized (mLock) {
            this.mAdItems = items;
        }
        notifyDataSetChanged();
    }

    public void addItem(AdItem item) {
        synchronized (mLock) {
            mAdItems.add(item);
        }
        notifyDataSetChanged();
    }
    public void insertItem(AdItem item, int position) {
        synchronized (mLock) {
            mAdItems.add(position, item);
        }
        notifyDataSetChanged();
    }

    public AdItem getItem(int position) {
        return mAdItems.get(position);
    }

    public boolean isEmpty() {
        return mAdItems.isEmpty();
    }

    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }
}
