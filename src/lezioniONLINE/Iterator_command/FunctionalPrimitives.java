package Iterator_command;

import jdk.jshell.spi.ExecutionControl;
import pattern.SortingTest.Foca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalPrimitives {

    public static <A, B> List<B> map(Iterable<A> l, Function< ? super A, ? extends B> f){
        List<B> res = new ArrayList<>();
        for (A a : l)
            res.add(f.apply(a));
        return res;
    }
    // Classico esempio la sommatoria
    public static <A, B> List<B> mapFold(Iterable<A> l, Function< ? super A, ? extends B> f){
        return fold(l,new ArrayList<B>(),(elem, list) -> {
            list.add(f.apply(elem));
            return new ArrayList<>(list);
        });
    }
    /**
     *
     * @param l insieme di elemnti iterabili;
     * @param zero inizializzazione dell'accomulatore
     * @param f funzione di accumulazione
     * @param <A> elemento in input
     * @param <B> accumulatore
     * @return risultato della fold
     */
    public static <A, B> B fold(Iterable<A> l, B zero, BiFunction< A, B, B> f){
        B acc = zero;
        for (A a : l)
            acc = f.apply(a,acc);
        return acc;
    }
    public static <A, B> B fold_rec(Iterable<A> l, B zero, BiFunction< A, B, B> f){
        return fold_rec(l.iterator(),zero,f);
    }

    public static <A, B> B fold_rec(Iterator<A> l, B zero, BiFunction< A, B, B> f){
        if (!l.hasNext())
            return zero;
        else
            return  fold_rec(l, f.apply( l.next(), zero), f);
    }

    public static <A> List<A> filter(Iterable<A> l, Function< ? super A,Boolean> f){
        List<A> res = new ArrayList<>();
        for (A a : l)
            if (f.apply(a)) res.add(a);
        return res;
    }

    public static <A> List<A>  filter_rec(Iterable<A> l, Function< ? super A, Boolean> f){
        return fold_rec(l.iterator(), new ArrayList<>(),(a, as) ->{
            if (f.apply(a))
                as.add(a);
            return as;
        });
    }

    public static <A> void iter(Iterable<A> l, Consumer<A> f){
        for( A a : l)
            f.accept(a);
    }

    public static <X> void print(Collection<X> c){
        map(c, (Function<X, Void>) x -> {
            System.out.println(x);
            return null;
        });
    }

    public static class Foca {
        public final double peso;

        public Foca(double peso){
            this.peso = peso;
        }

        @Override
        public String toString(){
            return "Foca[w:"+peso+"]";
        }
    }
    public static class FocaMorbida extends Foca {

        public FocaMorbida(double peso){
            super(peso);
        }

        @Override
        public String toString(){
            return "FocaMorbida[w:"+peso+"]";
        }
    }
    public static void main(String[] args) {
        List<Integer> c = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c.add(i);
        }
        c = map(c, e -> e * 2);
        print(c);

        List<FocaMorbida> foca = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            foca.add(new FocaMorbida(i));
        }
        //foca = map(foca, (Foca e) -> new FocaMorbida(e.peso));
        foca = mapFold(foca, (Foca f) -> new FocaMorbida(f.peso));
        print(foca);
        System.out.println("\nFold");
        System.out.println(fold(foca,new ArrayList<Double>(),(focaMorbida, doubles) -> {
                doubles.add(focaMorbida.peso);
                return doubles;
        }));
        System.out.println(fold_rec(foca,new ArrayList<Double>(),(focaMorbida, doubles) -> {
            doubles.add(focaMorbida.peso);
            return doubles;
        }));

        System.out.println(fold(foca, 0.,(focaMorbida, integer) -> focaMorbida.peso + integer));
        System.out.println(fold_rec(foca, 0.,(focaMorbida, integer) -> focaMorbida.peso + integer));
        List<Double> f = filter(fold (foca,new ArrayList<Double>(),
                (focaMorbida, doubles) -> {
                    doubles.add(focaMorbida.peso);
                    return doubles;
                }), (Double a) -> a % 2 == 0
        );
        iter(f , x -> System.out.println(x));


        Function<FocaMorbida, String> toS = Foca::toString;

    }
}
