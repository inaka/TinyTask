package inaka.com.tinytask;

abstract class Something<T> {

    /**
     * The method that actually does all of the work.
     * @return T
     * @throws Exception
     */
    abstract T whichDoes() throws Exception;

    /**
     * Optional: you can get a callback if the task was cancelled
     */
    void ifCancelled() { }

}
