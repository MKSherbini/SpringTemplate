package com.skull;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("smt = " + System.getenv("SMT"));
        System.out.println("args = " + Arrays.toString(args));
    }
    // mvn package && java -jar target\hello-docker-1.0.jar
}
