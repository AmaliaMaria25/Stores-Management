package com.java.services;

import com.java.Main;
import com.java.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreService {
    public static void add(final String FILE_NAME, Store store, List<Store> storeList) {
        storeList.add(store);

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
        System.out.println("[Store] " + store.getName() + " was added successfully");
    }

    public static void update(final String FILE_NAME, String storeName, Store store, List<Store> storeList) {
        Optional<Store> searchedStore = searchStore(storeName, storeList);

        searchedStore.get().setId(store.getId());
        searchedStore.get().setName(store.getName());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);

        System.out.println("[Store] " + storeName + " is now called " + store.getName());
    }

    public static void delete(final String FILE_NAME, String name, List<Store> storeList) {
        Optional<Store> searchedStore = searchStore(name, storeList);

        storeList.remove(searchedStore.get());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);

        System.out.println("[Store] " + name + " was deleted");
    }

    public static Optional<Store> searchStore(String name, List<Store> storeList) {
        return storeList.stream().filter(item -> item.getName().compareTo(name) == 0).findFirst();
    }

    public static void createStore() {
        if (Main.getStores() == null) Main.setStores(new ArrayList<>());

        System.out.println("Choose an unique deposit name");
        String storeName = UtilService.getScanner().next();

        if (searchStore(storeName, Main.getStores()).isPresent()) {
            System.out.println("Store already exist!");
            return;
        }

        add(Main.getFileName(), new Store(0, storeName), Main.getStores());
    }

    public static String readStore() {
        if (Main.getStores() == null || Main.getStores().isEmpty()) {
            System.out.println("No stores");
            Main.setStores(new ArrayList<>());
            return "";
        }

        System.out.println("Type the wanted storage:");
        for (Store store : Main.getStores()) {
            System.out.print(store.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }

    public static void editStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore, Main.getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(Main.getFileName(), chosenStore, new Store(0, newData), Main.getStores());
    }

    public static void deleteStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore, Main.getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        delete(Main.getFileName(), chosenStore, Main.getStores());
    }

    public static void display(List<Store> storeList) {
        if (storeList == null || storeList.isEmpty()) {
            System.out.println("No stores");
            return;
        }

        storeList.forEach((store) -> System.out.println(store.getName()));
    }

    public static void displayStores() {
        display(Main.getStores());
    }
}
