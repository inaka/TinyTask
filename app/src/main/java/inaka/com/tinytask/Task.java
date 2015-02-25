package inaka.com.tinytask;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public abstract class Task<T> extends AsyncTask<Void, Void, T> {

    private WeakReference<DoThis> mCallback;
    private long mStartTime;
    private long mMaxMillis;

    public Task(DoThis callback, long maxMillis) {
        mCallback = new WeakReference<>(callback);
        mStartTime = System.currentTimeMillis();
        mMaxMillis = maxMillis;
        execute();
    }

    public Task(DoThis callback) {
        mCallback = new WeakReference<>(callback);
        mMaxMillis = -1;
        execute();
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);

        long now = System.currentTimeMillis();
        if(now - mStartTime < mMaxMillis) {
            DoThis callback = mCallback.get();
            if(callback != null) {
                callback.ifOK(t);
            }
        }

    }
}


