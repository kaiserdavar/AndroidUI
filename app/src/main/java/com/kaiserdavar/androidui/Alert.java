package com.kaiserdavar.androidui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.kaiserdavar.androidui.container.DialogContainer;
import com.kaiserdavar.androidui.scroll.VScroll;
import com.kaiserdavar.androidui.stack.HStack;
import com.kaiserdavar.androidui.stack.RStack;
import com.kaiserdavar.androidui.stack.RadioItem;
import com.kaiserdavar.androidui.stack.VStack;
import com.kaiserdavar.androidui.style.AlertStyle;
import com.kaiserdavar.androidui.style.TextStyle;

import java.util.Calendar;

public class Alert extends DialogContainer implements
        View.OnClickListener, TextWatcher,
        TimePicker.OnTimeChangedListener, DatePicker.OnDateChangedListener {
    public static AlertStyle defaultStyle;

    public static final int MODE_MESSAGE = 0;
    public static final int MODE_INPUT = 1;
    public static final int MODE_PROGRESS = 2;
    public static final int MODE_IMAGE = 3;
    public static final int MODE_SELECT_LIST = 4;
    public static final int MODE_SINGLE_CHOICE_LIST = 5;
    public static final int MODE_MULTI_CHOICE_LIST = 6;
    public static final int MODE_DATE_PICKER = 7;
    public static final int MODE_TIME_PICKER = 8;
    public static final int MODE_CUSTOM_VIEW = 10;

    //public static final int ACTION_DISMISS = -1;
    public static final int ACTION_NEGATIVE = 0;
    public static final int ACTION_POSITIVE = 1;
    public static final int ACTION_CUSTOM = 2;
    public static final int ACTION_LIST_ITEM = 3;

    public static final String NEGATIVE_ACTION_VIEW_TAG = "NegativeAction";
    public static final String POSITIVE_ACTION_VIEW_TAG = "PositiveAction";
    public static final String CUSTOM_ACTION_VIEW_TAG = "CustomAction";
    public static final String FRAGMENT_DEFAULT_TAG = "BaseAlert";

    private int mMode;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private int mMessageIconRes;
    private String[] mListItems;
    private boolean[] mSelectedItems;
    private CharSequence mInitialInputText;
    private int mInputType = InputType.TYPE_CLASS_TEXT;
    private CharSequence mInputText;
    private CharSequence mProgressDescription;
    private boolean mRtl = true;
    private String mPositiveButtonText;
    private String mNegativeButtonText;
    private String mCustomButtonText;
    private boolean mIsPositiveActionDestructive;
    private boolean mIsCancelableOnTouchOutside = true;
    private boolean mIsCancelable = true;
    private View mCustomView;
    private AlertStyle alertStyle;

    private OnAlertActionListener onAlertActionListener;
    private int mLastItemSelected = -1;
    private RStack listStack;

    private int[] mDate;
    private int[] mTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mMode = savedInstanceState.getInt("Mode");
            mTitle = savedInstanceState.getString("Title");
            mMessage = savedInstanceState.getCharSequence("Message");
            mMessageIconRes = savedInstanceState.getInt("MessageIconRes");
            mListItems = savedInstanceState.getStringArray("ListItems");
            mSelectedItems = savedInstanceState.getBooleanArray("SelectedItems");
            mLastItemSelected = savedInstanceState.getInt("LastItemSelected");
            mInitialInputText = savedInstanceState.getString("InitialInputText");
            mInputType = savedInstanceState.getInt("InputType");
            mProgressDescription = savedInstanceState.getString("ProgressDescription");
            mNegativeButtonText = savedInstanceState.getString("NegativeButtonText");
            mPositiveButtonText = savedInstanceState.getString("PositiveButtonText");
            mCustomButtonText = savedInstanceState.getString("CustomButtonText");
            mIsCancelableOnTouchOutside = savedInstanceState.getBoolean("IsCancelableOnTouchOutside");
            mIsCancelable = savedInstanceState.getBoolean("IsCancelable");
            mRtl = savedInstanceState.getBoolean("Rtl");
            mIsPositiveActionDestructive = savedInstanceState.getBoolean("IsPositiveActionDestructive");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle b) {
        super.onSaveInstanceState(b);
        b.putInt("Mode", mMode);
        b.putCharSequence("Title", mTitle);
        b.putCharSequence("Message", mMessage);
        b.putInt("MessageIconRes", mMessageIconRes);
        b.putStringArray("ListItems", mListItems);
        b.putBooleanArray("SelectedItems", mSelectedItems);
        b.putInt("LastItemSelected", mLastItemSelected);
        b.putCharSequence("InitialInputText", mInitialInputText);
        b.putInt("InputType", mInputType);
        b.putCharSequence("ProgressDescription", mProgressDescription);
        b.putString("NegativeButtonText", mNegativeButtonText);
        b.putString("PositiveButtonText", mPositiveButtonText);
        b.putString("CustomButtonText", mCustomButtonText);
        b.putBoolean("IsCancelableOnTouchOutside", mIsCancelableOnTouchOutside);
        b.putBoolean("IsCancelable", mIsCancelable);
        b.putBoolean("Rtl", mRtl);
        b.putBoolean("IsPositiveActionDestructive", mIsPositiveActionDestructive);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(requireContext(), getTheme());
    }

    @Override
    protected Vue onContent() {
        return getMainView();
    }

    private Vue getMainView() {
        Context context = getContext();
        if (alertStyle == null) {
            alertStyle = defaultStyle;
        }

        VStack mainStack = VStack.create(context)
                .fullSize()
                .paddingVertical(16)
                .spacing(16)
                .vue(v -> {
                    if (mRtl) v.rtl();
                });
        if (mTitle != null) {
            mainStack.child(Text.create(context)
                    .fullWidth()
                    .marginHorizontal(16)
                    .lines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .textSize(20)
                    .text(mTitle)
                    .vue(v -> {
                        if (mMessageIconRes != 0) {
                            v.drawablePadding(16);
                            v.drawableTop(mMessageIconRes);
                            v.textAlign(Gravity.CENTER_HORIZONTAL);
                        }
                        if (alertStyle != null)
                            v.style(alertStyle.titleStyle);
                    }));
        }


        if (mMessage != null || mMode == MODE_DATE_PICKER || mMode == MODE_TIME_PICKER) {
            VStack messageStack = VStack.create(context).fullWidth();
            mainStack.child(VScroll.create(context)
                    .fullWidth()
                    .height(0).weight(1f)
                    .child(messageStack));

            if (mMessage != null || mMessageIconRes != 0) {
                messageStack.child(Text.create(context)
                        .fullWidth()
                        .marginHorizontal(16)
                        .lines(1)
                        .lineSpacing(8)
                        .text(mMessage)
                        .vue(v -> {
                            if (alertStyle != null)
                                v.style(alertStyle.messageStyle);
                        }));
            }

            if (mMode == MODE_DATE_PICKER) {
                if (mDate == null) {
                    mDate = new int[3];
                    Calendar c = Calendar.getInstance();
                    mDate[0] = c.get(Calendar.MONTH);
                    mDate[1] = c.get(Calendar.DAY_OF_MONTH);
                    mDate[2] = c.get(Calendar.YEAR);
                }

                DatePicker datePicker = new DatePicker(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                datePicker.setLayoutParams(layoutParams);
                datePicker.init(mDate[2], mDate[0], mDate[1], this);
                datePicker.setSpinnersShown(true);
                datePicker.setCalendarViewShown(false);

                messageStack.child(datePicker);
            }
            else if (mMode == MODE_TIME_PICKER) {
                if (mTime == null) {
                    mTime = new int[2];
                    Calendar c = Calendar.getInstance();
                    mTime[0] = c.get(Calendar.HOUR_OF_DAY);
                    mTime[1] = c.get(Calendar.MINUTE);
                }

                TimePicker timePicker = new TimePicker(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                timePicker.setLayoutParams(layoutParams);
                timePicker.setIs24HourView(true);
                timePicker.setOnTimeChangedListener(this);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    timePicker.setCurrentHour(mTime[0]);
                    timePicker.setCurrentMinute(mTime[1]);
                } else {
                    timePicker.setHour(mTime[0]);
                    timePicker.setMinute(mTime[1]);
                }

                messageStack.child(timePicker);
            }
        }

        if (mMode == MODE_INPUT) {
            mainStack.child(InputText.create(context)
                    .fullWidth()
                    .marginHorizontal(16)
                    .inputType(mInputType)
                    .onText(this)
                    .vue(v -> {
                        if (alertStyle != null)
                            v.style(alertStyle.inputTextStyle);
                        if (mInitialInputText != null) {
                            v.text(mInitialInputText);
                            v.view().setSelection(mInitialInputText.length());
                        }
                    }));

            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        else if (mMode == MODE_PROGRESS) {
            mainStack.child(HStack.create(context)
                    .fullWidth()
                    .marginHorizontal(16)
                    .gravity(Gravity.CENTER)
                    .child(Progress.create(context)
                            .size(30, 30)
                            .margin(8)
                    )
                    .child(Text.create(context)
                            .width(0).weight(1f)
                            .text(mProgressDescription))
            );
        }
        else if (mMode == MODE_SELECT_LIST ||
                mMode == MODE_SINGLE_CHOICE_LIST ||
                mMode == MODE_MULTI_CHOICE_LIST) {
            mainStack.child(RStack.create(context)
                    .fullWidth()
                    .height(0).weight(1f)
                    .vue(v -> listStack = v)
                    .onItemCount(() -> mListItems.length)
                    .onChild(viewType -> getListItemView())
                    .onBind((vh, position, viewType) -> onListItemView(vh, position))
                    .load());
        } else if (mMode == MODE_CUSTOM_VIEW) {
            mainStack.child(mCustomView);
        }

        if (mPositiveButtonText != null || mNegativeButtonText != null || mCustomButtonText != null) {
            HStack buttonStack = HStack.create(context)
                    .minHeight(40)
                    .marginHorizontal(16)
                    .spacing(8);
            if (mainStack.view().getChildCount() > 0)
                mainStack.child(Spacer.create(context).height(8));
            mainStack.child(buttonStack);

            if (mPositiveButtonText != null) {
                buttonStack.child(Text.create(context)
                        .width(0).weight(1f)
                        .fullHeight()
                        .paddingHorizontal(16)
                        .textAlign(Gravity.CENTER)
                        .text(mPositiveButtonText)
                        .tag(POSITIVE_ACTION_VIEW_TAG)
                        .selectableItemBackground()
                        .view(v -> v.setOnClickListener(this))
                        .vue(v -> {
                            if (alertStyle != null) {
                                v.style(mIsPositiveActionDestructive
                                        ? alertStyle.positiveDestructiveButtonStyle
                                        : alertStyle.positiveButtonStyle);
                            }
                        }));
            }
            if (mNegativeButtonText != null) {
                buttonStack.child(Text.create(context)
                        .width(0).weight(1f)
                        .fullHeight()
                        .paddingHorizontal(16)
                        .textAlign(Gravity.CENTER)
                        .text(mNegativeButtonText)
                        .tag(NEGATIVE_ACTION_VIEW_TAG)
                        .selectableItemBackground()
                        .view(v -> v.setOnClickListener(this))
                        .vue(v -> {
                            if (alertStyle != null)
                                v.style(alertStyle.negativeButtonStyle);
                        }));
            }
            if (mCustomButtonText != null) {
                buttonStack.child(Text.create(context)
                        .width(0).weight(1f)
                        .fullHeight()
                        .paddingHorizontal(16)
                        .textAlign(Gravity.CENTER)
                        .text(mCustomButtonText)
                        .tag(CUSTOM_ACTION_VIEW_TAG)
                        .selectableItemBackground()
                        .view(v -> v.setOnClickListener(this))
                        .vue(v -> {
                            if (alertStyle != null)
                                v.style(alertStyle.customButtonStyle);
                        }));
            }
        }
        return mainStack;
    }


    private Vue getListItemView() {
        Context context = getContext();
        HStack itemStack = HStack.create(context)
                .fullWidth()
                .paddingHorizontal(16)
                .paddingVertical(8)
                .gravity(Gravity.CENTER_VERTICAL)
                .spacing(8)
                .selectableItemBackground();
        if (mMode == MODE_SINGLE_CHOICE_LIST) {
            itemStack.child(RadioItem.create(context).id(2));
        } else if (mMode == MODE_MULTI_CHOICE_LIST) {
            itemStack.child(Check.create(context).id(3));
        }
        itemStack.child(Text.create(context)
                .id(1)
                .width(0).weight(1f)
                .vue(v -> {
                    if (alertStyle != null)
                        v.style(alertStyle.listItemStyle);
                }));
        return itemStack;
    }
    private void onListItemView(RStack.ItemAdapter.ViewHolder vh, int position) {
        HStack itemStack = (HStack) vh.vue;
        Text title = vh.find(1);
        RadioItem radio = vh.find(2);
        Check check = vh.find(3);
        title.text(mListItems[position]);
        if (radio != null) {
            radio.view().setChecked(mLastItemSelected == position);
        } else if (check != null) {
            check.toggle(mSelectedItems[position]);
        }
        itemStack.onClick(() -> onItemClick(position));
    }


    //Builder methods --------------------------->

    @SuppressWarnings("unused")
    public Alert mode(int mode) {
        mMode = mode;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert style(AlertStyle alertStyle) {
        this.alertStyle = alertStyle;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert title(CharSequence title) {
        mTitle = title;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert message(CharSequence message) {
        mMessage = message;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert icon(@DrawableRes int iconRes) {
        mMessageIconRes = iconRes;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert inputText(CharSequence initialInputText) {
        mInitialInputText = initialInputText;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert inputType(int type) {
        mInputType = type;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert progressDescription(CharSequence description) {
        mProgressDescription = description;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert listItems(String... items) {
        mListItems = items;
        mSelectedItems = new boolean[items.length];
        return this;
    }

    @SuppressWarnings("unused")
    public Alert listsSelectedItems(boolean[] selectedItems) {
        mSelectedItems = selectedItems;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert listSelectedItem(int selectedItem) {
        mLastItemSelected = selectedItem;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert time(int hour, int minute) {
        if (mTime == null)
            mTime = new int[2];
        mTime[0] = hour;
        mTime[1] = minute;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert date(int month, int day, int year) {
        if (mDate == null)
            mDate = new int[3];
        mDate[0] = month;
        mDate[1] = day;
        mDate[2] = year;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert negativeAction(String label) {
        mNegativeButtonText = label;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert positiveAction(String label, boolean isDestructive) {
        mPositiveButtonText = label;
        mIsPositiveActionDestructive = isDestructive;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert customAction(String label) {
        mCustomButtonText = label;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert onAction(OnAlertActionListener l) {
        onAlertActionListener = l;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert cancelableOnTouchOutside(boolean c) {
        mIsCancelableOnTouchOutside = c;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert cancelable(boolean c) {
        mIsCancelable = c;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert setRtl(boolean rtl) {
        mRtl = rtl;
        return this;
    }

    @SuppressWarnings("unused")
    public Alert view(View view) {
        mCustomView = view;
        return this;
    }

    //<-----------------------------------------



    public void show(FragmentManager manager) {
        show(manager, FRAGMENT_DEFAULT_TAG);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public void onItemClick(int position) {
        mLastItemSelected = position;
        if (mMode == MODE_SELECT_LIST) {
            callBack(ACTION_LIST_ITEM, position);
        }
        else if (mMode == MODE_SINGLE_CHOICE_LIST) {
            if (mPositiveButtonText == null) {
                callBack(ACTION_LIST_ITEM, position);
            } else {
                if (listStack != null)
                    listStack.load();
            }
        }
        else if (mMode == MODE_MULTI_CHOICE_LIST) {
            mSelectedItems[position] = !mSelectedItems[position];
        }
    }

    @Override
    public void onClick(View v) {
        String tag = (String)v.getTag();
        switch (tag) {
            case NEGATIVE_ACTION_VIEW_TAG:
                callBack(ACTION_NEGATIVE, null);
                break;
            case POSITIVE_ACTION_VIEW_TAG:
                switch (mMode) {
                    case MODE_MESSAGE:
                        callBack(ACTION_POSITIVE, null);
                        break;
                    case MODE_INPUT:
                        callBack(ACTION_POSITIVE, mInputText == null ? null : mInputText.toString());
                        break;
                    case MODE_PROGRESS:

                        break;
                    case MODE_IMAGE:

                        break;
                    case MODE_SELECT_LIST:
                        callBack(ACTION_POSITIVE, mLastItemSelected);
                        break;
                    case MODE_SINGLE_CHOICE_LIST:
                        callBack(ACTION_POSITIVE, mLastItemSelected);
                        break;
                    case MODE_MULTI_CHOICE_LIST:
                        callBack(ACTION_POSITIVE, mSelectedItems);
                        break;
                    case MODE_DATE_PICKER:
                        callBack(ACTION_POSITIVE, mDate);
                        break;
                    case MODE_TIME_PICKER:
                        callBack(ACTION_POSITIVE, mTime);
                        break;
                    default:
                        callBack(ACTION_POSITIVE, null);
                }
                break;
            case CUSTOM_ACTION_VIEW_TAG:
                callBack(ACTION_CUSTOM, null);
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mInputText = s;
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (mTime == null)
            mTime = new int[2];
        mTime[0] = hourOfDay;
        mTime[1] = minute;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (mDate == null)
            mDate = new int[3];
        mDate[0] = monthOfYear;
        mDate[1] = dayOfMonth;
        mDate[2] = year;
    }

    private void callBack(int action, Object data) {
        if (onAlertActionListener != null) {
            if (onAlertActionListener.onAction(action, data)) {
                if (mMode == MODE_INPUT) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                }
                dismiss();
            }
        } else {
            dismiss();
        }
    }

    public interface OnAlertActionListener {
        boolean onAction(int action, Object data);
    }

}
