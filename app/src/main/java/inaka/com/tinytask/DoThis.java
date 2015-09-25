package inaka.com.tinytask;

public interface DoThis<T> {

    void ifOK(T result);

    void ifNotOK(Exception e);

}
