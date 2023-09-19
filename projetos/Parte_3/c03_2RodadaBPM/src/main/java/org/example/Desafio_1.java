package org.example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Desafio_1 {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        //Ler o número de linhas digitada pelo usuário
        System.out.println("Digite a quantidade de números a serem ordenados: ");
        int linhas = input.nextInt();

        //Criar uma lista de inteiros
        List<Integer> listaNumeros = new ArrayList<>();

        System.out.println("Digite os números inteiros não negativos: ");


        //Loop para percorrer as linhas digitadas pelo usuário
        for (int i = 0; i < linhas; i++) {
            int numero = input.nextInt();

            //Aqui é para validar se o número digitado é não negativo
            if (numero < 0) {
                System.out.println("Números negativos não são aceitos!");
                i--;
                continue;
            }
            listaNumeros.add(numero);
        }
        //Ordena os números pares em ordem crescente
        var pares = listaNumeros.stream().filter(l -> l % 2 == 0).sorted().collect(Collectors.toList());

        //Ordena os números ímpares em ordem decrescente
        var impares = listaNumeros.stream().filter(l -> l % 2 != 0)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        //Combinação dos números pares e ímpares
        List<Integer> total = new ArrayList<>();
        total.addAll(pares);
        total.addAll(impares);

        //Imprime os números
        System.out.println("Os números ordenados são: ");
        total.forEach(System.out::println);

    }


}
