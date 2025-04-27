package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;
import app.model.enums.Sex;
import app.model.enums.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    List<String> questions = new ArrayList<>();

    public void initialMenu() {
        int option = -1;
        do {
            System.out.println("\n1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
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

    public void newRegister() {
        System.out.print("\n" + questions.get(0) + " ");
        String petName = sc.nextLine();
        System.out.print(questions.get(1) + " ");
        String stringPetType = sc.nextLine().toUpperCase();
        Type petType = Type.valueOf(stringPetType);
        System.out.print(questions.get(2) + " ");
        String stringPetSex = sc.nextLine().toUpperCase();
        Sex petSex = Sex.valueOf(stringPetSex);
        System.out.print(questions.get(3) + "\n");
        System.out.print("    Rua: ");
        String petStreet = sc.nextLine();
        System.out.print("    Número da casa: ");
        Integer petHouseNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("    Cidade: ");
        String petCity = sc.nextLine();
        Address petAddress = new Address(petStreet, petHouseNumber, petCity);
        System.out.print(questions.get(4) + " ");
        Double petAge = sc.nextDouble();
        System.out.print(questions.get(5) + " ");
        Double petWeight = sc.nextDouble();
        sc.nextLine();
        System.out.print(questions.get(6) + " ");
        String petBreed = sc.nextLine();
        Pet pet = new Pet(petName, petType, petSex, petAddress, petAge, petWeight, petBreed);
        System.out.println("PET CADASTRADO COM SUCESSO!");
        System.out.println(pet);
    }
}
