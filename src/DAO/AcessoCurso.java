package DAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import Controllers.CursoController;
import Entities.Curso;

public class AcessoCurso {
    private String filePath;
    private CursoController cursoController;

    public AcessoCurso(String aFilePath, CursoController alunoController) {
        this.cursoController = alunoController;
        this.filePath = aFilePath;
    }

    public void loadCurso() {
        try (InputStream is = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] palavras = linha.split(";");

                String nome = palavras[0];
                String nivel = palavras[1];
                int ano = Integer.parseInt(palavras[2]);

                Curso curso = new Curso(nome, nivel, ano);
                cursoController.addCurso(curso);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveCurso() {

        try (OutputStream os = new FileOutputStream(filePath/* , true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);) {
            for (Curso p : cursoController.getCursos()) {
                pw.println(p.getNome() + ";" + p.getNivel() + ";" + p.getAno());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
