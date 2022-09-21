package run.demo01.doubandemo.main.favorite;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.databinding.FragmentFavouriteBinding;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding favouriteBinding;
    private MovieRoomViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favouriteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        initData();
        return favouriteBinding.getRoot();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        favouriteBinding.recycleView.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter(getActivity());
        favouriteBinding.recycleView.setAdapter(adapter);

         viewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MovieRoomViewModel.class);
        viewModel.getPagedListMutableLiveData().observe(getActivity(), new Observer<PagedList<MovieBean>>() {
            @Override
            public void onChanged(PagedList<MovieBean> movieBeans) {
                adapter.submitList(movieBeans);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: ");
        if (viewModel != null)
        {
            //更新列表
            viewModel.updateData();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "onPause: " );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "onCreate: ");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("TAG", "onViewCreated: ");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("TAG", "onAttach: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "onDestroyView: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", "onDetach: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("TAG", "onHiddenChanged: ");
    }
}