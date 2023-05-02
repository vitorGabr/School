package Views;

import java.util.List;
import java.util.NoSuchElementException;

import Controllers.CursoController;
import Entities.Curso;
import Enums.NivelCurso;
import Utils.LeitorDeDados;

public class CursoView {

    private CursoController cursoController;
    private LeitorDeDados leitor = new LeitorDeDados();

    public CursoView(CursoController controller) {
        this.cursoController = controller;
    }

    public void adicionarCurso() {
        Curso curso = entrarCurso();
        if (curso == null) {
            return;
        }
        if (cursoController.addCurso(curso)) {
            System.out.println("Adicionando Curso " + curso);
        } else {
            System.out.println("Já existe um curso cadastrado com esses valores: " + curso);
        }

    }

    public void listaTodosCursos() {
        System.out.println("Listando todos os cursos");
        for (Curso p : cursoController.getCursos()) {
            System.out.println(p);
        }
    }

    public void listarCusosByAno() {
        List<Curso> _cursos = listarCursosByAno();
        for (Curso p : _cursos) {
            System.out.println(p);
        }
    }

    public Curso listarCursoById() {
        String nome = leitor.lerString("Entre com o nome do curso: ");
        String nivel = leitor.lerString("Entre com o nível do curso: ");
        int ano = leitor.lerInteiro("Entre com o ano do curso: ");
        return cursoController.getCursoById(nome + "_" + nivel + "_" + ano);
    }

    private Curso entrarCurso() {
        try {
            String nome = leitor.lerString("Entre com o nome do curso: ");
            NivelCurso nivel = NivelCurso.fromString(leitor.lerString("Entre com o nível do curso: "));
            int ano = leitor.lerInteiro("Entre com o ano do curso: ");

            String id = nome + nivel + ano;

            Curso temCurso = cursoController.getCursoById(id);
            if (temCurso != null) {
                System.out.println("Já temos um curso cadastrado !!!");
                System.out.println(temCurso);
                return null;
            }

            return new Curso(nome, nivel, ano);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<Curso> listarCursosByAno() {
        int ano = leitor.lerInteiro("Entre com o ano do curso: ");
        return cursoController.getCursoByAno(ano);
    }
}
