package ru.ekuchin.multithreading;

public class MyThread implements Runnable {

    @Override
    public void run() {
        doJob(2000);
    }

    public static void doJob(long latency ){
        for(int i=0;i<5;i++){
            System.out.println("Привет из потока "+Thread.currentThread().getName());
            long time = (long) (Math.random()*latency);
            System.out.println(time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
