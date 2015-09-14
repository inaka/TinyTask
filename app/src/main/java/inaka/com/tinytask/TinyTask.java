package inaka.com.tinytask;

public class TinyTask<T> {

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
        return new TinyTask(something);
    }

    public TinyTask whenDone(DoThis<T> callback) {
        this.callback = callback;
        return this;
    }

    public Something<T> getSomething() {
        return something;
    }

    public DoThis<T> getCallback() {
        return callback;
    }

    public void go() {
        genericTask = new GenericTask<>(this);
        genericTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void cancel() {
        if(genericTask != null) {
            genericTask.cancel(true);
        }
        genericTask = null;
        something = null;
        callback = null;
    }

    public boolean isCancelled() {
        if(genericTask != null) {
            genericTask.isCancelled();
        }
        return true;
    }
}


