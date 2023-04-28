package Controllers;

import Entities.Aluno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AlunoController {
    private Map<String, Aluno> alunos = new TreeMap<>();

    public Map<String, Aluno> getAlunosById() {
        return this.alunos;
    }

    public boolean addAluno(Aluno aluno) {
        if (alunos.containsKey(aluno.getId())) {
            return false;
        }
        this.alunos.put(aluno.getId(), aluno);

        return true;
    }

    public Collection<Aluno> getAlunos() {
        return this.alunos.values();
    }

    public Aluno getAlunoById(String id) {
        return alunos.get(id);
    }

    public List<Aluno> getAlunosByNome(String keyName) {

        List<Aluno> patosByName = new ArrayList<>();

        for (Aluno p : alunos.values()) {
            if (p.getName().toLowerCase().contains(keyName.toLowerCase())) {
                patosByName.add(p);
            }
        }
        return patosByName;
    }

}
