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
        Map<String, List<String>> palvrs = this.load();
        for (List<String> palavras : palvrs.values()) {
            if (palavras.size() > 0) {
                String id = palavras.get(0) != null ? palavras.get(0) : "";
                String nome = palavras.get(1) != null ? palavras.get(1) : "";

                Aluno aluno = new Aluno(id, nome);
                alunoController.addAluno(aluno);
            }
        }
    }

    public void saveAlunos() {
        this.save(alunoController.getAlunos());
    }
}
