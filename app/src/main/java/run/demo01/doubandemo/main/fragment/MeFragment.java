package run.demo01.doubandemo.main.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    private FragmentMeBinding meBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        meBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_me, container, false);
        return meBinding.getRoot();
    }
}