package Entities;

import java.util.NoSuchElementException;

public class Curso {

    private String id;
    private String nome;
    private String nivel;
    private int ano;

    public Curso(String _nome, String _nivel, int _ano) {
        verificaNivel(_nivel);
        verificaAno(_ano);
        this.ano = _ano;
        this.nome = _nome;
        this.nivel = _nivel;
        this.id = _nome + "_" + _nivel + "_" + String.valueOf(_ano);
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    private void verificaNivel(String _nivel) {
        if (!(_nivel.equals("GRADUACAO") || _nivel.equals("POS_GRADUACAO"))) {
            throw new NoSuchElementException("Você precisa digitar um valor válido para o nível !!!");
        }
    }

    private void verificaAno(int _ano) {
        int _anoLength = Integer.toString(_ano).length();
        if ((_anoLength != 2 || _anoLength != 4) && (ano < 0 || ano > 9999)) {
            throw new IllegalArgumentException("Ano inválido. o valor tem que ser um ano válido!!!");
        }
    }

    @Override
    public String toString() {
        return nome + ";" + nivel + ";" + ano;
    }

}
