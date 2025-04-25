package app.menu;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);

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

    public void formReader() {
        String path = "app/formulario.txt";
        List<String> answers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String question;

            while ((question = br.readLine()) != null) {
                System.out.print(question + " ");
                String answer = sc.nextLine();
                answers.add(answer);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void formOption(int option) {
        switch (option) {
            case 1:
                formReader();
                break;
            case 6:
                System.out.println("\nSaindo do sistema. Até logo!");
                break;
        }
    }
}
