import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Igor on 11/22/2016.
 */
public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    private TickCounter tickCounter;

    public Bank(int n, double initialBalance, TickCounter tickCounter) {

        accounts = new double[n];
        for(int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }

        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();

        this.tickCounter = tickCounter;

    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while(accounts[from] < amount)
                sufficientFunds.await();

            tickCounter.increment();
//            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
//            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[from] += amount;
//            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double summ = 0;
            for (double a : accounts)
                summ += a;

            return summ;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
