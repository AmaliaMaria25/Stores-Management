package com.java.services;

import com.java.exceptions.InvalidNumericInputException;
import com.java.models.Store;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UtilService {
    public static String readValue() {
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        try {
            final String s;
            s = reader.readLine();
            return s;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean isNumeric(final String value) throws InvalidNumericInputException {
        if (value == null) {
            return false;
        }
        try {
            final int numeric = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumericInputException();
        }
        return true;
    }

    public static void createNewStore() {
        System.out.println("Enter the name of the new store: ");
        final String name = readValue();
        Store store = new Store(1, name, null);
        System.out.println("The store " + store.getName() + " was successfully created.");
    }
}
