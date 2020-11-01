package com.java;

import com.java.models.Section;
import com.java.models.Store;
import com.java.models.Product;
import com.java.services.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String FILE_NAME = "section";
    private static List<Store> stores;

    public static void main(String[] args) {
        init();
        MenuService.mainMenu();

        String FILE_NAME = args[0];
        UtilService.archiveAsZip();
    }

    private static void init() {
        stores = UtilService.getAllData(FILE_NAME + ".xml");
        if (stores == null) stores = new ArrayList<>();

    }


    public static List<Store> getStores() {
        return stores;
    }

    public static void setStores(List<Store> stores) {
        Main.stores = stores;
    }

    public static String getFileName() {
        return FILE_NAME;
    }
}
