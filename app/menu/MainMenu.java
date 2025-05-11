package app.menu;

import app.model.entities.Pet;

import java.util.*;

public class MainMenu {
    public static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    public static final List<String> questions = new ArrayList<>();
    public static final List<Pet> pets = new ArrayList<>();

    // todo Melhorar a visualização dos menus //

    public static void initialMenu() {
        int option = -1;
        do {
            System.out.println("\n[1] Cadastrar um novo pet");
            System.out.println("[2] Listar pets por algum critério (idade, nome, raça)");
            System.out.println("[3] Deletar um pet cadastrado");
            System.out.println("[4] Listar todos os pets cadastrados");
            System.out.println("[5] Alterar os dados do pet cadastrados");
            System.out.println("[6] Sair\n");
            System.out.print("Digite o número da opção desejada: ");

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

    private static void formOption(int option) {
        switch (option) {
            case 1:
                NewRegister.formReader();
                NewRegister.newRegister();
                MainMenu.dashedLines();
                MainMenu.initialMenu();
                break;
            case 2:
                ListFilteredPets.listFilteredPets();
                MainMenu.dashedLines();
                MainMenu.initialMenu();
                break;
            case 3:
                DeleteRegisteredPet.executeDeletion();
                MainMenu.dashedLines();
                MainMenu.initialMenu();
                break;
            case 4:
                ListAllRegisteredPets.listAllRegisteredPets();
                MainMenu.dashedLines();
                MainMenu.initialMenu();
                break;
            case 5:
                System.out.println("\nTestando opção 5.");
                break;
            case 6:
                System.out.println("\nSaindo do sistema. Até logo!");
                break;
        }
    }

    private static void dashedLines() {
        char trace = '-';
        int repetition = 100;

        System.out.println();

        for (int i = 0; i < repetition; i++) {
            System.out.print(trace);
        }

        System.out.println();
    }
}