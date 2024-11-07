package com.mycompany.rsa;

import javax.crypto.Cipher; // Importa a classe Cipher para criptografia e descriptografia
import java.security.*; // Importa classes de segurança, como KeyPair, PublicKey e PrivateKey
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64; // Importa a classe Base64 para codificação e decodificação em Base64
import java.util.Scanner; // Importa a classe Scanner para ler a entrada do usuário

public class RSAUtil { // Declara a classe principal CriptografaAssimetrica

    /**
     * Método para gerar um par de chaves RSA (pública e privada)
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException { 
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); // Inicializa o gerador de par de chaves RSA
        keyGen.initialize(512); // Configura o tamanho da chave para 512 bits, o menor possível, 2048 bits seria um tamanho seguro para RSA
        return keyGen.generateKeyPair(); // Gera e retorna o par de chaves
    }

    /**
     * Método para criptografar dados usando a chave pública
     * @param plainText
     * @param publicKey
     * @return cipherText
     * @throws Exception 
     */
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Inicializa o Cipher com o algoritmo RSA
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Define o Cipher para o modo de criptografia com a chave pública
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes()); // Criptografa os dados e armazena em encryptedBytes
        return Base64.getEncoder().encodeToString(encryptedBytes); // Codifica os dados criptografados em Base64 e retorna como string
    }

    /**
     * Método para descriptografar dados usando a chave privada
     * @param cipherText
     * @param privateKey
     * @return
     * @throws Exception 
     */
    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText); // Decodifica a string Base64 para bytes
        Cipher cipher = Cipher.getInstance("RSA"); // Inicializa o Cipher com o algoritmo RSA
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // Define o Cipher para o modo de descriptografia com a chave privada
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes); // Descriptografa os dados e armazena em decryptedBytes
        return new String(decryptedBytes); // Converte os bytes descriptografados para string e retorna
    }
    
    /**
     * Método para converter a chave pública para uma String Base64
     * @param publicKey
     * @return 
     */
    public static String publicKeyToString(PublicKey publicKey) {
        byte[] publicKeyBytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }
    
    /**
     * Método para reconstruir a chave pública a partir de uma String Base64
     * @param publicKeyString
     * @return
     * @throws Exception 
     */
    public static PublicKey stringToPublicKey(String publicKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
    
    /**
     * Método para converter a chave privada para uma String Base64
     * @param privateKey
     * @return 
     */
    public static String privateKeyToString(PrivateKey privateKey) {
        byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    /**
     * Método para reconstruir a chave privada a partir de uma String Base64
     * @param privateKeyString
     * @return
     * @throws Exception 
     */
    public static PrivateKey stringToPrivateKey(String privateKeyString) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * Método principal que demonstra o uso da criptografia e descriptografia RSA
     */
    public static void teste() {
        try (Scanner scanner = new Scanner(System.in)) { // Inicializa o Scanner para ler a entrada do usuário
            try {
                // Gera um par de chaves RSA
                KeyPair keyPair = generateRSAKeyPair(); // Gera o par de chaves RSA
                PublicKey publicKey = keyPair.getPublic(); // Obtém a chave pública
                PrivateKey privateKey = keyPair.getPrivate(); // Obtém a chave privada

                System.out.println("Texto original: "); // Pede ao usuário para inserir um texto
                String message = scanner.nextLine(); // Lê o texto digitado pelo usuário

                // Criptografa o texto usando a chave pública
                String encryptedMessage = encrypt(message, publicKey); // Chama o método encrypt para criptografar o texto
                System.out.println("Texto criptografado: " + encryptedMessage); // Exibe o texto criptografado

                // Descriptografa o texto usando a chave privada
                String decryptedMessage = decrypt(encryptedMessage, privateKey); // Chama o método decrypt para descriptografar o texto
                System.out.println("Texto descriptografado: " + decryptedMessage); // Exibe o texto descriptografado

            } catch (Exception e) { // Captura exceções relacionadas a criptografia e descriptografia
                e.printStackTrace(); // Imprime o rastreamento de pilha do erro
            }
        }
    }
}
