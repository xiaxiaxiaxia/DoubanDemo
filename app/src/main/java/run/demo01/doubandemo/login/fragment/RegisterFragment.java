package run.demo01.doubandemo.login.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.databinding.FragmentRegisterBinding;
import run.demo01.doubandemo.login.viewmodel.RegViewModel;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding registerBinding;
    private RegViewModel regViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        registerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register,container,false);
        regViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(RegViewModel.class);
        registerBinding.setRegViewModel(regViewModel);
        initData();
        return registerBinding.getRoot();
    }

    private void initData() {
        regViewModel.getRegisterResponse().observe(getActivity(), new Observer<BaseData>() {
            @Override
            public void onChanged(BaseData baseData) {
                if (baseData.getCode() == 1)
                {
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.loginFragment);
                }
            }
        });

        registerBinding.backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.welcomeFragment);
            }
        });
    }
}