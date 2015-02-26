package inaka.com.tinytask;

public abstract class Something<T> {

    abstract T whichDoes() throws Exception;

    /**
     * Optional: you can get a callback if the task was cancelled
     */
    void ifCancelled() { }

}
