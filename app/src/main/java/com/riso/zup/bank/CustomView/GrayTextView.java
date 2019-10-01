package com.riso.zup.bank.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.riso.zup.bank.R;

public class GrayTextView extends AppCompatTextView {
    public GrayTextView(Context context) {
        super(context);
        setInit();
    }

    public GrayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInit();
    }
    private void setInit(){
        this.setTextColor(getResources().getColor(R.color.gray));
    }
}
