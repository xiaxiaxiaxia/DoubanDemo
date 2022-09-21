package run.demo01.doubandemo.main.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.http.HttpConfig;
import run.demo01.doubandemo.main.movie.PagingDataSourceFactory;

public class MovieRoomViewModel extends ViewModel {
    private final RoomPagingDataSourceFactory pagingDataSourceFactory;
    private LiveData<PagedList<MovieBean>> pagedListMutableLiveData;
    public MovieRoomViewModel(){
        pagingDataSourceFactory = new RoomPagingDataSourceFactory();
        updateData();
    }

    public LiveData<PagedList<MovieBean>> getPagedListMutableLiveData() {
        return pagedListMutableLiveData;
    }

    public void updateData() {
        this.pagedListMutableLiveData = new LivePagedListBuilder<Integer,MovieBean>(pagingDataSourceFactory, Integer.parseInt(HttpConfig.PAGE_SIZE)).build() ;
    }
}
