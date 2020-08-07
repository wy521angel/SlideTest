package com.example.wy521angel.slidetest.hover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wy521angel.slidetest.R;


public class BlankFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int color;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color = getArguments().getInt(ARG_PARAM1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        inflate.findViewById(R.id.vContainer).setBackgroundColor(color);

        return inflate;
    }

    public static BlankFragment newInstance(int color) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM1, color);
        fragment.setArguments(bundle);
        return fragment;
    }
}
