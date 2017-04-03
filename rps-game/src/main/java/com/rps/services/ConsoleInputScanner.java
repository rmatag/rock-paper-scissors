package com.rps.services;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputScanner {
    private static final int FIRST_CORRECT_OPTION = 1;
    private static final int LAST_CORRECT_OPTION = 4;

    private InputStream in;

    public ConsoleInputScanner() {
        this.in = System.in;
    }

    public ConsoleInputScanner(InputStream in) {
        this.in = in;
    }

    public int getOptionFromUser() {
        boolean correctSelectedOption = false;
        int option = -1;

        try {

            while(!correctSelectedOption) {
                Scanner input = new Scanner(this.in);

                System.out.println("\n\nChoose one Game Mode");
                System.out.println("-------------------------\n");
                System.out.println("1 - FAIR");
                System.out.println("2 - UNFAIR");
                System.out.println("3 - REMOTE");
                System.out.println("4 - QUIT");


                option = input.nextInt();
                correctSelectedOption = option >= FIRST_CORRECT_OPTION && option <= LAST_CORRECT_OPTION;
            }
        } catch (InputMismatchException e) {
            throw e;
        }

        return option;

    }
}
