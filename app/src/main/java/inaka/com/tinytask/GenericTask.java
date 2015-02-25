package inaka.com.tinytask;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class GenericTask<T> extends AsyncTask<Void, Void, T> {

    private WeakReference<Context> context;
    private Something<T> something;
    private DoThis<T> callback;
    private Exception exception;

    public GenericTask(TinyTask<T> tinyTask) {
        context = tinyTask.getContext();
        something = tinyTask.getSomething();
        callback = tinyTask.getCallback();
    }

    @Override
    protected T doInBackground(Void... params) {
        try {
            return something.whichDoes();
        } catch(Exception e) {
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(T result) {

        if(!isCancelled()) {
            Context c = context.get();
            if(c != null) {

                if(exception != null) {
                    callback.ifNotOK(exception);
                } else {
                    callback.ifOK(result);
                }
            }
        } else {
            callback.ifNotOK(new RuntimeException("task was cancelled"));
        }

    }
}
