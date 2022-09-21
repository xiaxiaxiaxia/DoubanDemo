package run.demo01.doubandemo.login.fragment;

import android.content.Intent;
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

import run.demo01.doubandemo.main.MainActivity;
import run.demo01.doubandemo.R;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.LoginBean;
import run.demo01.doubandemo.databinding.FragmentLoginBinding;
import run.demo01.doubandemo.login.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding loginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider(this,(ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        loginBinding.setLoginViewModel(loginViewModel);
        initData();
        return loginBinding.getRoot();
    }

    private void initData() {
        //设置登录信息的监听
        loginViewModel.getLoginResponse().observe(getActivity(), new Observer<BaseData<LoginBean>>() {
            @Override
            public void onChanged(BaseData<LoginBean> loginBeanBaseData) {
                if (loginBeanBaseData.getCode() == 1)
                {
                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(getActivity(), MainActivity.class);
                    startActivity(it);
                }
            }
        });

        loginBinding.backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.welcomeFragment);
            }
        });
    }
}