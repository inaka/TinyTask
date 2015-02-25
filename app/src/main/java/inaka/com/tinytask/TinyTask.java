package inaka.com.tinytask;

import android.content.Context;

import java.lang.ref.WeakReference;

public class TinyTask<T> {

    static TinyTask instance = null;
    private GenericTask<T> genericTask;
    private WeakReference<Context> context;
    private Something<T> something;
    private DoThis<T> callback;

    private TinyTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    private TinyTask(Something<T> something) {
        this.something = something;
    }

    private TinyTask(DoThis<T> callback) {
        this.callback = callback;
    }

    public static TinyTask with(Context context) {
        if(instance == null) {
            instance = new TinyTask(context);
        } else {
            instance.context = new WeakReference<>(context);
        }
        return instance;
    }

    public TinyTask perform(Something<T> something) {
        if(instance == null) {
            instance = new TinyTask<>(something);
        } else {
            instance.something = something;
        }
        return instance;
    }

    public TinyTask whenDone(DoThis<T> callback) {
        if(instance == null) {
            instance = new TinyTask<>(callback);
        } else {
            instance.callback = callback;
        }
        return instance;
    }

    public Something<T> getSomething() {
        return something;
    }

    public DoThis<T> getCallback() {
        return callback;
    }

    public WeakReference<Context> getContext() {
        return context;
    }

    public void go() {
        if(genericTask == null) {
            genericTask = new GenericTask<>(this);
            genericTask.execute();
        }
    }

    public void cancel() {
        if(genericTask != null) {
            something.ifCancelled();
            genericTask.cancel(true);
        }
    }

    public boolean isCancelled() {
        return genericTask.isCancelled();
    }
}


