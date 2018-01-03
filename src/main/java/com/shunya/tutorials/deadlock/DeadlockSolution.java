package com.shunya.tutorials.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {

    final Lock lock1 = new ReentrantLock();
    final Lock lock2 = new ReentrantLock();

    public void deadLockDemo() {
        Thread t1 = new Thread(new RunnableA());
        t1.setName("Thread A");
        t1.start();


        Thread t2 = new Thread(new RunnableB());
        t2.setName("Thread B");
        t2.start();
    }

    public static void main(String[] args) {
        new DeadlockSolution().deadLockDemo();
    }

    class RunnableA implements Runnable {

        public void run() {
            boolean done = false;
            while (!done) {
                if (lock1.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Got lockObject1. Trying for lockObject2");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + ": Got lockObject2.");
                                done = true;
                            } finally {
                                lock2.unlock();
                            }
                        }

                    } finally {
                        lock1.unlock();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class RunnableB implements Runnable {

        public void run() {
            boolean done = false;
            while (!done) {
                if (lock2.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Got lockObject2. Trying for lockObject1");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + ": Got lockObject1.");
                                done = true;
                            } finally {
                                lock1.unlock();
                            }
                        }

                    } finally {
                        lock2.unlock();
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
