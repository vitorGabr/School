package Controllers;

import Entities.Rendimento;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RendimentoController {
    private Map<String, Rendimento> rendimentos = new TreeMap<>();

    public List<Rendimento> getRendimentosByAlunoId(String id) {
        return this.rendimentos.values().stream()
                .filter(rend -> rend.getAlunoId().equals(id))
                .collect(Collectors.toList());
    }

    public boolean addRendimento(Rendimento rendimento) {
        if (rendimentos.containsKey(rendimento.getId())) {
            return false;
        }
        this.rendimentos.put(rendimento.getId(), rendimento);

        return true;
    }

    public Collection<Rendimento> getRendimentos() {
        return this.rendimentos.values();
    }

    public List<Rendimento> getRendimentosByCursoId(String id) {
        return this.rendimentos.values().stream()
                .filter(rend -> rend.getCursoId().equals(id))
                .collect(Collectors.toList());
    }

    public Rendimento getRendimentoById(String id) {
        return rendimentos.get(id);
    }

}
