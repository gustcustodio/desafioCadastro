package app.menu;

import app.model.entities.Pet;
import app.model.enums.Type;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static app.menu.MainMenu.sc;

public class SearchMenu {

    private static final Path petsFolder = Paths.get("app/petsCadastrados");

    public static void executeSearch() {
        Type petType = askPetType();
        List<Integer> criteria = askFilters();
        Map<Integer, String> criteriaValues = collectCriteriaValues(criteria);
        List<Pet> allPets = loadSavedPets();

        List<Pet> filteredPets = allPets.stream()
                .filter(p -> p.getPetType() == petType)
                .filter(p -> filterByCriteria(p, criteriaValues))
                .toList();

        if (filteredPets.isEmpty()) {
            System.out.println("\nNenhum pet encontrado com os critérios informados.");
        } else {
            System.out.println("\nPets encontrados:");
            filteredPets.forEach(System.out::println);
        }
    }

    public static void listAllRegisteredPets() {
        List<Pet> allRegisteredPets = loadSavedPets();

        if (allRegisteredPets.isEmpty()) {
            System.out.println("\nNão existe nenhum pet cadastrado");
        } else {
            System.out.println("\nPets cadastrados:");
            allRegisteredPets.forEach(System.out::println);
        }
    }

    private static Type askPetType() {
        int choice = askForNumber("""
      
                Qual tipo de animal deseja buscar?
                [1] - Cachorro
                [2] - Gato
                Digite a opção desejada:\s""", 2);
        return (choice == 1) ? Type.CACHORRO : Type.GATO;
    }

    private static List<Integer> askFilters() {
        int filters = askForNumber("\nQuantos filtros deseja usar? (1 ou 2): ", 2);
        List<Integer> criteria = new ArrayList<>();

        for (int i = 1; i <= filters; i++) {
            printOptionsMenu();
            int option = askForNumber("Informe o número do filtro desejado: ", 6);
            criteria.add(option);
        }

        return criteria;
    }

    private static Map<Integer, String> collectCriteriaValues(List<Integer> criteria) {
        Map<Integer, String> values = new HashMap<>();
        for (Integer criterion : criteria) {
            switch (criterion) {
                case 1 -> {
                    System.out.print("Nome ou sobrenome: ");
                    values.put(1, sc.nextLine().trim().toLowerCase());
                }
                case 2 -> {
                    System.out.print("Sexo (MACHO/FEMEA): ");
                    values.put(2, sc.nextLine().trim().toUpperCase());
                }
                case 3 -> {
                    System.out.print("Idade (ex: 7 anos): ");
                    String input = sc.nextLine().trim();
                    values.put(3, input.replaceAll("[^0-9]", "")); // Remove "anos" ou "mês"
                }
                case 4 -> {
                    System.out.print("Peso (ex: 10.5kg): ");
                    String input = sc.nextLine().trim();
                    values.put(4, input.replaceAll("[^0-9.]", "")); // Remove "kg"
                }
                case 5 -> {
                    System.out.print("Raça: ");
                    values.put(5, sc.nextLine().trim().toLowerCase());
                }
                case 6 -> {
                    System.out.print("Cidade: ");
                    values.put(6, sc.nextLine().trim().toLowerCase());
                }
            }
        }
        return values;
    }

    private static boolean filterByCriteria(Pet pet, Map<Integer, String> values) {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            int criterion = entry.getKey();
            String userValue = entry.getValue();

            switch (criterion) {
                case 1 -> {
                    String name = pet.getPetName().toLowerCase();
                    if (!name.contains(userValue)) return false;
                }
                case 2 -> {
                    if (!pet.getPetSex().name().equals(userValue)) return false;
                }
                case 3 -> {
                    String petAge = pet.getPetAge().replaceAll("[^0-9]", ""); // Remove "anos"
                    if (!petAge.equals(userValue)) return false;
                }
                case 4 -> {
                    String petWeight = pet.getPetWeight().replaceAll("[^0-9.]", ""); // Remove "kg"
                    if (!petWeight.equals(userValue)) return false;
                }
                case 5 -> {
                    String breed = pet.getPetBreed().toLowerCase();
                    if (!breed.contains(userValue)) return false;
                }
                case 6 -> {
                    String city = pet.getPetAddress().getCityOfAdress().toLowerCase();
                    if (!city.contains(userValue)) return false;
                }
            }
        }
        return true;
    }

    private static List<Pet> loadSavedPets() {
        List<Pet> pets = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(petsFolder, "*.txt")) {
            for (Path path : stream) {
                try {
                    Pet pet = Pet.fromTxtFile(path);
                    pets.add(pet);
                } catch (IOException e) {
                    System.out.println("Erro ao ler: " + path.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar a pasta de pets.");
        }

        return pets;
    }

    private static void printOptionsMenu() {
        System.out.println("""
                
                Selecione um filtro adicional:
                [1] - Nome ou sobrenome
                [2] - Sexo
                [3] - Idade
                [4] - Peso
                [5] - Raça
                [6] - Endereço""");
    }

    private static int askForNumber(String prompt, int maxOption) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value < 1 || value > maxOption) {
                    throw new IllegalArgumentException("Número inválido.");
                }
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }
}