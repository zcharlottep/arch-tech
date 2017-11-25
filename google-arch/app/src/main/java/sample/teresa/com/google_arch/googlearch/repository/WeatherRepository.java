package sample.teresa.com.google_arch.googlearch.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sample.teresa.com.google_arch.App;
import sample.teresa.com.google_arch.constant.Constant;
import sample.teresa.com.google_arch.googlearch.db.AppDataBase;
import sample.teresa.com.google_arch.googlearch.livedata.WeatherLiveData;
import sample.teresa.com.google_arch.googlearch.model.Weather;
import sample.teresa.com.google_arch.googlearch.model.WeatherInfo;
import sample.teresa.com.google_arch.inject.IWeatherService;
import timber.log.Timber;

/**
 * Created by zhup on 2017/10/17.
 */

public class WeatherRepository {

    private IWeatherService mWeatherService;

    private Application mApplication;

    public WeatherRepository(Application application) {
        mApplication = application;
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Constant.baseWeatherUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mWeatherService = build.create(IWeatherService.class);
    }

    public LiveData<Weather> getWeatherInfo(Object... args) {
//        Call<Weather> weatherCall = mWeatherService.getWeatherCall((String) args[0], (String) args[1]);
//        weatherCall.enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                Timber.e("response:"+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                Timber.e("onFailure");
//            }
//        });
        final MutableLiveData liveData = new WeatherLiveData<Weather>();
//        Weather weather = new Weather();
//        WeatherInfo weatherInfo = new WeatherInfo();
//        weatherInfo.setCity("哈哈");
//        weather.setWeatherinfo(weatherInfo);
//        liveData.setValue(weather);
//        return liveData;
        Observable<Weather> weatherObservable = mWeatherService.getWeather((String) args[0]);
        weatherObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull final Weather weather) throws Exception {
                        Schedulers.io().createWorker().schedule(new Runnable() {
                            @Override
                            public void run() {
                                //存储数据库
                                AppDataBase.getInstance(mApplication).weatherDao().insertAll(weather);
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                        Timber.e("weather:" + weather);
                        liveData.setValue(weather);
                    }
                });
        return liveData;
    }
}
