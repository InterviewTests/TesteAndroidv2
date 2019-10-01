package com.riso.zup.bank.customView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.riso.zup.bank.R;

public class WhiteTextView extends AppCompatTextView {
    public WhiteTextView(Context context) {
        super(context);
        setInit();
    }

    public WhiteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInit();
    }

    private void setInit(){
        this.setTextColor(getResources().getColor(R.color.white));
    }


}
