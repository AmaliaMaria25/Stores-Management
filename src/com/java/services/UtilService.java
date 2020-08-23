package com.java.services;

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

    public static boolean isNumeric(final String value) {
        if (value == null) {
            return false;
        }
        try {
            final int numeric = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void mainMenuSelection() {
        System.out.println("Choose an option:");
        System.out.println("1 - Load stores");
        System.out.println("2 - Create new store");
        System.out.println("3 - Create new section");
        System.out.println("4 - Create new product");

        final String selected = readValue();
        if(isNumeric(selected))
        {
            final int selectedValue = Integer.parseInt(selected);
            if(selectedValue == 1) {
                loadStores();
            }
            else if(selectedValue == 2) {
                createNewStore();
            }
        }
    }

    public static void loadStores() {

    }

    public static void createNewStore() {
        System.out.println("Enter the name of the new store: ");
        final String name = readValue();
        Store store = new Store(1, name, null);
        System.out.println("The store " + store.getName() + " was successfully created.");
    }
}
