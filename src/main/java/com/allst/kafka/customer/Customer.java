package com.allst.kafka.customer;

/**
 * 定义一个简单的客户类
 * @author June
 * 2018-07-29
 * @version 1.0
 */
public class Customer {
    private int customerID;
    private String customerName;

    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public int getID() {
        return customerID;
    }

    public String getName() {
        return customerName;
    }
}
