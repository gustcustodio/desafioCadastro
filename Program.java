import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static Scanner perguntasFormulario;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        openFile();
        readRecords();
        closeFile();
    }

    public static void openFile() {
        try {
            perguntasFormulario = new Scanner(Paths.get("formulario.txt"));
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo. Encerrando.");
            System.exit(1);
        }
    }

    public static void readRecords() {
        try {
            while (perguntasFormulario.hasNextLine()) {
                String pergunta = perguntasFormulario.nextLine();

                if (pergunta.isEmpty()) {
                    continue;
                }

                System.out.println(pergunta);
                System.out.print("Reposta: ");
                String resposta = sc.nextLine();
                System.out.println("Você respondeu: " + resposta);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler perguntas. Encerrando.");
        }
    }

    public static void closeFile() {
        if (perguntasFormulario != null) {
            perguntasFormulario.close();
        }
    }
}
