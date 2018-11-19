package com.fedyushko.lilia.p0031_firstprogect.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fedyushko.lilia.p0031_firstprogect.R;

public class DivideView extends LinearLayout {

    private ImageView arrowView;
    private TextView arrowDescription;

    public DivideView(Context context) {
        this(context, null);
    }

    public DivideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0 );
    }

    public DivideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.v_divide_info, this);

        arrowView = (ImageView) findViewById(R.id.arrow);
        arrowDescription = (TextView) findViewById(R.id.arrowDescription);
    }

    public void setText(String text) {
        arrowDescription.setText(text);
    }
}
