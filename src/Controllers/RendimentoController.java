package Controllers;

import Entities.Rendimento;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class RendimentoController {
    private Map<String, Rendimento> rendimentos = new TreeMap<>();

    public Map<String, Rendimento> getRendimentosById() {
        return this.rendimentos;
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

    public Rendimento getRendimentoById(String id) {
        return rendimentos.get(id);
    }


}
