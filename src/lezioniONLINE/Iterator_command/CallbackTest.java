package Iterator_command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class CallbackTest {

    public static <A, B> Collection<B> map(Collection<A> l, Function<A,B> f){
        Collection<B> res = new ArrayList<>();
        for (A a : l)
            res.add(f.apply(a));
        return res;
    }



    public static <X> void print(Collection<X> c){
        map(c, (Function<X, Void>) x -> {
            System.out.println(x);
            return null;
        });
    }

    public static void main(String[] args) {
        Collection<Integer> l = new IteratorRev_Collection<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }

        System.out.println("Parte 1");
        for (int n : map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        })){
            System.out.println(n);
        }
        System.out.println("\nParte 2");
        for (int n : map(l,integer -> integer * 2)){
            System.out.println(n);
        }

        System.out.println("\nParte 3");
        Function<Integer, Integer> f = integer -> integer * 2;
        for (int n : map(l,f)){
            System.out.println(n);
        }
    }
}
