package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocationEditTextPreference extends EditTextPreference implements TextWatcher {


    private EditText mEditText;
    private int mMinLength;

    private static final int DEFAULT_MIN_INPUT_LENGTH = 2;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LocationEditTextPreference, 0, 0);

        try {
            mMinLength = attributes.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MIN_INPUT_LENGTH);
        } finally {
            attributes.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        mEditText = getEditText();
        mEditText.addTextChangedListener(this);
        hidePositiveButtonIfInputInvalid();
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        mEditText.removeTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {
       hidePositiveButtonIfInputInvalid();
    }

    private void hidePositiveButtonIfInputInvalid() {
        if (getDialog() instanceof AlertDialog) {
            AlertDialog alertDialog = (AlertDialog) getDialog();
            Button posButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

            if (mEditText.getText().length() < mMinLength)  {
                posButton.setEnabled(false);
            } else {
                posButton.setEnabled(true);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}

