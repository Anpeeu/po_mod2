package myjdk;

public class MyIdentityMap<K, V> implements MyMap < K, V> {

    private MyArrayList <Pair< K, V>> m;
    public MyIdentityMap(){
        m = new MyArrayList<>();
    }
    @Override
    public V get(K k) throws NotFoundException{
        MyIterator< Pair< K, V>> it = m.iterator();
        while (it.hasNext()){
            Pair< K, V> p = it.next();
            if (p.first.equals(k)) return p.second;
        }
        throw new NotFoundException();
    }

    @Override
    public void put(K k, V v) {
        MyIterator< Pair< K, V>> it = m.iterator();
        while (it.hasNext()){
            Pair< K, V> p = it.next();
            if (p.first.equals(k)){
                 p.second = v;
                 return;
            }
        }
        m.add( new Pair<>(k, v));
    }

    @Override
    public void clear() {
        m = new MyArrayList<>();
    }
}

