package com.app.frame.binding.viewadapter.webview;

import androidx.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;

public class ViewAdapter {
    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
}
