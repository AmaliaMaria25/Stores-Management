package com.java.services;

import com.java.Main;
import com.java.models.Section;
import com.java.models.Store;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//import javax.rmi.CORBA.Util;

public class SectionService {
    public static void add(final String FILE_NAME, Section section, String storeName, List<Store> storeList) {
        Optional<Store> store = StoreService.searchStore(storeName, storeList);
        store.get().getSections().add(section);

        UtilService.writeInXML(FILE_NAME.concat(".csv"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
        System.out.println("[Section] " + section.getName() + " was added successfully");
    }

    public static void update(final String FILE_NAME, Section section, String storeName, String sectionName, List<Store> storeList) {
        Optional<Store> store = StoreService.searchStore(storeName, storeList);

        Optional<Section> searchedSection = searchSection(sectionName, store.get().getSections());

        searchedSection.get().setId(section.getId());
        searchedSection.get().setName(section.getName());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);

        System.out.println("[Section] " + sectionName + " is now called " + section.getName());
    }

    public static void delete(final String FILE_NAME, String sectionName, String storeName, List<Store> storeList) {
        Optional<Store> store = StoreService.searchStore(storeName, storeList);

        Optional<Section> searchedSection = searchSection(sectionName, store.get().getSections());

        store.get().getSections().remove(searchedSection.get());

        UtilService.writeInXML(FILE_NAME.concat(".xml"), storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);

        System.out.println("[Section] " + sectionName + " was deleted");
    }


    public static Optional<Section> searchSection(String sectionName, Set<Section> sectionList) {
        return sectionList.stream().filter(section -> section.getName().compareTo(sectionName) == 0).findFirst();
    }

    public static void createSection() {
        Optional<Store> searchedStore;

        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        if (!(searchedStore = StoreService.searchStore(chosenStore, Main.getStores())).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        if (searchedStore.get().getSections() == null) searchedStore.get().setSections(new HashSet<>());

        System.out.println("Choose a section name");
        String sectionName = UtilService.getScanner().next();

        if (searchSection(sectionName, searchedStore.get().getSections()).isPresent()) {
            System.out.println("Section is already existent");
            return;
        }

        add(Main.getFileName(), new Section(0, sectionName), chosenStore, Main.getStores());
    }

    public static String readSection(Store store) {
        if (store.getSections() == null || store.getSections().isEmpty()) {
            System.out.println("No sections");
            store.setSections(new HashSet<>());
            return "";
        }

        System.out.println("Type the wanted section: ");
        for (Section loopSection : store.getSections()) {
            System.out.println(loopSection.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }

    public static void editSection() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, Main.getStores());
        if (!store.isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        String chosenSection = readSection(store.get());

        if (chosenSection.equals("")) return;

        if (!searchSection(chosenSection, store.get().getSections()).isPresent()) {
            System.out.println("Section is not existent");
            return;
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(Main.getFileName(), new Section(0, newData), chosenStore, chosenSection, Main.getStores());
    }

    public static void deleteSection() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, Main.getStores());
        if (!store.isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        String chosenSection = readSection(store.get());

        if (chosenSection.equals("")) return;

        if (!searchSection(chosenSection, store.get().getSections()).isPresent()) {
            System.out.println("Section is not existent!");
            return;
        }

        delete(Main.getFileName(), chosenSection, chosenStore, Main.getStores());
    }

    public static void display(Store store) {
        try {
            store.getSections().forEach(section -> System.out.println(section.getName()));
        } catch (NullPointerException nullPointerException) {
            System.out.println("Couldn't find any sections of this storage");
        }
    }

    public static void displaySections() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        if (!StoreService.searchStore(chosenStore, Main.getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        Optional<Store> store = StoreService.searchStore(chosenStore, Main.getStores());
        display(store.get());
    }
}
