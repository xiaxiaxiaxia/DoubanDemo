package run.demo01.doubandemo.login.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.databinding.FragmentWelcomeBinding;


public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWelcomeBinding welcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        welcomeBinding.setAction(new WelcomeAction(this));
        return welcomeBinding.getRoot();
    }

    public class WelcomeAction{
        Fragment mFragment;

        public WelcomeAction(Fragment mFragment) {
            this.mFragment = mFragment;
        }

        public void gotoReg(){
            NavHostFragment.findNavController(mFragment).navigate(R.id.registerFragment);
        }

        public void gotoLogin(){
            NavHostFragment.findNavController(mFragment).navigate(R.id.loginFragment);
        }
    }
}