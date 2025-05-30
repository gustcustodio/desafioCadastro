package app.model.entities;

import app.model.enums.Sex;
import app.model.enums.Type;
import app.utils.Constants;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static app.menu.MainMenu.questions;

public class Pet {
    private String petName, petBreed, petAge, petWeight;

    private Sex petSex;
    private Type petType;
    private Address petAddress;

    private Path petFilePath;

    public Pet() {
    }

    public Pet(String petName, Type petType, Sex petSex, Address petAddress,
               String petAge, String petWeight, String petBreed) {
        this.petName = petName;
        this.petType = petType;
        this.petSex = petSex;
        this.petAddress = petAddress;
        this.petAge = petAge;
        this.petWeight = petWeight;
        this.petBreed = petBreed;
    }

    public static Pet fromTxtFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        String name = lines.get(0).substring(4).trim();
        Type type = Type.valueOf(lines.get(1).substring(4).trim().toUpperCase());
        Sex sex = Sex.valueOf(lines.get(2).substring(4).trim().toUpperCase());

        String[] addressParts = lines.get(3).substring(4).trim().split(",");
        String street = addressParts[0].trim();
        String number = addressParts[1].trim();
        String city = addressParts[2].trim();
        Address address = new Address(street, number, city);

        String age = lines.get(4).substring(4).trim();
        String weight = lines.get(5).substring(4).trim();
        String breed = lines.get(6).substring(4).trim();

        Pet pet = new Pet(name, type, sex, address, age, weight, breed);
        pet.petFilePath = path;

        return pet;
    }

    public String nameChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print("\n" + questions.get(0) + " ");
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
                System.out.println("⚠️️ Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

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
                System.out.println("⚠️️ Erro: " + e.getMessage());
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
                System.out.println("⚠️️ Erro: " + e.getMessage());
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

                if (!petStreet.matches("^[\\p{L}0-9\\s]+$")) {
                    throw new
                            IllegalArgumentException("A rua deve conter apenas letras e espaço!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Erro: " + e.getMessage());
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
                System.out.println("⚠️️ Erro: Digite apenas números inteiros.");
                System.out.println("Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Erro: " + e.getMessage());
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
                System.out.println("⚠️️ Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }

        return new Address(petStreet, stringPetHouseNumber, petCity);
    }

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
                System.out.println("⚠️️ Erro: Digite um número válido.");
                System.out.println("Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

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
                System.out.println("⚠️️ Erro: Digite um número válido.");
                System.out.println("Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    public String breedChecker(Scanner sc) {
        while (true) {
            try {
                System.out.print(questions.get(6) + " ");
                String petBreed = sc.nextLine().trim();

                if (petBreed.isEmpty()) {
                    petBreed = Constants.NOT_INFORMED;
                }

                if (!petBreed.matches("^[\\p{L}\\s\\-]+$")) {
                    throw new
                            IllegalArgumentException("O nome deve conter apenas letras e espaço!");
                }

                return petBreed;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️️ Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Type getPetType() {
        return petType;
    }

    public void setPetType(Type petType) {
        this.petType = petType;
    }

    public Sex getPetSex() {
        return petSex;
    }

    public void setPetSex(Sex petSex) {
        this.petSex = petSex;
    }

    public Address getPetAddress() {
        return petAddress;
    }

    public void setPetAddress(Address petAddress) {
        this.petAddress = petAddress;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public Path getFilePath() {
        return petFilePath;
    }

    @Override
    public String toString() {
        return petName
                + " - "
                + petType
                + " - "
                + petSex
                + " - "
                + petAddress
                + " - "
                + petAge
                + " - "
                + petWeight
                + " - "
                + petBreed;
    }
}
