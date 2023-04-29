package Views;

import java.util.Scanner;

import Controllers.AlunoController;
import Controllers.CursoController;
import Controllers.RendimentoController;
import DAO.AcessoAluno;
import DAO.AcessoCurso;
import DAO.AcessoRendimento;

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

    public View() {
        alunoController = new AlunoController();
        cursoController = new CursoController();
        rendimentoController = new RendimentoController();
        acessoCursos = new AcessoCurso("files/cursos.csv", cursoController);
        acessoAlunos = new AcessoAluno("files/alunos.csv", alunoController);
        alunoView = new AlunoView(alunoController);
        cursoView = new CursoView(cursoController);
        rendimentoView = new RendimentoView(rendimentoController, alunoController, cursoController);
    }

    public void init() {
        acessoAlunos.loadAluno();
        acessoCursos.loadCurso();
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
                    listarAlunosByCurso();
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

        Scanner in = new Scanner(System.in);
        String linha = in.nextLine();

        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            System.out.println("O valor entrado : " + linha + " nao eh valido");
            System.out.println("   a opcao deve ser um numero inteiro\n");
            return getOpcao();
        }

    }

    public void listarAlunosByCurso() {
        rendimentoView.listarRendimentosByCurso(this.cursoView.listarCursoById());
    }

    public void sair() {
        System.out.println("saindo do programa");
        System.out.println("salvando Alunos");
        acessoAlunos.saveAlunos();
        acessoCursos.saveCurso();
    }

}
