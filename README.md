# Projeto de Criptografia com Cifras

Este projeto implementa três diferentes tipos de criptografia clássica: Transposição de Colunas, Cifra de Vigenère, e Cifra de César. Ele permite criptografar e descriptografar mensagens de texto lidas de um arquivo de entrada (`input.txt`), com o resultado sendo salvo em um arquivo de saída (`output.txt`).
<br>
Todo o projeto se trata de um protótipo, com poucas funções, variaveis globais estaticas e afins.
## Funcionalidades

- **Transposição de Colunas**: Organiza o texto em uma matriz de colunas com base em uma chave fornecida pelo usuário e realiza a criptografia ou descriptografia através da reorganização dessas colunas.
  
- **Cifra de Vigenère**: Uma técnica de criptografia polialfabética que usa uma chave para modificar os caracteres de entrada com base na soma de seus valores numéricos e os da chave.
  
- **Cifra de César**: Um método de criptografia por substituição que desloca cada caractere do texto de acordo com um número fixo.

## Estrutura do Projeto

- **`com.mycompany.colunas.TransposicaoDeColunas`**: Implementa o algoritmo de transposição de colunas.
- **`com.mycompany.vigenere.CifraDeVigenere`**: Implementa o algoritmo de criptografia de Vigenère.
- **`com.mycompany.cesar.CifraDeCesar`**: Implementa o algoritmo de cifra de César.

## Requisitos do Sistema

- **Java 8+**: Certifique-se de ter o JDK instalado para compilar e executar o projeto.
- **IDE**: Recomendado usar uma IDE como NetBeans ou Eclipse para facilitar o desenvolvimento e execução.

## Como Usar

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio

2. **Run File**:
  selecione a classe que deseja executar e rode o arquivo.

## ADENDO:
- não use uma chave com letras repetidas para a transposição de colunas, pois o algoritmo não irá diferenciar uma coluna da outra, e ele não trata essa situação.
