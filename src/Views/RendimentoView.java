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
        String _ra = leitor.lerString("Entre com o Ra do aluno");
        if (this.alunoController.getAlunoById(_ra) == null) {
            System.out.println("Não existe nenhum aluno cadastro com esse RA!!!");
            return;
        }
        System.out.println("Listando o aluno: ");
        System.out.println(_ra);
        mostrarStatusAluno(rendimentoController.getRendimentosByAlunoId(_ra));
    }

    public void listarRendimentosByCurso(Curso curso) {
        System.out.println("Listando cada aluno que cursou essa matéria: ");
        mostrarStatusAluno(rendimentoController.getRendimentosByCursoId(curso.getId()));
    }

    private Rendimento entrarRendimento(String alunoRa, String cursoId) {
        double np1 = leitor.lerDouble("Entre com a nota da NP1: ");
        double np2 = leitor.lerDouble("Entre com a nota da NP2: ");
        double exame = leitor.lerDouble("Entre com a nota do Exame: ");
        double reposicao = leitor.lerDouble("Entre com a nota da Reposição: ");

        Curso curso = this.cursoController.getCursoById(cursoId);

        return new Rendimento(alunoRa, cursoId, np1, np2, exame, reposicao, curso.getNivel());
    }

    private void mostrarStatusAluno(List<Rendimento> rendimentos) {
        for (Rendimento rendimento : rendimentos) {

            Aluno aluno = alunoController.getAlunoById(rendimento.getAlunoId());

            System.out.println("------------------");
            System.out.println("ALUNO: " + aluno.getNome());
            System.out.println("------");
            System.out.println("STATUS: " + (rendimento.getAprovado() == true ? "PASSOU" : "NÃO PASSOU"));
            System.out.println("------");
            System.out.println("MÉDIA: " + rendimento.getMedia());
            System.out.println("------------------");
        }
    }

}
