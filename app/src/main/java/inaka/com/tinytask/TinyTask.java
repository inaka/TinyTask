package inaka.com.tinytask;

public class TinyTask<T> {

    private static TinyTask mInstance = null;
    private GenericTask<T> mGenericTask;
    private Something<T> mSomething;
    private DoThis<T> mCallback;

    private TinyTask(Something<T> something) {
        this.mSomething = something;
    }

    private TinyTask(DoThis<T> callback) {
        this.mCallback = callback;
    }

    public static TinyTask perform(Something something) {
        if (mInstance == null) {
            mInstance = new TinyTask(something);
        } else {
            mInstance.mSomething = something;
        }
        return mInstance;
    }

    public TinyTask whenDone(DoThis<T> callback) {
        if (mInstance == null) {
            mInstance = new TinyTask<>(callback);
        } else {
            mInstance.mCallback = callback;
        }
        return mInstance;
    }

    public Something<T> getSomething() {
        return mSomething;
    }

    public DoThis<T> getCallback() {
        return mCallback;
    }

    public void go() {
        if (mGenericTask == null) {
            mGenericTask = new GenericTask<>(this);
            mGenericTask.execute();
        }
    }

    public void cancel() {
        if (mGenericTask != null) {
            mGenericTask.cancel(true);
        }
    }

    public boolean isCancelled() {
        return mGenericTask.isCancelled();
    }
}


