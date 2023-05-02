package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Controllers.CursoController;
import Entities.Curso;
import Enums.NivelCurso;

public class AcessoCurso extends DAO {
    private CursoController cursoController;

    public AcessoCurso(String aFilePath, CursoController alunoController) {
        super(new ArrayList<>(Arrays.asList(aFilePath)));
        this.cursoController = alunoController;
    }

    public void loadCurso() {
        Map<String, List<String>> data = this.load();
        for (List<String> lista : data.values()) {
            for (String palavra : lista) {
                String[] palavras = palavra.split(";");
                String nome = palavras[0] != null ? palavras[0] : "";
                NivelCurso nivel = NivelCurso.fromString(palavras[1] != null ? palavras[1] : "");
                int ano = Integer.parseInt(palavras[2]);
                Curso curso = new Curso(nome, nivel, ano);
                cursoController.addCurso(curso);
            }
        }
    }

    public void saveCurso() {
        this.save(cursoController.getCursos());
    }
}
