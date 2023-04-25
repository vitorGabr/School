import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.AcessoAluno;
import Entities.Aluno;
import Entities.Curso;
import Entities.Rendimento;

public class Controller {
    public static void main(String[] args) {

        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Curso> cursos = new ArrayList<>();
        ArrayList<Rendimento> rendimentos = new ArrayList<>();

        int acao = 1;

        while (acao >= 1) {
            try {
                Scanner in = new Scanner(System.in);
                switch (acao) {
                    case 1:
                        List<Aluno> als = AcessoAluno.loadAluno("files/alunos.csv");

                        for (Aluno aluno : als) {
                            System.out.println(aluno.toString());
                        }
                        break;

                    default:
                        break;
                }
                System.out.println("Digite uma açã0");
                String ac = in.nextLine();
                int numeroInteiro = Integer.parseInt(ac);
                acao = numeroInteiro;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }
}
