package com.allst.mq.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 队列生产者
 *
 * @author June
 * @since 2021年08月
 */
public class Producer implements Runnable {

    private final BlockingQueue<Book> queue;

    public Producer(BlockingQueue<Book> queue) {
        this.queue = queue;
    }

    private Integer index = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                if (queue.remainingCapacity() <= 0) {
                    System.out.println("大量的书本已经上架了，大家快来买。。。");
                } else {
                    Book book = new Book();
                    book.setName("Java");
                    book.setId(++index);
                    System.out.println("正在印刷第 " + book.getId() + " 号书本");
                    queue.put(book);
                    System.out.println("已经印刷了 " + queue.size() + " 本书");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
