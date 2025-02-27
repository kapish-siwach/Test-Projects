package com.first.navbartest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;

public class SettingsFragment extends Fragment{

    public SettingsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] names={"Ram","Shyam","Hari","Sita","Gita"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(requireContext(),R.layout.spinner_dropdown,names);

        ListView listView=view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

}