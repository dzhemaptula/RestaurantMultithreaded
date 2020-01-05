package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant{
    Lock accessLock = new ReentrantLock();
    Condition canBringFood = accessLock.newCondition();
    BlockingQueue<Order> sharedOrders;
    boolean orderAvailable = false;
    int ordersMade = 0;
    int  ordersNeeded = 10;

    public Restaurant(BlockingQueue<Order> sharedOrders){
        this.sharedOrders=sharedOrders;
    }

    public void placeOrder(Order order) {
        accessLock.lock();
        if(this.hasFood()){
            try {
                ordersMade++;
                orderAvailable=true;
                sharedOrders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            canBringFood.signal();
        }
        accessLock.unlock();
    }

    public boolean hasFood() {
        return ordersMade<ordersNeeded;
    }

    public Order takeOrder() {
        accessLock.lock();
        try {
            while (!orderAvailable) {
                System.out.println("No orders available. Waiting...");
                canBringFood.await();
            }
            Order order = sharedOrders.take();
            if(sharedOrders.isEmpty())
                orderAvailable=false;
            return order;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            accessLock.unlock();
        }
        throw new IllegalArgumentException("Something went wrong in haalOrderOp");
    }

    public Object getOrderNumber() {
        return sharedOrders.size();
    }
}
