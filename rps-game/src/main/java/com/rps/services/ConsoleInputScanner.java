package com.rps.services;

import java.util.Scanner;

public class ConsoleInputScanner {

    public int getOptionFromUser() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nChoose one Game Mode");
        System.out.println("-------------------------\n");
        System.out.println("1 - FAIR");
        System.out.println("2 - UNFAIR");
        System.out.println("3 - REMOTE");
        System.out.println("4 - QUIT");

        return input.nextInt();
    }
}
