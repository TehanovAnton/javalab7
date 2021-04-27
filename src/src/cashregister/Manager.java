package src.cashregister;

import javax.security.auth.callback.CallbackHandler;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Manager {
    private static int cashRegisterAmount = 2;
    private Semaphore semaphore = new Semaphore(cashRegisterAmount, true);
    private Queue<CashRegistrer> registers;

    public Manager(Queue<CashRegistrer> registers) {
        this.registers = registers;
    }

    public  CashRegistrer getRegister(int maxWaitTime) throws Exception {
        if (semaphore.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
            return registers.poll();
        }

        throw new Exception(" ждёт ");
    }

    public void retrieveRegister(CashRegistrer register) {
        registers.add(register);
        semaphore.release();
    }
}
