package threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Main {

    public static void prime95(String name,int times){


        Boolean isPrime = true;
        for (int n = 3, numPrime = 0; numPrime < times; isPrime = true, n++){

            for (int div = 2; isPrime && div < Math.sqrt(n)+1; div++){
                if (n % div == 0)
                    isPrime = false;
            }
            if (isPrime) {
                //System.out.println(String.format(name + "    trovato : %10d        nP : %10d ", n, numPrime));
                numPrime++;
            }
        }
    }



    public static void fun(String id, long millis, int times){
        try {
            for (int i = 0; i < times; i++) {
                System.out.println(id+" : "+i);
                Thread.sleep(millis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static class MyThread extends Thread {
        @Override
        public void run(){
            fun("1", 200, 10);
        }
    }

    private static Random rnd = new Random();

    private static int rand(int a, int b){
        return rnd.nextInt(b - a + 1) + a;
    }


    static int N_THREAD = 1000;
    static int N_PRIME = 10000000;

    public static void main(String[] args) throws InterruptedException {
        //MyThread t = new MyThread();
        //t.start();
        Collection<Thread> threads = new ArrayList<>();

        /*
        System.out.println(rand(5,10));

        for (int i = 0; i < rand(5,10); i++){
            final String name = String.format("thread#%d",i);
            //Thread t = new Thread(() -> {
            //    fun(name, rand(100, 800), rand(10,30));
            //});
            Thread t = new Thread( new Runnable() {
                @Override
                public void run() {
                    fun(name, rand(100, 800), rand(10,30));
                }
            });


            t.start();
            threads.add(t);
        }

        fun("main", 100, 30);
        */

        for (int i = 0; i < N_THREAD; i++) {
            final String name = String.format("thread#%d",i);
            Thread t = new Thread(() -> prime95(name,N_PRIME));
            t.start();
            threads.add(t);
        }

        for (Thread t : threads)
            t.join();
    }

}
