package com.kaiserdavar.androidui;

import android.content.Context;
import android.webkit.WebView;

import java.util.Map;

public class Web extends BaseVue<Web, WebView> {

    public static Web create(Context context) {
        return new Web(context);
    }

    public Web(WebView view) {
        super(view);
    }

    public Web(Context context) {
        super(context);
    }

    @Override
    protected WebView onGetMainView(Context context) {
        return new WebView(context);
    }

    public Web url(String url) {
        view.loadUrl(url);
        return this;
    }
    public Web url(String url, Map<String, String> headers) {
        view.loadUrl(url, headers);
        return this;
    }
    public Web data(String data, String mimeType, String encoding) {
        view.loadData(data, mimeType, encoding);
        return this;
    }

}
