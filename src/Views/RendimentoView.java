package Views;

import java.util.List;

import Controllers.AlunoController;
import Controllers.CursoController;
import Controllers.RendimentoController;
import Entities.Aluno;
import Entities.Curso;
import Entities.Rendimento;
import Utils.LeitorDeDados;

public class RendimentoView {

    private RendimentoController rendimentoController;
    private AlunoController alunoController;
    private CursoController cursoController;

    private LeitorDeDados leitor = new LeitorDeDados();

    public RendimentoView(
            RendimentoController _rendimentoController,
            AlunoController _alunoController,
            CursoController _cursoController) {
        this.rendimentoController = _rendimentoController;
        this.alunoController = _alunoController;
        this.cursoController = _cursoController;
    }

    public void adicionarRendimento(String alunoRa, String cursoId) {
        Rendimento rendimento = entrarRendimento(alunoRa, cursoId);
        System.out.println("Adicionado rendimento: ");
        System.out.println(rendimento);
        rendimentoController.addRendimento(rendimento);
    }

    public void listarAlunoByRa() {
        String _ra = entrarRa();
        if (this.alunoController.getAlunoById(_ra) == null) {
            System.out.println("\nNão existe nenhum aluno cadastro com esse RA!!!");
            return;
        }
        System.out.println("\nListando o aluno: ");
        System.out.println(_ra);
        mostrarStatusAluno(rendimentoController.getRendimentosByAlunoId(_ra));
    }

    public void listarRendimentosByCurso(Curso curso) {
        System.out.println("\nListando cada aluno que cursou essa matéria: ");
        mostrarStatusAluno(rendimentoController.getRendimentosByCursoId(curso.getId()));
    }

    private Rendimento entrarRendimento(String alunoRa, String cursoId) {
        double np1 = entrarNp1();
        double np2 = entrarNp2();
        double exame = entrarExame();
        double reposicao = entrarReposicao();

        Curso curso = this.cursoController.getCursoById(cursoId);

        return new Rendimento(alunoRa, cursoId, np1, np2, exame, reposicao, curso.getNivel());
    }

    private void mostrarStatusAluno(List<Rendimento> rendimentos) {
        double sumMedia = 0;
        for (Rendimento rendimento : rendimentos) {
            sumMedia += rendimento.getMedia();

            Aluno aluno = alunoController.getAlunoById(rendimento.getAlunoId());
            Curso curso = cursoController.getCursoById(rendimento.getCursoId());

            System.out.println("------------------");
            System.out.println("MATÉRIA: " + curso.getNome());
            System.out.println("------");
            System.out.println("ALUNO: " + aluno.getNome());
            System.out.println("------");
            System.out.println("STATUS: " + (rendimento.getAprovado() == true ? "PASSOU" : "NÃO PASSOU"));
            System.out.println("------");
            System.out.println("MÉDIA: " + rendimento.getMedia());
            System.out.println("------------------");
        }
        System.out.println("\nMÉDIA TOTAL: " + (sumMedia / rendimentos.size()));
        System.out.println("------------------");

    }

    private String entrarRa() {
        return leitor.lerString("\nEntre com o Ra do aluno: ");
    }

    private Double entrarNp1() {
        return leitor.lerDouble("\nEntre com a nota da NP1: ");
    }

    private Double entrarNp2() {
        return leitor.lerDouble("\nEntre com a nota da NP2: ");
    }

    private Double entrarExame() {
        return leitor.lerDouble("\nEntre com a nota do Exame: ");
    }

    private Double entrarReposicao() {
        return leitor.lerDouble("\nEntre com a nota da Reposição: ");
    }

}
