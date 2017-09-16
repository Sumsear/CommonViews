package com.xuzhenhao.common.view.spinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;

import com.xuzhenhao.common.view.CommonUtil;
import com.xuzhenhao.common.view.R;

import java.util.List;

/**
 * TODO 简单的spinner
 * Created by XuZhenhao on 2017/7/13 0013.
 */

public class SimpleSpinner extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private ListPopupWindow mListPopupWindow;
    private PopupWindow mPopupWindow;
    private List<String> mSimpleData;
    private String [] mSimpleDataStr;
    private Drawable arrowDrawable;

    private OnSpinnerClickListener onSpinnerClickListener;
    private OnItemSelectedListener onItemSelectedListener;

    public SimpleSpinner(Context context) {
        super(context);
        init(context);
    }

    public SimpleSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        this.setOnClickListener(this);
        mListPopupWindow = new ListPopupWindow(context);
        mListPopupWindow.setAnchorView(this);
        mListPopupWindow.setModal(true);

        mListPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                animateArrow(isRotate);
            }
        });

        arrowDrawable = ContextCompat.getDrawable(context, R.drawable.arrow);
        setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDrawable, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = CommonUtil.px2dip(this.getContext(), MeasureSpec.getSize(widthMeasureSpec));
        mListPopupWindow.setWidth(width);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mListPopupWindow.setWidth(getWidth());
    }

    @Override

    public void onClick(View v) {

        if (onSpinnerClickListener != null){
            onSpinnerClickListener.onClick(v);
        }

        mListPopupWindow.show();
        animateArrow(isRotate);
    }

    public ListPopupWindow getWindow() {
        return mListPopupWindow;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setOnSpinnerClickListener(OnSpinnerClickListener onSpinnerClickListener) {
        this.onSpinnerClickListener = onSpinnerClickListener;
    }

    public void attachData(String [] data) {
        if (data == null) {
            throw new NullPointerException("List can not be null");
        }
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleSpinner.this.setText(mSimpleDataStr[position]);
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                }
                mListPopupWindow.dismiss();
            }
        });
        mSimpleDataStr = data;
        mListPopupWindow.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.spinner_list_item, data));
        this.setText(mSimpleDataStr[0]);
    }


    public void attachData(List<String> data) {
        if (data == null) {
            throw new NullPointerException("List can not be null");
        }
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleSpinner.this.setText(mSimpleData.get(position));
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                }
                mListPopupWindow.dismiss();
            }
        });
        mSimpleData = data;
        mListPopupWindow.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.spinner_list_item, data));
        this.setText(mSimpleData.get(0));
    }


    public void setAdapter(final SimpleAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("adapter can not be null");
        }

        mListPopupWindow.setAdapter(adapter);
        mListPopupWindow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SimpleSpinner.this.setText(adapter.getItemValue(position));
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SimpleSpinner.this.setText(adapter.getItemValue(position));
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                }
                mListPopupWindow.dismiss();
            }
        });

        if (adapter.getData().size() > 0){
            this.setText(adapter.getItemValue(0));
        }

    }

    private int MAX_LEVEL = 10000;
    private boolean isRotate = true;

    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : MAX_LEVEL;
        int end = shouldRotateUp ? MAX_LEVEL : 0;
        ObjectAnimator animator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.start();
        isRotate = !shouldRotateUp;
    }


    public interface OnItemSelectedListener {

        void onItemSelected(AdapterView<?> parent, View view, int position, long id);
    }


    public interface OnSpinnerClickListener {

        void onClick(View v);
    }

}
