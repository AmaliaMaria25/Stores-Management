package com.java.services;

import com.java.exceptions.InvalidNumericInputException;
import com.java.models.Store;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UtilService {
    private static Scanner scanner = new Scanner(System.in);
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

    public static void writeInXML(final String FILE_NAME, List<Store> storeList){
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
             XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)) {

            xmlEncoder.writeObject(storeList);
        } catch (Exception ex) {
        }
    }

    public static List<Store> getAllData(final String FILE_NAME){
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
             XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream)) {

            Object object;
            do {
                object = xmlDecoder.readObject();
                if (object instanceof List && !((List) object).isEmpty() && ((List)object).get(0) instanceof Store) {
                    return (List<Store>)object;
                }
            } while (object != null);

        } catch (Exception ex) {
        }
        return null;
    }

    public static Scanner getScanner() {
        return scanner;
    }

}
