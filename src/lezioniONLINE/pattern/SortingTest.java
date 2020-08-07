package pattern;

import java.util.*;

public class SortingTest {

    public static <T> List<T> sort(List<T> l, Comparator<? super T> c){
        return null;
    }
    public static <T extends Comparable<? super T>> List<T> sort(List<T> l){
        return null;
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

    public static void main(String[] args) {
        Random r = new Random();

        byte[] b = new byte[10];

       /* for (int i = 0; i < 10; i++) {
            r.nextBytes(b);
            System.out.println(new String(b));
        }*/

        // CASO 1
        List<Foca> c1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c1.add(new Foca(r.nextDouble()));
        }
        /*
        Collections.sort(c1, new Comparator<Foca>(){
            @Override
            public int compare(Foca o1, Foca o2) {
                return Double.compare(o1.peso,o2.peso);
            }
        });

        for (Foca f : c1)
            System.out.println(f);

        System.out.println("\n\n");
        // CASO 1
        List<Foca> c2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c2.add(new Foca(r.nextDouble()));
        }
        Collections.sort(c2, (o1, o2) ->  Double.compare(o1.peso,o2.peso));

        //Collections.sort(c2, Comparator.comparingDouble(o -> o.peso));

        for (Foca f : c2)
            System.out.println(f);


        //Test.foca();
        */

        Iterator<Foca> it = c1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }

        System.out.println("\n\n Stampo dopo");
        for (Foca f : c1)
            System.out.println(f);

    }



    /* TEST */
    /*
    public static class Animale{
        Animale foo(Animale a){
            return this;
        }
    }
    public static class Foca extends Animale{

        @Override
        Foca foo(Animale a){
            return this;
        }
    }

    public interface Test{
        void foo();

        static void foca(){
            System.out.println(" ciao");
        }
    }
    */
}
