package run.demo01.doubandemo.main.favorite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import run.demo01.doubandemo.data.MovieBean;

//2
@Dao
public interface MovieDao {
    @Query("select * from movie")
    List<MovieBean> queryAll();

    @Insert
    void  addMovie(MovieBean movieBean);

    @Query("select * from movie where id=:id")
    MovieBean queryById(int id);

    @Query("select * from movie limit :pageSize offset :position")
    List<MovieBean> query(String pageSize,String position);

    @Delete
    void delete(MovieBean... movieBeans);
}
