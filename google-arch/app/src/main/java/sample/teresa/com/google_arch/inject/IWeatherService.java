package sample.teresa.com.google_arch.inject;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sample.teresa.com.google_arch.googlearch.model.Weather;

/**
 * Created by zhup on 2017/10/17.
 */

public interface IWeatherService {

    @GET("data/sk/{path}")
    Observable<Weather> getWeather(@Path("path") String path);

    @GET("data/sk/{path}")
    Call<Weather> getWeatherCall(@Path("path") String path);
}
