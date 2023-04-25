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

import Entities.Aluno;

public class AcessoAluno {
    public static List<Aluno> loadAluno(String filePath) {

        List<Aluno> alunos = new ArrayList<>();

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

                String id = palavras[0];
                String nome = palavras[1];

                Aluno aluno = new Aluno(id, nome);
                alunos.add(aluno);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return alunos;

    }

    public static void saveAlunos(List<Aluno> alunos, String filePath) {

        try (OutputStream os = new FileOutputStream(filePath/* , true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);) {
            for (Aluno aluno : alunos) {
                pw.println(aluno.getId() + "," + aluno.getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
