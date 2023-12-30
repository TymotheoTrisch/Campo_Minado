# Campo Minado em TypeScript

Uma implementação simples do jogo Campo Minado em java swing.

## Instalação

Certifique-se de ter o [JDK](https://www.oracle.com/br/java/technologies/downloads/) instalado em seu sistema.

1. **Clone o repositório:**

```
   git clone https://github.com/TymotheoTrisch/Campo_Minado.git
```

2. **Acesse o diretório do projeto:**
  ```
    cd Campo_Minado/source
  ```
3. **Compile o jogo:**
   ```
     cd Classes
     javac *.java

     cd ../View
     javac *.java
   ```

4. Execute o jogo:
  ```
    Java Main
  ```

# Como Jogar
Bem-vindo ao Campo Minado! Este é um jogo clássico onde o objetivo é descobrir todas as células sem minas. Aqui estão as regras básicas:

### Objetivo do Jogo

1. **Descobrir todas as células vazias:** Clique em uma célula para revelar seu conteúdo. Se for uma célula vazia, ela mostrará o número de minas nas células vizinhas.

2. **Marcar as células com minas:** Se você suspeitar que uma célula contém uma mina, marque-a com uma bandeira. Clique novamente para remover a bandeira.

### Regras

1. **Número nas células:** As células vazias exibirão um número indicando quantas minas estão nas células vizinhas. Use essas informações para tomar decisões estratégicas.

2. **Mina detonada:** Se clicar em uma célula com uma mina, o jogo termina. Todas as minas serão reveladas.

3. **Vitória:** O jogo é vencido quando todas as células sem minas são descobertas e todas as minas são marcadas corretamente.

### Comandos

- **Clique esquerdo:** Revela uma célula.
- **Clique direito:** Marca ou desmarca uma célula com uma bandeira.

### Iniciando um Novo Jogo

1. **Execute o jogo:**

    ```
      java Main
    ```

2. **Siga as instruções no menu ajuda:** O jogo fornecerá as orientações necessárias para jogar.

Divirta-se jogando o Campo Minado!

# Visual do Jogo
![Campo_Minado_Imagem](https://github.com/TymotheoTrisch/Campo_Minado/blob/main/Campo_Minado_Image.png)

# Estrutura do Projeto
O projeto foi dividido em Logica e Visual:

- **Lógica:** Localizada na pasta Classes.
- **Visual:** Localizada na pasta View.

# Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para propor melhorias, corrigir bugs ou adicionar novos recursos. Certifique-se de seguir as boas práticas de desenvolvimento.

# Licença
Este projeto é licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.

# Contato
Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato:


Tymotheo Trisch
</br>
Email: tymotheo.dev@gmail.com
</br>
GitHub: TymotheoTrisch
