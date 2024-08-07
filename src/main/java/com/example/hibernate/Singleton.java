package com.example.hibernate;

public class Singleton {

    private Singleton() {
    }
    private static Singleton instance ;

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
