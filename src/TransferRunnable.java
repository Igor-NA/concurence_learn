/**
 * Created by Igor on 11/22/2016.
 */
public class TransferRunnable implements Runnable {
    private final Bank bank;
    private final int fromAccount;
    private final double maxAccount;
    private int DELAY = 10;

    public TransferRunnable(Bank b, int from, double max) {
        bank = b;
        fromAccount = from;
        maxAccount = max;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            while(true) {
                int toAccount = (int)(bank.size() * Math.random());
                double amount = maxAccount * Math.random();

                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int)(DELAY * Math.random()));

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
