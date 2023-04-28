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

import Controllers.AlunoController;
import Entities.Aluno;

public class AcessoAluno {
    private String filePath;
    private AlunoController alunoController;

    public AcessoAluno(String aFilePath, AlunoController alunoController) {
        this.alunoController = alunoController;
        this.filePath = aFilePath;
    }

    public void loadAluno() {
        try (InputStream is = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] palavras = linha.split(";");

                String id = palavras[0];
                String nome = palavras[1];
                System.out.println(id);
                System.out.println(nome);

                Aluno aluno = new Aluno(id, nome);
                alunoController.addAluno(aluno);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveAlunos() {

        try (OutputStream os = new FileOutputStream(filePath/* , true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);) {
            for (Aluno p : alunoController.getAlunos()) {
                pw.println(p.getId() + "," + p.getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
