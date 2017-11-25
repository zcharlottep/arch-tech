package sample.teresa.com.google_arch.googlearch.livedata;

import android.arch.lifecycle.MutableLiveData;

import java.util.logging.Logger;

import timber.log.Timber;

/**
 * Created by zhup on 2017/10/17.
 */

public class WeatherLiveData<T> extends MutableLiveData {

    /**
     * 在LiveData有活跃的观察者的时候调用
     */
    @Override
    protected void onActive() {
        super.onActive();
        Timber.e("onActive");
    }

    /**
     * 没有活跃的观察者的时候调用
     */
    @Override
    protected void onInactive() {
        super.onInactive();
        Timber.e("onInactive");
    }

    /**
     *  调用此方法通知外部观察者数据改变
     * @param value
     */
    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }
}
