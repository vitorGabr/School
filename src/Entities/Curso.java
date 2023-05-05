package Entities;

import Enums.NivelCurso;
import Interfaces.Output;

public class Curso implements Output {

    private String id;
    private String nome;
    private NivelCurso nivel;
    private int ano;

    public Curso(String _nome, NivelCurso _nivel, int _ano) {
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

    public NivelCurso getNivel() {
        return nivel;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    private void verificaAno(int _ano) {
        int _anoLength = Integer.toString(_ano).length();
        if ((_anoLength != 2 || _anoLength != 4) && (ano < 0 || ano > 9999)) {
            throw new IllegalArgumentException("Ano inválido. o valor tem que ser um ano válido!!!");
        }
    }

    @Override
    public String toString() {
        String message = "\n";
        message += "\n Nome: " + nome;
        message += "\n Nível: " + nivel;
        message += "\n Ano: " + ano;
        return message;
    }

    @Override
    public String saida() {
        return nome + ";" + nivel + ";" + ano;
    }

}
