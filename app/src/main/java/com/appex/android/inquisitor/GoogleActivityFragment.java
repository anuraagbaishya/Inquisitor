package com.appex.android.inquisitor;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GoogleActivityFragment extends Fragment {
    private String mUrl="https://www.google.com";
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_google, container, false);
        final ProgressBar progressBar=(ProgressBar)rootView.findViewById(R.id.progressbar);
        progressBar.setMax(100);
        mWebView = (WebView)rootView.findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress){
                if (progress==100){
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(progress);
                }
            }
        });
        mWebView.loadUrl(mUrl);
        return rootView;
    }
}
