package com.zhouchatian.mygithub.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouchatian.mygithub.R;

/**
 * Created by  Mr.Robot on 2017/5/11.
 * GitHub:https://github.com/TheSadFrog
 */

public class DynamicFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        return view;
    }
}
