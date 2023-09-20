package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Desafio_2 {

    public static void main(String args[]) throws Exception {


        Scanner entrada = new Scanner(System.in);
        System.out.println("Valor inteiro para converter para Date. Digite um valor inteiro: ");
        long valorEntrada = entrada.nextLong();

        // Multiplicamos por 1000 para converter de segundos para milissegundos
        Date data = new Date(valorEntrada * 1000L);

        //Formatar a data de acordo com o formato especificado (dia/mês/ano horas:minutos:segundos).
        SimpleDateFormat converter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        //Fuso horário do SimpleDateFormat como UTC (GMT-0).
        converter.setTimeZone(TimeZone.getTimeZone("GMT-0"));

        //Método format para formatar a data e armazenamos o resultado em uma string.
        String dataFormatada = converter.format(data);
        System.out.println("O Inteiro que digitou foi: " + valorEntrada);
        System.out.println("A Data formatada é: " + dataFormatada);
        entrada.close();

    }
}