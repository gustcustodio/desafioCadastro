package app.menu;

import app.model.entities.Address;
import app.model.entities.Pet;
import app.model.enums.Sex;
import app.model.enums.Type;

import java.io.*;
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

        try {
            File file = new File("app/petsCadastrados/" + fileName);
            if (file.createNewFile()) {
                System.out.println("\uD83D\uDCC4 Um arquivo com as informações do pet foi criado.");
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
                System.out.println("⚠️️ Ocorreu um erro na escrita do arquivo");
            }
        } catch (IOException e) {
            System.out.println("⚠️️ Ocorreu um erro na criação do arquivo.");
        }
    }

}
