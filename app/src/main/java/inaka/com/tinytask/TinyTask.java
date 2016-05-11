package inaka.com.tinytask;

public class TinyTask<T> {

    private static TinyTask instance = null;
    private GenericTask<T> genericTask;
    private Something<T> something;
    private DoThis<T> callback;

    private TinyTask(Something<T> something) {
        this.something = something;
    }

    private TinyTask(DoThis<T> callback) {
        this.callback = callback;
    }

    public static TinyTask perform(Something something) {
        if(instance == null) {
            instance = new TinyTask(something);
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

    public void go() {
        if(genericTask == null) {
            genericTask = new GenericTask<>(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                genericTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                genericTask.execute();
            }
        }
    }

    public void cancel() {
        if(genericTask != null) {
            genericTask.cancel(true);
        }
    }

    public boolean isCancelled() {
        return genericTask.isCancelled();
    }
}


