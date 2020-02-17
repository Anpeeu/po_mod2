package lezione2_recap;


import java.util.ArrayList;
import java.util.Collections;

/*
    Metodi e funzioni, tutti i metodi di una classe prendono un parametro in più che sarebbe un oggetto del tipo della classe che state scrivendo
    e il nome del parametro è this
    Parla dell`implementazione tramite c di un linguaggio ad oggetti java like
     + in realtà è una funzione binaria, con una rappresentazione diversa
     e se  +/plus fosse una funzione della  classe int ? Che somma a se stessa un altro intero passato come parametro ?
     1.plus(8) -> 9

     Collection è il  supertipo di tutte le collezioni di elementi monodimensionali
     Collections ha solo metodi statici  (quindi NON HA IL THIS), è un contenitore per le funzioni di utility sulle collection                         metodo static non ha this per definizione, poichè non è legato alla sua classe

     Io posso gestire un  dog come un animal poichè essendo estensione sua,  possiede TUTTE  le sue caratteristiche (più qualcosa in più), in questo modo abilito
     un  tipo di polimorfismo , il subtyping


     Perchè la programmazione ad oggetti ?
     quando si sviluppa un grande software (tante persone diverse nel tempo), l`obbiettivo è che funzioni,  ma  cosa significa che funzioni?
     si intende che il programma esca con meno errori  possibili, per aumentarne l`affidabilità
     Il modo piu semplice per risolvere il problema è riutilizzare le funzioni già  implementate e che sò che funzioni.
     Per porter riusare molto queste funzioni è necessario che esse funzionino con tipi diversi OVVERO POLIMORFISMO!!!!

*/
public class Lezione{
    /* MDOFICA DELLA LEZIONE 4*/
    public interface Creature{}

    public static class Animal implements Creature{
        protected int  weight;
        protected Animal partner;

        public Animal(int weight, Animal p ){
            this.weight = weight;
            this.partner = p;
        }
        public void eat(Animal a){
            weight += a.weight;
        }
        public Animal getPartner(){
            return this.partner;
        }
    }

    public static class Dog extends Animal{
        private String color;
        private Dog newPartner

        public Dog(int w,String c, Dog partner){
            super(w, partner);
            this.color = c;
            this.newPartner = partner;

        }


        /**
         *
         * @param a Modificato in Creature nella lezione 4
         */
        public void eat(Creature a) {
            weight += 10;
        }

        // LEZIONE 4 MODIFICA
        @Override
        public Dog getPartner() {
            return newPartner;
        }

        public void bark(){
            System.out.println("Bau Bau");
        }
    }
    public static class Cat extends Animal{
        private String color;

        public Cat(int w,String c){
            super(w);
            this.color = c;
        }

        @Override
        public void eat(Animal a) {
            weight += a.weight / 3;
            color = color + " fat";
        }

        public void meow(){
            System.out.println("meow");
        }
    }
    public static class Persian extends Cat {

        public Persian(int w) {
            super(w, "Grey");
        }

        @Override
        public void meow() {
            System.out.println("WRYYYYYYYYY");
        }

    }

    public static void main(String[] args) {
        Dog fido = new Dog(15, "bruno");
        Dog baldo = new Dog(20,"bianco");
        Animal jackie = new Dog(2, "nero");
        Animal cat = new Cat(2, "rosa");
        Cat foca = new Cat(1, "grey");
        fido.bark();
        cat.eat(fido);
        Persian p = new Persian(2);
        p.meow();

        /* DIFFERENZA TRA METODO E FUNZIONE STATICA */
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.sort((integer, t1) -> t1.compareTo(integer) );
        Collections.sort(l);

    }
}

