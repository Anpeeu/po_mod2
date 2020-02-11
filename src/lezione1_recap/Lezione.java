package lezione1_recap;

/*
    INTRODUZIONE
    sistema con type system statico
    a compile time controllo tipi
    Nei linguaggi dinamici accade che in runtime  possono avvenire errori di compile time (intero in una string)
    In java un simile errore viene controllato a tempo di compilazione

    Esempio in c, con i cast tra puntatori, anche in c capita che  avvengano errori di tipi a runtime, i cast forzano i tipi
    (tutto nei bene della leggerezza in exe del codice c)
    void f(int *){
        float  y = *((float*) x); //questo è un cast
    }

    in java si posono castare oggetti  da un  tipo ad un suo sottotipo, solo scendendi la gerarchia,
    posso anche qui fare programmi con errori

    Dovremmo imparare la programmazione sicura, evitando di usare i cast per esempio

    La programmazione ad oggetti è uno stile, java è ad oggetti ed imperativo
    Se un linguaggio ha assegnamenti è imperativo

    Programmazione senza assegnamenti è una programmazione più sicura, evitando di fare modifiche alle variabili riduco i bug

    EREDITARIETÀ
    Classe è un insieme, oggetto è un valore che appartimene all insieme
    Dynamic dispatching :  chiama il metodo giusto anche se il tipo di una variabile è una superclasse
    Polimorfismo : foca

    SE HO PIU CLASSI INNESTATE SE NON SONO STATICHE, DEVO PRIMA IMPLEMENTARE LEZIONE
    se sono statiche non sono più vincolate ad un file e alla classe lezione
*/

public class Lezione{

    public static class Animal{
        protected int  weight;

        public Animal(int weight){
            this.weight = weight;
        }
        public void eat(Animal a){
            weight += a.weight;
        }
    }

    public static class Dog extends Animal{
        private String color;

        public Dog(int w,String c){
            super(w);
            this.color = c;
        }

        @Override
        public void eat(Animal a) {
            weight += a.weight / 2;
        }

        public void bark(){
            System.out.println("Bau Bau");
        }
    }
    /* Cat estende Animal e non cane altrimenti i gatti avrebbero il metodo barks*/
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

        public void meow(){}
    }
    public static void main(String[] args) {
        Dog fido = new Dog(15, "bruno");
        Dog baldo = new Dog(20,"bianco");
        /* I tipi sono compatibili finche si rimane nella gerarchia*/
        Animal jackie = new Dog(2, "nero");
        Animal cat = new Cat(2, "rosa");
        Cat foca = new Cat(1, "grey");
        /*
            cat ha tipo animal, il compilatore non che in realtà che c`è un gatto dentro, questo evita troppo casingo con casting
            A run time però vengono mantenuti ed infatti il dispatching avvia il metodo giusto
            Se castassi il compilatore si dovrebbe fidare di me, caos.

            ho un oggetto di tipo T, un oggetto di tipo S  purchè S sia un supertipo di T
        */
        //foca = cat;
        fido.bark();
        /*
           cat è un metodo di animal ? si
           fido è un animal ?  si per principio di subsumption (susunzione) si va dal tipo piu  specifico  al tipo più generale
                posso spacciare un cane per un animale, NON un animale come un cane
                dallo specifico al generale.
           polimorfismo è permettere ad una funzione di lavorare con tipi diversi (nel nostro caso polimorfismo subtype)

           A runtime eseguo il eat corretto, grazie al dynamic dispatching che invoca il metodo di Cat (metodo originale)
           Gli oggetti in memoria sono salvati come elenco dei suoi campi (suoi e degli antentati), e una tabella ai puntatori di tuttii metodi
           che lui può chiamare  ->  Virtual table
           se cambio il valore di uno dei puntatori all`interno della tabella posso cambiare quale metodo chiama
        */
        cat.eat(fido);

        
    }
}
