package Entities;

public class Aluno implements Output {

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
        String message = "\n";
        message += "\n ID: " + id;
        message += "\n Nome: " + nome;
        return message;
    }

    @Override
    public String saida() {
        return id + ";" + nome;
    }

}