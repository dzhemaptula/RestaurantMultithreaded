package domein;

import java.security.SecureRandom;

public class Waiter implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    private final String name;
    Restaurant restaurant;

    public Waiter(Restaurant restaurant, String name){
        this.restaurant = restaurant;
        this.name = name;
    }

    @Override
    public void run() {
            while (true) {

                try {
                    Order order = restaurant.takeOrder();
                    displayState(String.format("Waiter %s is bringing %s%n", name, order));
                    Thread.sleep(generator.nextInt(2000));
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
    }
    private synchronized void displayState(String operation){
        System.out.printf("%s", operation);
    }
}
