package com.appex.android.inquisitor.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.appex.android.inquisitor.R;

public class GoogleActivityFragment extends Fragment {
    private ProgressDialog pDialog;
    private String mUrl="https://www.google.com";
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(true);
        pDialog.setMessage("Loading...");
        pDialog.setTitle("Please Wait");
        pDialog.setIndeterminate(true);
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_google, container, false);
        final ProgressBar progressBar=(ProgressBar)rootView.findViewById(R.id.progressbar);
        progressBar.setMax(100);
        mWebView = (WebView)rootView.findViewById(R.id.webView);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.loadUrl(mUrl);
        return rootView;
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pDialog.show();
            view.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pDialog.dismiss();
            view.setVisibility(View.VISIBLE);
        }
    }
}
