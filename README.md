# Gasto - Backend

Bem-vindo ao **Gasto**, um sistema profissional de controle de gastos pessoais desenvolvido em Java com Spring Boot, focado em desempenho, organização e extensibilidade. O objetivo deste projeto é permitir que usuários gerenciem seus gastos diários e fixos, acompanhem fornecedores, regiões e perfis de usuário, proporcionando uma visão clara e eficiente das finanças pessoais.

## Sobre o Projeto

O Gasto foi arquitetado para oferecer uma API REST robusta, possibilitando o registro, consulta e gerenciamento de despesas pessoais de forma simples, segura e escalável. Ele é ideal para aplicações web e mobile que buscam um backend flexível com integração facilitada.

### Principais Funcionalidades

- Cadastro e gerenciamento de despesas diárias e fixas
- Controle de usuários, perfis e permissões
- Gerenciamento de fornecedores e regiões
- Estrutura modular, fácil de manter e evoluir

## Tecnologias Utilizadas

- **Java 17** – Linguagem principal
- **Spring Boot 3.4.8** – Framework para desenvolvimento ágil
- **Spring Data JPA** – Abstração e persistência de dados
- **MySQL** – Banco de dados relacional
- **Lombok** – Redução de boilerplate no código Java
- **Maven** – Gerenciador de dependências e build

## Estrutura do Projeto

O projeto está dividido em camadas seguindo boas práticas de arquitetura RESTful:

- **Models**: Representação das entidades do domínio:
    - `GastosFixoModel` — Gestão de despesas fixas
    - `GastosPorDiaModel` — Controle de gastos diários
    - `EstadosModel` — Estados/Regiões atendidas
    - `UsuariosModel` — Usuários do sistema
    - `PerfilModel` — Perfis e permissões
    - `ProveedoreModel` — Fornecedores vinculados
    - `VariableGlobalModel` — Parâmetros e variáveis globais

- **Controllers**: Endpoints REST para interação externa
- **DTOs**: Objetos de transferência de dados para requisições e respostas
- **Services**: Regras de negócio e operações sobre as entidades

## Configuração do Banco de Dados

O backend está pronto para utilização com MySQL. Certifique-se de:

- Ter o MySQL instalado
- Criar um banco de dados dedicado para o projeto
- Configurar as propriedades de acesso (`application.properties` ou `application.yml`)

## Guia de Execução

1. **Clone o repositório**
    ```bash
    git clone https://github.com/seu-usuario/gasto.git
    ```
2. **Configure o banco de dados MySQL** com as informações de acesso.
3. **Instale as dependências** e execute o projeto:
    ```bash
    mvn spring-boot:run
    ```
4. Acesse as rotas REST documentadas para começar a integrar seu frontend ou utilizar ferramentas de requisições (ex: Postman).

## Fluxo de Trabalho (GitFlow)

Este projeto segue o GitFlow para organização e colaboração eficiente:

- `main`: Código estável e pronto para produção
- `develop`: Ambiente de integração dos recursos em desenvolvimento
- `feature/*`: Branches para desenvolvimento de novas funcionalidades
- `hotfix/*`: Correções rápidas em produção (main)
- `release/*`: Preparação de novas versões

## Colaboração

Contribuições são bem-vindas! Faça um fork, crie sua branch e envie seu pull request seguindo nossas recomendações. Utilize sempre as issues para discussão de problemas e sugestões.

---

**Autor**: Adson Sá

Para dúvidas, sugestões ou melhorias, entre em contato ou abra uma issue no repositório.

