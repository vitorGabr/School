package DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import Controllers.RendimentoController;
import Entities.Rendimento;

public class AcessoRendimento extends DAO {
    private RendimentoController rendiController;

    public AcessoRendimento(List<String> aFilePath, RendimentoController rendiController) {
        super(aFilePath);
        this.rendiController = rendiController;
    }

    public void loadRendimentos() {
        Map<String, List<String>> palvrs = this.load();
        for (String key : palvrs.keySet()) {
            List<String> lista = palvrs.get(key);
            for (String palavra : lista) {
                String[] palavras = palavra.split(";");
                String aluno_id = palavras[0];
                String curso_id = key;
                double np1 = Double.parseDouble(palavras[1]);
                double np2 = Double.parseDouble(palavras[2]);
                double reposicao = Double.parseDouble(palavras[3]);
                double exame = Double.parseDouble(palavras[4]);

                Rendimento rendimento = new Rendimento(aluno_id, curso_id, np1, np2, reposicao, exame);
                rendiController.addRendimento(rendimento);
            }
        }
    }

    public void saveRendimentos() {
        Map<String, List<Rendimento>> rendimentos = rendiController.getRendimentos();
        for (String p : rendimentos.keySet()) {
            List<Rendimento> rendimento = rendimentos.get(p);
            String path = "files/" + p + ".csv";
            try (OutputStream os = new FileOutputStream(path/* , true */);
                    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                    PrintWriter pw = new PrintWriter(osw, true);) {
                for (Rendimento r : rendimento) {
                    System.out.println(r.getAlunoId());
                    pw.println(r.getAlunoId() + ";" + r.getNp1() + ";" + r.getNp2() + ";" +
                            r.getReposicao() + ";"
                            + r.getExame());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
