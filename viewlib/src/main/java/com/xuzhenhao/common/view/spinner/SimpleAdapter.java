package com.xuzhenhao.common.view.spinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * TODO
 * Created by XuZhenhao on 2017/7/13 0013.
 */

public abstract class SimpleAdapter<T> extends BaseAdapter {


    /**
     * TODO 获取数据
     * @return list<T>
     */
    public abstract List<T> getData();

    @Override
    public int getCount() {
        return getData().size();
    }

    @Override
    public T getItem(int position) {
        return getData().get(position);
    }

    public abstract String getItemValue(int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
