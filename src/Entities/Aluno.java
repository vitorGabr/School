package Entities;

public class Aluno {

    private String id;
    private String name;

    public Aluno(String _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }

}