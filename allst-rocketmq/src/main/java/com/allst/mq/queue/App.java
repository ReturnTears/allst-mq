package com.allst.mq.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author June
 * @since 2021年08月
 */
public class App {
    public static void main(String[] args) {
        BlockingQueue<Book> queue = new ArrayBlockingQueue<>(20);

        new Thread(new Producer(queue)).start();

        new Thread(new Consumer(queue)).start();
    }
}
