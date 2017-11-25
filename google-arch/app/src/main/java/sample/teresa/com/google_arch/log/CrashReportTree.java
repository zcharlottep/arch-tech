package sample.teresa.com.google_arch.log;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by zhup on 2017/10/17.
 * 记录crash日志
 */

public class CrashReportTree extends Timber.Tree{
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
//        Log.e(tag,"priority:"+priority+",tag:"+tag+",message:"+message+",t:"+t);
    }
}
