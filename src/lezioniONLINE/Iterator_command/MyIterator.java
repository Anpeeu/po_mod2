package Iterator_command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {

    private int pos;
    private List<T> l;


    public MyIterator(Iterator<T> it){
        l = new ArrayList<>();
        pos = -1;
        while (it.hasNext()){
            pos++;
            l.add(it.next());
        }
    }

    @Override
    public boolean hasNext() {
        return pos >= 0;
    }

    @Override
    public T next() {
        return l.get(pos--);
    }
}
