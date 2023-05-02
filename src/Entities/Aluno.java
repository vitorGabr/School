package Entities;

public class Aluno {

    private String id;
    private String nome;

    public Aluno(String _id, String _nome) {
        this.id = _id;
        this.nome = _nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return id + ";" + nome;
    }

}