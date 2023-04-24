package Entities;

public class Rendimento {
    private Aluno aluno;
    private Curso curso;
    private double np1;
    private double np2;
    private double reposicao;
    private double exame;

    public Rendimento(
            Aluno _aluno,
            Curso _curso,
            double _np1,
            double _np2,
            double _reposicao,
            double _exame
    ){
        this.aluno = _aluno;
        this.curso = _curso;
        this.exame = _exame;
        this.np1 = _np1;
        this.reposicao = _reposicao;
    }

    public double getExame() {
        return exame;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public Curso getCurso() {return curso;}

    public double getNp1() {
        return np1;
    }

    public double getNp2() {
        return np2;
    }

    public double getReposicao() {
        return reposicao;
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


}
