# Carteira Digital

## Sobre o Projeto

A **Carteira Digital** é um sistema desenvolvido para um administrador controlar informações de usuários, permitindo visualizar dados como idade de aposentadoria, salário de aposentado, salário anual e salário pós-imposto. O objetivo é fornecer uma ferramenta de gestão financeira eficiente para acompanhamento de aposentadorias.

## Tecnologias Utilizadas

- **Java 17**
- **Maven**
- **JPA/Hibernate**
- **Servlets**
- **Banco de Dados (MySQL)**
- **HTML/CSS** (para a interface web)

## Estrutura do Projeto

O projeto segue uma estrutura baseada em MVC:

```
TrabalhoLPB_3Parte2/
│── src/main/java/
│   ├── Controller/  # Controladores da aplicação
│   ├── Model/       # Classes de modelo e DAO
│── src/main/resources/
│   ├── META-INF/persistence.xml  # Configuração do JPA
│── src/main/webapp/
│   ├── index.html  # Página principal
│   ├── CarteiraDigital.css  # Estilos da aplicação
│── pom.xml  # Gerenciador de dependências Maven
```

## Como Rodar o Projeto

### Pré-requisitos

- Ter o **Java 17** instalado
- Ter o **Maven** configurado
- Configurar o banco de dados no `persistence.xml`

### Passos

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/Carteira-digital.git
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd Carteira-digital/TrabalhoLPB_3Parte2
   ```
3. Compile e execute o projeto usando Maven:
   ```sh
   mvn clean package
   ```
4. Rode o servidor de aplicação (Tomcat, WildFly etc.) e acesse via navegador:
   ```
   http://localhost:8080/
   ```

## Melhorias que podem ser adicionadas futuramente

- Implementação de autenticação mais robusta
- Interface mais dinâmica
- Suporte a mais tipos de transação


