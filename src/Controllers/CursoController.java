package Controllers;

import Entities.Curso;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CursoController {
    private Map<String, Curso> cursos = new TreeMap<>();

    public Map<String, Curso> getCursosById() {
        return this.cursos;
    }

    public boolean addCurso(Curso curso) {
        if (cursos.containsKey(curso.getId())) {
            return false;
        }
        this.cursos.put(curso.getId(), curso);

        return true;
    }

    public Collection<Curso> getCursos() {
        return this.cursos.values();
    }

    public Curso getCursoById(String id) {
        return cursos.get(id);
    }

    public List<Curso> getCursoByAno(int ano) {
        return cursos.values().stream().filter(item -> item.getAno() == ano).collect(Collectors.toList());

    }

}
