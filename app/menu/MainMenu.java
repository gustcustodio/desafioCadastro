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
        String petName = nameChecker(sc);
        /*
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
        */
    }

    // todo Implementar o método nameChecker com as seguintes regras: //
    // ! O usuário obrigatoriamente deverá cadastrar um pet com nome e sobrenome, caso contrário, lance uma exceção. ! //
    // ! O nome completo NÃO poderá conter caracteres especiais, somente letras de A-Z. ! //
    public String nameChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print("\n" + questions.get(0) + " ");
                String petName = sc.nextLine();

                if (!petName.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new
                            IllegalArgumentException("O nome deve conter apenas letras e espaço.");
                }

                if (!petName.contains(" ")) {
                    throw new
                            IllegalArgumentException("O nome deve conter um sobrenome");
                }

                return petName;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
        }
    }

    // todo Implementar o método typeChecker e sexChecker com a seguinte regra: //
    // ! Para o TIPO e SEXO do pet, você deverá usar ENUM. ! //
    public void typeChecker() {
    }

    public void sexChecker() {
    }

    // todo Implementar o método weightChecker com as seguintes regras: //
    // ! Em peso aproximado do pet, o usuário poderá digitar números com vírgulas ou ponto, mas somente números. ! //
    // ! Caso o usuário digite um peso maior que 60kg ou um peso menor que 0.5kg, ! lance uma exceção. ! //
    public void weightChecker() {
    }

    // todo Implementar o método ageChecker com as seguintes regras: //
    // ! Na idade aproximada do pet, o usuário poderá digitar números com vírgulas ou ponto,  mas somente números. ! //
    // ! Caso o usuário digite uma idade maior que 20 anos, lance uma exceção. ! //
    // ! Caso o usuário digite uma idade menor que 1 ano (idade em meses), transforme em 0.x  anos. ! //
    public void ageChecker() {
    }

    // todo Implementar o método breedChecker com a seguinte regra: //
    // ! No campo raça o usuário não poderá usar números nem caracteres especiais. ! //
    public void breedChecker() {
    }
}
