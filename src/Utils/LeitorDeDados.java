package Utils;

import java.util.Scanner;

public class LeitorDeDados {
  private Scanner scanner;

  public LeitorDeDados() {
    scanner = new Scanner(System.in);
  }

  public String lerString(String input) {
    System.out.print(input != null ? input : "Digite uma string: ");
    return scanner.nextLine();
  }

  public int lerInteiro(String input) {

    int valor = 0;
    boolean entradaValida = false;
    do {
      try {
        System.out.print(input != null ? input : "Digite um inteiro: ");
        valor = Integer.parseInt(scanner.nextLine());
        entradaValida = true;
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida! Digite um número inteiro.");
      }
    } while (!entradaValida);
    return valor;
  }

  public double lerDouble(String input) {
    double valor = 0;
    boolean entradaValida = false;
    do {
      try {
        System.out.print(input != null ? input : "Digite um double: ");
        valor = Double.parseDouble(scanner.nextLine());
        entradaValida = true;
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida! Digite um número decimal.");
      }
    } while (!entradaValida);
    return valor;
  }
}