package myjdk;

public class MyArrayList <T> implements MyList<T> {

    private Object[] a;
    private int DIM = 100;
    private int actualSize;

    public MyArrayList(){
        this.a = new Object[DIM];
        actualSize = 0;
    }

    @Override
    public T get(int i) throws OutOfBoundsException {
        return null;
    }

    @Override
    public void add(int i, T x) {

    }

    @Override
    public boolean remove(int i) {
        return false;
    }

    @Override
    public void add(T x) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    @Override
    public boolean remove(T x) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            int pos = 0;
            @Override
            public T next() {
                return (T) a[pos++];

            }

            @Override
            public boolean hasNext() {
                return pos < actualSize;
            }
        };
    }
}
