package com.example.cricket;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cricket.R;


public class Frag1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag1,container,false);
    }
}
