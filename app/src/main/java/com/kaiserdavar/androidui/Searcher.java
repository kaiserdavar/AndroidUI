package com.kaiserdavar.androidui;

import android.content.Context;

import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;

public class Searcher extends BaseVue<Searcher, SearchView>
        implements SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

    private OnQueryListener onQueryTextSubmitListener;
    private OnQueryListener onQueryTextChangeListener;

    private OnSuggestionListener onSuggestionSelectListener;
    private OnSuggestionListener onSuggestionClickListener;

    public static Searcher create(Context context) {
        return new Searcher(context);
    }

    public Searcher(SearchView view) {
        super(view);
    }

    public Searcher(Context context) {
        super(context);
    }

    @Override
    protected SearchView onGetMainView(Context context) {
        SearchView v = new SearchView(context);
        v.setOnQueryTextListener(this);
        return v;
    }

    public Searcher query(CharSequence query, boolean submit) {
        view.setQuery(query, submit);
        return this;
    }
    public Searcher queryHint(CharSequence hint) {
        view.setQueryHint(hint);
        return this;
    }
    public Searcher suggestionAdapter(CursorAdapter adapter) {
        view.setSuggestionsAdapter(adapter);
        return this;
    }

    public Searcher inputType(int inputType) {
        view.setInputType(inputType);
        return this;
    }
    public Searcher imeOptions(int imeOptions) {
        view.setImeOptions(imeOptions);
        return this;
    }

    public Searcher onSearchClick(OnClickListener listener) {
        view.setOnSearchClickListener(view1 -> {
            listener.onClick();
        });
        return this;
    }
    public Searcher onFocus(OnFocusListener listener) {
        view.setOnQueryTextFocusChangeListener((view1, b) -> {
            listener.onFocus(b);
        });
        return this;
    }
    public Searcher onClose(SearchView.OnCloseListener listener) {
        view.setOnCloseListener(listener);
        return this;
    }
    public Searcher onSubmit(OnQueryListener listener) {
        onQueryTextSubmitListener = listener;
        return this;
    }
    public Searcher onQuery(OnQueryListener listener) {
        onQueryTextChangeListener = listener;
        return this;
    }
    public Searcher onSuggestionSelect(OnSuggestionListener listener) {
        onSuggestionSelectListener = listener;
        return this;
    }
    public Searcher onSuggestionClick(OnSuggestionListener listener) {
        onSuggestionClickListener = listener;
        return this;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (onQueryTextSubmitListener != null)
            return onQueryTextSubmitListener.onQuery(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (onQueryTextChangeListener != null)
            return onQueryTextChangeListener.onQuery(newText);
        return false;
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        if (onSuggestionSelectListener != null)
            return onSuggestionSelectListener.onSuggestion(position);
        return false;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        if (onSuggestionClickListener != null)
            return onSuggestionClickListener.onSuggestion(position);
        return false;
    }


    public interface OnQueryListener {
        boolean onQuery(String text);
    }
    public interface OnSuggestionListener {
        boolean onSuggestion(int position);
    }
}
