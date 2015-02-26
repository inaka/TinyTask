package inaka.com.tinytask;

import android.os.AsyncTask;

public class GenericTask<T> extends AsyncTask<Void, Void, T> {

    private Something<T> something;
    private DoThis<T> callback;
    private Exception exception;

    public GenericTask(TinyTask<T> tinyTask) {
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
            if(exception != null) {
                callback.ifNotOK(exception);
            } else {
                callback.ifOK(result);
            }
        } else {
            callback.ifNotOK(new RuntimeException("task was cancelled"));
        }

    }
}
