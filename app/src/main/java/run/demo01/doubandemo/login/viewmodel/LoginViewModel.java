package run.demo01.doubandemo.login.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.LoginBean;
import run.demo01.doubandemo.utils.RetrofitUtils;

public class LoginViewModel extends ViewModel {
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    MutableLiveData<BaseData<LoginBean>> loginResponse = new MutableLiveData<>();

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void login(){
        RetrofitUtils.getInstance().doLogin(name.getValue(), password.getValue(), new Observer<BaseData<LoginBean>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull BaseData<LoginBean> response) {
                loginResponse.postValue(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public MutableLiveData<BaseData<LoginBean>> getLoginResponse() {
        return loginResponse;
    }
}
