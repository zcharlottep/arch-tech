package sample.teresa.com.google_arch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import sample.teresa.com.google_arch.base.BaseLifecycleActivity;
import sample.teresa.com.google_arch.databinding.ActivityMainBinding;
import sample.teresa.com.google_arch.googlearch.livedata.WeatherLiveData;
import sample.teresa.com.google_arch.googlearch.model.Weather;
import sample.teresa.com.google_arch.googlearch.viewmodel.WeatherViewModel;
import timber.log.Timber;


/**
 * 采用google arch框架来加载天气信息,并存取到本地
 */
public class MainActivity extends BaseLifecycleActivity implements Observer, View.OnClickListener {

    @BindView(R.id.city)
    TextView mCity;

    private ActivityMainBinding mActivityMainBinding;

    private WeatherViewModel mWeatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无参数实例化
        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        mActivityMainBinding.setViewModel(mWeatherViewModel);
//        User user = new User();
//        user.setName("呵呵");
//        mActivityMainBinding.setUser(user);
        mActivityMainBinding.back.setOnClickListener(this);
        mActivityMainBinding.getWeatherInfo.setOnClickListener(this);
    }

    @Override
    protected void initLayout() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onChanged(@Nullable Object o) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back:
                finish();
                break;
            case R.id.get_weather_info:
                String path = "101010100.html";
                WeatherLiveData<Weather> weatherInfo = (WeatherLiveData<Weather>) mWeatherViewModel.getWeatherInfo(path);
                weatherInfo.observe(this, new Observer<Weather>() {
                    @Override
                    public void onChanged(@Nullable Weather weather) {
                        Timber.e("onChanged:"+weather.getWeatherinfo().getCity());
//                        WeatherInfo weatherinfo = weather.getWeatherinfo();
//                        mActivityMainBinding.setWeatherInfo(weatherinfo);
//                        weatherinfo.setCity(weather.getWeatherinfo().getCity());
                        mCity.setText(weather.getWeatherinfo().getCity());
                    }
                });
                break;
        }
    }
}
