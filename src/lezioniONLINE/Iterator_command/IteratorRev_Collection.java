package Iterator_command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public class IteratorRev_Collection<T>  extends ArrayList<T> {
    public IteratorRev_Collection() {
        super();
    }
    public IteratorRev_Collection(int size) {
        super(size);
    }

    @Override
    public Iterator<T> iterator(){
        return new MyIterator(super.iterator());
    }
}