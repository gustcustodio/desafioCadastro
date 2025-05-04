package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;
import app.model.enums.Sex;
import app.model.enums.Type;
import app.utils.Constants;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainMenu {
    public static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    public static final List<String> questions = new ArrayList<>();
    public static final List<Pet> pets = new ArrayList<>();

    public void initialMenu() {
        int option = -1;
        do {
            System.out.println("\n1. Cadastrar um novo pet");
            System.out.println("2. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Alterar os dados do pet cadastrados");
            System.out.println("6. Sair\n");
            System.out.print("Digite a opção desejada: ");

            try {
                option = sc.nextInt();
                sc.nextLine();

                if (option <= 0 || option > 6) {
                    System.out.println("Opção inválida. Digite um número entre 1 e 6.");
                } else {
                    formOption(option);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                sc.nextLine();
            }

        } while (option != 6);
    }

    public void formOption(int option) {
        switch (option) {
            case 1:
                formReader();
                newRegister();
                break;
            case 2:
                System.out.println("\nTestando opção 2.");
                break;
            case 3:
                System.out.println("\nTestando opção 3.");
                break;
            case 4:
                System.out.println("\nTestando opção 4.");
                break;
            case 5:
                System.out.println("\nTestando opção 5.");
                break;
            case 6:
                System.out.println("\nSaindo do sistema. Até logo!");
                break;
        }
    }

    public void formReader() {
        String path = "app/formulario.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    questions.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    int index = 0;

    public void newRegister() {
        Pet pet = new Pet();
        String petName = pet.nameChecker(sc);
        Type petType = pet.typeChecker(sc);
        Sex petSex = pet.sexChecker(sc);
        Address petAddress = pet.addressChecker(sc);
        String petAge = pet.ageChecker(sc);
        String petWeight = pet.weightChecker(sc);
        String petBreed = pet.breedChecker(sc);
        pets.add(new Pet(petName, petType, petSex, petAddress, petAge, petWeight, petBreed));
        System.out.println("\nPET CADASTRADO COM SUCESSO!");
        savePetFile(index);
        index++;
        initialMenu();
    }

    public void savePetFile(int index) {
        String formattedName =
                pets.get(index).getPetName().replace(" ", "").toUpperCase();
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dateAndTimeFormatted = timeNow.format(timeFormatter);

        String fileName = dateAndTimeFormatted + "-" + formattedName + ".txt";

        try {
            File file = new File("app/petsCadastrados/" + fileName);
            if (file.createNewFile()) {
                System.out.println("Um arquivo com as informações do pet foi criado.");
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("1 - " + pets.get(index).getPetName());
                bw.newLine();
                bw.write("2 - " + pets.get(index).getPetType());
                bw.newLine();
                bw.write("3 - " + pets.get(index).getPetSex());
                bw.newLine();
                bw.write("4 - " + pets.get(index).getPetAddress().toString());
                bw.newLine();
                bw.write("5 - " + pets.get(index).getPetAge() + " anos");
                bw.newLine();
                bw.write("6 - " + pets.get(index).getPetWeight() + "kg");
                bw.newLine();
                bw.write("7 - " + pets.get(index).getPetBreed());
            } catch (IOException e) {
                System.out.println("Ocorreu um erro na escrita do arquivo");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro na criação do arquivo.");
        }
    }
}
