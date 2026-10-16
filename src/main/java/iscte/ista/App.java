package iscte.ista;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Gera uma lista de alunos americanos imaginarios em formato CSV.
 * Os nomes sao obtidos a partir dos ficheiros JSON do projeto American Names.
 */
public class App {

    private static final String FIRSTNAMES_F_FILE = "data/firstnames_f.json";
    private static final String FIRSTNAMES_M_FILE = "data/firstnames_m.json";
    private static final String SURNAMES_FILE = "data/surnames.json";

    private static final String STUDENTS_CVS_FILE = "data/students.cvs";

    private static final int INITIAL_NUMBER = 21000;

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // ler os nomes proprios (femininos + masculinos) e os apelidos
        List<String> firstNames = new ArrayList<>();
        firstNames.addAll(readNames(mapper, FIRSTNAMES_F_FILE));
        firstNames.addAll(readNames(mapper, FIRSTNAMES_M_FILE));
        List<String> surnames = readNames(mapper, SURNAMES_FILE);

        Scanner in = new Scanner(System.in);
        System.out.print("Quantos alunos quer gerar? ");
        int total = in.nextInt();

        long max = (long) firstNames.size() * surnames.size();
        if (total <= 0 || total > max) {
            System.out.println("Numero invalido.");
            return;
        }

        Random random = new Random();
        Set<String> usedNames = new HashSet<>(); // para nao haver nomes repetidos
        List<Student> students = new ArrayList<>();

        int number = INITIAL_NUMBER;
        while (students.size() < total) {
            String first = firstNames.get(random.nextInt(firstNames.size()));
            String last = surnames.get(random.nextInt(surnames.size()));
            String name = first + " " + last;

            if (usedNames.add(name)) {
                students.add(new Student(name, number));
                number++;
            }
        }

        // escrever o ficheiro CSV
        try (PrintWriter out = new PrintWriter(STUDENTS_CVS_FILE)) {
            out.println("number,name");
            for (Student s : students) {
                out.println(s.toCSV());
            }
        }

        System.out.println(total + " alunos guardados em " + STUDENTS_CVS_FILE);
    }

    private static List<String> readNames(ObjectMapper mapper, String file) throws IOException {
        return mapper.readValue(new File(file), new TypeReference<List<String>>() {});
    }
}
