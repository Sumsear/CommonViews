package com.xuzhenhao.common.view.toolbar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuzhenhao.common.view.R;


/**
 * TODO 自定义ToolBar
 * Created by XuZhenhao on 2017/6/13 0013.
 */

public class CustomToolBar extends LinearLayout {

    private View placeHolder;
    private LinearLayout llContain;
    private LinearLayout llSubTitleContain;

    private LinearLayout leftContain;
    private LinearLayout centerContain;
    private LinearLayout rightContain;


    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tvRight;
    private TextView tvSubMain;
    private TextView tvSubhead;

    private ImageView ivLeft;
    private ImageView ivCenter;
    private ImageView ivRight;


    private int leftTextSize;
    private int rightTextSize;

    private OnLeftClickListener mLeftClickListener;
    private OnRightClickListener mRightClickListener;

    public CustomToolBar(Context context) {
        this(context, null);
        init(context);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * TODO 初始化 view
     */
    private void init(Context context) {

        //初始化color
        int titleColor = Color.WHITE;
        int leftTextColor = Color.WHITE;
        int rightTextColor = Color.WHITE;

        //初始化size
        leftTextSize = 16;
        rightTextSize = 16;

        //初始化view
        LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this);

        llContain = (LinearLayout) findViewById(R.id.toolbar_title);
        placeHolder = findViewById(R.id.placeholder_view);

        initLeft(leftTextColor);

        initCenter(titleColor);

        initRight(rightTextColor);

        initSub();
    }

