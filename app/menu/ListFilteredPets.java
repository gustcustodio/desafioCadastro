package app.menu;

import app.model.entities.Pet;

import java.util.List;

public class ListFilteredPets {
    public static void listFilteredPets() {
        List<Pet> filteredPets = PetSearch.executeSearch();

        if (filteredPets.isEmpty()) {
            System.out.println("\nNenhum pet encontrado com os critérios informados.");
        } else {
            System.out.println("\nPets encontrados:");
            filteredPets.forEach(System.out::println);
        }
    }
}
