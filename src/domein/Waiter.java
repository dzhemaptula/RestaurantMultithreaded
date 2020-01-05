package domein;

import java.security.SecureRandom;

public class Waiter implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    private final String naam;
    Restaurant restaurant;

    public Waiter(Restaurant restaurant, String naam){
        this.restaurant = restaurant;
        this.naam = naam;
    }

    @Override
    public void run() {
            while (true) {

                try {
                    Order order = restaurant.takeOrder();
                    System.out.printf("Waiter %s is bringing %s%n", naam, order);
                    Thread.sleep(generator.nextInt(2000));
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
    }
}
