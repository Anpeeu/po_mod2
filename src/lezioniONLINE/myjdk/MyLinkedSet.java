package myjdk;

public class MyLinkedSet<T> implements MySet <T> {

    protected MyLinkedList<T> l;
    public MyLinkedSet(){
        l = new MyLinkedList<>();
    }

    @Override
    public void add(T x) {
        if(!l.contains(x))
            l.add(x);
    }

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public boolean contains(T x) {
        return l.contains(x);
    }

    @Override
    public boolean remove(T x) {
        return l.remove(x);
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public boolean isEmpty() {
        return l.isEmpty();
    }

    @Override
    public MyIterator<T> iterator() {
        return l.iterator();
    }
}
