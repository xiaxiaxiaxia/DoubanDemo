package run.demo01.doubandemo.main.favorite;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import run.demo01.doubandemo.MyApplication;
import run.demo01.doubandemo.base.DoubanDataBase;
import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.http.HttpConfig;

public class MovieRoomPagingDataSource extends PageKeyedDataSource<Integer, MovieBean> {
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, MovieBean> loadCallback) {
        int position = (loadParams.key-1)*Integer.parseInt(HttpConfig.PAGE_SIZE);
        MovieDao movieDao = DoubanDataBase.getInstance(MyApplication.getInstance()).getMovieDao();
        List<MovieBean> movieBeans = movieDao.query(HttpConfig.PAGE_SIZE,position+"");
        loadCallback.onResult(movieBeans,loadParams.key+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, MovieBean> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, MovieBean> loadInitialCallback) {

        MovieDao movieDao = DoubanDataBase.getInstance(MyApplication.getInstance()).getMovieDao();
        List<MovieBean> movieBeans = movieDao.query(HttpConfig.PAGE_SIZE,"0");
        loadInitialCallback.onResult(movieBeans,null,2);
    }
}
