package run.demo01.doubandemo.main.movie;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import run.demo01.doubandemo.base.BaseData;
import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.http.HttpConfig;
import run.demo01.doubandemo.utils.RetrofitUtils;

public class MoviePagingDataSource extends PageKeyedDataSource<Integer, MovieBean> {
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, MovieBean> loadCallback) {
        int pageNum = loadParams.key;
        RetrofitUtils.getInstance().getMovies(pageNum+"", HttpConfig.PAGE_SIZE, new Observer<BaseData<List<MovieBean>>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(BaseData<List<MovieBean>> response) {
                if (response.getCode() == 1)
                    loadCallback.onResult(response.getData(),pageNum+1);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, MovieBean> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, MovieBean> loadInitialCallback) {
        RetrofitUtils.getInstance().getMovies("1", HttpConfig.PAGE_SIZE, new Observer<BaseData<List<MovieBean>>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(BaseData<List<MovieBean>> response) {
                if (response.getCode() == 1)
                    loadInitialCallback.onResult(response.getData(),null,2);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
