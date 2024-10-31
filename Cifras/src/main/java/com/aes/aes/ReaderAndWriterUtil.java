package com.aes.aes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author NANDONES
 */
public class ReaderAndWriterUtil {

    static BufferedReader reader = null;
    static BufferedWriter writer = null;

    public static String LeInputTXT() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        StringBuilder content = new StringBuilder();
        String line;

        // Lê o arquivo linha por linha
        while ((line = reader.readLine()) != null) {
            // Adiciona a linha lida ao StringBuilder
            content.append(line).append("\n"); // Adiciona a nova linha manualmente
        }
        content.setLength(content.length() - 1);//remove o último '/n'
        reader.close();

        //só pra dar output pelo retorno visual
        char[] charArray = content.toString().toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println("[" + charArray[i] + "](" + ((int) charArray[i]) + ")");
        }

        return content.toString();
    }
    
    public static void EscreveOutputTXT(String teste) throws IOException {
        writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(teste);
        writer.close();
        System.out.println("era pra ter escrito");
    }
}
