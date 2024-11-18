/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

/**
 *
 * @author nandones
 */
public class main {
    public static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("prssione [c] para criptografar e [d] para descriptografar");
        char menu = input.next().charAt(0);
        if(menu == 'c' || menu == 'C'){
            ReaderAndWriterUtil.EscreveOutputTXT(criptografa(ReaderAndWriterUtil.LeInputTXT()));
        }else{
            ReaderAndWriterUtil.EscreveOutputTXT(descriptografa(ReaderAndWriterUtil.LeInputTXT()));
        }
    }
    
    public static String criptografa(String plainText) throws Exception{
        
        System.out.println("gerando o par de chaves RSA...");
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(); // Gera o par de chaves RSA
        PublicKey publicKey = keyPair.getPublic(); // Obtém a chave pública
        PrivateKey privateKey = keyPair.getPrivate(); // Obtém a chave privada
        
        //apenas para imprimir a chave pública
        String publicKeySTR = RSAUtil.publicKeyToString(publicKey);
        System.out.println("Chave RSA pública (Base64): " + publicKeySTR);
        
        //apenas para imprimir a chave privada
        String privateKeySTR = RSAUtil.privateKeyToString(privateKey);
        System.out.println("Chave RSA privada (Base64): " + privateKeySTR);
        System.out.println("copie a chave acima para descriptografar o texto");
        
        System.out.println("criptografando o PlainText...");
        String cipherText = RSAUtil.encrypt(plainText, publicKey);
        System.out.println("Texto Criptografado: " + cipherText);
        
        return cipherText;
        
    }
    
    public static String descriptografa(String cipherText) throws Exception{
        System.out.println("digite aqui a chave RSA privada (Base64):");
        String privateKeySTR = input.next();
        System.out.println("gerando a chave pública RSA a partir da String...");
        PrivateKey privateKey = RSAUtil.stringToPrivateKey(privateKeySTR);
        
  
        
        System.out.println("decriptografando...");
        String plainText = RSAUtil.decrypt(cipherText, privateKey);
        System.out.println("Texto Decriptografado: " + plainText);
        return plainText;
    }

    
}
