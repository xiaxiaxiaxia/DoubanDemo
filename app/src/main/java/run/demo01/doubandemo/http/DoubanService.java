package run.demo01.doubandemo.http;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.LoginBean;
import run.demo01.doubandemo.data.MovieBean;

public interface DoubanService {

    @FormUrlEncoded
    @POST("login")
    Observable<BaseData<LoginBean>> doLogin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("reg")
    Observable<BaseData<LoginBean>> doRegister(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getMovies")
    Observable<BaseData<List<MovieBean>>> getMovies(@FieldMap Map<String,String> map);
}
