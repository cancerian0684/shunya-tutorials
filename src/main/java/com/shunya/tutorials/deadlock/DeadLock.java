package com.shunya.tutorials.deadlock;

public class DeadLock {

    final String resource1 = "Resource1";
    final String resource2 = "Resource2";

    public void thread1Work() {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (resource1) {
                    synchronized (resource2) {
                        System.out.println(resource1 + resource2);
                    }
                }
            }
        });
        t1.start();
    }

    public void thread2Work() {
        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (resource2) {
                    synchronized (resource1) {
                        System.out.println(resource1 + resource2);
                    }
                }
            }
        });
        t2.start();
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.thread1Work();
        deadLock.thread2Work();
    }
}
