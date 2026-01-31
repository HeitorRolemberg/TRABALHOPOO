# Jogo O Torneio (POO em Java)

O jogo O Torneio foi desenvolvido como parte da avaliação da disciplina de Programação Orientada a Objetos (POO) pelos estudantes:
Evilyn Aquino, Heitor Rolemberg, Leonardo Quintela e Nicolle Santana.

Este projeto é um jogo simples desenvolvido em Java.
O jogo simula uma batalha entre dois times: Aliança da Luz e Ronda das Sombras.

## Organização do projeto

O projeto foi organizado em pastas para separar as partes do código:

- src/
  - Main.java: classe responsável por iniciar o jogo.
  - PainelJogo.java: classe que controla a tela principal do jogo e as interações.
  - Elementos_do_Jogo/
    - Combatente.java: classe base dos personagens.
    - Mago.java: personagem com ataque mágico.
    - Atirador.java: personagem com ataque à distância.
    - Tanque.java: personagem com mais vida e defesa.
    - Arena.java: classe que representa a arena de batalha.
    - Teste.java: classe usada para testes.

- Imagens/
  Contém as imagens do jogo (personagens, cenário e botões).

## Processo de instalação

1. Baixar o projeto em formato .zip.
2. Extrair os arquivos em uma pasta no computador.
3. Abrir o projeto em uma IDE (Eclipse, NetBeans ou IntelliJ).
4. Verificar se as pastas src e Imagens estão no local correto.

## Como executar pelo terminal

1. Abrir a pasta do projeto no computador.
2. Abrir o terminal dentro da pasta do projeto.

3. Para executar o jogo, digite no terminal:
```
java -cp src Main
```

Se estiver tudo correto, a janela do jogo será aberta.

## Funcionamento do jogo

O jogo funciona por turnos.
Cada personagem possui atributos como vida, ataque e defesa.
Quando um ataque é realizado, o valor do ataque é calculado e a vida do outro personagem é atualizada.

## Interface gráfica

A interface foi feita usando Java Swing.
Ela mostra o cenário, os personagens, a barra de vida e os botões para realizar as ações.

## Tecnologias usadas

Java  
Java Swing  

## Objetivo do projeto

O objetivo deste projeto é praticar os conceitos básicos de Programação Orientada a Objetos e criar uma interface gráfica simples em Java.

