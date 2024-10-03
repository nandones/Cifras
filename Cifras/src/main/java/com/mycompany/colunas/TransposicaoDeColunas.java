package com.mycompany.colunas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
    static char[] outputArray = null;
    static char[][] colunas = null;
    /*[coluna][linha]*/
    static char[] chaveArray = null;
    static int quantidadeLinhas;

    public static void main(String[] args) {

        //LEITURA
        LeituraDoInputTxt();
        if (opcao == 'd' || opcao == 'D') {
            descriptografa();
        } else {
            criptografa();
        }

    }

    /**
     * cria o inputArray e solicita c para criptografar e d para
     * descriptografar, alterando<br>
     * a variável opcao
     */
    public static void LeituraDoInputTxt() {
        try {
            Scanner input = new Scanner(System.in);
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader("input.txt"));

            System.out.println("Qual a chave?");
            chaveSTR = input.next();
            chaveArray = chaveSTR.toCharArray();
            System.out.println("digite \"d\" para descriptografar e \"c\" para criptografar");
            opcao = input.next().charAt(0);

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            inputArray = content.toString().toCharArray();

        } catch (IOException ex) {
            Logger.getLogger(TransposicaoDeColunas.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static void criptografa() {
        calculaLinhas();
        transformaVetorInputEmMatriz();
        printarColunas();
        System.out.println("");
        ordenaMatrizOrdemAlfabetica();
        printarColunas();
        criaOutputArray();
        escreveTxtCriptografado();
    }

    public static void descriptografa() {
        calculaLinhas();
        transformaVetorInputEmMatriz();
        printarColunas();
        System.out.println("");
        ordenaMatrizOrdemPelaChave();
        printarColunas();
        criaOutputArray();
        escreveTxtCriptografado();
    }

    /**
     * calcula a quantidade de linhas que cada coluna terá.<br>
     * atribui essa quantidade a variavel quantidadeLinhas.
     */
    public static void calculaLinhas() {
        quantidadeLinhas = Math.round((float) inputArray.length / chaveArray.length);
    }

    /**
     * preenche a matriz colunas [coluna][linha] com os char's provenientes do
     * inputArray.<br>
     * a linha zero recebe a chave. o espaço[j][i] que ficar em branco recebe
     * Ø.<br>
     * o código para descriptografar não tem como ficar em branco.
     */
    public static void transformaVetorInputEmMatriz() {
        colunas = new char[chaveArray.length][(quantidadeLinhas + 1)];
        /*
         ----------------------->chaveArray.length
        ⬇ | letra letra   ... |
        ⬇ | codig codig   ... |
        ⬇ | codig codig   ... |
        ⬇ | codig codig   ... |
        ⬇
        ️(quantidadeLinhas+1)
         */

        //primeiro preecher [0][0-n] com a chave:
        for (int i = 0; i < chaveArray.length; i++) {
            colunas[i][0] = chaveArray[i];
        }

        //preenche o resto da matriz
        int contadorDoInput = 0;
        for (int i = 1; i < (quantidadeLinhas + 1); i++) {
            for (int j = 0; j < chaveArray.length; j++) {
                if (contadorDoInput < inputArray.length) {
                    colunas[j][i] = inputArray[contadorDoInput];
                    contadorDoInput++;
                } else {
                    colunas[j][i] = 'Ø';
                }

            }

        }
    }

    /**
     * metodo para embaralhar seguindo a criptografia.
     */
    public static void ordenaMatrizOrdemAlfabetica() {
        // Array auxiliar para armazenar as colunas
        char[][] matrizAux = new char[chaveArray.length][quantidadeLinhas + 1];

        // Preenche o array auxiliar com as colunas da matriz original (incluindo a linha da chave)
        for (int i = 0; i < chaveArray.length; i++) {
            for (int j = 0; j <= quantidadeLinhas; j++) { // <= para incluir a última linha
                matrizAux[i][j] = colunas[i][j];
            }
        }

        // Ordena as colunas com base nos valores da primeira linha (linha 0)
        Arrays.sort(matrizAux, (col1, col2) -> Character.compare(col1[0], col2[0]));

        // Copia as colunas ordenadas de volta para a matriz original
        for (int i = 0; i < chaveArray.length; i++) {
            for (int j = 0; j <= quantidadeLinhas; j++) { // <= para incluir a última linha
                colunas[i][j] = matrizAux[i][j];
            }
        }
    }

    /**
     * metodo para desembaralhar seguindo a chave.
     */
    public static void ordenaMatrizOrdemPelaChave() {
        // Criar uma matriz auxiliar para armazenar as colunas
        char[][] matrizAux = new char[chaveArray.length][quantidadeLinhas + 1];

        // Preenche o array auxiliar com as colunas da matriz original (incluindo a linha da chave)
        for (int i = 0; i < chaveArray.length; i++) {
            for (int j = 0; j <= quantidadeLinhas; j++) { // <= para incluir a última linha
                matrizAux[i][j] = colunas[i][j];
            }
        }

        for (int charChave = 0; charChave < chaveArray.length; charChave++) {
            for (int colunaEmQuestao = 0; colunaEmQuestao < chaveArray.length; colunaEmQuestao++) {
                if(chaveArray[charChave]== matrizAux[colunaEmQuestao][0]){
                    for (int i = 0; i < matrizAux[0].length; i++) {
                        colunas[colunaEmQuestao][i] = matrizAux[charChave][i];
                    }
                }
            }
        }
        System.out.println("\n\nteste\n\n");
        for (int i = 0; i < colunas.length; i++) {
            for (int j = 0; j < colunas[0].length; j++) {
                System.out.print(colunas[i][j]);
            }
            System.out.println("");
        }

 
    }

    /**
     * cria o outputArray , empilhando as colunas em ordem crescente da primeira
     * linha das colunas.<br>
     * Apaga também os valores da chave, anteriormente utilizados para formar a
     * matriz da função printar.
     *
     * @see printar()
     */
    public static void criaOutputArray() {
        int tamanhoFinal = (colunas.length * colunas[0].length);
        outputArray = new char[tamanhoFinal];

        int contadorPosicaoOutputArray = 0;

        // Itera pelas colunas e pelas linhas da matriz, montando o array criptografado
        System.out.println("{");
        for (int i = 0; i < chaveArray.length; i++) {
            for (int j = 1; j <= quantidadeLinhas; j++) { // Começa de 1 para ignorar a linha da chave
                outputArray[contadorPosicaoOutputArray] = colunas[i][j];
                System.out.print(outputArray[contadorPosicaoOutputArray]); // Exibe o caractere
                contadorPosicaoOutputArray++;
            }
        }
        System.out.println("}");
    }

    public static void escreveTxtCriptografado() {
        System.out.println("escreveTxtCriptografado{");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
            StringBuilder content = new StringBuilder();
            for (char c : outputArray) {
                content.append(c);
            }
            System.out.println(content.toString());
            String txtCriptografado = content.toString();
            System.out.println(txtCriptografado);
            writer.write(txtCriptografado);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TransposicaoDeColunas.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("}");

    }

    public static void printarColunas() {
        for (int i = 0; i < quantidadeLinhas + 1; i++) {
            System.out.print("| ");
            for (int j = 0; j < chaveArray.length; j++) {
                System.out.print(colunas[j][i] + " ");
            }
            System.out.print(" |\n");
        }
    }
}
