package com.riso.zup.bank.customView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.riso.zup.bank.R;

public class GrayLightTextView extends AppCompatTextView {
    public GrayLightTextView(Context context) {
        super(context);
        setInit();
    }

    public GrayLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInit();
    }
    private void setInit(){
        this.setTextColor(getResources().getColor(R.color.gray_light));
    }
}
