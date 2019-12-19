package com.boot.bvserver.service.impl;

public class Threads implements  Runnable{

    private static int num = 100;

    @Override
    public synchronized void run() {
        System.out.println(num--);
    }
}
