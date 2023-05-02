package Entities;

import Enums.NivelCurso;

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
            double _exame,
            NivelCurso _nivelCurso) {
        this.aluno_id = _aluno_id;
        this.curso_id = _curso_id;
        this.np1 = _np1;
        this.np2 = _np2;
        this.exame = _exame;
        this.reposicao = _reposicao;
        this.calcMedia(_nivelCurso);
    }

    public double getMedia() {
        return media;
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

    private void calcMedia(NivelCurso _nivel) {
        double repo = this.reposicao;
        double min = Math.min(this.np1, this.np1);
        if (repo > min) {
            this.reposicao = min;
        }
        this.media = (this.np1 + this.np2) / 2;
        this.aprovado = _nivel == NivelCurso.GRADUACAO ? this.media >= 7 : this.media >= 5;
        double finalMedia = (this.exame + this.media / 2);
        if (finalMedia > 5) {
            this.media = finalMedia;
            this.aprovado = true;
        }
    }

    @Override
    public String toString() {
        return np1 + ";" + np2 + ";" + reposicao + ";" + exame;
    }


}
