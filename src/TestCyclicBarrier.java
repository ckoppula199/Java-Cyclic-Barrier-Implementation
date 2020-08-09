import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.sleep;

public class TestCyclicBarrier {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrrierEvent());

        MyRunnable myRunnable1=new MyRunnable(cyclicBarrier);

        //Create and start 3 threads
        new Thread(myRunnable1,"Thread-1").start();
        new Thread(myRunnable1,"Thread-2").start();
        sleep(15);
        new Thread(myRunnable1,"Thread-3").start();

        sleep(1000);

        new Thread(myRunnable1,"Thread-4").start();
        new Thread(myRunnable1,"Thread-5").start();
        new Thread(myRunnable1,"Thread-6").start();

    }
}



class MyRunnable implements Runnable {

    private CyclicBarrier cyclicBarrierCustom;

    MyRunnable(CyclicBarrier cyclicBarrierCustom) {
        this.cyclicBarrierCustom = cyclicBarrierCustom;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() +
                " is waiting for all other threads to reach common barrier point");

        try {
            cyclicBarrierCustom.await(1, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException bbe) {
            System.out.println(Thread.currentThread().getName() + " Broken Barrier");
            return;
        } catch (TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + " TIMEDOUT ");
            return;
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " INTERRUPTED");
        }
        System.out.println("As all threads have reached common barrier point "
                + Thread.currentThread().getName() +
                " has been released");
    }
}







class CyclicBarrrierEvent implements Runnable {
    public void run() {
        System.out.println("Barrier runnable has executed");
    }
}