package org.example;

import java.util.Scanner;

public class Main {

    @SuppressWarnings("StringEquality")
    public static void main(String[] args) {
        clienteDAO dao = new clienteDAO();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Seu usuario: ");
            String user = scanner.nextLine();
            System.out.println("Sua senha: ");
            String senha = scanner.nextLine();
            String resultado1 = dao.verificarLogin(user, senha);
            String[] partes = resultado1.split(",");  // Divide a string usando a vírgula como delimitador
            String id = partes[0];  
            String nome = partes[1];  

            if (id != "0") {
                while (true) {
                    System.out.println("-=-".repeat(30));
                    System.out.println("Olá " + nome + "! seu id é: " + id);
                    System.out.println("-=-".repeat(30));
                    System.out.println("Oque você deseja fazer?");
                    System.out.println("1-Ver saldo");
                    System.out.println("2-Transferencia");
                    System.out.println("3-sair");
                    System.out.println("-=-".repeat(30));
                    String escolha = scanner.nextLine();
                    if (escolha.equals("1")) {
                        String resultado = dao.verificarsaldo(Integer.parseInt(id));
                        System.out.println(resultado);

                    }
                    if (escolha.equals("2")) {
                        System.out.println("Id de quem irá receber esse dinheiro: ");
                        int id_recebedor = Integer.parseInt(scanner.nextLine());
                        System.out.println("Valor: ");
                        double valor = Double.parseDouble(scanner.nextLine());
                        String resultado2 = dao.transferir(Integer.parseInt(id), id_recebedor,valor);
                        System.out.println(resultado2);
                    }
                    if (escolha.equals("3")) {
                        System.out.print("Saindo...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                    }
                }
            } else {
                System.out.println("Login inválido!");
            }
        }
    }
}
