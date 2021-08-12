package com.allst.mq.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 队列消费者
 *
 * @author June
 * @since 2021年08月
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Book> queue;

    public Consumer(BlockingQueue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("正在准备买书本");
                final Book kouZhao = queue.take();
                System.out.println("买到了书本: " + kouZhao.getId()  + ", " + kouZhao.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
