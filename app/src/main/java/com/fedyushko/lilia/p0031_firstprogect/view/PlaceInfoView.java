package com.fedyushko.lilia.p0031_firstprogect.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fedyushko.lilia.p0031_firstprogect.R;

/**
 * Created by Lilia on 10.06.2017.
 */

public class PlaceInfoView extends LinearLayout {

    private RoundImageView mAvatarView;
    private TextView mDescriptionView;

    public PlaceInfoView(Context context) {
        this(context, null);
    }

    public PlaceInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaceInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.v_place_info, this);

        mAvatarView = (RoundImageView) findViewById(R.id.avatar);
        mDescriptionView = (TextView) findViewById(R.id.description);
    }

    public void setText(String text) {
        mDescriptionView.setText(text);
    }

    public void setImageResource(int resId) {
        mAvatarView.setImageResource(resId);
    }
}


