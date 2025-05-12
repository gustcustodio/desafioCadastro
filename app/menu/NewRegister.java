package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;
import app.model.enums.Sex;
import app.model.enums.Type;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static app.menu.MainMenu.*;

public class NewRegister {
    public static void formReader() {
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
            System.out.println("⚠️️ Error: " + e.getMessage());
        }
    }

    private static int index = 0;

    public static void newRegister() {
        Pet pet = new Pet();
        String petName = pet.nameChecker(sc);
        Type petType = pet.typeChecker(sc);
        Sex petSex = pet.sexChecker(sc);
        Address petAddress = pet.addressChecker(sc);
        String petAge = pet.ageChecker(sc);
        String petWeight = pet.weightChecker(sc);
        String petBreed = pet.breedChecker(sc);
        pets.add(new Pet(petName, petType, petSex, petAddress, petAge, petWeight, petBreed));
        System.out.println("\n✅ PET CADASTRADO COM SUCESSO!");
        savePetFile(index);
        index++;
    }

    public static void savePetFile(int index) {
        String formattedName = pets.get(index).getPetName().replace(" ", "").toUpperCase();
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dateAndTimeFormatted = timeNow.format(timeFormatter);

        String fileName = dateAndTimeFormatted + "-" + formattedName + ".txt";
        Path filePath = Path.of("app/petsCadastrados", fileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            System.out.println("\uD83D\uDCC4 Um arquivo com as informações do pet foi criado.");

            try (BufferedWriter writer = Files.newBufferedWriter(
                    filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE)) {

                Pet pet = pets.get(index);
                writer.write("1 - " + pet.getPetName());
                writer.newLine();
                writer.write("2 - " + pet.getPetType());
                writer.newLine();
                writer.write("3 - " + pet.getPetSex());
                writer.newLine();
                writer.write("4 - Rua " +
                        pet.getPetAddress().getStreetOfAdress() + ", " +
                        pet.getPetAddress().getNumberOfAddress() + ", " +
                        pet.getPetAddress().getCityOfAdress());
                writer.newLine();
                writer.write("5 - " + pet.getPetAge() + " anos");
                writer.newLine();
                writer.write("6 - " + pet.getPetWeight() + "kg");
                writer.newLine();
                writer.write("7 - " + pet.getPetBreed());

            } catch (IOException e) {
                System.out.println("⚠️️ Ocorreu um erro na escrita do arquivo");
            }
        } catch (FileAlreadyExistsException e) {
            System.out.println("⚠️️ O arquivo já existe.");
        } catch (IOException e) {
            System.out.println("⚠️️ Ocorreu um erro na criação do arquivo.");
        }
    }
}
