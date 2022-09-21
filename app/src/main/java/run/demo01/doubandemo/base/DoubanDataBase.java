package run.demo01.doubandemo.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import run.demo01.doubandemo.data.MovieBean;
import run.demo01.doubandemo.main.favorite.MovieDao;

//3
@Database(entities = MovieBean.class,version = 1,exportSchema = false)
public abstract class DoubanDataBase extends RoomDatabase {
    public DoubanDataBase(){

    }

    private static  DoubanDataBase INSTANCE;

    public static DoubanDataBase getInstance(Context context){
        if (INSTANCE == null)
        {
            synchronized (DoubanDataBase.class){
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context,DoubanDataBase.class,"movie.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao getMovieDao();
}
