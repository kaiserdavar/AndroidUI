package com.kaiserdavar.androidui;

import android.content.Context;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.util.LetterAndDigitsInputFilter;
import com.kaiserdavar.androidui.util.NumberTextWatcher;
import com.kaiserdavar.androidui.util.OnEditTextChangeListener;

public class BaseInputText<T, M extends EditText> extends com.kaiserdavar.androidui.BaseText<T, M>
        implements TextView.OnEditorActionListener {

    private boolean isChanging;
    private OnClickListener onEditorDone;
    private OnClickListener onEditorGo;
    private OnClickListener onEditorSend;
    private OnClickListener onEditorNext;
    private OnClickListener onEditorPrevious;
    private OnClickListener onEditorSearch;

    public BaseInputText(M view) {
        super(view);
    }

    public BaseInputText(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new EditText(context);
    }


    @Override
    public T text(MutableLiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> changeText(s, null));
        view.addTextChangedListener(new OnEditTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeText(charSequence, liveData);
            }
        });
        return t;
    }

    public T letterAndDigitsInput() {
        addFilter(new LetterAndDigitsInputFilter());
        return t;
    }
    public T groupedNumberInput() {
        view.setInputType(InputType.TYPE_CLASS_NUMBER);
        view.addTextChangedListener(new NumberTextWatcher(view));
        return t;
    }

    public T onText(com.kaiserdavar.androidui.OnTextListener listener) {
        view.addTextChangedListener(new OnEditTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onText(charSequence);
            }
        });
        return t;
    }

    public T onText(OnEditTextChangeListener listener) {
        view.addTextChangedListener(listener);
        return t;
    }

    public T onText(TextWatcher listener) {
        view.addTextChangedListener(listener);
        return t;
    }

    public T onEditorGo(OnClickListener listener) {
        onEditorGo = listener;
        return t;
    }
    public T onEditorSearch(OnClickListener listener) {
        onEditorSearch = listener;
        return t;
    }
    public T onEditorSend(OnClickListener listener) {
        onEditorSend = listener;
        return t;
    }
    public T onEditorNext(OnClickListener listener) {
        onEditorNext = listener;
        return t;
    }
    public T onEditorDone(OnClickListener listener) {
        onEditorDone = listener;
        return t;
    }
    public T onEditorPrevious(OnClickListener listener) {
        onEditorPrevious = listener;
        return t;
    }

    public T onEditorAction(OnEditorActionListener listener) {
        view.setOnEditorActionListener((textView, i, keyEvent) -> listener.onEditorAction(i));
        return t;
    }

    protected void changeText(CharSequence text, MutableLiveData<CharSequence> liveData) {
        if (isChanging)
            return;
        isChanging = true;
        if (liveData != null)
            liveData.setValue(text);
        else
            view.setText(text);
        isChanging = false;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_GO:
                if (onEditorGo != null) onEditorGo.onClick();
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                if (onEditorSearch != null) onEditorSearch.onClick();
                break;
            case EditorInfo.IME_ACTION_SEND:
                if (onEditorSend != null) onEditorSend.onClick();
                break;
            case EditorInfo.IME_ACTION_NEXT:
                if (onEditorNext != null) onEditorNext.onClick();
                break;
            case EditorInfo.IME_ACTION_DONE:
                if (onEditorDone != null) onEditorDone.onClick();
                break;
            case EditorInfo.IME_ACTION_PREVIOUS:
                if (onEditorPrevious != null) onEditorPrevious.onClick();
                break;
        }
        return false;
    }

    public interface OnEditorActionListener {
        boolean onEditorAction(int actionId);
    }

}
