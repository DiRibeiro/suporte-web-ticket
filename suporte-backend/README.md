# 🧰 Suporte Backend – Spring Boot + PostgreSQL

Este módulo é a API REST do sistema de **Suporte / Help Desk**, responsável por:

- Gerenciar **tickets**, **comentários**, **usuários**, **técnicos** e **categorias**
- Expor endpoints REST para o frontend em Vue
- Persistir dados em **PostgreSQL** usando Spring Data JPA

---

## ⚙️ Tecnologias

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Validation (Jakarta Validation)
- PostgreSQL
- Maven
- Lombok (se estiver configurado no projeto / IDE)

---

## 📂 Estrutura de pacotes

```text
suporte-backend/
└── src/main/java/com/example/suporte
    ├── config/
    │   └── GlobalExceptionHandler.java   # Tratamento global de erros da API
    ├── controller/
    │   ├── CategoriaController.java
    │   ├── ComentarioController.java
    │   ├── TecnicoController.java
    │   ├── TicketController.java
    │   └── UsuarioController.java
    ├── domain/
    │   ├── Categoria.java
    │   ├── Comentario.java
    │   ├── Status.java        # Enum: NOVO, EM_ANDAMENTO, RESOLVIDO, FECHADO
    │   ├── Tecnico.java
    │   ├── Ticket.java
    │   └── Usuario.java
    ├── dto/
    │   ├── CategoriaDTO.java
    │   ├── ComentarioDTO.java
    │   ├── TecnicoDTO.java
    │   ├── UsuarioDTO.java
    │   └── ticket/
    │       ├── TicketRequestDTO.java
    │       └── TicketResponseDTO.java
    ├── repository/
    │   ├── CategoriaRepository.java
    │   ├── ComentarioRepository.java
    │   ├── TecnicoRepository.java
    │   ├── TicketRepository.java
    │   └── UsuarioRepository.java
    ├── service/
    │   ├── CategoriaService.java
    │   ├── ComentarioService.java
    │   ├── TecnicoService.java
    │   ├── TicketService.java
    │   ├── UsuarioService.java
    │   └── SuporteApplication.java   # Classe principal (Spring Boot)
    └── resources/
        └── application.yml
🗄️ Configuração do banco de dados (PostgreSQL)
Arquivo: src/main/resources/application.yml

Exemplo de configuração:

yaml
Copiar código
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/suporte_db
    username: SEU_USUARIO
    password: SUA_SENHA

  jpa:
    hibernate:
      ddl-auto: update      # ou validate / create / create-drop de acordo com o ambiente
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  jackson:
    serialization:
      write-dates-as-timestamps: false

server:
  port: 8080
Altere url, username e password de acordo com o seu ambiente local.

🔁 Camada de serviço e DTOs
As regras de negócio ficam em service/*Service.java

Controllers não trabalham diretamente com as entidades JPA:

Request/entrada → *DTO / TicketRequestDTO

Resposta/saída → *DTO / TicketResponseDTO

Validações com Bean Validation:

@NotBlank, @NotNull, @Email, etc.

DTOs anotados e validados nos controllers com @Valid

Exemplo de fluxo (Ticket):

TicketController recebe um TicketRequestDTO

Chama TicketService

Service mapeia DTO → Ticket (domínio), salva com TicketRepository

Retorna TicketResponseDTO para o controller → cliente HTTP

🌐 Endpoints principais (resumo)
O prefixo padrão é algo como /api/... (ajuste de acordo com o @RequestMapping em cada controller).

Tickets
GET /api/tickets – lista paginada (?page=0&size=20)

GET /api/tickets/{id} – busca um ticket pelo ID

POST /api/tickets – cria um novo ticket

PUT /api/tickets/{id} – atualiza um ticket existente

DELETE /api/tickets/{id} – remove um ticket

Filtros comuns (podem existir conforme sua implementação):

?criadorId=1

?status=NOVO

Usuários
GET /api/usuarios

GET /api/usuarios/{id}

POST /api/usuarios

PUT /api/usuarios/{id}

DELETE /api/usuarios/{id}

Técnicos
GET /api/tecnicos

GET /api/tecnicos/{id}

POST /api/tecnicos

PUT /api/tecnicos/{id}

DELETE /api/tecnicos/{id}

Categorias
GET /api/categorias

GET /api/categorias/{id}

POST /api/categorias

PUT /api/categorias/{id}

DELETE /api/categorias/{id}

Comentários
GET /api/comentarios?ticketId={id}

POST /api/comentarios – cria comentário vinculado a um ticket

DELETE /api/comentarios/{id} – remove comentário

(Ajuste os caminhos exatos se estiverem diferentes no seu código.)

🧨 Tratamento de erros
config/GlobalExceptionHandler.java centraliza o tratamento de exceções, retornando respostas JSON amigáveis, por exemplo:

404 – recurso não encontrado

400 – erro de validação (campos obrigatórios, formato inválido, etc.)

500 – erro interno de servidor

▶️ Como executar
Via Maven
bash
Copiar código
cd suporte-backend
mvn spring-boot:run
Via IDE (IntelliJ, Eclipse, VS Code + Extensão)
Rodar a classe SuporteApplication.java como Java Application.

A API ficará disponível em:

text
Copiar código
http://localhost:8080
✅ Requisitos
Java 17+

Maven

PostgreSQL rodando e acessível

Banco criado e configurado (ex.: suporte_db)
## Documentação da API

#### Retorna todos os itens

```http
  GET /api/items
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

#### add(num1, num2)

Recebe dois números e retorna a sua soma.


## Apêndice

Coloque qualquer informação adicional aqui


## Etiquetas

Adicione etiquetas de algum lugar, como: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


## Referência

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)

## Documentação de cores

| Cor               | Hexadecimal                                                |
| ----------------- | ---------------------------------------------------------------- |
| Cor exemplo       | ![#0a192f](https://via.placeholder.com/10/0a192f?text=+) #0a192f |
| Cor exemplo       | ![#f8f8f8](https://via.placeholder.com/10/f8f8f8?text=+) #f8f8f8 |
| Cor exemplo       | ![#00b48a](https://via.placeholder.com/10/00b48a?text=+) #00b48a |
| Cor exemplo       | ![#00d1a0](https://via.placeholder.com/10/00b48a?text=+) #00d1a0 |


## Demonstração

Insira um gif ou um link de alguma demonstração

