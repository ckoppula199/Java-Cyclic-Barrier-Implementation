import java.lang.Runnable;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
public interface CyclicBarrierInterface {
    void await() throws InterruptedException,
            BrokenBarrierException;
    void await(long timeout, TimeUnit timeUnit) throws
            InterruptedException, BrokenBarrierException, TimeoutException;
    int getNumberWaiting();
    int getParties();
    boolean isBroken();
    void reset();
}
