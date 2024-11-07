
# Projeto de Criptografia com Cifras

Projeto de Criptografia com Cifras
Este projeto implementa cinco tipos de criptografia, sendo três clássicas (Transposição de Colunas, Cifra de Vigenère, e Cifra de César), uma moderna simétrica (AES), e uma moderna assimétrica (RSA). Ele permite criptografar e descriptografar mensagens de texto lidas de um arquivo de entrada (input.txt), com o resultado sendo salvo em um arquivo de saída (output.txt). <br> Todo o projeto se trata de um protótipo, com poucas funções, variáveis globais estáticas e afins.

## Funcionalidades

- **Transposição de Colunas**: Organiza o texto em uma matriz de colunas com base em uma chave fornecida pelo usuário e realiza a criptografia ou descriptografia através da reorganização dessas colunas.
  
- **Cifra de Vigenère**: Uma técnica de criptografia polialfabética que usa uma chave para modificar os caracteres de entrada com base na soma de seus valores numéricos e os da chave.
  
- **Cifra de César**: Um método de criptografia por substituição que desloca cada caractere do texto de acordo com um número fixo.

- **AES (Advanced Encryption Standard)**: Utiliza a criptografia simétrica avançada AES com chave de 128 bits e um vetor de inicialização (IV) para encriptar e desencriptar textos, oferecendo alta segurança. A chave e o IV são gerados automaticamente, com a chave sendo exibida em Base64 para ser usada posteriormente na descriptografia, e o vetor de inicialização gerado a partir da chave como seed, permitindo ao decodificador receber apenas a chave e o texto criptografado como parâmetro para descriptografá-la.
- **RSA (Rivest-Shamir-Adleman)**: Implementa criptografia assimétrica, gerando um par de chaves pública e privada. Permite a criptografia de mensagens com a chave pública, que só podem ser descriptografadas pela chave privada correspondente. A chave pública pode ser distribuída, enquanto a chave privada é mantida em segredo, garantindo a segurança da comunicação.


## Estrutura do Projeto

- **`com.mycompany.colunas.TransposicaoDeColunas`**: Implementa o algoritmo de transposição de colunas.
- **`com.mycompany.vigenere.CifraDeVigenere`**: Implementa o algoritmo de criptografia de Vigenère.
- **`com.mycompany.cesar.CifraDeCesar`**: Implementa o algoritmo de cifra de César.
- **`com.aes.aes.AESUtil`**: Implementa o algoritmo AES para criptografar e descriptografar textos com segurança moderna.
- **`com.mycompany.rsa.RSAUtil:`** Implementa o algoritmo RSA, incluindo geração de chaves, criptografia e descriptografia com segurança assimétrica.

## Requisitos do Sistema

- **Java 8+**: Certifique-se de ter o JDK instalado para compilar e executar o projeto.
- **IDE**: Recomendado usar uma IDE como NetBeans ou Eclipse para facilitar o desenvolvimento e execução.

## Como Usar

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Run File**:
   Selecione a classe que deseja executar e rode o arquivo; Projects > Cifras > Source packages > selecione o package com o algoritmo desejado > e rightclick no arquivo que contenha main class > "Run File".

## ADENDO:
- Para a transposição de colunas, não utilize chaves com letras repetidas, pois o algoritmo não diferencia colunas idênticas.
  
- Para a criptografia AES, copie a chave exibida no terminal ao final da criptografia para que possa ser usada na descriptografia.
