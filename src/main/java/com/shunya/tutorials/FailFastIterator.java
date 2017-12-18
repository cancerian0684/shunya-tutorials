package com.shunya.tutorials;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastIterator {
    public static void main(String args[]) {
        failSafeIterator();
    }

    private static void failFastException() {
        List<Integer> failFastList = new ArrayList<>();
        failFastList.add(10);
        failFastList.add(20);
        failFastList.add(30);
        failFastList.add(40);
        int indexFlag = 0;
        Iterator it = failFastList.iterator();
        while (it.hasNext()) {
            if (++indexFlag == 2) {
                failFastList.remove(indexFlag);
            }
            System.out.println(it.next());
        }
    }

    private static void failFastProperWay() {
        List<Integer> failFastList = new ArrayList<>();
        failFastList.add(10);
        failFastList.add(20);
        failFastList.add(30);
        failFastList.add(40);
        int indexFlag = 0;
        Iterator it = failFastList.iterator();
        while (it.hasNext()) {
            if (++indexFlag == 2) {
                it.remove();
            }
            System.out.println(it.next());
        }
    }

    private static void failSafeIterator() {
        List<Integer> failSafeList = new CopyOnWriteArrayList<>();
        failSafeList.add(10);
        failSafeList.add(20);
        failSafeList.add(30);
        failSafeList.add(40);
        int indexFlag = 0;
        Iterator it = failSafeList.iterator();
        while (it.hasNext()) {
            if (++indexFlag == 2) {
                failSafeList.remove(indexFlag);
            }
            System.out.print(it.next()+", ");
        }
    }
}