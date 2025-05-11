package app.menu;

import app.model.entities.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static app.menu.MainMenu.sc;

public class DeleteRegisteredPet {
    public static void executeDeletion() {
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
        Pet petToDelete = filteredPets.get(petIndex - 1);

        System.out.print("\nTem certeza que deseja deletar o pet " + petToDelete.getPetName() + "? (SIM/NÃO): ");
        String confirmation = sc.nextLine().trim().toUpperCase();

        if (confirmation.equals("SIM")) {
            deletePet(petToDelete);
            System.out.println("\nPet deletado com sucesso!");
        } else {
            System.out.println("\nPet não deletado.");
        }
    }

    private static int askForPetIndex(int maxIndex) {
        while (true) {
            try {
                System.out.print("\nInforme o número do pet que deseja deletar: ");
                int index = Integer.parseInt(sc.nextLine().trim());

                if (index < 1 || index > maxIndex) {
                    throw new IllegalArgumentException("Número inválido");
                }
                return index;
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    private static void deletePet(Pet pet) {
        Path petFilePath = pet.getFilePath();
        try {
            Files.deleteIfExists(petFilePath);
        } catch (IOException e) {
            System.out.println("Erro ao tentar deletar o arquivo do pet: " + e.getMessage());
        }
    }
}
