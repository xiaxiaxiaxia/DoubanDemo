package run.demo01.doubandemo.main.movie;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import run.demo01.doubandemo.data.MovieBean;

public class PagingDataSourceFactory extends DataSource.Factory<Integer, MovieBean> {

    @NonNull
    @Override
    public DataSource<Integer, MovieBean> create() {
        return new MoviePagingDataSource();
    }
}
