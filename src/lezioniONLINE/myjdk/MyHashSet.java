package myjdk;

public class MyHashSet <T> extends MyLinkedSet<T> {
    private  HashFun<T> h;

    @FunctionalInterface
    public interface HashFun<E>{
        long hash(E e);
    }

    public MyHashSet(){
        super();
        this.h = T::hashCode;
        this.h = x -> x.hashCode();
    }

    public MyHashSet(HashFun<T> h){
        super();
        this.h = h;
    }

    @Override
    public  void add(T x){
        MyIterator <T> it = iterator();
        boolean found = false;
        while ( it.hasNext()){
            T e = it.next();
            if (h.hash(x) == h.hash(e))
                found = true;
        }
        if (!found)
            l.add(x);
    }
}
