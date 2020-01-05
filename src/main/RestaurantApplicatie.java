package main;

import domein.Waiter;
import domein.Chef;
import domein.Order;
import domein.Restaurant;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class RestaurantApplicatie
{

    public static void main(String[] args)
    {
        BlockingQueue<Order> sharedQueue = new LinkedBlockingDeque<>();
        ExecutorService application = 
                Executors.newFixedThreadPool(6);
        Restaurant restaurant = new Restaurant(sharedQueue);
        Waiter waiter1 = new Waiter(restaurant, "Sofie");
        Waiter waiter2 = new Waiter(restaurant, "Hendrik");
        Chef chef = new Chef(restaurant, "Stef");
        Chef chef2 = new Chef(restaurant, "Dz");
        Chef chef3 = new Chef(restaurant, "Aa");
        Chef chef4 = new Chef(restaurant, "WWWWW");

        application.execute(waiter1);
        application.execute(waiter2);
        application.execute(chef);
        application.execute(chef2);

        application.shutdown();
    }

}
