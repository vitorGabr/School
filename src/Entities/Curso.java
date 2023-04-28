package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Curso {

    private String id;
    private String nome;
    private String nivel;
    private int ano;

    public Curso(String _nome, String _nivel, int _ano) {
        this.ano = _ano;
        this.nome = _nome;
        this.ano = _ano;
        this.id = String.valueOf(_ano) + _nivel + _nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return nome + "," + nivel + "," + ano;
    }

}
