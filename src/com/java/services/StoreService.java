package com.java.services;

import com.java.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService {
    public static void add(final String FILE_NAME, Store store, List<Store> storeList) {
        if(storeList == null) storeList = new ArrayList<>();

        if(searchStore(store.getName(),storeList) != null){
            System.out.println("Store already exist!");
            return;
        }
        storeList.add(store);
        UtilService.writeInXML(FILE_NAME, storeList);
    }

    public static void update(final String FILE_NAME, Store store, List<Store> storeList){
        Store searchedStore = searchStore(store.getName(),storeList);
        if(searchedStore == null) return;

        searchedStore.setId(store.getId());
        searchedStore.setName(store.getName());

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static void delete(final String FILE_NAME, String name, List<Store> storeList){
        Store searchedStore = searchStore(name,storeList);
        if(searchedStore == null) return;

        storeList.remove(searchedStore);

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static Store searchStore(String name,List<Store> storeList){
        try {
            return storeList.stream().filter(item -> item.getName().compareTo(name) == 0).findFirst().get();
        }catch(NoSuchElementException exception){
            System.out.println("Could not find the store with the specified name;");
            exception.printStackTrace();
        }
        return null;
    }

}
