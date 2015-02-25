package inaka.com.tinytask;

public interface DoThis<T> {

    public void ifOK(T result);
    public void ifNotOK(Exception e);

}
