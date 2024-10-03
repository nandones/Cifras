package com.mycompany.cesar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NANDONES
 */
public class CifraDeCesar {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        char[] charArray = null;
        int chave = 0;
        char opcao;

        //LEITURA
        try {
            reader = new BufferedReader(new FileReader("input.txt"));

            System.out.println("Qual o valor da chave?"
                    + "65536 = 0");
            chave = input.nextInt();
            System.out.println("digite \"d\" para descriptografar e \"c\" para criptografar");
            opcao = input.next().charAt(0);
            if (opcao == 'd' || opcao == 'D') {
                chave = chave * -1;
            }

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
            Logger.getLogger(CifraDeCesar.class.getName()).log(Level.SEVERE, null, ex);

        }

        //ESCRITA
        try {

            writer = new BufferedWriter(new FileWriter("output.txt"));
            StringBuilder content = new StringBuilder();
            //o -1 pula o último '\n'
            System.out.println("{");
            for (int i = 0; i < charArray.length - 1; i++) { //pula o último \n

                char newChar = (char) ((int) charArray[i] + chave);
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
            Logger.getLogger(CifraDeCesar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
