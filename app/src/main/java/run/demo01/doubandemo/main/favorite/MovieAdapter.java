package run.demo01.doubandemo.main.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import run.demo01.doubandemo.R;
import run.demo01.doubandemo.data.MovieBean;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie2, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieBean movieBean = getItem(position);
        Glide.with(mContext).load(movieBean.getPic_link()).into(holder.logoIv);
        holder.titleTv.setText(movieBean.getCname());
        holder.infoTv.setText(movieBean.getInfo());

    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView logoIv;
        TextView titleTv;
        TextView infoTv;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            logoIv = itemView.findViewById(R.id.logoIv);
            titleTv = itemView.findViewById(R.id.titleTv);
            infoTv = itemView.findViewById(R.id.infoTv);
        }
    }
}
