package run.demo01.doubandemo.main.movie;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding movieBinding;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        initData();
        return movieBinding.getRoot();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        movieBinding.recycleView.setLayoutManager(linearLayoutManager);

        movieBinding.recycleView.setAdapter(movieAdapter = new MovieAdapter(getActivity()));

        movieViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

        movieViewModel.getPagedListMutableLiveData().observe(getActivity(), new Observer<PagedList<MovieBean>>() {
            @Override
            public void onChanged(PagedList<MovieBean> movieBeans) {
                movieAdapter.submitList(movieBeans);
            }
        });
    }
}