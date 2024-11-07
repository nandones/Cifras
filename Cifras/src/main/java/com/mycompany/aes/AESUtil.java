package com.mycompany.aes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher; // Importa a classe Cipher para operações de criptografia e descriptografia
import javax.crypto.KeyGenerator; // Importa a classe KeyGenerator para gerar uma chave AES
import javax.crypto.SecretKey; // Importa a classe SecretKey que representa a chave AES
import javax.crypto.spec.IvParameterSpec; // Importa IvParameterSpec para representar o IV (Vetor de Inicialização)
import java.security.SecureRandom; // Importa SecureRandom para gerar números aleatórios
import java.util.Base64; // Importa Base64 para codificação e decodificação em Base64
import java.util.Scanner;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nandones
 */
public class AESUtil {
    
    public static Scanner input = new Scanner(System.in);
    
    /**
     * Função para gerar uma chave AES de 128 bits
     * @return
     * @throws Exception 
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES"); // Cria uma instância do KeyGenerator para o algoritmo AES
        keyGen.init(128); // Inicializa o KeyGenerator com um tamanho de chave de 128 bits
        return keyGen.generateKey(); // Gera e retorna a chave AES
    }
    
    /**
     * Método para converter uma encodedKey (Base64) diretamente em uma SecretKey.<br>
     * Após obter a encodedKey gerada pela criptografia, podemos utiliza-la para descriptografar<br>
     * e gerar o IV.
     * @param encodedKey
     * @return 
     */
    public static SecretKey generateKeyFromEncodedString(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey); // Decodifica a string Base64 para bytes
        return new SecretKeySpec(decodedKey, "AES"); // Cria e retorna uma SecretKey com os bytes decodificados
    }
    
    public static String keyToString(SecretKey key){
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Converte a chave para Base64
        //System.out.println("Chave AES (Base64): " + encodedKey); // Imprime a chave AES codificada
        //System.out.println("copie esta chave para descriptografar o texto");
        return encodedKey;
    }

    /**
     * Função para gerar um vetor de inicialização (IV) com base na chave.<br>
     * A atual implementação garante a geração do mesmo (IV) para cada key.
     * @param key para gerar um vetor de inicialização (IV) com base na chave
     * @return 
     * @throws java.security.NoSuchAlgorithmException 
     */
    public static IvParameterSpec generateIv(SecretKey key) throws NoSuchAlgorithmException {
    byte[] keyBytes = key.getEncoded(); // Obtém os bytes da chave
    MessageDigest md = MessageDigest.getInstance("SHA-256"); // Usa SHA-256 para garantir 32 bytes de saída
    byte[] iv = md.digest(keyBytes); // Gera um hash da chave

    // Trunca o hash para 16 bytes (tamanho necessário para o IV do AES)
    byte[] iv16 = new byte[16];
    System.arraycopy(iv, 0, iv16, 0, 16);

    return new IvParameterSpec(iv16);
}

    /**
     * Função para criptografar o texto
     * @param plainText
     * @param key
     * @param iv
     * @return
     * @throws Exception 
     */
    public static String encrypt(String plainText, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // Cria uma instância de Cipher usando o modo AES/CBC/PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, key, iv); // Inicializa o Cipher para o modo de criptografia com a chave e o IV
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes()); // Criptografa o texto em bytes e armazena em encryptedBytes
        return Base64.getEncoder().encodeToString(encryptedBytes); // Codifica o texto criptografado em Base64 e retorna como string
    }

    /**
     * Função para descriptografar o texto
     * @param encryptedText
     * @param key
     * @param iv
     * @return
     * @throws Exception 
     */
    public static String decrypt(String encryptedText, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // Cria uma instância de Cipher usando o modo AES/CBC/PKCS5Padding
        cipher.init(Cipher.DECRYPT_MODE, key, iv); // Inicializa o Cipher para o modo de descriptografia com a chave e o IV
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText)); // Decodifica o texto Base64 e descriptografa
        return new String(decryptedBytes); // Converte os bytes descriptografados em uma string e retorna
    }
    
    /**
     * exemplo extremamente trivial para testar o fluxo de comandos abstraindo <br>
     * geração de iv distribuida, input string (base64) para chave que decriptografa o txt e etc.
     */
    public static void teste() {
        try {
            String plainText = "Mensagem secreta"; // Texto que será criptografado

            // Gerando a chave e o IV com base na chave
            SecretKey key = generateKey(); // Gera a chave AES
            IvParameterSpec iv = generateIv(key); // Gera o vetor de inicialização baseado na chave

            // Convertendo e imprimindo a chave em Base64
            String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Converte a chave para Base64
            System.out.println("Chave AES (Base64): " + encodedKey); // Imprime a chave AES codificada

            // Criptografando
            String encryptedText = encrypt(plainText, key, iv); // Criptografa o texto original
            System.out.println("Texto Criptografado: " + encryptedText); // Imprime o texto criptografado

            // Descriptografando
            String decryptedText = decrypt(encryptedText, key, iv); // Descriptografa o texto criptografado
            System.out.println("Texto Descriptografado: " + decryptedText); // Imprime o texto descriptografado
            

            
            
            
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o rastreamento da pilha de erros, se ocorrer alguma exceção
        }
    }
}
