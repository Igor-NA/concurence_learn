import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by SEkaterina on 22.11.2016.
 */
public class TickCounter extends TimerTask {
    private AtomicLong tick = new AtomicLong(0L);

    public void increment() {
        tick.incrementAndGet();
    }

    @Override
    public void run() {
        System.out.printf("Current tick is: %d\n", tick.getAndSet(0L));
    }
}
