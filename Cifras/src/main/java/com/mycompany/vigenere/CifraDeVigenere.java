package com.mycompany.vigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CifraDeVigenere {

    static String chaveSTR = "";
    static char opcao;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        char[] charArray = null;
        char[] chaveARR = null;

        //LEITURA
        try {
            reader = new BufferedReader(new FileReader("input.txt"));

            System.out.println("Qual a chave?");
            chaveSTR = input.next();
            chaveARR = chaveSTR.toCharArray();
            System.out.println("digite \"d\" para descriptografar e \"c\" para criptografar");
            opcao = input.next().charAt(0);

            StringBuilder content = new StringBuilder();
            String line;

            // Lê o arquivo linha por linha
            while ((line = reader.readLine()) != null) {
                // Adiciona a linha lida ao StringBuilder
                content.append(line).append("\n"); // Adiciona a nova linha manualmente
            }
            reader.close();

            // Converte o conteúdo para um vetor de char
            //ATENÇÃO:
            //ele acrescenta uma quebra de linha extra como último
            //caractere.
            charArray = content.toString().toCharArray();

        } catch (IOException ex) {
            Logger.getLogger(CifraDeVigenere.class.getName()).log(Level.SEVERE, null, ex);

        }

        //ESCRITA
        try {

            writer = new BufferedWriter(new FileWriter("output.txt"));
            StringBuilder content = new StringBuilder();
            //o -1 pula o último '\n'
            System.out.println("{");
            int posicaoCaracter = 0;
            for (int i = 0; i < charArray.length - 1; i++) { //pula o último \n
                int valor;
                if(posicaoCaracter == chaveARR.length){
                    posicaoCaracter = 0;
                }
                valor = (int) chaveARR[posicaoCaracter];

                if (opcao == 'd' || opcao == 'D') {
                    valor = valor * -1;
                }
                char newChar = (char) ((int) charArray[i] + valor);
                content.append(newChar);
                //System.out.println(" "+charArray[i]+" -> "+ newChar);
                //System.out.print("["+charArray[i]+"]");
                //System.out.print("["+newChar+"]");
                System.out.println("[" + charArray[i] + "](" + ((int) charArray[i]) + ") = [" + newChar + "] (" + ((int) newChar) + ")");
            }
            System.out.println("}");
            String txtCriptografado = content.toString();
            writer.write(txtCriptografado);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CifraDeVigenere.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
