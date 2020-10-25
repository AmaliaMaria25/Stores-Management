package com.java.services;

import com.java.Main;
import com.java.models.Product;
import com.java.models.Section;
import com.java.models.Store;


//import javax.rmi.CORBA.Util;

import java.util.*;

public class ProductService {
    public static void add(final String FILE_NAME, String storeName, String sectionName, Product product, List<Store> storeList){
        Optional<Store> store = StoreService.searchStore(storeName,storeList);
        if(!store.isPresent()) return;

        Section section = SectionService.searchSection(sectionName,store.get().getSections());
        if(section == null) return;

        if(section.getProducts() == null) section.setProducts(new HashSet<>());

        if(searchProduct(product.getName(),section.getProducts()) != null){
            System.out.println("The product already exist!");
            return;
        }

        section.getProducts().add(product);

        UtilService.writeInXML(FILE_NAME.concat(".xml"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);

    }

    public static void update(final String FILE_NAME, String storeName, String sectionName, String productName, Product product, List<Store> storeList){
        Optional<Store> searchedStore = StoreService.searchStore(storeName,storeList);
        if(!searchedStore.isPresent()) return;

        Section searchedSection = SectionService.searchSection(sectionName,searchedStore.get().getSections());
        if(searchedSection == null) return;

        Product searchedProduct = searchProduct(productName,searchedSection.getProducts());
        if(searchedProduct == null) return;

        searchedProduct.setId(product.getId());
        searchedProduct.setDescription(product.getDescription());
        searchedProduct.setName(product.getName());
        searchedProduct.setPrice(product.getPrice());

        UtilService.writeInXML(FILE_NAME.concat(".xml"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static void delete(final String FILE_NAME, String storeName, String sectionName, String productName, List<Store> storeList){
        Optional<Store> searchedStore = StoreService.searchStore(storeName,storeList);
        if(!searchedStore.isPresent()) return;

        Section searchedSection = SectionService.searchSection(sectionName,searchedStore.get().getSections());
        if(searchedSection == null) return;

        Product searchedProduct = searchProduct(productName,searchedSection.getProducts());
        if(searchedProduct == null) return;

        searchedSection.getProducts().remove(searchedProduct);

        UtilService.writeInXML(FILE_NAME.concat(".xml"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static Product searchProduct(String productName, Set<Product> productSet) {
        try {
            return productSet.stream().filter(item -> item.getName().compareTo(productName) == 0).findFirst().get();
        } catch (NoSuchElementException exception) {
            //System.out.println("Could not find the product with the specified name;");
        }
        return null;
    }

    public static void createProduct(){
        Optional<Store> store;
        Section section;
        String chosenStore = "";
        String chosenSection="";
        String productName="";

        chosenStore = StoreService.readStore();
        store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

       chosenSection = SectionService.readSection(store.get());
        section = SectionService.searchSection(chosenSection,store.get().getSections());
        if(section == null) return;

        System.out.println("Choose an unique product name for this store and section: ");
        productName=UtilService.getScanner().next();

        add(Main.getFileName(),chosenStore,chosenSection,new Product(0,productName),Main.getStores());
    }

    public static String readProduct(Section section){
        String chosenProduct = "";
        System.out.println("Type product name: ");
        for(Product product:section.getProducts()){
            System.out.println(product.getName()+"\t");
        }
        chosenProduct = UtilService.getScanner().next();
        return chosenProduct;
    }



    public static void editProduct(){
        String newData = "";
        String chosenSection = "";
        String chosenProduct = "";
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

        chosenSection = SectionService.readSection(store.get());
        Section section = SectionService.searchSection(chosenSection,store.get().getSections());
        if(section == null) return;

        chosenProduct = readProduct(section);

        System.out.println("Choose a new name:");
        newData =  UtilService.getScanner().next();

        update(Main.getFileName(),chosenStore,chosenSection,chosenProduct,new Product(0,newData),Main.getStores());
    }

    public static void deleteProduct(){
        String chosenProduct = "";
        String chosenSection = "";
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

        chosenSection = SectionService.readSection(store.get());
        Section section = SectionService.searchSection(chosenSection,store.get().getSections());
        if(section == null) return;

        chosenProduct = readProduct(section);

        delete(Main.getFileName(),chosenStore,chosenSection,chosenProduct,Main.getStores());
    }

    public static void display(Section section){
        try {
            section.getProducts().forEach(System.out::println);
        }catch(NullPointerException nullPointerException){
            System.out.println("Couldn't find any products of this section");
        }
    }

    public static void displayProducts(){
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

        String chosenSection = SectionService.readSection(store.get());
        Section section = SectionService.searchSection(chosenSection,store.get().getSections());
        if(section == null) return;

        display(section);
    }
}
