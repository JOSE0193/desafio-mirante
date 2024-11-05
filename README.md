# Desafio Mirante - Projeto para crud de cidades

Projeto de aplicação back-end e front-end de para listar, buscar, adicionar, alterara e excluir cidades.

## 🧑‍💻 Tecnologias

* **Java 17**
* **Spring Boot 3**
* **JPA + Hibernate**
* **API REST**
* **PostgreSQL**
* **Maven**
* **Docker**
* **docker-compose**
* **JUnit 5 + Mockito (back-end tests)**
* **Swagger**
* **Mailtrap (Simulates an email server)**

## 🛠️ Ferramentas Usadas


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![PrimeNg](https://img.shields.io/badge/PrimeNG-DD0031.svg?style=for-the-badge&logo=PrimeNG&logoColor=white)
![Jest](https://img.shields.io/badge/-jest-%23C21325?style=for-the-badge&logo=jest&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)


## 🎯 Arquitetura Proposta

### Cidade
- **POST** `/mirante/cidades`: Cria um registro de cidade.
- **GET** `/mirante/cidades`: Lista as cidades de forma páginada.
- **GET** `/mirante/cidades/id`: Buscar uma cidade específica.
- **PUT** `/mirante/cidades/id`: Atualiza uma cidade específica.
- **DELETE** `/mirante/cidades/id`: Remove um registro de cidade.


Para iniciar a api é necessário instalar:

* **Docker**
* **PostgreSQL (Se quiser instalar localmente)**
* **Java 17**
* **IntelliJ or another ide of your choice**

Quando a aplicação estiver rodando, este é o link para acesso a documentação com swagger:
* http://localhost:8080/mirante/swagger-ui/index.html#/

# Front-end Angular 

O projeto front end foi desenvolvido com Angular na versão 17.3.6 e os componentes utilizando PrimeNg. 

<p align="center">
    <img src="/public/tela-listagem.jpeg" alt="Imagem tela inicial" width="400px">
</p>

## Funcionalidades

- 🔐 – **Sistema de Autenticação**: Tela de login e de cadastro para somente permitir entrada de usuários cadastrados.

- 🛒 **Gerenciamento de agendamentos**: CRUD de cadastro de clientes e dos respectivos agendamentos dos atendimentos prestados com status e informações de clientes e dos procedimentos.

- 🔗 **Integração com API **: Integração plataforma front-end web e com api desenvolvida com Spring.

- 📱 **Integração com APP **: Integração com aplicação mobile desenvolvida com react native para o monitoramento dos atendimentos.(Previsto)

## Tecnologias Utilizadas
- **Node.js e NPM**: Ambiente de execução para usar o Anular.
- **Angular**: Framework web minimalista e flexível para desenvolvimento de componentes.
- **PrimeNG**: Biblioteca para estilização das telas.
- **Css**: Para estilização do css das dos componentes das telas.

## Como rodar o projeto
### Pré-requisitos
- **Node.js** instalado (v14.x ou superior)
- **Git** para clonar o repositório
- **Angular CLI** instalado para rodar o projeto
- **NPM** para instalar bibliotecas e rodar o projeto.

### Passo a passo

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/JOSE0193/cidades-desafio.git
    ```

2. **Instale as dependências**:
    ```bash
    npm install
    ```

4. **Inicie o servidor**:
   ```bash
        npm run start
        # or
        ng serve

