package lezione4_generics;
/*
    Vecchio stile di programmazione per gestire strutture che ricevono qualsiasi oggetto -> tutto posto ad object e poi casto a quello che mi serve
    Stile molto buggato

    <qui dentro posso scrivere molti nomi di tipi>
    Si può definire un tipo parametrico su un altro tipo
    Esempio MyList non è un tipo nomrmale, ha bisogno di un altro tipo concreto al posto della T (il compilatore controlla che questo venga fatto, altrimenti da un errore)

    Ragionandoci su si pò pensare che i generics possono esser visti come un tipo che insieme ad un altro tipo ne genera un altro MyList + Integer -> Mylist<Integer>

    MyList<String> n = new MyList<>(); è uguale a MyList<String> n = new MyList<String>(); semplicemente sottointendo che in questo caso gli do un Mylist uguale a quello di sinistra
    quindi  combina MyList+String -> MyList<String>
    Alla fine T è come una variabile di una funzione che viene sostituita appena viene chiamato il tipo MyList (similmente ad una funzione)

    Parameter quando lo definisci è parametro
    Argomento quando lo costruisco/applico

    E se volessi fare una lista con tipi diversi?
    Sfruttiamo la gerarchia!!
    Dog e Cat sono figli di Animal
    Quindi tramite  subsunzione se creo una lista di Animal, e ci sinserisco cani e gatti questa cosa va benissimo
    ATTENZIONE : quando effettuo un get, il tipo di ritorno  sarà Animal in ogni caso QUINDI se voglio usare i metodi specifici di Dpg o Cat
    dovrei castare (MALE. NON SERVE MAI CASTARE, SI PUÒ SEMPRE FARE IN UN MODO DIVERSO)

    Wildcards
    - MyList< ? extends Animal> x; ? è un tipo figlio di Animal
    - MyList< ? super Dog> x; ? è (un) padre di Dog

    Coovarianza e controvarianza GUARDARE LEZIONE 2 PER CAPIRE ESEMPI (variano insieme all`ereditarietà)
        metodo eat dei cani override quello di Animal
        Quando ereditare e overridate siamo abituati a copiare la firma del padre
        In realtà java non richiede che abbia la stessa firma, da la seguete regola :
            " i parametri sono controvarianti, mentre i tipi di ritorno sono covarianti "
        Posso spingere i parametri in su alla gerarchia (interfacce o classi, questo non mi intaressa)
        Mentre i valori di ritorno devono essere per forza figli del tipo che aveva il padre
 */


public class MyList <T>{

    private static class Node <T>{
        public T info;
        public Node next;

        public Node(T info, Node<T> next){
            this.info = info;
            this.next = next;
        }

        @Override
        public String toString() {
            return " Node{" +
                    "info=" + info +
                    ", \nnext=" + next +
                    '}';
        }
    }

    Node<T> head;

    public MyList(){
        head = null;
    }

    public void add(T e){
        Node<T> n = new Node( e, head);
        head = n;
    }

    public static class NotFoundException extends Exception{}
    public void remove(T e) throws NotFoundException {
    }

    public static class OutOfBoundsException extends Exception{}

    public T getIndex(int i) throws OutOfBoundsException {
        Node<T> n = head;
        for (; i > 0; --i)
            if((n = n.next) == null) throw new OutOfBoundsException();
        return n.info;
    }

    @Override
    public String toString() {
        /*
            Codice inutile, lo commento
         */
        /*
        Node<T> temp = head;
        String res = "";
        for (; temp != null; temp  =  temp.next)
            res += temp.toString()+"\n";
        */
        return head.toString();
    }

    public static void main(String[] args) {
        MyList<String> n = new MyList<>();
        n.add("ciao");
        n.add("foca");
        n.add("luigi");
        //n.add(true);
        System.out.println(n);
    }

}
