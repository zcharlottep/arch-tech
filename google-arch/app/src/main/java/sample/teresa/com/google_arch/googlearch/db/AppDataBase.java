package sample.teresa.com.google_arch.googlearch.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sample.teresa.com.google_arch.googlearch.dao.WeatherDao;
import sample.teresa.com.google_arch.googlearch.model.Weather;

/**
 * Created by zhup on 2017/10/18.
 */

@Database(entities = {Weather.class}, version = 2,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase mInstance;

    public static AppDataBase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AppDataBase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context, AppDataBase.class, "weather_db").build();
                }
            }
        }
        return mInstance;
    }

    public abstract WeatherDao weatherDao();
}
