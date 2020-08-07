package esami;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.lang.Integer;
public class Esame30_05_19 {

    public interface BiFunction < A, B, C >{
        C apply(A a,B b);
    }

    public static class Pair<A,B>{
        public final A a;
        public final B b;

        public Pair(A a,B b){
            this.a = a;
            this.b = b;
        }
    }

    public static class Triple< A, B, C>{
        public final A a;
        public final B b;
        public final C c;


        public Triple(A a, B b, C c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static < A, B, C> Iterator<Triple<A,B,C>> evaiIterator(Iterator< Pair<A, B>> it, BiFunction<A,B,C> f){
        Collection<Triple<A,B,C>> res = new ArrayList<>();
        while (it.hasNext()){
            Pair<A,B> p = it.next();
            res.add(new Triple<A, B, C> (p.a, p.b, f.apply(p.a,p.b)));
        }
        return res.iterator();
    }

    // PARTE 2
    public static class FiboSequenceCompatta extends ArrayList<Integer>{
        public FiboSequenceCompatta (int it) throws InterruptedException {
            fib (it);
        }
        public synchronized void fib(int it) throws InterruptedException {
            int i;
                if (size() < 2){
                    add(1);
                    add(1);
                }
            for (i = it; size() != i; i--) { }
            for ( ; i <= it; i++) {
                add(  get(size()-1) + get(size()-2));
            }
        }
    }
/*
    public static class FiboSequence implements Iterable<Integer> {
        protected static ArrayList<Integer> l = new ArrayList<>();

        public FiboSequence(int it){
            int i;
            synchronized (l) {
                if (l.size() < 2) {
                    l.add(1);
                    l.add(1);
                }
            }
            synchronized (l) {
                for (i = it; l.size() != i; i--) {
                }
            }
            synchronized (l) {
                for ( ; i <= it; i++) {
                    l.add(  l.get(l.size()-1) + l.get(l.size()-2));
                }
            }


        }

        @NotNull
        @Override
        public Iterator<Integer> iterator() {
            return l.iterator();
        }
    }
*/
    // PARTE 3
    public static class FactThread extends Thread{
        private int n;
        private int id;
        private int res;


        @NotNull
        private FiboSequenceCompatta f;

        public FactThread(int id, int n, @NotNull FiboSequenceCompatta f){
            this.n = n;
            this.id = id;
            this.f = f;
        }

        @Override
        public String toString() {
            return "Terminato Thread:"+this.id+",n:"+this.n+" ,res:"+this.res;
        }

        @Override
        public void run(){


            try {
                f.fib(n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res = f.get(n);
        }


    }

    public static Collection<FactThread> multiFact(Collection<Integer> c) throws InterruptedException {
        Collection<FactThread> res = new ArrayList<>();
        FiboSequenceCompatta f = new FiboSequenceCompatta(2);
        int id = 0;
        for( int i : c){
            res.add( new FactThread(++id,i,f));
        }
        return res;
    }

    // TESTING
    public static void main(String[] args) throws InterruptedException {
         /*

        List<Pair<String,Integer>> l = new ArrayList<>();
        l.add(new Pair<>("ciao",1));
        System.out.println(evaiIterator(l.iterator(),( String a, Integer b) -> a + b.toString()));

         */

          /*

         Iterator<Integer> fs = new FiboSequence(100).iterator()
         while (it.hasNext()){
            Integer i = it.next();
            sout(i);
         }

          */


        Random r = new Random();

        Collection<Integer> in = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            in.add(r.nextInt(4));
        }


        Collection<FactThread> c = multiFact(in);
        for (FactThread t : c) {
            t.start();
        }

        for (FactThread t : c) {
            t.join();
            System.out.println(t);
        }
    }
}
