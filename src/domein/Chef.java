package domein;

import java.security.SecureRandom;

public class Chef implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    Restaurant restaurant;
    private final String naam;
    public Chef(Restaurant restaurant, String naam){
        this.restaurant=restaurant;
        this.naam=naam;
    }


    @Override
    public void run() {
        while(restaurant.hasFood()){
            try{
                Order order = new Order();
                Thread.sleep(generator.nextInt(2000));
                restaurant.placeOrder(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
