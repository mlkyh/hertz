package com.mlkyh.hertz;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MultiTextWatcher {

    private TextWatcherWithInstance callback;

    public void setCallback(TextWatcherWithInstance callback) {
        this.callback = callback;
    }

    public MultiTextWatcher registerEditText(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                callback.beforeTextChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callback.onTextChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                callback.afterTextChanged();
            }
        });

        return this;
    }

    interface TextWatcherWithInstance {
        void beforeTextChanged();

        void onTextChanged();

        void afterTextChanged();
    }
}
