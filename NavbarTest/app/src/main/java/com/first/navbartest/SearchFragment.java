package com.first.navbartest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class SearchFragment extends Fragment {

    WebView search;
    public SearchFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search=view.findViewById(R.id.webView);
        search.loadUrl("https://www.google.com/");
        search.getSettings().setJavaScriptEnabled(true);
        search.getSettings().setDomStorageEnabled(true);
    }
}