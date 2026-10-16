package iscte.ista;

/**
 * Guarda a informacao de um estudante (nome e numero).
 */
public class Student {

    private String name;
    private int number;

    // o Jackson precisa de um construtor sem argumentos para fazer a desserializacao
    public Student() {
    }

    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', number=" + number + "}";
    }
}
