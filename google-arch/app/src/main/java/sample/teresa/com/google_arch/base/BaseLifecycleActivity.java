package sample.teresa.com.google_arch.base;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import sample.teresa.com.google_arch.App;

/**
 * Created by zhup on 2017/10/16.
 */

public abstract class BaseLifecycleActivity extends LifecycleActivity implements LifecycleRegistryOwner {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        ButterKnife.bind(this);
        App.addActivity(this);
    }

    /**
     * 设置root res layout
     */
    protected abstract void initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
