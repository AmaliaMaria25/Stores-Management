package com.java.services;

import com.java.exceptions.InvalidNumericInputException;
import com.java.models.Store;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UtilService {
    private static Scanner scanner = new Scanner(System.in);
    public static String readValue() {
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        try {
            return reader.readLine();
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

    public static void writeInCSV(final String FILE_NAME, List<Store> storeList) {

        File file = new File(FILE_NAME);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Store");
            bw.newLine();
            for(int i=0;i<storeList.size();i++)
            {
                bw.write(storeList.get(i).getName());
                bw.newLine();
            }
            bw.write("\n");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void archiveAsZip() {
        try {
            String sourceFile = "section.csv";
            File file = new File(sourceFile);

            FileOutputStream fileoutput = new FileOutputStream("compressed.zip");
            ZipOutputStream zipoutput = new ZipOutputStream(fileoutput);

            zipoutput.putNextEntry(new ZipEntry(file.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get("section.csv"));
            zipoutput.write(bytes, 0, bytes.length);
            zipoutput.closeEntry();
            zipoutput.close();

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", "section.csv");
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }

    public static void displayStock(List<Store> stores){
        stores.forEach(store->{
            System.out.println("[Store]: "+store.getName());
            if(store.getSections() != null)
                store.getSections().forEach(section -> {
                    System.out.println("\t[Section]: "+section.getName());
                    if(section.getProducts() != null)
                        section.getProducts().forEach(product -> {
                            System.out.println("\t\t[Product]: "+product.getName());
                        });
                });
        });
    }
}



