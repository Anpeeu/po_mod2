package lezione6_;

/*
    - Pagina del jdk Interace Collection <E> jdk
    La sua  superinfaccia è iterable, ovvero è collection è figlia  di  iterable

    Collection è parametrica su E
    - Iterable <T>
        iterable non ha  super interfacce, è in cima alla sua gerarchia

    iterable <T> <- collection <T> <-(roba,roba, List<T> <- ArrayList<T>)

    Osserviamo Le subinterfacce di Collection<T>, troviamo List<T> (lista), Queue<t> (coda), Set <T> (insiemi)
    Known implementing classes : Classi che  implementano questa interfacce es ArrayList<T>

    Una  collection è una sequenza di cose, una lista è figlia di collection più un piccolo particolare, cè il get per posizione
    o indice
    Che metodi ha iterable<T>? 
    -foreach e Spliterator vedremo in seguito 
    concentriamoci su iterator

    Iterator ritorna un iteratore che permette di scorrere gli elementi che appartengono algli elementi iterabili
    L`interfaccia iterator ha 4 metodi :
       - hasNext();
       - next();


    A seconda dell`livello che vuoi implementare dovrei ovviamente imolementare solo i metodi in su della gerarchia



*/

import java.util.*;

public class Lezione{

    public static void populate (Collection<Integer> a){
        // Popolo la lista
        a.add(7);
        a.add(2);
        a.add(2);
        a.add(123);

    }

    public static void sum (Iterable<Integer> a){
        //Mi ritorna l`iteratore di a (che è Collection<Integer>) con tipo paramentrico Integer lo stesso che ha la collezione
        Iterator<Integer> it =  a.iterator();
        /*
            Un iteratore è una scatola nera con solo 2 pulsanti, un c`è il prossimo e un dammi il prossimo
        */
        int r = 0;
        while (it.hasNext()) {
            r += it.next();
        }
        System.out.println(r);
    }

    public static void sum2(Iterable<Integer> a){
        /*
            Un iteratore è una scatola nera con solo 2 pulsanti, un c`è il prossimo e un dammi il prossimo
        */
        int r = 0;
        for (Integer n : a) {
            r += n;
        }
        System.out.println(r);
    }
    public static void main(String[] args) {
        /*
            È di tipo paramentrico si! utilizza integer!
            essendo inferibile il tipo dell`arraylist posso omettere il type argument a dx

            Uso al posto di arraylist list, ovviamente perdo tutte le caratteristiche peculiari di ArrayList
            Continuo la salita a Collection <T>
            Se provassi a salire ancora di piu noterei che Iterable non ha il metodo add, quindi inutilizzabile
            Torno a Collection

            Modifica finale, stringo il più possibile il tipo nelle mie funzioni, quanto posso salire per aggiungere un elemento ?
            Massimo a Collection, se salgo di più non posso più aggiungere, stessa cosa vale per la stampa

            QUESTO È ECCEZIONALE PER LA PROGRAMMAZIONE POLIMORFICA, OGNI COSA CHE SIA FIGLIA DI COLLECTION ED È FORMATA
            DA INTERI PUÒ ESERE GESTITA CON QUESTI METODO

            Infatti ora con set funziona lo stesso

            statement istruzioni
            expression espressione valutabile

            for ( T t1 : E )
        */
        ArrayList<Integer> a = new ArrayList<>();
        // subsunzione di a
        populate(a);
        // subsunzione di a
        sum(a);

        //HashSet è un set che utilizza l`hash code per NON accettare duplicati
        Set<Integer> s = new HashSet<>();
        // subsunzione di a
        populate(s);
        // subsunzione di a
        sum(s);



    }
}