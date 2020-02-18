package lezione5_genericsLove;

/*
    Spiegazione di git e git hub
    Organizzazione di un buon progetto tramite divisone del codice in varie classi ed in package
    Speiegazione di cos`è (percorso di una classe/interfaccia), esempi del jdk

    Inizio lezione vera

    Questione partner tra Animal e Dog, per evitare il casting :
    - abbiamo pensato di aggiungere un nuovo campo  di tipo  Dog con  nome  newpartner
      grazie al fatto che posso ritornare sottotipi rispetto alla funzione del padre tutto funziona
      Problema, duplico l`informazione.
    Rendo  il metodo  di getPartner astratto.
    Quando non so che cosa fare, come implementare un certo metodo perchè sono troppo alto nella gerarchia, lo  definisco come abstract.
    Quando ho anche solo 1 metodo abstract la classe diventa abstract -> la classe non può essere costruita.
    In questo modo io creo dei genitori non costruibili che servono solo come collante per ifigli e per le loro funzionalità.

    Animal  è ora diventato abstract.
    API : modo in cui un programma mostra le sue funzionalità all`esterno
    Qualsiasi classe che estenda Animal DEVE implementare getPartner COME VUOLE, senza  vincoli o attributi extra.

    Noto che tra fratelli ci sono replicazioni di dati, vediamo come  si  può risolvere.

    Prodotto  schema logico delle gerarchie tra  Creature<-Animal<-(Dog,Cat<-Persian)
    Animal implementa l`attributo weight perchè è condivisa tra tutti
    Noto che anche color è condivisa tra i figli, il concetto di ereditarietà dice propro che in questi casi sarebbero da raggruppare in una classe padre
    E se alcuni Animali non volessero avere il colore ?  Possibile, come  risolvo?
    UTILIZZO una classe intermedia ColoredAnimal
    lo  schema diventa  quindi  Creature<-Animal<- COLOREDANIMAL<-(Dog,Cat<-Persian)

    ColoredAnimal diventa una classe abstract (giustamente)
    anche se abstrat HO bisogno di un costruttore altrimenti spezzerei la catena che dai figli va verso il padre di tutti

    Ora che ho risolto il colore devo risolvere la questione del partner
    Soluzione ?  GENERICS
    Creo un nuovo attributo di tipo A che è estensione di Animal

    extends  piccola parentesi [
        - public class a extends b {} a è figlia di b
        - B< A extends B> è DEVE essere un estensione di B
        - < ? extends B > wildcards
    La classe ColoredAnimal propaga il generics
    Mentre dog e cat Pongono il generics con il loro tipo

    Avendo risolto il problema posso eliminare l`abstract che ho messo in Animal e implementare il getPartner direttamente dentro Animal
    Modifico tutte le classi con le nuove informazioni
        - modifico tutti i costruttori
*/
public class Lezione {
    public static interface Creature {}

    public static abstract class Animal< A  extends Animal> implements Creature {
        protected int  weight;
        protected A  partner;

        public Animal(int weight ,A partner){
            this.weight = weight;
            this.partner = partner;
        }
        public void eat(Animal a){
            weight += a.weight;
        }
        // versione abstract public abstract A getPartner();
        public A getPartner(){
            return partner;
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static abstract class ColoredAnimal< A  extends Animal> extends Animal< A >{
        protected String color;

        protected ColoredAnimal(int weight,A partner, String color) {
            super(weight,partner);
            this.color = color;
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static class Dog extends ColoredAnimal<Dog> {
        private Dog partner;

        public Dog(int w, Dog partner, String c) {
            super(w,partner,c);
        }

        public void eat(Creature a) {
            this.weight += 10;
        }

        public  void bark(){}
    }

//----------------------------------------------------------------------------------------------------------------------

    public static class  Cat extends ColoredAnimal<Cat>{

        public Cat(int w, Cat partner, String c){
            super( w, partner, c);
        }
        public void meow() {
            System.out.println("MUDA MUDA MUDA");
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("foca monaca");
    }
}
