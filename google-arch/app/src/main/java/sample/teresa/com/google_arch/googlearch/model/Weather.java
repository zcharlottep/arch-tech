package sample.teresa.com.google_arch.googlearch.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * Created by zhup on 2017/10/17.
 * 天气类
 * <p>
 * http://www.weather.com.cn/data/sk/101010100.html
 */

//@Entity(tableName = "weather", foreignKeys = @ForeignKey(entity = WeatherInfo.class,
//parentColumns = "id",childColumns = "uid",onDelete = CASCADE,onUpdate = CASCADE))
@Entity(tableName = "weather")
public class Weather{

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    private WeatherInfo weatherinfo;

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;

    }

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }
}
