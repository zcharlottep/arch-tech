package sample.teresa.com.google_arch;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import sample.teresa.com.google_arch.base.BaseLifecycleActivity;
import sample.teresa.com.google_arch.databinding.ActivitySecondBinding;
import sample.teresa.com.google_arch.googlearch.viewmodel.WeatherViewModel;
import timber.log.Timber;

/**
 * Created by zhup on 2017/10/18.
 */

public class SecondActivity extends BaseLifecycleActivity implements View.OnClickListener {

    private ActivitySecondBinding mActivitySecondBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeatherViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        mActivitySecondBinding.setViewModel(viewModel);
        Timber.e("viewModel:"+viewModel);
        mActivitySecondBinding.back.setOnClickListener(this);
    }

    @Override
    protected void initLayout() {
        mActivitySecondBinding = DataBindingUtil.setContentView(this,R.layout.activity_second);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back:
                App.safeExitApp();
                break;
        }
    }
}
