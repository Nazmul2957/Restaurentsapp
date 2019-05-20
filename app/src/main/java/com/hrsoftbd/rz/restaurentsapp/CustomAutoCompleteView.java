package com.hrsoftbd.rz.restaurentsapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by Hp on 02-May-19.
 */

public class CustomAutoCompleteView extends android.support.v7.widget.AppCompatAutoCompleteTextView {

    public CustomAutoCompleteView(Context context) {
        super(context);

    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    // this is how to disable AutoCompleteTextView filter
    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }

    /*
     * after a selection we have to capture the new value and append to the existing text
     */
    @Override
    protected void replaceText(final CharSequence text) {
        super.replaceText(text);
    }
}
