package com.java.services;

import com.java.Main;
import com.java.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StoreService {
    public static void add(final String FILE_NAME, Store store, List<Store> storeList) {
        if(storeList == null) storeList = new ArrayList<>();

        if(searchStore(store.getName(),storeList).isPresent()){
            System.out.println("Store already exist!");
            return;
        }
        storeList.add(store);
        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static void update(final String FILE_NAME,String storeName, Store store, List<Store> storeList){
        Optional<Store> searchedStore = searchStore(storeName,storeList);
        if(!searchedStore.isPresent()) return;

        searchedStore.get().setId(store.getId());
        searchedStore.get().setName(store.getName());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static void delete(final String FILE_NAME, String name, List<Store> storeList){
        Optional<Store> searchedStore = searchStore(name,storeList);
        if(!searchedStore.isPresent()) return;

        storeList.remove(searchedStore.get());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static Optional<Store> searchStore(String name, List<Store> storeList) {
            return storeList.stream()
                    .filter(item -> item.getName().equals(0))
                    .findFirst();
    }

    public static void createStore(){
        System.out.println("Choose an unique deposit name");
        String storeName = UtilService.getScanner().next();
        add(Main.getFileName(),new Store(0,storeName),Main.getStores());
    }

    public static String readStore(){
        String chosenStore = "";
        System.out.println("Type the wanted storage:");
        for(Store store: Main.getStores()){
            System.out.print(store.getName()+"\t");
        }

        chosenStore = UtilService.getScanner().next();
        return chosenStore;
    }

    public static void editStore(){
        String newData = "";
        String chosenStore = readStore();

        System.out.println("Choose a new name:");
        newData = UtilService.getScanner().next();

        update(Main.getFileName(),chosenStore,new Store(0,newData),Main.getStores());
    }

    public static void deleteStore(){
        String chosenStore = readStore();
        delete(Main.getFileName(),chosenStore,Main.getStores());
    }

    public static void display(List<Store> storeList){
        storeList.forEach((store) -> System.out.println(store.getName()));
    }

    public static void displayStores(){
        display(Main.getStores());
    }
}
