/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.aes.aes;

import static com.aes.aes.AESUtil.generateIv;
import com.mycompany.colunas.TransposicaoDeColunas;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author nandones
 */
public class main {
    
    //public static IvParameterSpec iv;
    
    public static Scanner input = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //testandoChavesEivS();
        //AESUtil.teste();
        String plainTexto = ReaderAndWriterUtil.LeInputTXT();
        ReaderAndWriterUtil.EscreveOutputTXT("testehahahahah");
        /*System.out.println("Olá, estaremos testando a criptografia."
                + "\nPrimeiro, defina a String correspondente ao plainText:");
        String plainText = input.next();
        criptografa(plainText);
        
        System.out.println("Olá, estaremos agora testando a decriptografia."
                + "\nPrimeiro, defina a String correspondente ao encryptedText:");
        String encryptedText = input.next();
        
        descriptografa(encryptedText);*/
        
    }
    
    public static void criptografa(String plainText) throws Exception{
        System.out.println("gerando a chave AES...");
  
        SecretKey key = AESUtil.generateKey();
        
        //apenas para imprimir a chave
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Converte a chave para Base64
        System.out.println("Chave AES (Base64): " + encodedKey); // Imprime a chave AES codificada
        System.out.println("copie esta chave para descriptografar o texto");
        
        System.out.println("gerando o IV...");
        IvParameterSpec iv = generateIv(key);
        
        System.out.println("criptografando...");
        String encryptedText = AESUtil.encrypt(plainText, key, iv);
        System.out.println("Texto Criptografado: " + encryptedText);
    }
    
    public static void descriptografa(String encryptedText) throws Exception{
        System.out.println("digite aqui a chave AES(Base64):");
        String encodedKey = input.next();
        System.out.println("gerando a chave AES...");
        SecretKey key = AESUtil.generateKeyFromEncodedString(encodedKey);
        
        System.out.println("gerando o IV...");
        IvParameterSpec iv = generateIv(key);
        
        System.out.println("decriptografando...");
        String plainText = AESUtil.decrypt(encryptedText, key, iv);
        System.out.println("Texto Decriptografado: " + plainText);
    }
    
    public static void testandoChavesEivS() throws Exception{
        SecretKey key = AESUtil.generateKey();
        
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Converte a chave para Base64
        System.out.println("Chave AES (Base64): " + encodedKey); // Imprime a chave AES codificada
        System.out.println("copie esta chave para descriptografar o texto");
        
        System.out.println("digite aqui a chave AES(Base64):");
        String encodedKey2 = input.next();
        System.out.println("gerando a chave AES...");
        SecretKey key2 = AESUtil.generateKeyFromEncodedString(encodedKey2);
        
        System.out.println("Chave AES (Base64) 2 : " + encodedKey2);
        
        System.out.println(key.equals(key2));
        
        System.out.println("\n\nAS CHAVES ESTÃO IGUAIS, OK\n\n");
        
        IvParameterSpec iv = generateIv(key);
        
        IvParameterSpec iv2 = generateIv(key);
        
        System.out.println(iv.equals(iv2));
        
        
    }
    
}

    