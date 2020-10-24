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
    private static void init(){
        stores = UtilService.getAllData(FILE_NAME);
        if(stores == null) stores = new ArrayList<>();

    }

/*    private static void addStuff(){
        StoreService.add(FILE_NAME,new Store(1,"Store1"),stores);
        StoreService.add(FILE_NAME,new Store(2,"Store2"),stores);
        SectionService.add(FILE_NAME,new Section(1,"Section1"),"Store1",stores);
        ProductService.add(FILE_NAME,"Store1","Section1",new Product(1,"Product1"),stores);

        System.out.println(stores);
    }*/

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
