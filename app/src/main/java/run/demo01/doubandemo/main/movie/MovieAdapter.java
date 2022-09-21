package run.demo01.doubandemo.main.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.base.DoubanDataBase;
import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.main.favorite.MovieDao;

public class MovieAdapter extends PagedListAdapter<MovieBean,MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private static final DiffUtil.ItemCallback<MovieBean> diffCallBack = new DiffUtil.ItemCallback<MovieBean>() {

        @Override
        public boolean areItemsTheSame(@NonNull MovieBean oldItem, @NonNull MovieBean newItem) {
            //判断两个item是否相同
            return Objects.equals(oldItem,newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieBean oldItem, @NonNull MovieBean newItem) {
            //判断内容是否相同
            return oldItem.getId() == newItem.getId();
        }
    };

    protected MovieAdapter(Context context) {
        super(diffCallBack);
        mContext = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieBean movieBean = getItem(position);
        Glide.with(mContext).load(movieBean.getPic_link()).into(holder.logoIv);
        holder.titleTv.setText(movieBean.getCname());
        holder.infoTv.setText(movieBean.getInfo());
        holder.loveIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDao movieDao = DoubanDataBase.getInstance(mContext).getMovieDao();
                MovieBean tempBean = movieDao.queryById(movieBean.getId());
                //判断是否已经添加
                if (tempBean != null)
                {
                    //已经添加了
                    Toast.makeText(mContext, "该电影已经添加了", Toast.LENGTH_SHORT).show();
                }else{
                    movieDao.addMovie(movieBean);
                    Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView logoIv;
        ImageView loveIv;
        TextView titleTv;
        TextView infoTv;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            logoIv = itemView.findViewById(R.id.logoIv);
            loveIv = itemView.findViewById(R.id.loveIv);
            titleTv = itemView.findViewById(R.id.titleTv);
            infoTv = itemView.findViewById(R.id.infoTv);
        }
    }
}
