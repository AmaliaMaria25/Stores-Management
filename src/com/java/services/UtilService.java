package com.java.services;

import com.java.models.Store;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

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

    public static void writeInXML(final String FILE_NAME, List<Store> storeList){
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
             XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)) {

            xmlEncoder.writeObject(storeList);

            xmlEncoder.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();

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
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
