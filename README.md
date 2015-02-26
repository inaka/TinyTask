TinyTask
=====

A tiny Android library to create async background tasks. Highly inspired by [NanoTasks](https://github.com/fabiendevos/nanotasks), but with a funnier API :)

### Abstract
Android's `AsyncTasks` are highly criticized for being bad, unreliable, outdated, etc. Are they perfect? No.
Do we have better alternatives? Sure, but sometimes all we want is a quick and simple way to run something in the background.

### What is it, really?
Just a tiny wrapper around an `AsyncTask`, with a funny looking API.

### How to download and Install
Add the following to your `build.gradle` file:

```groovy
repositories {
	maven {
		url "https://jitpack.io"
	}
}

dependencies {
	// ...
  compile 'com.github.inaka:tinytask:v1'
  // ...
}
```

### Code Example
The API methods should be self-explanatory :)

```java
public class TestActivity extends Activity {

    private static final String TAG = "TinyTask";
    private TinyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }

    public void test() {

        task = TinyTask.perform(new Something<String>() {
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

            } catch (InterruptedException e) { }
        }
        return "done!";
    }

    public void onPause() {
        super.onPause();

        // you should cancel the task accordingly to avoid NPE in the callbacks
        task.cancel();
    }

}
```
### Contact Us
For **questions** or **general comments** regarding the use of this library, please use our public
[hipchat room](https://www.hipchat.com/gpBpW3SsT).

If you find any **bugs** or have a **problem** while using this library, please [open an issue](https://github.com/inaka/galgo/issues/new) in this repo (or a pull request :)).

And you can check all of our open-source projects at [inaka.github.io](http://inaka.github.io)

### Thanks to
[NanoTasks](https://github.com/fabiendevos/nanotasks) for inspiration
