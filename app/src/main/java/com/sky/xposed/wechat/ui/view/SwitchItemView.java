package com.sky.xposed.wechat.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-12.
 */

public class SwitchItemView extends FrameLayout implements View.OnClickListener {

    private TextView tvName;
    private Switch mSwitch;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public SwitchItemView(@NonNull Context context) {
        this(context, null);
    }

    public SwitchItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    private void initView() {

        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));

        tvName = new TextView(getContext());
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(14);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;

        addView(tvName, params);

        mSwitch = new Switch(getContext());
        mSwitch.setClickable(false);
        mSwitch.setFocusable(false);
        mSwitch.setFocusableInTouchMode(false);

        params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;

        addView(mSwitch, params);
        setOnClickListener(this);
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }

    @Override
    public void onClick(View v) {

        mSwitch.setChecked(!mSwitch.isChecked());

        if (mOnCheckedChangeListener != null)
            mOnCheckedChangeListener.onCheckedChanged(this, mSwitch.isChecked());
    }

    public interface OnCheckedChangeListener {

        void onCheckedChanged(View view, boolean isChecked);
    }
}