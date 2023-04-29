package Views;

import java.util.Scanner;

import Controllers.AlunoController;
import Controllers.CursoController;
import Controllers.RendimentoController;
import Entities.Aluno;
import Entities.Curso;
import Entities.Rendimento;

public class RendimentoView {

    private RendimentoController rendimentoController;
    private AlunoController alunoController;
    private CursoController cursoController;

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
        if (rendimentoController.addRendimento(rendimento)) {
            System.out.println("Adicionando Rendimento " + rendimento);
        } else {
            System.out.println("Falha ao adicionar rendimento " + rendimento);
        }

    }

    public void listarAlunoByRa() {
        String _ra = entraRa();
        if (this.alunoController.getAlunoById(_ra) == null) {
            System.out.println("Não existe nenhum aluno cadastro com esse RA!!!");
            return;
        }
        System.out.println("Listando o aluno:");
        System.out.println();
        System.out.println(_ra);
        for (Rendimento p : rendimentoController.getRendimentosByAlunoId(_ra)) {
            Curso curso = cursoController.getCursoById(p.getCursoId());
            if (curso == null) {
                System.out.println("Não existe nenhum curso cadastro com esse ID!!!");
                return;
            }
            p.calcMedia(curso);
            System.out.println(p);
            System.out.println("------");
            System.out.println(p.getAprovado() == true ? "PASSOU" : "NÃO PASSOU");
            System.out.println("------");
            System.out.println("MÉDIA: " + p.getMedia());
        }
    }

    public void listarRendimentosByCurso(Curso curso) {
        System.out.println("Listando cada aluno que cursou essa matéria:");
        for (Rendimento p : rendimentoController.getRendimentosByCursoId(curso.getId())) {
            Aluno aluno = alunoController.getAlunoById(p.getAlunoId());
            if (aluno == null) {
                System.out.println("Não existe nenhum aluno cadastro com esse RA!!!");
                return;
            }
            p.calcMedia(curso);
            System.out.println();
            System.out.println("ALUNO: " + aluno.getName());
            System.out.println("------");
            System.out.println("STATUS: " + (p.getAprovado() == true ? "PASSOU" : "NÃO PASSOU"));
            System.out.println("------");
            System.out.println("MÉDIA: " + p.getMedia());
        }

    }

    private Rendimento entrarRendimento(String alunoRa, String cursoId) {
        double np1 = entraNP1();
        double np2 = entraNP2();
        double exame = entraExame();
        double reposicao = entraReposicao();

        return new Rendimento(alunoRa, cursoId, np1, np2, exame, reposicao);
    }

    public String entraRa() {
        System.out.println("Entre com o Ra do aluno");
        Scanner in = new Scanner(System.in);

        String id = in.nextLine();

        return id.trim();
    }

    public double entraNP1() {
        System.out.println("Entre com a nota da NP1");
        Scanner in = new Scanner(System.in);
        return Double.parseDouble(in.nextLine().trim());
    }

    public double entraNP2() {
        System.out.println("Entre com a nota da NP2");
        Scanner in = new Scanner(System.in);
        return Double.parseDouble(in.nextLine().trim());
    }

    public double entraReposicao() {
        System.out.println("Entre com a nota da reposição");
        Scanner in = new Scanner(System.in);
        return Double.parseDouble(in.nextLine().trim());
    }

    public double entraExame() {
        System.out.println("Entre com a nota do exame");
        Scanner in = new Scanner(System.in);
        return Double.parseDouble(in.nextLine().trim());
    }
}
