package inaka.com.tinytask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity {

    private static final String TAG = "TinyTask";
    private TinyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }

    public void test() {

        //noinspection unchecked
        task = TinyTask.with(this).perform(new Something<String>() {
            @Override
            public String whichDoes() {
                return pretendToDoSomething();
            }
        }).whenDone(new DoThis<String>() {
            @Override
            public void ifOK(String result) {
                Log.i(TAG, result);
            }

            @Override
            public void ifNotOK(Exception e) {
                Log.i(TAG, e.toString());
            }
        });

        task.go();
    }

    private String pretendToDoSomething() {
        for (int i = 0; i < 20; i++) {
            try {

                if(!task.isCancelled()) {
                    Log.i(TAG, "doing something..");
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "done!";
    }

    public void onPause() {
        super.onPause();
        task.cancel();
    }

}
