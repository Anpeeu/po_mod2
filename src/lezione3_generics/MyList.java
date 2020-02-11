package lezione3_generics;
/*
    implementiamo una versione "didattica" di una lista
    in c
    struct mynode {
        int data;
        struct mynode *next;
    }
    In java abbiamo le classi che possono esser viste come struct con metodi per gestirli riuniti in un unico file

    prima faremo una lista monomorfa con gli int, POI verrà generalizzata per poter fare liste di altre cose

    i generics alla fine sono dire che sto implementando una classe con un buco
    e se usassi object per salvare tutti i dati ? si ma perdo  ENORMEMENTE DI

*/
public class MyList <T>{
    private static class Node <T>{
        public T info;
        public Node next;

        public Node(T info, Node<T> next){
            this.info = info;
            this.next = next;
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

    public static void main(String[] args) {
        MyList<String> n = new MyList<>();
    }

}
