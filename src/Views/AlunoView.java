package Views;

import java.util.Collection;
import java.util.Scanner;

import Controllers.AlunoController;
import DAO.AcessoAluno;
import Entities.Aluno;

public class AlunoView {

    AcessoAluno acessoAlunos;
    AlunoController alunoController;

    public AlunoView() {
        alunoController = new AlunoController();
        acessoAlunos = new AcessoAluno("files/alunos.csv", alunoController);
    }

    public void init() {
        acessoAlunos.loadPatos();
        options();
    }

    public void options() {
        int opcao = 0;
        do {
            opcao = getOpcao();
            switch (opcao) {
                case 1:
                    adicionaPato();
                    break;
                case 2:
                    listaTodosPatos();
                    break;
                case 3:
                    listaPatosByNome();
                    break;
                case 4:
                    encontraPatoById();
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
        System.out.println("1 - para adicionar Pato");
        System.out.println("2 - listar todos patos");
        System.out.println("3 - listar todos patos por nome");
        System.out.println("4 - encontrar pato pelo id");
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

    public void sair() {
        System.out.println("saindo do programa");
        System.out.println("salvando patos");
        acessoAlunos.saveAlunos();
    }

    public void adicionaPato() {
        Aluno aluno = entrAluno();
        if (aluno == null) {
            return;
        }
        if (alunoController.addAluno(aluno)) {
            System.out.println("Adicionando Pato " + aluno);
        } else {
            System.out.println("Falha ao adicionar pato " + aluno);
        }

    }

    public void listaTodosPatos() {
        System.out.println("Listando todos os patos");
        for (Aluno p : alunoController.getAlunos()) {
            System.out.println(p);
        }
    }

    public void listaPatosByNome() {
        String nome = entraNome();
        listaPatosByNome(nome);
    }

    public void listaPatosByNome(String keyNome) {
        System.out.println("Listando todos os patos.nome contem \"" + keyNome + "\"");
        Collection<Aluno> alunos = alunoController.getAlunosByNome(keyNome);
        if (alunos.size() == 0) {
            System.out.println("Nenhum pato em que nome contem " + keyNome);
        }
        System.out.println("   " + alunos.size() + " patos encontrados");
        for (Aluno p : alunos) {
            System.out.println(p);
        }
        System.out.println();
    }

    public void encontraPatoById() {
        String id = entraId();
        encontraPatoById(id);
    }

    public void encontraPatoById(String keyId) {
        System.out.println("Procurando pato com id \"" + keyId + "\"");
        Aluno p = alunoController.getAlunoById(keyId);
        if (p == null) {
            System.out.println("Pato nao encontrado");
        } else {
            System.out.println("Pato encontrado:");
            System.out.println(p);
        }
    }

    public Aluno entrAluno() {
        String id = entraId();

        Aluno temAluno = alunoController.getAlunoById(id);
        if (temAluno != null) {
            System.out.println("Já temos um pato com este id:");
            System.out.println(temAluno);
            return null;
        }

        String nome = entraNome();
        return new Aluno(id, nome);
    }

    public String entraId() {
        System.out.println("Entre com o Id do pato");
        Scanner in = new Scanner(System.in);

        String id = in.nextLine();

        return id.trim();
    }

    public String entraNome() {
        System.out.println("Entre com o nome do pato");
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

}
