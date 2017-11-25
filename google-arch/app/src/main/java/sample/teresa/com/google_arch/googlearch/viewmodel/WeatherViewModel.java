package sample.teresa.com.google_arch.googlearch.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import sample.teresa.com.google_arch.googlearch.model.Weather;
import sample.teresa.com.google_arch.googlearch.repository.WeatherRepository;
import timber.log.Timber;


/**
 * Created by zhup on 2017/10/17.
 * view model类--为组件提供数据，并负责和数据处理的业务逻辑部分进行通信。
 * 脱离组件的生命周期，所以不能获得组件的引用
 */

public class WeatherViewModel extends AndroidViewModel{

//    private MutableLiveData mLiveData = new WeatherLiveData<>();

    private WeatherRepository mWeatherRepository; // 数据处理的业务逻辑类

    public WeatherViewModel(Application application) {
        super(application);
        mWeatherRepository = new WeatherRepository(application);
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    void _onCreate(){
//        mMyRepository = new MyRepository();
//        Timber.e("_onCreate!!!");
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    void _onDestroy(){
//        Timber.e("_onDestroy!!!");
//    }


    /**
     * 释放资源
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.e("onCleared!!!");
    }

//    public MutableLiveData getLiveData() {
//        return mLiveData;
//    }

    public LiveData<Weather> getWeatherInfo(Object... args){
        return mWeatherRepository.getWeatherInfo(args);
    }


}
