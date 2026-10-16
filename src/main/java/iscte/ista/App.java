package iscte.ista;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Exemplo de serializacao de um Student para JSON e vice-versa.
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // objeto -> JSON
        Student s = new Student("Joao Pereira", 104500);
        String json = mapper.writeValueAsString(s);
        System.out.println("JSON: " + json);

        // JSON -> objeto (a ordem dos campos nao interessa)
        String outroJson = "{\"number\": 104000, \"name\": \"Maria Silva\"}";
        Student s2 = mapper.readValue(outroJson, Student.class);
        System.out.println("Objeto: " + s2);
    }
}
