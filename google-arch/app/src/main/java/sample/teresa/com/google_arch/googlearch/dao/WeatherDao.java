package sample.teresa.com.google_arch.googlearch.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import sample.teresa.com.google_arch.googlearch.model.Weather;

/**
 * Created by zhup on 2017/10/18.
 */

@Dao
public interface WeatherDao {

    @Query("select * from Weather")
    LiveData<Weather> getAll();

    @Query("select * from Weather where id =:id")
    Weather getItemById(String id);

    @Insert
    void insertAll(Weather... weather);

    @Delete
    void delete(Weather weather);

}
