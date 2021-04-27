package src.cashregister;

import java.util.ArrayDeque;
import java.util.Queue;

public class CashRegistrer {

    private int cashRegisterId;

    public CashRegistrer(int cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    public void useRegister(int clientServiceTime) throws InterruptedException {
        Thread.sleep(clientServiceTime);
    }
}
