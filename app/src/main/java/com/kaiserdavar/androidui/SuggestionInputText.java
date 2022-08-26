package com.kaiserdavar.androidui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.style.TextStyle;
import com.kaiserdavar.androidui.util.OnEditTextChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestionInputText extends com.kaiserdavar.androidui.BaseText<SuggestionInputText, AutoCompleteTextView> {

    private TextStyle listTextStyle;
    private Filter filter;
    private boolean isChanging;

    public static SuggestionInputText create(Context context) {
        return new SuggestionInputText(context);
    }

    public SuggestionInputText(AutoCompleteTextView view) {
        super(view);
    }

    public SuggestionInputText(Context context) {
        super(context);
    }

    @Override
    protected AutoCompleteTextView onGetMainView(Context context) {
        return new AutoCompleteTextView(context);
    }

    public SuggestionInputText text(MutableLiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> {
            if (isChanging)
                return;
            isChanging = true;
            view.setText(s);
            isChanging = false;
        });
        view.addTextChangedListener(new OnEditTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isChanging)
                    return;
                isChanging = true;
                liveData.setValue(charSequence);
                isChanging = false;
            }
        });
        return this;
    }

    public SuggestionInputText onFocus(OnFocusListener listener) {
        view.setOnFocusChangeListener((view1, b) -> {
            listener.onFocus(b);
        });
        return this;
    }

    public SuggestionInputText onText(OnEditTextChangeListener listener) {
        view.addTextChangedListener(listener);
        return this;
    }

    public SuggestionInputText onItemClick(OnItemClickListener listener) {
        view.setOnItemClickListener((adapterView, view1, i, l) -> {
            listener.onItemClick(i);
        });
        return this;
    }

    public SuggestionInputText onItemSelect(OnItemClickListener listener) {
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onItemClick(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                listener.onItemClick(-1);
            }
        });
        return this;
    }

    public <T extends ListAdapter & Filterable> SuggestionInputText adapter(T adapter) {
        view.setAdapter(adapter);
        return this;
    }

    public SuggestionInputText data(LiveData<List<String>> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::data);
        return this;
    }
    public SuggestionInputText data(List<String> items) {
        view.setAdapter(new ItemAdapter(items, filter, listTextStyle));
        return this;
    }
    public SuggestionInputText data(String[] items) {
        view.setAdapter(new ItemAdapter(Arrays.asList(items), filter, listTextStyle));
        return this;
    }
    public SuggestionInputText filter(Filter filter) {
        this.filter = filter;
        if (view.getAdapter() instanceof ItemAdapter) {
            ((ItemAdapter) view.getAdapter()).setFilter(filter);
        }
        return this;
    }

    public SuggestionInputText listTextStyle(TextStyle style) {
        this.listTextStyle = style;
        if (view.getAdapter() instanceof ItemAdapter) {
            ((ItemAdapter) view.getAdapter()).setTextStyle(style);
        }
        return this;
    }
    public SuggestionInputText listWidth(int dp) {
        view.setDropDownWidth(px(dp));
        return this;
    }
    public SuggestionInputText listHeight(int dp) {
        view.setDropDownHeight(px(dp));
        return this;
    }
    public SuggestionInputText listAnchor(int id) {
        view.setDropDownAnchor(id);
        return this;
    }
    public SuggestionInputText listBackground(@DrawableRes int resId) {
        view.setDropDownBackgroundResource(resId);
        return this;
    }
    public SuggestionInputText listOffset(int horizontal, int vertical) {
        view.setDropDownHorizontalOffset(px(horizontal));
        view.setDropDownVerticalOffset(px(vertical));
        return this;
    }

    public SuggestionInputText threshold(int threshold) {
        view.setThreshold(threshold);
        return this;
    }
    public SuggestionInputText selectItem(int position) {
        view.setListSelection(position);
        return this;
    }
    public SuggestionInputText validator(AutoCompleteTextView.Validator validator) {
        view.setValidator(validator);
        return this;
    }

    private static class ItemAdapter extends BaseAdapter implements Filterable {

        private List<String> allItems;
        private List<String> items;
        private Filter filter;
        private TextStyle textStyle;

        public ItemAdapter(List<String> items, Filter filter, TextStyle textStyle) {
            this.allItems = items;
            this.filter = filter;
            this.textStyle = textStyle;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            return Text.create(viewGroup.getContext())
                    .style(textStyle)
                    .text(items.get(position))
                    .padding(8)
                    .fullWidth()
                    .createView();
        }

        public void setTextStyle(TextStyle style) {
            textStyle = style;
        }
        public void setFilter(Filter filter) {
            this.filter = filter;
        }

        @Override
        public Filter getFilter() {
            if (filter == null)
                filter = new ArrayFilter();
            return filter;
        }

        private class ArrayFilter extends Filter {
            private final Object lock = new Object();

            @Override
            protected FilterResults performFiltering(CharSequence prefix) {
                FilterResults results = new FilterResults();
                if (allItems == null) {
                    synchronized (lock) {
                        allItems = new ArrayList<>();
                    }
                }

                if (prefix == null || prefix.length() == 0) {
                    synchronized (lock) {
                        results.values = allItems;
                        results.count = allItems.size();
                    }
                } else {
                    final String searchStrLowerCase = prefix.toString().toLowerCase();
                    ArrayList<String> matchValues = new ArrayList<>();
                    for (String dataItem : allItems) {
                        if (dataItem.toLowerCase().startsWith(searchStrLowerCase)) {
                            matchValues.add(dataItem);
                        }
                    }
                    results.values = matchValues;
                    results.count = matchValues.size();
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                items = results.values != null ? (List<String>)results.values : null;
                if (results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }
        }
    }

}
