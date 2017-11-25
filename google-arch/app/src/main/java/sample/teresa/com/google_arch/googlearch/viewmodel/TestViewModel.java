package sample.teresa.com.google_arch.googlearch.viewmodel;

import android.arch.lifecycle.ViewModel;

import timber.log.Timber;

/**
 * Created by zhup on 2017/10/18.
 */

public class TestViewModel extends ViewModel{

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.e("onCleared:释放资源！！！");
    }
}
