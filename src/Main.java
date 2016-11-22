import java.util.Timer;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) throws InterruptedException {
        TickCounter tickCounter = new TickCounter();
        Timer timer = new Timer();
        timer.schedule(tickCounter, 0, 1000);

        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE, tickCounter);
        Thread.sleep(1000);


        for(int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i , INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
