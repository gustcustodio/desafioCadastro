package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static app.menu.MainMenu.sc;

public class ChangeRegisteredPet {
    public static void executeEdition() {
        List<Pet> filteredPets = PetSearch.executeSearch();

        if (filteredPets.isEmpty()) {
            System.out.println("\nNenhum pet foi encontrado com os critérios informados");
            return;
        }

        System.out.println("\nPets encontrados:");
        for (int i = 0; i < filteredPets.size(); i++) {
            System.out.println(i + 1 + ". " + filteredPets.get(i));
        }

        int petIndex = askForPetIndex(filteredPets.size());
        Pet petToEdit = filteredPets.get(petIndex - 1);

        System.out.println("\nVocê selecionou o pet: " + petToEdit.getPetName());

        System.out.println
                ("\n⚠️️ Caso queira manter o valor atual, basta pressionar Enter sem digitar nada. ⚠️️");

        editPetData(petToEdit);

        savePetChanges(petToEdit, petToEdit.getFilePath());

        System.out.println("\n✅ Pet atualizado com sucesso!");
    }

    private static int askForPetIndex(int maxIndex) {
        while (true) {
            try {
                System.out.print("\nInforme o número do pet desejado para editar: ");
                int index = Integer.parseInt(sc.nextLine().trim());

                if (index < 1 || index > maxIndex) {
                    throw new IllegalArgumentException("⚠️️ Número inválido");
                }
                return index;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Entrada inválida. Tente novamente.");
            }
        }
    }

    private static void editPetData(Pet pet) {
        System.out.print("\nNovo nome do pet (atual: " + pet.getPetName() + "): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) pet.setPetName(newName);

        System.out.print("Nova idade do pet (atual: " + pet.getPetAge() + "): ");
        String newAge = sc.nextLine().trim();
        if (!newAge.isEmpty()) pet.setPetAge(newAge);

        System.out.print("Novo peso do pet (atual: " + pet.getPetWeight() + "): ");
        String newWeight = sc.nextLine().trim();
        if (!newWeight.isEmpty()) pet.setPetWeight(newWeight);

        System.out.print("Nova raça do pet (atual: " + pet.getPetBreed() + "): ");
        String newBreed = sc.nextLine().trim();
        if (!newBreed.isEmpty()) pet.setPetBreed(newBreed);

        editAddress(pet.getPetAddress());
    }

    private static void editAddress(Address address) {
        System.out.print("Nova cidade do pet (atual: " + address.getCityOfAdress() + "): ");
        String newCity = sc.nextLine().trim();
        if (!newCity.isEmpty()) address.setCityOfAdress(newCity);

        System.out.print("Nova rua do pet (atual: " + address.getStreetOfAdress() + "): ");
        String newStreet = sc.nextLine().trim();
        if (!newStreet.isEmpty()) address.setStreetOfAdress(newStreet);

        System.out.print("Novo número do endereço do pet (atual: " + address.getNumberOfAddress() + "): ");
        String newNumber = sc.nextLine().trim();
        if (!newNumber.isEmpty()) address.setNumberOfAddress(newNumber);
    }

    private static void savePetChanges(Pet pet, Path originalPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                originalPath,
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write("1 - " + pet.getPetName());
            writer.newLine();
            writer.write("2 - " + pet.getPetType());
            writer.newLine();
            writer.write("3 - " + pet.getPetSex());
            writer.newLine();
            writer.write("4 - Rua " +
                    pet.getPetAddress().getStreetOfAdress() + ", " +
                    pet.getPetAddress().getNumberOfAddress() + ", " +
                    pet.getPetAddress().getCityOfAdress());
            writer.newLine();
            writer.write("5 - " + pet.getPetAge() + " anos");
            writer.newLine();
            writer.write("6 - " + pet.getPetWeight() + "kg");
            writer.newLine();
            writer.write("7 - " + pet.getPetBreed());

        } catch (IOException e) {
            System.out.println("⚠️️ Erro ao salvar as alterações: " + e.getMessage());
        }
    }
}