    /**
     * TODO 初始化 左侧
     *
     * @param leftTextColor 初始颜色
     */
    private void initLeft(int leftTextColor) {

        leftContain = (LinearLayout) findViewById(R.id.left_contain_ll);

        tvLeft = (TextView) findViewById(R.id.left_tv);
        tvLeft.setTextColor(leftTextColor);
        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftTextSize);
        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLeftClickListener == null) {
                    return;
                }
                mLeftClickListener.click(v);
            }
        });

        ivLeft = (ImageView) findViewById(R.id.left_iv);
        ivLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLeftClickListener == null) {
                    return;
                }
                mLeftClickListener.click(v);
            }
        });
    }

    /**
     * TODO init center
     *
     * @param titleColor init color
     */
    private void initCenter(int titleColor) {

        centerContain = (LinearLayout) findViewById(R.id.center_contain_ll);

        tvCenter = (TextView) findViewById(R.id.toolbar_title_tv);
        tvCenter.setTextColor(titleColor);

        ivCenter = (ImageView) findViewById(R.id.toolbar_title_iv);
    }

    /**
     * TODO init right
     *
     * @param rightTextColor init color
     */
    private void initRight(int rightTextColor) {

        rightContain = (LinearLayout) findViewById(R.id.right_contain_ll);

        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setTextColor(rightTextColor);
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightTextSize);
        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightClickListener == null) {
                    return;
                }
                mRightClickListener.click(v);
            }
        });

        ivRight = (ImageView) findViewById(R.id.right_iv);
        ivRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRightClickListener == null) {
                    return;
                }
                mRightClickListener.click(v);
            }
        });
    }

    /**
     * TODO init sub
     */
    private void initSub() {
        llSubTitleContain = (LinearLayout) findViewById(R.id.subtitle_contain_ll);
        tvSubMain = (TextView) findViewById(R.id.sub_head_tv);
        tvSubhead = (TextView) findViewById(R.id.sub_subhead_tv);
    }

    /**
     * TODO 事件订阅
     *
     * @param clickListener event
     * @return view
     */
    public CustomToolBar setOnLeftClickListener(OnLeftClickListener clickListener) {
        this.mLeftClickListener = clickListener;
        return this;
    }

    /**
     * TODO 事件订阅
     *
     * @param clickListener event
     * @return view
     */
    public CustomToolBar setOnRightClickListener(OnRightClickListener clickListener) {
        this.mRightClickListener = clickListener;
        return this;
    }


    /**
     * TODO 事件订阅
     *
     * @param value event
     * @return view
     */
    public CustomToolBar setLeftText(String value) {

        tvLeft.setVisibility(View.VISIBLE);
        ivLeft.setVisibility(View.GONE);
        tvLeft.setText(value);
        return this;
    }

    /**
     * TODO 设置标题
     *
     * @param value value
     * @return view
     */
    public CustomToolBar setTitle(String value) {

        tvCenter.setVisibility(View.VISIBLE);
        ivCenter.setVisibility(View.GONE);
        tvCenter.setText(value);
        return this;
    }


    /**
     * TODO 设置右侧导航标题
     *
     * @param value value
     * @return view
     */
    public CustomToolBar setRightText(String value) {

        tvRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.GONE);
        tvRight.setText(value);
        return this;
    }

    /**
     * TODO 设置标题字体颜色
     *
     * @param titleColor color'
     * @return view
     */
    public CustomToolBar setTitleColor(@ColorRes int titleColor) {
        tvCenter.setTextColor(ContextCompat.getColor(getContext(), titleColor));
        return this;
    }

    /**
     * TODO 设置左侧导航字体颜色
     *
     * @param leftTextColor color
     * @return view
     */
    public CustomToolBar setLeftTextColor(@ColorRes int leftTextColor) {
        tvLeft.setTextColor(ContextCompat.getColor(getContext(), leftTextColor));
        return this;
    }

    /**
     * TODO 设置右侧导航字体颜色
     *
     * @param rightTextColor color
     * @return view
     */
    public CustomToolBar setRightTextColor(@ColorRes int rightTextColor) {
        tvRight.setTextColor(ContextCompat.getColor(getContext(), rightTextColor));
        return this;
    }

    /**
     * TODO 设置标题字体大小
     *
     * @param titleTextSize 标题字体大小
     * @return view
     */
    public CustomToolBar setTitleTextSize(int titleTextSize) {
        tvCenter.setTextSize(titleTextSize);
        return this;
    }

    /**
     * TODO 设置左侧导航字体大小
     *
     * @param leftTextSize size
     * @return view
     */
    public CustomToolBar setLeftTextSize(int leftTextSize) {
        tvLeft.setTextSize(leftTextSize);
        return this;
    }

    /**
     * TODO 设置右侧导航字体大小
     *
     * @param rightTextSize size
     * @return view
     */
    public CustomToolBar setRightTextSize(int rightTextSize) {

        tvRight.setTextSize(rightTextSize);
        return this;
    }

    /**
     * TODO 设置是否占位 status bar ，用来来解决因隐藏status bar时导致的view上移
     *
     * @param isPlaceHolder is place holder
     * @return view
     */
    public CustomToolBar setPlaceHolder(boolean isPlaceHolder) {

        if (isPlaceHolder) {
            placeHolder.setVisibility(View.VISIBLE);
        } else {
            placeHolder.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * TODO 获取当前占位 view
     *
     * @return place holder
     */
    public View getPlaceHolder() {
        return placeHolder;
    }

    /**
     * TODO 设置左侧导航图标
     *
     * @param icon res
     * @return view
     */
    public CustomToolBar setLeftNavigation(int icon) {

        ivLeft.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.GONE);
        ivLeft.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
        return this;
    }

    /**
     * TODO 设置标题图标
     *
     * @param icon res
     * @return view
     */
    public CustomToolBar setCenterImage(@DrawableRes int icon) {

        ivCenter.setVisibility(View.VISIBLE);
        tvCenter.setVisibility(View.GONE);
        ivCenter.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
        return this;
    }

    /**
     * TODO 设置右侧导航图标
     *
     * @param icon res
     * @return view
     */
    public CustomToolBar setRightNavigation(int icon) {

        ivRight.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.GONE);
        ivRight.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
        return this;
    }

    /**
     * TODO 设置子标题
     *
     * @param title value
     * @return view
     */
    public CustomToolBar setSubTitle(String title) {
        llSubTitleContain.setVisibility(View.VISIBLE);
        tvSubMain.setVisibility(View.VISIBLE);
        tvSubMain.setText(title);
        return this;
    }

    /**
     * TODO 设置子标题颜色
     *
     * @param subTitleColor color
     * @return view
     */
    public CustomToolBar setSubTitleColor(@ColorRes int subTitleColor) {
        tvSubMain.setTextColor(ContextCompat.getColor(getContext(), subTitleColor));
        return this;
    }

    /**
     * TODO 设置子标题字体大小
     *
     * @param leftTextSize size
     * @return view
     */
    public CustomToolBar setSubTitleTextSize(int leftTextSize) {
        tvSubMain.setTextSize(leftTextSize);
        return this;
    }

    /**
     * TODO 设置副标题
     *
     * @param text value
     * @return view
     */
    public CustomToolBar setSubhead(String text) {
        llSubTitleContain.setVisibility(View.VISIBLE);
        tvSubhead.setVisibility(View.VISIBLE);
        tvSubhead.setText(text);
        return this;
    }

    /**
     * TODO 给副标题添加颜色
     *
     * @param subTitleColor color
     * @return view
     */
    public CustomToolBar setSubheadColor(@ColorRes int subTitleColor) {
        tvSubhead.setTextColor(ContextCompat.getColor(getContext(), subTitleColor));
        return this;
    }

    /**
     * TODO 设置副标题字体大小
     *
     * @param rightTextSize size
     * @return view
     */
    public CustomToolBar setSubheadTextSize(int rightTextSize) {
        tvSubhead.setTextSize(rightTextSize);
        return this;
    }

    /**
     * TODO 左侧添加用户自定义view
     *
     * @param view custom view
     * @return view
     */
    public CustomToolBar addLeftView(View view) {

        tvLeft.setVisibility(View.GONE);
        ivLeft.setVisibility(View.GONE);
        leftContain.addView(view);
        return this;
    }

    /**
     * TODO 左侧添加用户自定义view
     *
     * @param view custom view
     * @return view
     */
    public CustomToolBar addCenterView(View view) {

        tvCenter.setVisibility(View.GONE);
        ivCenter.setVisibility(View.GONE);
        tvSubMain.setVisibility(View.GONE);
        tvSubhead.setVisibility(View.GONE);
        centerContain.addView(view);
        return this;
    }

    /**
     * TODO 右侧添加自定义 view
     *
     * @param view custom view
     * @return view
     */
    public CustomToolBar addRightView(View view) {
        tvRight.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
        rightContain.addView(view);
        return this;
    }


    public CustomToolBar setHideBg(@ColorRes int color, @DrawableRes int left) {
        llContain.setBackgroundColor(ContextCompat.getColor(getContext(), color));
        setLeftNavigation(left);
        placeHolder.setBackgroundColor(ContextCompat.getColor(getContext(), color));
        return this;
    }

    public CustomToolBar setShowBg(@DrawableRes int bg, @DrawableRes int left) {
        llContain.setBackground(ContextCompat.getDrawable(getContext(), bg));

        setLeftNavigation(left);
        placeHolder.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.status));
        return this;
    }

    public CustomToolBar setHideBg(@ColorRes int color, @DrawableRes int left, @DrawableRes int right) {
        llContain.setBackgroundColor(ContextCompat.getColor(getContext(), color));
        setLeftNavigation(left);
        setRightNavigation(right);
        placeHolder.setBackgroundColor(ContextCompat.getColor(getContext(), color));
        return this;
    }

    public CustomToolBar setShowBg(@DrawableRes int bg, @DrawableRes int left, @DrawableRes int right) {
        llContain.setBackground(ContextCompat.getDrawable(getContext(), bg));

        setLeftNavigation(left);
        setRightNavigation(right);
        placeHolder.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.status));
        return this;
    }

    public interface OnLeftClickListener {

        void click(View v);

    }

    public interface OnRightClickListener {

        void click(View v);

    }
}