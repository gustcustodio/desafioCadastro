package app.menu;

import app.model.entities.Pet;

import java.util.List;

public class ListAllRegisteredPets {
    public static void listAllRegisteredPets() {
        List<Pet> allRegisteredPets = PetSearch.loadSavedPets();

        if (allRegisteredPets.isEmpty()) {
            System.out.println("\nNão existe nenhum pet cadastrado");
        } else {
            System.out.println("\nPets cadastrados:");
            allRegisteredPets.forEach(System.out::println);
        }
    }
}
