package com.example.assginment_mob201.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assginment_mob201.Activity.MainActivity;
import com.example.assginment_mob201.R;


public class Fragment_WebView_News extends Fragment {
    private WebView webView;
    private String link;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //khi cần bật toolbar back thì true là bật còn false là tắt
        ((MainActivity) requireActivity()).toolbarBack(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view_news, container, false);

        this.initViewById(view);

        this.getArgument();

        this.initWebView();

        return view;
    }

    private void initWebView() {
        this.webView.loadUrl(this.link);
        this.webView.setWebViewClient(new WebViewClient());
    }

    private void getArgument() {
        this.link = getArguments().getString("link");
    }

    private void initViewById(View view) {
        this.webView = view.findViewById(R.id.wv_news);
    }
}