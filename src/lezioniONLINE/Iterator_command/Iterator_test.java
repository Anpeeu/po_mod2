package Iterator_command;

import java.util.Collection;
import java.util.Iterator;

public class Iterator_test {
    public static void main(String[] args) {
        Collection<Integer> l = new IteratorRev_Collection<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }

        Iterator<Integer> it = l.iterator();
        while (it.hasNext()){
            int n = it.next();
            System.out.println(n);
        }
    }
}
