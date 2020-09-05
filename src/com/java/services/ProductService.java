package com.java.services;

import com.java.models.Product;
import com.java.models.Section;
import com.java.models.Store;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class ProductService {
    public static void add(final String FILE_NAME, String storeName, String sectionName, Product product, List<Store> storeList){
        Store store = StoreService.searchStore(storeName,storeList);
        if(store == null) return;

        Section section = SectionService.searchSection(sectionName,store.getSections());
        if(section == null) return;

        if(section.getProducts() == null) section.setProducts(new HashSet<>());

        if(searchProduct(product.getName(),section.getProducts()) != null){
            System.out.println("The product already exist!");
            return;
        }

        section.getProducts().add(product);

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static void update(final String FILE_NAME, String storeName, String sectionName, Product product, List<Store> storeList){
        Store searchedStore = StoreService.searchStore(storeName,storeList);
        if(searchedStore == null) return;

        Section searchedSection = SectionService.searchSection(sectionName,searchedStore.getSections());
        if(searchedSection == null) return;

        Product searchedProduct = searchProduct(product.getName(),searchedSection.getProducts());
        if(searchedProduct == null) return;

        searchedProduct.setId(product.getId());
        searchedProduct.setDescription(product.getDescription());
        searchedProduct.setName(product.getName());
        searchedProduct.setPrice(product.getPrice());

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static void delete(final String FILE_NAME, String storeName, String sectionName, String productName, List<Store> storeList){
        Store searchedStore = StoreService.searchStore(storeName,storeList);
        if(searchedStore == null) return;

        Section searchedSection = SectionService.searchSection(sectionName,searchedStore.getSections());
        if(searchedSection == null) return;

        Product searchedProduct = searchProduct(productName,searchedSection.getProducts());
        if(searchedProduct == null) return;

        searchedSection.getProducts().remove(searchedProduct);

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static Product searchProduct(String productName, Set<Product> productSet){
        try{
            return productSet.stream().filter(item-> item.getName().compareTo(productName) == 0).findFirst().get();
        }catch(NoSuchElementException exception){
            System.out.println("Could not find the store with the specified name;");
            exception.printStackTrace();
        }
        return null;
    }
}
