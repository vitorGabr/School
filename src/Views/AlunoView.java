package Views;

import java.util.Scanner;

import Controllers.AlunoController;
import Entities.Aluno;

public class AlunoView {

    private AlunoController alunoController;

    public AlunoView(AlunoController controller) {
        this.alunoController = controller;
    }

    public void adicionarAluno() {
        Aluno aluno = entrarAluno();
        if (aluno == null) {
            return;
        }
        if (alunoController.addAluno(aluno)) {
            System.out.println("Adicionando Aluno " + aluno);
        } else {
            System.out.println("Falha ao adicionar aluno " + aluno);
        }

    }

    public void listaTodosAlunos() {
        System.out.println("Listando todos os alunos");
        for (Aluno p : alunoController.getAlunos()) {
            System.out.println(p);
        }
    }


    private Aluno entrarAluno() {
        String id = entraId();

        Aluno temAluno = alunoController.getAlunoById(id);
        if (temAluno != null) {
            System.out.println("Já temos um aluno com este id:");
            System.out.println(temAluno);
            return null;
        }

        String nome = entraNome();
        return new Aluno(id, nome);
    }

    public String entraId() {
        System.out.println("Entre com o Id do aluno");
        Scanner in = new Scanner(System.in);

        String id = in.nextLine();

        return id.trim();
    }

    public String entraNome() {
        System.out.println("Entre com o nome do aluno");
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public AlunoController getController() {
        return alunoController;
    }

}
