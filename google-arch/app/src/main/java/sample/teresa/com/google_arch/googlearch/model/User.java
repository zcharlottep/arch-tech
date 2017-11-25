package sample.teresa.com.google_arch.googlearch.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import sample.teresa.com.google_arch.BR;

/**
 * Created by zhup on 2017/10/18.
 */

@Deprecated
public class User extends BaseObservable{

    private String name;
    private String sex;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }
}
