package src.cashregister;

import java.util.function.DoubleToIntFunction;

public class Client extends Thread {
    private String clientId;
    private int clientServiceTime;
    private Manager manager;

    public Client(String clientId, int clientServiceTime, Manager manager) {
        this.clientId = clientId;
        this.clientServiceTime = clientServiceTime;
        this.manager = manager;
    }

    public String getClientId() {
        return clientId;
    }

    public int getClientServiceTime() {
        return clientServiceTime;
    }

    @Override
    public void run() {
        CashRegistrer register = null;

        for (boolean wait = true; wait; ) {
            wait = false;

            try {
                register = manager.getRegister(clientServiceTime);

                System.out.println(clientId + "на кассе");
                register.useRegister(clientServiceTime);
            }
            catch (Exception e) {
                System.out.println("Клиент " + clientId + " ждёт -> ");
                wait = true;
            }
            finally {
                if (register != null) {
                    System.out.println(clientId + "сделал заказ");
                    manager.retrieveRegister(register);
                    wait = false;
                }
            }
        }
    }
}
