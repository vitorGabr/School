package Views;

import Controllers.AlunoController;
import Entities.Aluno;
import Utils.LeitorDeDados;

public class AlunoView {

    private AlunoController alunoController;
    private LeitorDeDados leitor = new LeitorDeDados();

    public AlunoView(AlunoController controller) {
        this.alunoController = controller;
    }

    public void adicionarAluno() {
        Aluno aluno = entrarAluno();
        if (aluno == null) {
            return;
        }
        if (alunoController.addAluno(aluno)) {
            System.out.println("\nAdicionando Aluno " + aluno);
        } else {
            System.out.println("\nFalha ao adicionar aluno " + aluno);
        }
    }

    public void listaTodosAlunos() {
        System.out.println("\nListando todos os alunos");
        for (Aluno p : alunoController.getAlunos()) {
            System.out.println(p);
        }
    }

    public Aluno listarAlunoById() {
        String id = leitor.lerString("\nEntre com o Id do aluno: ");
        Aluno temAluno = alunoController.getAlunoById(id);
        if (temAluno == null) {
            System.out.println("\nNão temos nenhum aluno cadastrado com esse RA!!!");
            return null;
        }
        return temAluno;
    }

    private Aluno entrarAluno() {
        String id = leitor.lerString("\nEntre com o Id do aluno: ");

        Aluno temAluno = alunoController.getAlunoById(id);
        if (temAluno != null) {
            System.out.println("\nJá temos um aluno com este id:");
            System.out.println(temAluno);
            return null;
        }

        String nome = leitor.lerString("\nEntre com o Nome do aluno: ");
        return new Aluno(id, nome);
    }

}
