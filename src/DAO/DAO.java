package DAO;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Interfaces.Output;

public abstract class DAO {
    private List<String> filePath;

    public DAO(List<String> aFilePath) {
        this.filePath = aFilePath;
    }

    protected Map<String, List<String>> load() {
        Map<String, List<String>> lines = new TreeMap<>();
        for (String path : this.filePath) {
            try (InputStream is = new FileInputStream(path);
                    InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);) {
                String linha;
                File file = new File(path);
                List<String> linhas = new ArrayList<>();
                while ((linha = br.readLine()) != null) {
                    linhas.add(linha);
                }
                lines.put(file.getName().replace(".csv", ""), linhas);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("A string não pode ser um int.");
            }
        }

        return lines;

    }

    protected <T> void save(Collection<T> collection) {
        for (String path : this.filePath) {
            try (OutputStream os = new FileOutputStream(path/* , true */);
                    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                    PrintWriter pw = new PrintWriter(osw, true);) {
                for (Object p : collection) {
                    if (p instanceof Output) {
                        pw.println(((Output) p).saida());
                    } else {
                        pw.println(p);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
