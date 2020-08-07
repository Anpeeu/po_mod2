package Iterator_command;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorRev_list<T>  extends ArrayList <T> {
    public IteratorRev_list() {
        super();
    }
    public IteratorRev_list(int size){
        super(size);
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int pos = IteratorRev_list.this.size() - 1;
            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public T next() {
                return IteratorRev_list.this.get(pos--);
            }
        };
    }
}
