package org.example;

import java.util.Scanner;

public class Desafio_3 {

    public static void main(String args[]) throws Exception {

        Scanner entrada = new Scanner(System.in);
        System.out.println("Sequência Fibonacci (Recursividade).");
        System.out.println("Imprimir o valor relativo ao N número da Sequência Finonacci.");
        System.out.println("Digite o valor de N: ");

        int numeroN = entrada.nextInt();
        int resultadoN = fibonacci(numeroN);

        System.out.println("O valor digitado foi: " + numeroN);
        System.out.println();
        System.out.println("O resultado da sequência Fibonacci é: " + resultadoN);

        entrada.close();
    }

    //Método Fibonacci
    // Função fibonacci que calcula o N-ésimo termo da Sequência de Fibonacci usando recursão.
    // Se n for menor ou igual a 1, retornamos n como resultado. Caso contrário,
    // calculamos o termo somando os termos fibonacci(n - 1) e fibonacci(n - 2)
    public static int fibonacci(int numeroN) {
        if (numeroN <= 1) {
            return numeroN;
        } else {
            return fibonacci(numeroN - 1) + fibonacci(numeroN - 2);
        }
    }

}
