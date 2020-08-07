package threading;


import java.util.*;

public class PrimeFinder {

    private static class Monitor {
        List<Integer> l = new ArrayList<>();

        public synchronized void add(String name, int elem){
            l.add(elem);
            log(name+": aggiunto["+elem+"]");
            this.notifyAll();
        }

        public synchronized int remove( String name) throws InterruptedException {
            while (l.isEmpty()) {
                log(name+": Lista vuota");
                this.wait();
            }
            return l.remove(0);
        }

    }

    private static void log(String msg){
        Thread self = Thread.currentThread();
        System.out.println(String.format("%s[%d]: %s",self.getName(),self.getId(),msg));
    }

    public static class Consumer extends Thread{
        private final String name;
        private final PrimeFinder.Monitor m;
        private final List<Integer> l;


        public Consumer( String name,Monitor m){
            this.name = name;
            this.m = m;
            this.l = new ArrayList<>();
        }
        @Override
        public void run(){

                try {
                    while (true) {
                        int tmp = m.remove(name);
                        l.add(tmp);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }

        @Override
        public String toString(){
            return l.toString();
        }
    }

    public static class Producer extends Thread{
        private final String name;
        private final Set<Integer> primes;
        private final PrimeFinder.Monitor m;

        public Producer( String name,Monitor m){
            this.name = name;
            this.m = m;
            this.primes = new TreeSet<>();
        }

        @Override
        public void run(){
            boolean isPrime = true;
            for (int n = 2, next; true; isPrime = true, n++) {
                Iterator<Integer> it = primes.iterator();
                while (isPrime && it.hasNext() && (next = it.next()) <= Math.sqrt(n)) {
                    //next = it.next();
                    if (n % next == 0)
                        isPrime = false;
                }

                if (isPrime) {
                    primes.add(n);
                    m.add(name,n);
                    //System.out.println(primes);
                    //System.out.println(String.format(name + "    trovato : %10d ", n));
                    try {
                        this.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    static int N_CONSUMER = 4;

    public static void main(String[] args) throws InterruptedException {
        Monitor m = new Monitor();
        Producer p = new Producer("produttore", m);
        Collection<Consumer> c= new ArrayList<>();

        for (int i = 0; i < N_CONSUMER; i++) {
            c.add(new Consumer("consumatore"+i, m));
        }

        p.start();
        for ( Thread t : c)
            t.start();


        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit)
            if (in.next().equals("t")) exit = true;


        for ( Thread t : c)
            System.out.println(t.toString());

        for ( Thread t : c)
            t.interrupt();
        p.join();
        for ( Thread t : c)
            t.join();
    }
}
