package Views;

import java.util.ArrayList;
import java.util.List;

import Controllers.AlunoController;
import Controllers.CursoController;
import Controllers.RendimentoController;
import DAO.AcessoAluno;
import DAO.AcessoCurso;
import DAO.AcessoRendimento;
import Entities.Aluno;
import Entities.Curso;
import Utils.LeitorDeDados;

public class View {

    private AcessoAluno acessoAlunos;
    private AcessoCurso acessoCursos;
    private AcessoRendimento acessoRendimento;
    private AlunoController alunoController;
    private CursoController cursoController;
    private RendimentoController rendimentoController;

    private AlunoView alunoView;
    private CursoView cursoView;
    private RendimentoView rendimentoView;

    private LeitorDeDados leitor = new LeitorDeDados();

    public View() {
        alunoController = new AlunoController();
        cursoController = new CursoController();
        rendimentoController = new RendimentoController();
        acessoCursos = new AcessoCurso("files/cursos.csv", cursoController);
        acessoAlunos = new AcessoAluno("files/alunos.csv", alunoController);
        acessoRendimento = new AcessoRendimento(new ArrayList<>(), rendimentoController);
        alunoView = new AlunoView(alunoController);
        cursoView = new CursoView(cursoController);
        rendimentoView = new RendimentoView(rendimentoController, alunoController, cursoController);
    }

    public void init() {
        acessoAlunos.loadAluno();
        acessoCursos.loadCurso();
        List<String> rendimentosPath = new ArrayList<>();
        for (Curso curso : cursoController.getCursos()) {
            rendimentosPath.add("files/" + curso.getId() + ".csv");
        }
        acessoRendimento.loadRendimentos(rendimentosPath);
        options();
    }

    public void options() {
        int opcao = 0;
        do {
            opcao = getOpcao();
            switch (opcao) {
                case 1:
                    alunoView.adicionarAluno();
                    break;
                case 2:
                    alunoView.listaTodosAlunos();
                    break;
                case 3:
                    cursoView.adicionarCurso();
                    break;
                case 4:
                    cursoView.listaTodosCursos();
                    break;
                case 5:
                    cursoView.listarCusosByAno();
                    break;
                case 6:
                    rendimentoView.listarAlunoByRa();
                    break;
                case 7:
                    listarAlunosByCurso();
                    break;
                case 8:
                    cadastrarRendimento();
                    break;
                case 0:
                    sair();
                    break;
            }
        } while (opcao != 0);
        System.exit(0);
    }

    public int getOpcao() {

        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Escolha a opcao:");
        System.out.println("1 - Cadastre alunos");
        System.out.println("2 - Listar alunos");
        System.out.println("3 - Cadastre as matérias");
        System.out.println("4 - Listar todas as matérias cadastradas");
        System.out.println("5 - Listar matérias de um ano específico");
        System.out.println("6 - Escolher um aluno (pelo RA) e mostrar o relatório do aluno com");
        System.out.println("7 - Escolher uma matéria (pelo nome, ano e nível) e mostrar o relatório da matéria com");
        System.out.println("8 - Cadastre um Rendimento");
        System.out.println("0 - para sair do programa");
        System.out.println("--------------------------------");
        System.out.println();

        return leitor.lerInteiro("");

    }

    public void listarAlunosByCurso() {
        Curso curso = this.cursoView.listarCursoById();
        if (curso == null) {
            System.out.println("Não existe curso cadastrado como esses valores");
            return;
        }
        rendimentoView.listarRendimentosByCurso(curso);
    }

    public void cadastrarRendimento() {
        Curso curso = this.cursoView.listarCursoById();
        Aluno aluno = this.alunoView.listarAlunoById();
        if (curso == null) {
            System.out.println("Não existe curso cadastrado como esses valores");
            return;
        }
        rendimentoView.adicionarRendimento(aluno.getId(), curso.getId());
    }

    public void sair() {
        System.out.println("\nSaindo do programa");
        System.out.println("Salvando Alunos");
        System.out.println("Salvando Curso");
        System.out.println("Salvando Rendimentos\n");
        acessoAlunos.saveAlunos();
        acessoCursos.saveCurso();
        acessoRendimento.saveRendimentos();
    }

}
