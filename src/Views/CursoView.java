package Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Controllers.CursoController;
import Entities.Curso;

public class CursoView {

    private CursoController cursoController;

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

    private Curso entrarCurso() {
        String nome = entraNome();
        String nivel = entraNivel();
        if (!verificaNivel(nivel)) {
            System.out.println("Você precisa digitar um valor válido para o nível!!! ");
            return null;
        }
        int ano = entraAno();

        String id = nome + nivel + ano;

        Curso temCurso = cursoController.getCursoById(id);
        if (temCurso != null) {
            System.out.println("Já temos um curso cadastrado !!!");
            System.out.println(temCurso);
            return null;
        }

        return new Curso(nome, nivel, ano);
    }

    private List<Curso> listarCursosByAno() {
        int ano = entraAno();
        return cursoController.getCursoByAno(ano);
    }

    private String entraNome() {
        System.out.println("Entre com o nome do curso");
        Scanner in = new Scanner(System.in);

        String id = in.nextLine();

        return id.trim();
    }

    private String entraNivel() {
        System.out.println("Entre com o nivel do curso");
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    private int entraAno() {
        System.out.println("Entre com o ano do curso");
        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine().trim());
    }

    private boolean verificaNivel(String _nivel) {
        List<String> niveis = new ArrayList<>(Arrays.asList("GRADUACAO", "POS_GRADUACAO"));
        return niveis.contains(_nivel);
    }

}
