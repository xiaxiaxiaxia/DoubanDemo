package run.demo01.doubandemo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.LoginBean;
import run.demo01.doubandemo.http.DoubanService;
import run.demo01.doubandemo.http.HttpConfig;

public class RetrofitUtils {
    private static RetrofitUtils mainModel;
    private List<Cookie> cookiesSaved = new ArrayList<>();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HttpConfig.BASE_URL)
            .client(new OkHttpClient.Builder()//添加cookie
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookiesSaved.clear();
                            cookiesSaved.addAll(cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            return cookiesSaved;
                        }
                    })
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    private RetrofitUtils(){}

    public static RetrofitUtils getInstance(){
        if (mainModel == null)
        {
            synchronized (RetrofitUtils.class){
                if (mainModel == null)
                {
                    mainModel = new RetrofitUtils();
                }
            }
        }

        return mainModel;
    }

    /**
     * 登录
     * @param name
     * @param pwd
     * @param observer
     */
    public void doLogin(String name, String pwd, Observer observer)
    {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("pwd",pwd);
        retrofit.create(DoubanService.class)
                .doLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 注册
     * @param email
     * @param name
     * @param pwd
     * @param observer
     */
    public void doRegister(String email,String name, String pwd, Observer observer)
    {
        Map<String,String> map = new HashMap<>();
        map.put("email",email);
        map.put("name",name);
        map.put("pwd",pwd);
        retrofit.create(DoubanService.class)
                .doRegister(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getMovies(String pageNum,String pageSize, Observer observer)
    {
        Map<String,String> map = new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        retrofit.create(DoubanService.class)
                .getMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
