# Projeto Gasto - Backend

Sistema de controle de gastos pessoais desenvolvido em Spring Boot.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.8**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Maven**

## Estrutura do Projeto

### Modelos (Models)
- `GastosFixoModel` - Gerenciamento de gastos fixos
- `GastosPorDiaModel` - Controle de gastos diários
- `EstadosModel` - Estados/regiões
- `UsuariosModel` - Dados dos usuários
- `PerfilModel` - Perfis de usuário
- `ProveedoreModel` - Fornecedores
- `VariableGlobalModel` - Variáveis globais do sistema

## Configuração do Banco de Dados

O projeto está configurado para usar MySQL. Certifique-se de ter o MySQL instalado e configurado.

## Como Executar

1. Clone o repositório
2. Configure o banco de dados MySQL
3. Execute: `mvn spring-boot:run`

## Branches

- `main` - Branch principal de produção
- `develop` - Branch de desenvolvimento
- `feature/model` - Branch para desenvolvimento de modelos

## Desenvolvimento

Este projeto segue o padrão de branching GitFlow com as seguintes branches:
- **main**: Código de produção
- **develop**: Integração de features
- **feature/***: Desenvolvimento de novas funcionalidades
