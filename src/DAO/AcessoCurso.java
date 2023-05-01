package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Controllers.CursoController;
import Entities.Curso;

public class AcessoCurso extends DAO {
    private CursoController cursoController;

    public AcessoCurso(String aFilePath, CursoController alunoController) {
        super(new ArrayList<>(Arrays.asList(aFilePath)));
        this.cursoController = alunoController;
    }

    public void loadCurso() {
        Map<String, List<String>> palvrs = this.load();
        for (List<String> palavras : palvrs.values()) {
            String nome = palavras.get(0) != null ? palavras.get(0) : "";
            String nivel = palavras.get(1) != null ? palavras.get(1) : "";

            int ano = Integer.parseInt(palavras.get(2));

            Curso curso = new Curso(nome, nivel, ano);
            cursoController.addCurso(curso);
        }
    }

    public void saveCurso() {
        this.save(cursoController.getCursos());
    }
}
