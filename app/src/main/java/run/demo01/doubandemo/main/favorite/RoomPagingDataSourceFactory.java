package run.demo01.doubandemo.main.favorite;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.main.movie.MoviePagingDataSource;

public class RoomPagingDataSourceFactory extends DataSource.Factory<Integer, MovieBean> {

    @NonNull
    @Override
    public DataSource<Integer, MovieBean> create() {
        return new MovieRoomPagingDataSource();
    }
}
