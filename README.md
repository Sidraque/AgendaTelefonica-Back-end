# Back-End - Agenda Telefônica
Este repositório contém o projeto back-end de uma aplicação de agenda telefônica desenvolvida utilizando Java com Spring Boot 17. O front-end da aplicação foi desenvolvido em Angular e pode ser encontrado em seu respectivo repositório: https://github.com/Sidraque/AgendaTelefonica-Front-end

# Pré-requisitos
Antes de iniciar, certifique-se de que os seguintes componentes estão instalados e configurados em seu ambiente:

- JDK 17
- Maven
- PostgreSQL

# Configuração do Banco de Dados
Utilize os comandos SQL a seguir para configurar o banco de dados necessário para a aplicação:

CREATE SCHEMA desafio;
CREATE TABLE desafio.coontato(
    contato_id SERIAL PRIMARY KEY,
    contato_nome VARCHAR(100),
    contato_email VARCHAR(255),
    contato_celular VARCHAR(11),
    contato_telefone VARCHAR(10),
    contato_sn_favorito CHARACTER(1),
    contato_sn_ativo CHARACTER(1),
    contato_dh_cad TIMESTAMP WITHOUT TIME ZONE
);

Configuração do Projeto
Clone o repositório do back-end e navegue até o diretório do projeto e instale as dependências do Maven:

mvn install

Configure as variáveis de ambiente necessárias para a conexão com o banco de dados no arquivo src/main/resources/application.properties.

# Para executar a aplicação em ambiente de desenvolvimento, use:

mvn spring-boot:run

A aplicação estará acessível em http://localhost:8080/.

# Testes
Execute os testes com Maven:

mvn test
