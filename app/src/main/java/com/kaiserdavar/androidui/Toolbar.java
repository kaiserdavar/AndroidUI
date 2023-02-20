package com.kaiserdavar.androidui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;

import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.style.ToolbarStyle;
import com.kaiserdavar.androidui.style.VueStyle;

public class Toolbar extends BaseVue<Toolbar, androidx.appcompat.widget.Toolbar> {

    public static ToolbarStyle defaultStyle;

    public static Toolbar create(Context context) {
        return new Toolbar(context);
    }

    public Toolbar(androidx.appcompat.widget.Toolbar view) {
        super(view);
    }

    public Toolbar(Context context) {
        super(context);
    }

    @Override
    protected androidx.appcompat.widget.Toolbar onGetMainView(Context context) {
        return new androidx.appcompat.widget.Toolbar(context);
    }

    @Override
    protected void onInit() {
        if (defaultStyle != null) {
            t.style(defaultStyle);
        }
    }

    public Toolbar style(VueStyle vueStyle) {
        super.style(vueStyle);
        if (vueStyle instanceof ToolbarStyle) {
            ToolbarStyle style = (ToolbarStyle) vueStyle;
            if (style.titleStyle != null)
                view.setTitleTextAppearance(view.getContext(), style.titleStyle);
            if (style.subtitleStyle != null)
                view.setSubtitleTextAppearance(view.getContext(), style.subtitleStyle);
            if (style.logo != null)
                logo(style.logo);
            if (style.navigationIcon != null)
                navigationIcon(style.navigationIcon);
            if (style.overflowIcon != null)
                overflowIcon(style.overflowIcon);
            if (style.titleMarginTop != null)
                titleMarginTop(style.titleMarginTop);
            if (style.logo != null)
                titleMarginBottom(style.titleMarginBottom);
            if (style.logo != null)
                titleMarginStart(style.titleMarginStart);
            if (style.logo != null)
                titleMarginEnd(style.titleMarginEnd);
        }
        return this;
    }

    public Toolbar title(LiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::title);
        return this;
    }
    public Toolbar title(CharSequence title) {
        view.setTitle(title);
        return this;
    }
    public Toolbar title(@StringRes int titleRes) {
        view.setTitle(titleRes);
        return this;
    }
    public Toolbar subtitle(LiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::subtitle);
        return this;
    }
    public Toolbar subtitle(CharSequence subtitle) {
        view.setSubtitle(subtitle);
        return this;
    }
    public Toolbar subtitle(@StringRes int subtitleRes) {
        view.setSubtitle(subtitleRes);
        return this;
    }
    public Toolbar navigationIcon(@DrawableRes int iconRes) {
        view.setNavigationIcon(iconRes);
        return this;
    }
    public Toolbar navigationIcon(Drawable icon) {
        view.setNavigationIcon(icon);
        return this;
    }
    public Toolbar logo(@DrawableRes int logoRes) {
        view.setLogo(logoRes);
        return this;
    }
    public Toolbar logo(Drawable logo) {
        view.setLogo(logo);
        return this;
    }
    public Toolbar overflowIcon(@DrawableRes int iconRes) {
        view.setOverflowIcon(ResourcesCompat.getDrawable(view.getResources(),
                iconRes, view.getContext().getTheme()));
        return this;
    }
    public Toolbar overflowIcon(Drawable icon) {
        view.setOverflowIcon(icon);
        return this;
    }
    public Toolbar titleMarginTop(int dp) {
        view.setTitleMarginTop(px(dp));
        return this;
    }
    public Toolbar titleMarginBottom(int dp) {
        view.setTitleMarginBottom(px(dp));
        return this;
    }
    public Toolbar titleMarginStart(int dp) {
        view.setTitleMarginStart(px(dp));
        return this;
    }
    public Toolbar titleMarginEnd(int dp) {
        view.setTitleMarginEnd(px(dp));
        return this;
    }

    public Toolbar menuRes(@MenuRes int resId) {
        view.inflateMenu(resId);
        return this;
    }
    public Toolbar menu(OnCreateMenuListener listener) {
        listener.onCreate(view.getMenu());
        return this;
    }

    public Toolbar action(@StringRes int titleRes, @DrawableRes int iconRes,
                           MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        MenuItem menu = view.getMenu().add(titleRes);
        menu.setIcon(iconRes);
        menu.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.setOnMenuItemClickListener(onMenuItemClickListener);
        return this;
    }

    @Deprecated
    public Toolbar showBack() {
        return backOnNavigation();
    }

    public Toolbar backOnNavigation() {
        view.setNavigationOnClickListener(v -> {
            if (view.getContext() instanceof Activity) {
                ((Activity) view.getContext()).onBackPressed();
            }
        });
        return this;
    }

    public Toolbar onNavigation(OnClickListener listener) {
        view.setNavigationOnClickListener(v -> listener.onClick());
        return this;
    }

}
