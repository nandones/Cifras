package com.mycompany.colunas;

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
 * @author nandones
 */
public class TransposicaoDeColunas {

    static String chaveSTR = "";
    static char opcao;
    static char[] inputArray = null;
    static char[][] colunas = null; /*[coluna][linha]*/
    static char[] chaveArray = null;
    static int quantidadeLinhas;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        

        //LEITURA
        try {
            reader = new BufferedReader(new FileReader("input.txt"));

            System.out.println("Qual a chave?");
            chaveSTR = input.next();
            chaveArray = chaveSTR.toCharArray();
            System.out.println("digite \"d\" para descriptografar e \"c\" para criptografar");
            //opcao = input.next().charAt(0);

            StringBuilder content = new StringBuilder();
            String line;

            // Lê o arquivo linha por linha
            while ((line = reader.readLine()) != null) {
                // Adiciona a linha lida ao StringBuilder
                content.append(line);
            }
            reader.close();

            // Converte o conteúdo para um vetor de char
            inputArray = content.toString().toCharArray();

        } catch (IOException ex) {
            Logger.getLogger(TransposicaoDeColunas.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        calculaColunas();
        criptografar();
        printar();
        

        //ESCRITA
        /*try {

            writer = new BufferedWriter(new FileWriter("output.txt"));
            StringBuilder content = new StringBuilder();
            //o -1 pula o último '\n'
            System.out.println("{");
            int posicaoCaracter = 0;
            for (int i = 0; i < inputArray.length - 1; i++) { //pula o último \n
                int valor;
                if (posicaoCaracter == chaveArray.length) {
                    posicaoCaracter = 0;
                }
                valor = (int) chaveArray[posicaoCaracter];

                if (opcao == 'd' || opcao == 'D') {
                    valor = valor * -1;
                }
                char newChar = (char) ((int) inputArray[i] + valor);
                content.append(newChar);
                //System.out.println(" "+charArray[i]+" -> "+ newChar);
                //System.out.print("["+charArray[i]+"]");
                //System.out.print("["+newChar+"]");
                System.out.println("[" + inputArray[i] + "](" + ((int) inputArray[i]) + ") = [" + newChar + "] (" + ((int) newChar) + ")");
            }
            System.out.println("}");
            String txtCriptografado = content.toString();
            writer.write(txtCriptografado);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TransposicaoDeColunas.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
    
    public static void calculaColunas (){
        quantidadeLinhas = Math.round((float) inputArray.length/chaveArray.length);
    }
    
    public static void criptografar (){
        colunas = new char[chaveArray.length][(quantidadeLinhas + 1)];
        /*
        ---------------------->chaveArray.length
        | letra letra   ... |
        | codig codig   ... |
        | codig codig   ... |
        | codig codig   ... |
        ⬇
        ️(quantidadeLinhas+1)
        */
        
        //primeiro preecher [0][0-n] com a chave:
        for (int i = 0; i < chaveArray.length; i++) {
            colunas[i][0] = chaveArray[i];
        }
        
        //preenche o resto da matriz
        int contadorDoInput = 0;
        for (int i = 1; i < (quantidadeLinhas+1); i++) {
            for (int j = 0; j < chaveArray.length; j++) {
                if(contadorDoInput<inputArray.length){
                colunas[j][i] = inputArray[contadorDoInput];
                contadorDoInput++;
                }
                
            }
            
        }
    }
    
    public static void printar(){
        for (int i = 0; i < quantidadeLinhas+1; i++) {
            System.out.print("| ");
            for (int j = 0; j < chaveArray.length; j++) {
                System.out.print(colunas[j][i]+" ");
            }
            System.out.print(" |\n");
        }
    }
}
