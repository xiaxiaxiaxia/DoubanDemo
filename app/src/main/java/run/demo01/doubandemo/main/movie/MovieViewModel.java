package run.demo01.doubandemo.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.http.HttpConfig;

public class MovieViewModel extends ViewModel {
    private LiveData<PagedList<MovieBean>> pagedListMutableLiveData;
    public MovieViewModel(){
        PagingDataSourceFactory pagingDataSourceFactory = new PagingDataSourceFactory();
        this.pagedListMutableLiveData = new LivePagedListBuilder<Integer,MovieBean>(pagingDataSourceFactory, Integer.parseInt(HttpConfig.PAGE_SIZE)).build() ;
    }

    public LiveData<PagedList<MovieBean>> getPagedListMutableLiveData() {
        return pagedListMutableLiveData;
    }
}
