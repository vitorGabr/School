package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Controllers.AlunoController;
import Entities.Aluno;

public class AcessoAluno extends DAO {
    private AlunoController alunoController;

    public AcessoAluno(String filePath, AlunoController alunoController) {
        super(new ArrayList<>(Arrays.asList(filePath)));
        this.alunoController = alunoController;
    }

    public void loadAluno() {
        Map<String, List<String>> data = this.load();
        for (List<String> lista : data.values()) {
            for (String palavra : lista) {
                String[] palavras = palavra.split(";");
                String id = palavras[0] != null ? palavras[0] : "";
                String nome = palavras[1] != null ? palavras[1] : "";
                Aluno aluno = new Aluno(id, nome);
                alunoController.addAluno(aluno);
            }
        }
    }

    public void saveAlunos() {
        this.save(alunoController.getAlunos());
    }
}
