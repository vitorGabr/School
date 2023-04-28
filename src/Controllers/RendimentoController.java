package Controllers;

import Entities.Rendimento;

import java.util.ArrayList;
import java.util.List;

public class RendimentoController {
    private List<Rendimento> rendimentos = new ArrayList<>();

    public void addRendimentos(Rendimento rendimento) {
        this.rendimentos.add(rendimento);
    }

    public List<Rendimento> getRendimentos() {
        return this.rendimentos;
    }


}
