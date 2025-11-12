package com.saurabh.demo.service;


import org.springframework.stereotype.Service;

@Service
public class MathService {

    // 1. Multiplication
    public int multiply(int a, int b) {
        return a * b;
    }

    // 2. Division
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return (double) a / b;
    }

    // 3. Table of a number
    public String generateTable(int number, int upTo) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= upTo; i++) {
            sb.append(number).append(" x ").append(i).append(" = ").append(number * i).append("\n");
        }
        return sb.toString();
    }

    // 4. Counting from 1 to n
    public String countUpTo(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(i).append(" ");
        }
        return sb.toString().trim();
    }



    public String helloworld(){
        System.out.println("hey world how are you");
        String name = "hoshiyar";
        for (char c : name.toCharArray()) {
            System.out.println(c);
        }
        return name ;
    }


    public void printButterfly(int n) {
        // Upper half
        for (int i = 1; i <= n; i++) {
            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Spaces
            int spaces = 2 * (n - i);
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        // Lower half
        for (int i = n; i >= 1; i--) {
            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Spaces
            int spaces = 2 * (n - i);
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
