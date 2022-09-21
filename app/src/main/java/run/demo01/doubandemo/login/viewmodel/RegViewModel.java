package run.demo01.doubandemo.login.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.RegisterBean;
import run.demo01.doubandemo.utils.RetrofitUtils;

public class RegViewModel extends ViewModel {
    MutableLiveData<String> email = new MutableLiveData<>();
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    MutableLiveData<BaseData> registerResponse = new MutableLiveData<>();

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<BaseData> getRegisterResponse() {
        return registerResponse;
    }

    /**
     * 注册按钮点击事件
     */
    public void register(){
        RetrofitUtils.getInstance().doRegister(email.getValue(), name.getValue(), password.getValue(), new Observer<BaseData>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull BaseData registerBeanBaseData) {
                registerResponse.postValue(registerBeanBaseData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
