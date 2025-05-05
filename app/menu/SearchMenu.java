package app.menu;

import app.model.entities.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.menu.MainMenu.sc;

public class SearchMenu {
    public static final List<Integer> criterions = new ArrayList<>();
    Path petsFolder = Paths.get("app/petsCadastrados");

    public static void typeOfAnimal() {
        int type = readNumber(
                """
                        
                        Você está buscando qual tipo de animal?
                        [1] - Cachorro
                        [2] - Gato
                        Digite o número correspondente ao tipo desejado:\s""",
                2
        );
        criterions.add(type);
    }

    public static void runMenu() {
        int numberOfFilters = readNumber(
                "Quantos filtros deseja utilizar para realizar a busca (min. 1 - máx. 2)? ",
                2
        );

        for (int i = 1; i <= numberOfFilters; i++) {
            optionsMenu();
            int criterion = readNumber(
                    "Informe o número do critério que você deseja utilizar: ",
                    6
            );
            criterions.add(criterion);
        }
    }

    public static List<Pet> loadSavedPets(Path petsFolder) {
        List<Pet> petList = new ArrayList<>();

        try (Stream<Path> files = Files.list(petsFolder)) {

            List<Path> txtFiles = files
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toList());

            for (Path file : txtFiles) {
                try {
                    Pet pet = Pet.fromTxtFile(file);
                    petList.add(pet);
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo: " + file.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar a pasta");
        }

        return petList;
    }

    public static void optionsMenu() {
        System.out.println("Deseja utilizar qual filtro para realizar a busca?");
        System.out.println("[1] - Nome ou sobrenome");
        System.out.println("[2] - Sexo");
        System.out.println("[3] - Idade");
        System.out.println("[4] - Peso");
        System.out.println("[5] - Raça");
        System.out.println("[6] - Endereço");
    }

    private static int readNumber(String prompt, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();

                if (input.isBlank()) {
                    throw new IllegalArgumentException
                            ("Entrada não pode estar em branco.");
                }

                int value = Integer.parseInt(input);

                if (value < 1 || value > max) {
                    throw new IllegalArgumentException
                            ("Valor deve estar entre " + 1 + " e " + max + ".");
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Você deve digitar apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println("Tente novamente.\n");
        }
    }
}
