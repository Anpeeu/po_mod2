package lezione7_iteratori_static;

/*
    costrutto foreatch funziona solo su cose iterabili
    funziona come un per ogni elemento in : Struttura iterabile

    elimino il vecchio sum e tengo solo quella con il foreatch

    Il metodo sum è un metodo statico, non ha il this, anche giustamente, la classe in cui lui si trova è una collezione
    di funzioni ultili

    Creo una sotto interfaccia di iterable<integer> e la nomino summable

    POSSO USARE THIS AL POSTO DELLA ESPRESSIONE DEL COSTRUTTO FOREATCH

    Posso avere 1 superclasse e n interfacce implemntate
    modifico e uso T al posto di integer


    Overlap in myintegerArraylist perchè gli dico 2 volte che è un iterable
    Summable richiede di implementare anche Iterable e tutti i suoi metodi MA avendo esteso arraylist i metodi sono gia stati implementati

    QUANDO EREDITI CLASSI EREDITO CODICE
    QUANDO IMPLEMENTI ITERFACCE EREDITO FIRME ( caso particolare metodi default )

    In java < 9 NON potevo utilizzare l`implementazione di default e dovevo usare classi abstract MA essendo classi ero bloccato per quanto riguarda
    le estensioni di classi
*/

import java.util.*;

public class Lezione{

    public static void populate (Collection<Integer> a){
        a.add(7);
        a.add(2);
        a.add(2);
        a.add(123);

    }

    public static void sum(Iterable<Integer> a){
        int r = 0;
        for (Integer n : a) {
            r += n;
        }
        System.out.println(r);
    }
    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        populate(a);
        //sum(a);

        Set<Integer> s = new HashSet<>();
        populate(s);
        //sum(s);

        // Ora devo chiamare il metodo sum con questa nuova modalità

        MyIntegerArrayList u = new MyIntegerArrayList();
        u.populate();
        u.sum();
    }


    // Dovrei farla da un altra parte
    public interface Summable extends Iterable <Integer>{

        default void sum(){
            int r = 0;
            for (Integer n : this) {
                r += n;
            }
            System.out.println(r);
        }
        void populate ();
    }


    public static class MyIntegerArrayList extends ArrayList<Integer> implements Summable {
        public MyIntegerArrayList(){
            super();
        }

        @Override
        public void populate (){
            add(7);
            add(2);
            add(2);
            add(123);
        }
    }
}
/*
public interface Summable<T> extends Iterable <T>{

    //default void sum(){
    //    int r = 0;
    //    for (T n : this) {
    //        r += n;
    //    }
    //    System.out.println(r);
    //}
    void sum();
}


public static abstract class MyIntegerArrayList<T> extends ArrayList<T> implements Lezione.Summable<T> {
    public MyIntegerArrayList(){
        super();
    }

    public void populate()
        //*{
        //    add(7);
        //    add(2);
        //    add(2);
        //    add(123);
        //}

    }
}
*/
