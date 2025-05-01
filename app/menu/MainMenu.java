package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;
import app.model.enums.Sex;
import app.model.enums.Type;
import app.utils.Constants;

import java.io.*;
import java.util.*;

public class MainMenu {
    Scanner sc = new Scanner(System.in).useLocale(Locale.US);
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
        Type petType = typeChecker(sc);
        Sex petSex = sexChecker(sc);
        Address petAddress = addressChecker(sc);
        String petAge = ageChecker(sc);
        String petWeight = weightChecker(sc);
        String petBreed = breedChecker(sc);

        Pet pet = new Pet(petName, petType, petSex, petAddress, petAge, petWeight, petBreed);
        System.out.println("PET CADASTRADO COM SUCESSO!");
        System.out.println(pet);
    }

    // ! O usuário obrigatoriamente deverá cadastrar um pet com nome e sobrenome, caso contrário, lance uma exceção. ! //
    // ! O nome completo NÃO poderá conter caracteres especiais, somente letras de A-Z. ! //
    public String nameChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(0) + " ");
                String petName = sc.nextLine();

                if (petName.isBlank()) {
                    petName = Constants.NOT_INFORMED;
                }

                if (!petName.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new
                            IllegalArgumentException("O nome deve conter apenas letras e espaço!");
                }

                if (!petName.contains(" ")) {
                    throw new
                            IllegalArgumentException("O nome deve conter um sobrenome!");
                }

                return petName;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    // ! Para o TIPO e SEXO do pet, usar ENUM. ! //
    public Type typeChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(1) + " ");
                String stringPetType = sc.nextLine().trim().toUpperCase();

                if (!stringPetType.equals("CACHORRO") && !stringPetType.equals("GATO")) {
                    throw new
                            IllegalArgumentException("O tipo do pet deve ser 'Cachorro' ou 'Gato'!");
                }

                return Type.valueOf(stringPetType);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    public Sex sexChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(2) + " ");
                String stringPetSex = sc.nextLine().trim().toUpperCase();

                if (!stringPetSex.equals("MACHO") && !stringPetSex.equals("FEMEA")) {
                    throw new
                            IllegalArgumentException("O sexo do pet deve ser 'Macho' ou 'Femea'.");
                }

                return Sex.valueOf(stringPetSex);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    public Address addressChecker(Scanner sc) {
        System.out.print(questions.get(3) + "\n");

        String petStreet, stringPetHouseNumber, petCity;

        while (true) {
            try {
                System.out.print("    Rua: ");
                petStreet = sc.nextLine().trim();

                if (!petStreet.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new
                            IllegalArgumentException("A rua deve conter apenas letras e espaço!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }

        while (true) {
            try {
                System.out.print("    Número: ");
                stringPetHouseNumber = sc.nextLine().trim();

                if (stringPetHouseNumber.isBlank()) {
                    stringPetHouseNumber = Constants.NOT_INFORMED;
                    break;
                }

                int petHouseNumber = Integer.parseInt(stringPetHouseNumber);

                if (petHouseNumber <= 0) {
                    throw new
                            IllegalArgumentException("O número da casa deve ser maior do que zero" +
                            ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números inteiros.");
                System.out.println("Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }

        while (true) {
            try {
                System.out.print("    Cidade: ");
                petCity = sc.nextLine();

                if (!petCity.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new
                            IllegalArgumentException("A cidade deve conter apenas letras e espaço!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tenta novamente.");
            }
        }

        return new Address(petStreet, stringPetHouseNumber, petCity);
    }

    // ! Na idade aproximada do pet, o usuário poderá digitar números com vírgulas ou ponto, mas somente números. ! //
    // ! Caso o usuário digite uma idade maior que 20 anos, lance uma exceção. ! //
    // ! Caso o usuário digite uma idade menor que 1 ano (idade em meses), transforme em 0.x anos. ! //
    public String ageChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(4) + " ");
                String stringPetAge = sc.nextLine().trim().replace(",", ".");

                if (stringPetAge.isBlank()) {
                    return Constants.NOT_INFORMED;
                }

                double petAge = Double.parseDouble(stringPetAge);

                if (petAge <= 0 || petAge > 20) {
                    throw new
                            IllegalArgumentException("Idade deve estar entre 0.1 e 20 anos");
                }

                if (petAge < 1.0) {
                    System.out.println(
                            "Idade menor do que 1 ano detectada. Considerando o valor como meses."
                    );
                    petAge = petAge / 12.0;
                    return String.format("%.2f", petAge);
                }

                return stringPetAge;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.");
                System.out.println("Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    // ! Em peso aproximado do pet, o usuário poderá digitar números com vírgulas ou ponto, mas somente números. ! //
    // ! Caso o usuário digite um peso maior que 60kg ou um peso menor que 0.5kg, lance uma exceção. ! //
    public String weightChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(5) + " ");
                String stringPetWeight = sc.nextLine().trim().replace(",", ".");

                if (stringPetWeight.isBlank()) {
                    return Constants.NOT_INFORMED;
                }

                double petWeight = Double.parseDouble(stringPetWeight);


                if (petWeight > 60.0 || petWeight < 0.5) {
                    throw new
                            IllegalArgumentException("Peso inválido! Deve ser entre 0.5kg e 60kg.");
                }

                return stringPetWeight;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.");
                System.out.println("Tenta novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    // ! No campo raça o usuário não poderá usar números nem caracteres especiais. ! //
    public String breedChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(6) + " ");
                String petBreed = sc.nextLine().trim();

                if (petBreed.isEmpty()) {
                    petBreed = Constants.NOT_INFORMED;
                }

                if (!petBreed.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new
                            IllegalArgumentException("O nome deve conter apenas letras e espaço!");
                }

                return petBreed;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }
}
