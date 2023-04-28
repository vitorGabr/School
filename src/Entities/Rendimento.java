package Entities;

import java.util.Arrays;

public class Rendimento {

    private String aluno_id;
    private String curso_id;
    private double np1;
    private double np2;
    private double reposicao;
    private double exame;
    private boolean aprovado;
    private double media;

    public Rendimento(
            String _aluno_id,
            String _curso_id,
            double _np1,
            double _np2,
            double _reposicao,
            double _exame
    ){
        this.aluno_id = _aluno_id;
        this.curso_id = _curso_id;
        this.exame = _exame;
        this.np1 = _np1;
        this.reposicao = _reposicao;
        this.media = 0;
        this.aprovado = false;
    }

    public double getExame() {
        return exame;
    }

    public String getAlunoId() {
        return aluno_id;
    }

    public String getCursoId() {
        return curso_id;
    }

    public double getNp1() {
        return np1;
    }

    public double getNp2() {
        return np2;
    }

    public double getReposicao() {
        return reposicao;
    }

    public boolean getAprovado() {
        return aprovado;
    }

    public void setExame(double exame) {
        this.exame = exame;
    }

    public void setNp1(double np1) {
        this.np1 = np1;
    }

    public void setNp2(double np2) {
        this.np2 = np2;
    }

    public void setReposicao(double reposicao) {
        this.reposicao = reposicao;
    }

    public void calcMedia(Curso _curso) {
        double repo = this.reposicao;
        double[] nps = { this.np1, this.np2 };
        double min = Arrays.stream(nps).min().getAsDouble();
        if (repo > min) {
            this.reposicao = min;
        }
        this.media = Arrays.stream(nps).sum() / nps.length;
        this.aprovado = _curso.getNivel() == "GRADUAÇÃO" ? this.media >= 7 : this.media >= 5;
        double finalMedia = (this.exame + this.media / 2);
        if (finalMedia > 5) {
            this.media = finalMedia;
            this.aprovado = true;
        }
    }

}
