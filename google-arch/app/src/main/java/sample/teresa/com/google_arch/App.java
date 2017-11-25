package sample.teresa.com.google_arch;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sample.teresa.com.google_arch.log.CrashReportTree;
import timber.log.Timber;

/**
 * Created by zhup on 2017/10/16.
 */

public class App extends Application{

    /**
     * 上下文
     */
    private Context mMainContext;

    private static Map<String,WeakReference<Activity>> mWeakReferenceMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mMainContext = getApplicationContext();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else{
            //crash log tree
            Timber.plant(new CrashReportTree());
        }
    }

    /**
     * 获取上下文
     */
    public Context getContext(){
        return mMainContext;
    }

    public static void addActivity(Activity activity){
        String simpleName = activity.getClass().getSimpleName();
        if(!mWeakReferenceMap.containsKey(simpleName)){
            mWeakReferenceMap.put(simpleName,new WeakReference<>(activity));
        }
    }

    /**
     * 退出程序
     */
    private static void exitApp(){
        System.exit(0);
    }

    public static void safeExitApp(){
        try{
            if(mWeakReferenceMap!=null){
                Set<Map.Entry<String, WeakReference<Activity>>> entries = mWeakReferenceMap.entrySet();
                Iterator<Map.Entry<String, WeakReference<Activity>>> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, WeakReference<Activity>> next = iterator.next();
                    WeakReference<Activity> activityWeakReference = next.getValue();
                    Activity activity = activityWeakReference.get();
                    activity.finish();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            exitApp();
        }
    }
}
