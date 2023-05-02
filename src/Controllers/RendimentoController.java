package Controllers;

import Entities.Rendimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RendimentoController {

    private Map<String, List<Rendimento>> rendimentos = new TreeMap<>();

    public List<Rendimento> getRendimentosByAlunoId(String id) {
        List<Rendimento> _rendimento = new ArrayList<>();
        for (List<Rendimento> r : this.rendimentos.values()) {
            for (Rendimento rendi : r) {
                _rendimento.add(rendi);
            }
        }
        return _rendimento.stream()
                .filter(rend -> rend.getAlunoId().equals(id))
                .collect(Collectors.toList());
    }

    public void addRendimento(Rendimento rendimento) {
        System.out.println(rendimento.getAlunoId());
        List<Rendimento> _rendimentos = new ArrayList<>(Arrays.asList(rendimento));
        if (this.rendimentos.containsKey(rendimento.getCursoId())) {
            _rendimentos = this.rendimentos.get(rendimento.getCursoId());
            _rendimentos.add(rendimento);
        }

        this.rendimentos.put(rendimento.getCursoId(), _rendimentos);

    }

    public Map<String, List<Rendimento>> getRendimentos() {
        return this.rendimentos;
    }

    public List<Rendimento> getRendimentosByCursoId(String id) {
        return this.rendimentos.get(id);
    }

}
