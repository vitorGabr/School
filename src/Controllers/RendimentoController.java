package Controllers;

import Entities.Rendimento;
import Enums.Nivel;

import java.util.Arrays;

public class RendimentoController {
    private boolean aprovado = false;
    private double media = 0;
    private Rendimento rendimento;

    public RendimentoController(Rendimento _rendRendimento){
        this.rendimento = _rendRendimento;
    }

    public boolean getAprovado() {
        return aprovado;
    }

    public void calMedia() {
        double repo = this.rendimento.getReposicao();
        double[] nps = {this.rendimento.getNp1(),this.rendimento.getNp2()};
        double min = Arrays.stream(nps).min().getAsDouble();
        if( repo > min){
            this.rendimento.setReposicao(min);
        }
        this.media = Arrays.stream(nps).sum() / nps.length;
        this.aprovado = this.rendimento.getCurso().getNivel() == Nivel.GRADUACAO ? this.media >= 7 : this.media >= 5;
        double finalMedia = (this.rendimento.getExame()+this.media / 2);
        if(finalMedia > 5){
            this.media = finalMedia;
            this.aprovado = true;
        }
    }

}
