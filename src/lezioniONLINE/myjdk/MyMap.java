package myjdk;

public interface MyMap <K, T>  {
    T get(K k) throws NotFoundException;
    void put( K k, T t);
    void clear();
}
