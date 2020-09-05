package com.java.services;

import com.java.models.Section;
import com.java.models.Store;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SectionService {
    public static void add(final String FILE_NAME, Section section, String storeName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName,storeList);
        if(store == null) return;
        if(store.getSections() == null) store.setSections(new HashSet<>());

        if(searchSection(section.getName(),store.getSections()) != null){
            System.out.println("Section already exist!");
            return;
        }
        store.getSections().add(section);

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static void update(final String FILE_NAME, Section section, String storeName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName,storeList);
        if(store == null) return;

        Section searchedSection = searchSection(section.getName(),store.getSections());
        if(searchedSection == null) return;

        searchedSection.setId(section.getId());
        searchedSection.setName(section.getName());

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static void delete(final String FILE_NAME, String sectionName, String storeName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName,storeList);
        if(store == null) return;

        Section searchedSection = searchSection(sectionName,store.getSections());
        if(searchedSection == null) return;

        store.getSections().remove(searchedSection);

        UtilService.writeInXML(FILE_NAME,storeList);
    }

    public static Section searchSection(String sectionName, Set<Section> sectionList){
        try{
            return sectionList.stream().filter(section -> section.getName().compareTo(sectionName) == 0).findFirst().get();
        }catch(NoSuchElementException exception){
            System.out.println("Could not find the store with the specified name;");
            exception.printStackTrace();
        }
        return null;
    }
}
