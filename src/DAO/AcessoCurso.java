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
import java.util.ArrayList;
import java.util.List;

import Entities.Curso;

public class AcessoCurso {
    public static List<Curso> loadCurso(String filePath) {

        List<Curso> cursos = new ArrayList<>();

        try (InputStream is = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);) {
            String linha;
            int i = 0;
            while ((linha = br.readLine()) != null) {

                System.out.println("linha " + i++);

                String[] palavras = linha.split(",");

                for (String p : palavras) {
                    System.out.println("palavra: " + p);
                }

                String nome = palavras[0];
                String nivel = palavras[1];
                int ano = Integer.parseInt(palavras[2]);

                Curso curso = new Curso(nome, nivel, ano);
                cursos.add(curso);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cursos;

    }

    public static void saveAlunos(List<Curso> cursos, String filePath) {

        try (OutputStream os = new FileOutputStream(filePath/* , true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);) {
            for (Curso aluno : cursos) {
                pw.println(aluno.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
