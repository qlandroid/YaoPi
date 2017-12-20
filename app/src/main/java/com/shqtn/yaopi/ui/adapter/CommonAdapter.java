package com.shqtn.yaopi.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.shqtn.yaopi.ui.widget.LabelTextView;

import java.util.List;


/**
 * 万能适配器
 *
 * @author ql
 * @date 2016.5.1
 */
@SuppressWarnings("unchecked")
public abstract class CommonAdapter<E> extends BaseAdapter {

    private List<E> data; // 数据源
    private Context mContext;
    private int layoutId;

    public CommonAdapter(Context context, List<E> data, int layoutId) {
        this.mContext = context;
        this.data = data;
        this.layoutId = layoutId;
    }

    public void update(List<E> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public E getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, layoutId);

        setItemContent(holder, getItem(position), position);

        return holder.getConvertView();
    }

    /**
     * 设置item中各控件显示的内容
     *
     * @param holder
     * @param e
     */
    public abstract void setItemContent(ViewHolder holder, E e, int position);

    /**
     * 持有者类（内部类）
     *
     * @author Administrator
     */
    public static class ViewHolder {

        private View mConvertView;
        private SparseArray<View> mViews;

        public ViewHolder(Context context, int layoutId) {
            this.mConvertView = View.inflate(context, layoutId, null);
            mConvertView.setTag(this);

            mViews = new SparseArray<View>();
        }

        public static ViewHolder getViewHolder(Context context, View convertView, int layoutId) {
            if (convertView == null) {
                return new ViewHolder(context, layoutId);
            } else {
                return (ViewHolder) convertView.getTag();
            }
        }

        /**
         * 通过View的id获取控件
         *
         * @param resId
         * @return
         */
        public <T extends View> T getViewById(int resId) {
            View view = mViews.get(resId);
            if (view == null) {
                view = mConvertView.findViewById(resId);
                mViews.put(resId, view);
            }
            return (T) view;
        }

        /**
         * 为Item中的TextView设置数据
         *
         * @param text
         */
        public ViewHolder setText(int viewId, CharSequence text) {
            ((TextView) getViewById(viewId)).setText(text);
            return this;
        }

        public ViewHolder setLableText(int viewId, String text) {
            ((LabelTextView) getViewById(viewId)).setText(text);
            return this;
        }

        /**
         * 为Item中的ImageView设置数据
         */
        public ViewHolder setImageResource(int viewId, int imgId) {
            ((ImageView) getViewById(viewId)).setImageResource(imgId);
            return this;
        }

        /**
         * 为Item中的ImageView设置数据
         */
        public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ((ImageView) getViewById(viewId)).setImageBitmap(bm);
            return this;
        }

        /**
         * 获取当前ViewHolder对应的View对象
         *
         * @return
         */
        public View getConvertView() {
            return mConvertView;
        }
    }

}
