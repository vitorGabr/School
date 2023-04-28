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

import Controllers.RendimentoController;
import Entities.Rendimento;

public class AcessoRendimento {
    private String filePath;
    private RendimentoController rendiController;

    public AcessoRendimento(String aFilePath, RendimentoController rendiController) {
        this.rendiController = rendiController;
        this.filePath = aFilePath;
    }

    public void loadRendimentos() {
        try (InputStream is = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] palavras = linha.split(";");

                String aluno_id = palavras[0];
                String curso_id = palavras[1];
                double np1 = Double.parseDouble(palavras[2]);
                double np2 = Double.parseDouble(palavras[3]);
                double reposicao = Double.parseDouble(palavras[4]);
                double exame = Double.parseDouble(palavras[5]);

                Rendimento rendimento = new Rendimento(aluno_id, curso_id, np1, np2, reposicao, exame);
                rendiController.addRendimento(rendimento);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveCurso() {

        try (OutputStream os = new FileOutputStream(filePath/* , true */);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);) {
            for (Rendimento p : rendiController.getRendimentos()) {
                pw.println(p.getId() + "," + p.getNp1());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
